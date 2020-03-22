# chat

* chat-client    --  swing编写的客户端
* chat-common  -- 公共包  
* chat-server  -- 服务端 
* chat-web   -- web端

## 项目概述：
项目采用长短连接结合的方式，server工程使用netty完成核心的长连接交互，web工程提供基于HTTP协议的业务接口，例如登录、查询等，client为PC客户端，由swing编写；\
数据存储使用MongoDB，消息订阅与发布使用Redis