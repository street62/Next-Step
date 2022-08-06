package webserver.logicexecutor;

import db.DataBase;
import model.User;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.StatusCode;

public class LoginLogicExecutor implements LogicExecutor {
    @Override
    public Response execute(Request request) {
        User foundUser = DataBase.findUserById(request.getParam("id"));
        if (foundUser == null) {
            Response response = new Response(StatusCode.FOUND);
//            response.
        }
        return null;
    }
}
