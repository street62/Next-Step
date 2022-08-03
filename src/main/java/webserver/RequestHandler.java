package webserver;

import java.io.*;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.http.request.Request;
import webserver.http.response.Response;
import webserver.http.response.ResponseMaker;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private static final ResponseMaker responseMaker = new ResponseMaker();

    private Socket connection;
    private DataOutputStream dos;
    private BufferedReader br;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            dos = new DataOutputStream(out);
            br = new BufferedReader(new InputStreamReader(in));

            Request request = readRequest();
            Response response = makeResponseOf(request);
            sendResponse(response);

            br.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public Request readRequest() throws IOException {
        Request request = new Request(br);
        log.debug("Request: {} {}", request.getMethod(), request.getUri());
        return request;
    }

    public Response makeResponseOf(Request request) {
        return responseMaker.getResponse(request);
    }

    public void sendResponse(Response response) {
        try {
            dos.write(response.getAsByteArray());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }
}
