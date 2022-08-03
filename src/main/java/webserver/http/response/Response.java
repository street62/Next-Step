package webserver.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Response {

    private static final Logger log = LoggerFactory.getLogger(Response.class);
    protected StatusCode statusCode;
    protected byte[] header;
    protected byte[] body;

    public Response(StatusCode statusCode, byte[] body) {
        this.statusCode = statusCode;
        this.body = body;
        setHeader();
    }

    public Response(StatusCode statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body.getBytes(StandardCharsets.UTF_8);
        setHeader();
    }

    public Response(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    private void setHeader() {
        String headerString = String.format("HTTP/1.1 %d %s \r\n", statusCode.getCodeNumber(), statusCode) +
                "Content-Type: text/html;charset=utf-8\r\n" +
                "Content-Length: " + body.length + "\r\n" +
                "\r\n";
        header = headerString.getBytes(StandardCharsets.UTF_8);
    }

    public byte[] getHeader() {
        return header.clone();
    }

    public byte[] getBody() {
        return body.clone();
    }

    public byte[] getAsByteArray() {
        byte[] resByteArray = new byte[header.length + body.length];
        System.arraycopy(header, 0, resByteArray, 0, header.length);
        System.arraycopy(body, 0, resByteArray, header.length, body.length);
        return resByteArray;
    }
}
