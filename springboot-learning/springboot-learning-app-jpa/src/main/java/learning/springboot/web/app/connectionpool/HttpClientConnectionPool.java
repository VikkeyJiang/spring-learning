package learning.springboot.web.app.connectionpool;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientConnectionPool.class);
    private static final String url = "http://192.192.192.10:8080/webtest/get-with-sleep";
    private static final int requestNum = 2;
    private static CountDownLatch latch = new CountDownLatch(requestNum);

    public static void main(String[] args) throws IOException, InterruptedException {
        CloseableHttpClient httpClient = HttpClients.custom()
            .setMaxConnPerRoute(requestNum)
            .build();
        for (int i = 0; i < requestNum; i++) {
            callRequest(httpClient);
        }
        latch.await();
        httpClient.close();
        logger.info("complete");
    }

    private static void callRequest(CloseableHttpClient client) {
        HttpUriRequest uriRequest = RequestBuilder.get(url).build();
        try (CloseableHttpResponse response = client.execute(uriRequest)) {
            logger.info(response.toString());
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            latch.countDown();
        }
    }

}
