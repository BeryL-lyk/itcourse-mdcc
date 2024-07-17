package com.itcourse.mdcc.websocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/danmu/ws")
@Slf4j
public class DanmuSocketServer {
    /**
     *用来存放每个客户端的 session
     */
    public static Set<Session> sessionSet = new CopyOnWriteArraySet<>();

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionSet.add(session);
    }

    /**
     * 连接关闭时
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("关闭，删除指定session");
        for(Session s : sessionSet) {
            sessionSet.remove(s);
        }
    }

    /**
     * 收到客户端的消息时
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(String message,Session session) {
        DanmuSocketServer.sendMessage(message);
        log.info("收到的消息:"+message);
    }



    /**
     * 实现服务器主动推送 给每个客户端推送
     */
    public  static void sendMessage(String message) {
        for (Session session : sessionSet) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
