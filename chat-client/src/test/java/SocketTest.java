/*
 * 作者：刘时明
 * 时间：2020/3/22-14:12
 * 作用：
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketTest
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("127.0.0.1", 8888);
        System.out.println(socket);
        InputStream inputStream = socket.getInputStream();
        new Thread(() ->
        {
            while (true)
            {
                try
                {
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    byte[] bytes = new byte[5];
                    int len = inputStream.read(bytes);
                    byteStream.write(bytes, 0, len);
                    // 是否还有盈余数据
                    while (inputStream.available() > 0)
                    {
                        System.out.println("盈余");
                        len = inputStream.read(bytes);
                        byteStream.write(bytes, 0, len);
                    }
                    System.out.println(new String(byteStream.toByteArray()));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 10; i++)
        {
            outputStream.write("哈哈".getBytes());
            Thread.sleep(1000);
        }
    }
}
