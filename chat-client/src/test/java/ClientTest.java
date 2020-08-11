import com.lsm1998.chat.utils.DESUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @program: chat
 * @description:
 * @author: lsm
 * @create: 2020-08-11 16:15
 **/
public class ClientTest
{
    @Test
    public void login()
    {
        List<String> list = List.of(",", "123", "", "白日依山尽，黄河入海流");

        list.forEach(text ->
        {
            try
            {
                String key = DESUtil.getKey();
                String encrypt = DESUtil.encrypt(text, key);
                System.out.println(DESUtil.decrypt(encrypt, key));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}
