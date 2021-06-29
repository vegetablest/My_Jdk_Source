package com.bsfit.suaf.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <br>
 *
 * @author bangsun
 * @data 2021/5/8 9:33
 * @verson
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Component
@Slf4j
@Service
@ServerEndpoint("/api/websocket/{sid}")
public class WebSocketServer {

    private static int onLine=0;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet
            = new CopyOnWriteArraySet<WebSocketServer>();

    private Session session;

    private String sid = "";

    /**
     * 连接建立成功的调用方法
     * */
    @OnOpen
    public void onOpen(Session session, @PathVariable("sid") String sid){
        this.session = session;
        /*加入Set中*/
        webSocketSet.add(this);
        this.sid = sid;
        /*在线人数+1*/
        addOnlineCount();
        try {
            sendMessage("Connection Success……");
            log.info("有新窗口开始监听:" + sid + ",当前在线人数为:" + getOnlineCount());
        }catch (IOException e){
            log.error("websocket IO Exception");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        //在线数减1
        subOnlineCount();
        log.info("释放的sid为："+sid);
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (WebSocketServer socketServer : webSocketSet) {
            try {
                //为null则全部推送
                if (sid == null) {
                    socketServer.sendMessage(message);
                } else if (socketServer.sid.equals(sid)) {
                    socketServer.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onLine;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onLine++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onLine--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}