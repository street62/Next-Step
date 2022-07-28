package webserver.http;

import util.HttpRequestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private String method;
    private String uri;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> params = new HashMap<>();

    public Request(BufferedReader br) throws IOException {
        parseRequestLine(br);
        parseHeader(br);
        parseParamsInBody(br);
    }

    private void parseParamsInBody(BufferedReader br) throws IOException {
        if (!headers.containsKey("content-length")) {
            return;
        }
        char[] params = new char[Integer.parseInt(headers.get("content-length"))];
        this.params = HttpRequestUtils.parseQueryString(String.valueOf(params));
    }

    private void parseHeader(BufferedReader br) throws IOException {
        String line = br.readLine();
        while (line != null && line.length() != 0) {
            HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(line);
            headers.put(pair.getKey(), pair.getValue());
            line = br.readLine();
        }
    }

    private void parseRequestLine(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null) {
            return;
        }
        String[] requestLine = line.split(" ");
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

    public String getParam(String key) {
        return params.get(key);
    }

    public Map<String, String> getParams() {
        return new HashMap<>(params);
    }

    public String getHeaders(String key) {
        return headers.get(key);
    }
}
