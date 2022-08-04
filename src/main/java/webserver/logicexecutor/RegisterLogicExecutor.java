package webserver.logicexecutor;

import db.DataBase;
import model.User;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.StatusCode;

public class RegisterLogicExecutor implements LogicExecutor {
    @Override
    public Response execute(Request request) {
        User user = new User(request.getParam("userId"), request.getParam("password"), request.getParam("name"), request.getParam("email"));
        DataBase.addUser(user);

        Response response = new Response(StatusCode.FOUND);
        response.addRedirectLocation("http://localhost:8080/index.html");

        return response;
    }
}
