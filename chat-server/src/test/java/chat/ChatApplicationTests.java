package chat;

import com.lsm1998.chat.ChatServer;
import com.lsm1998.chat.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@SpringBootTest(classes = ChatServer.class)
class ChatApplicationTests
{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void contextLoads()
    {
        User user = new User();
        user.setId(1L);
        user.setNickname("新垣结衣不为人知的老公");
        user.setUsername("root");
        user.setPassword("123");
        user.setSalt("12345");
        mongoTemplate.save(user);
    }

    // @Autowired
    // private JdkSerializationRedisSerializer jdkSerializationRedisSerializer;

    private String topic="msg";

    @Test
    void testRedis()
    {
        String hello="hello";
        redisTemplate.convertAndSend(topic,hello.getBytes());
    }
}
