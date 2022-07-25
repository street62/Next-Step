package webserver.logicexecutor;

import webserver.http.Request;
import webserver.http.Response;

public interface LogicExecutor {
    Response execute(Request request);
}
