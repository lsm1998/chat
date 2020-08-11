/*
 * 作者：刘时明
 * 时间：2020/3/22-11:38
 * 作用：
 */

import com.lsm1998.chat.utils.HTTPClientUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

public class HTTPTest
{
    @Test
    public void test() throws IOException, InterruptedException
    {
        String url = "http://localhost:8080/auth/login";
        String json = "{\"username\": \"test1\", \"password\": \"1234567\",\"rememberMe\":true}";
        HttpResponse<String> response = HTTPClientUtil.post(url, json, null);
        System.out.println(response.statusCode());
        response.headers().map().forEach((k, v) -> System.out.println("key=" + k + ",value=" + v));
    }
}
