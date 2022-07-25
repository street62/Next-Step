package webserver.logicexecutor;

import model.User;
import webserver.http.Request;
import webserver.http.Response;
import webserver.http.StatusCode;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class LoginLogicExecutor implements LogicExecutor{
    @Override
    public Response execute(Request request) {
        Map<String, String> params = request.getParams();
        String userId = params.get("userId");
        String password = params.get("password");
        String name = params.get("name");
        String email = params.get("email");
        User user = new User(userId, password, name, email);
        return new Response(StatusCode.OK, user.toString().getBytes(StandardCharsets.UTF_8));
    }
}
