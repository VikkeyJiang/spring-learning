package learning.springboot.web.app.connectionpool;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OKHttpConnectionPool {

    private static final Logger logger = LoggerFactory.getLogger(OKHttpConnectionPool.class);

    public static void main(String[] args) throws InterruptedException {

        OkHttpClient client = new Builder()
            .build();
        CountDownLatch latch = new CountDownLatch(2);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            Request build = new Request.Builder().get().url("http://192.192.192.10:8080/webtest/get-with-sleep").build();
            Call call = client.newCall(build);
            try (Response response = call.execute()) {
                System.out.println(response.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
        latch.await();
        Long end = System.currentTimeMillis();
        logger.info(Long.valueOf(end - start).toString());
    }
}
