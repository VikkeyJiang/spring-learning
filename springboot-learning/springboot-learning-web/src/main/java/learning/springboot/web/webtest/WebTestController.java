package learning.springboot.web.webtest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/webtest")
public class WebTestController {

    @GetMapping(path = "/get-with-body")
    public List<String> getWithBody(@RequestBody MultipartFile file, HttpServletRequest request) {
        return Arrays.asList("abcd");
    }

    @GetMapping(path = "/get-with-sleep")
    public void getWithSleep() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

}
