/*
 * 作者：刘时明
 * 时间：2020/3/22-19:54
 * 作用：
 */
package chat;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class SubTest implements MessageListener
{
    @Override
    public void onMessage(Message message, byte[] bytes)
    {
        byte[] body= message.getBody();
        System.out.println("收到发布的消息==>"+new String(body));
    }
}
