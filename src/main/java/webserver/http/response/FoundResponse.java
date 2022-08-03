package webserver.http.response;

import java.nio.charset.StandardCharsets;

public class FoundResponse extends Response {
    public FoundResponse(String location) {
        super(StatusCode.FOUND);
        setHeader(location);
    }

    private void setHeader(String location) {
        String headerString = String.format("HTTP/1.1 %d %s \r\n", statusCode.getCodeNumber(), statusCode) +
                "Content-Type: text/html;charset=utf-8\r\n" +
                "Location: " + location + "\r\n";
        header = headerString.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] getAsByteArray() {
        return header.clone();
    }
}
