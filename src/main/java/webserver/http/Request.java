package webserver.http;

import util.HttpRequestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class Request {
    private String method;
    private String uri;
    private Map<String, String> headers;
    private Map<String, String> params;

    public Request(BufferedReader br) throws IOException {
        parseRequestLine(br);
        parseHeader(br);
        parseParams(br);
    }

    private void parseParams(BufferedReader br) throws IOException {
        String paramsString = br.readLine();
        if (paramsString.length() != 0) {
            this.params = HttpRequestUtils.parseQueryString(paramsString);
        }
    }

    private void parseHeader(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line.length() != 0) {
            sb.append(line);
        }
        String headerString = sb.toString();
    }

    private void parseRequestLine(BufferedReader br) throws IOException {
        String[] requestLine = br.readLine().split(" ");
        this.method = requestLine[0];
        String uri = requestLine[1];
        if (hasUriQuery(uri)) {
            this.params = HttpRequestUtils.parseQueryString(uri.split("\\?")[1]);
            uri = uri.split("\\?")[0];
        }
        this.uri = uri;
    }

    private boolean hasUriQuery(String uri) {
        return uri.split("\\?").length == 2;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
