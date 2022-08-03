package webserver.logicexecutor;

import webserver.http.request.Request;
import webserver.http.response.Response;

public interface LogicExecutor {
    Response execute(Request request);
}
