package webserver.http.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class Response {

    private static final Logger log = LoggerFactory.getLogger(Response.class);
    protected StatusCode statusCode;
    protected StringBuilder header = new StringBuilder();
    protected byte[] body;


    public Response(StatusCode statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body.getBytes(StandardCharsets.UTF_8);
        this.header.append(String.format("HTTP/1.1 %d %s \r\n", statusCode.getCodeNumber(), statusCode))
                .append(String.format("Content-Length: %d\n", this.body.length))
                .append("Content-Type: text/html;charset=utf-8\r\n");
    }

    public Response(StatusCode statusCode) {
        this.statusCode = statusCode;
        header.append(String.format("HTTP/1.1 %d %s \r\n", statusCode.getCodeNumber(), statusCode))
                .append("Content-Type: text/html;charset=utf-8\r\n");
    }

    public void addRedirectLocation(String location) {
        header.append("Location: ").append(location).append("\r\n");
    }

    public void addSetCookie(String setCookie) {
        header.append("Set-cookie: ").append(setCookie).append("\r\n");
    }

    public byte[] getHeaderArray() {
        return header.toString().getBytes(StandardCharsets.UTF_8);
    }

    public byte[] getAsByteArray() {
        byte[] header = getHeaderArray();
        if (body == null) {
            return header;
        }
        byte[] resByteArray = new byte[header.length + body.length + 2];
        System.arraycopy(header, 0, resByteArray, 0, header.length);
        System.arraycopy("\r\n".getBytes(StandardCharsets.UTF_8), 0, resByteArray, header.length, 2);
        System.arraycopy(body, 0, resByteArray, header.length + 2, body.length);
        return resByteArray;
    }
}
