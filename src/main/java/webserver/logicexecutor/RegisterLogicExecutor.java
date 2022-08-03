package webserver.logicexecutor;

import db.DataBase;
import model.User;
import webserver.http.FoundResponse;
import webserver.http.Request;
import webserver.http.Response;

public class RegisterLogicExecutor implements LogicExecutor {
    @Override
    public Response execute(Request request) {
        User user = new User(request.getParam("userId"), request.getParam("password"), request.getParam("name"), request.getParam("email"));
        DataBase.addUser(user);
        return new FoundResponse("http://localhost:8080/index.html");
    }
}
