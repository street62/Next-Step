package webserver.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    public void RequestTest() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("GET /index.html HTTP 1.1\r\n");
        sb.append("Host: localhost:8080\r\n");
        sb.append("Connection: keep-alive\r\n");
        sb.append("Cache-Control: max-age=0\r\n");
        sb.append("Upgrade-Insecure-Requests: 1\r\n");
        sb.append("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36\r\n");
        sb.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n");
        sb.append("Sec-Fetch-Site: none\r\n");
        sb.append("Sec-Fetch-Mode: navigate\r\n");
        sb.append("Sec-Fetch-User: ?1\r\n");
        sb.append("Sec-Fetch-Dest: document\r\n");
        sb.append("Accept-Encoding: gzip, deflate, br\r\n");
        sb.append("Accept-Language: en-US,en;q=0.9,ja-JP;q=0.8,ja;q=0.7,de-DE;q=0.6,de;q=0.5,ko-KR;q=0.4,ko;q=0.3\r\n");

        String s = sb.toString();

        InputStream is = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        Request request = new Request(br);

        assertThat(request.getUri()).isEqualTo("/index.html");
        assertThat(request.getMethod()).isEqualTo("GET");
        assertThat(request.getHeaders("Upgrade-Insecure-Requests")).isEqualTo("1");
        assertThat(request.getHeaders("Accept-Encoding")).isEqualTo("gzip, deflate, br");

    }

}