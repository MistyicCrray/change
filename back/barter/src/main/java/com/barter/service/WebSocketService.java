package com.barter.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.barter.model.User;


/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 *                 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/websocket/{username}")
@Component
public class WebSocketService {
	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	private String username;
	private List<User> users = new ArrayList<>();
	// 存储当前用户，单个消息发送
	private static Map<String, Session> map = new HashMap<>();
	// 存储用户，群发消息发送
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<WebSocketService> webSocketSet = new CopyOnWriteArraySet<WebSocketService>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 * @throws UnsupportedEncodingException
	 */
	@OnOpen
	public void onOpen(@PathParam("username") String username, Session session)
			throws UnsupportedEncodingException {
		this.session = session;
		// 解决乱码问题
		String decode = new String(username.getBytes("ISO8859-1"), "utf-8");
		this.username = decode;
		User user = new User();
		user.setUserName(decode);
		users.add(user);
		// 存储用户信息 session为用户发送信息对象
		map.put(username, session);
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@OnClose
	public void onClose() throws UnsupportedEncodingException {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		Collection<Session> values = map.values();
		values.remove(this);
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 * @throws IOException
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		String[] mes = message.split("-");
		System.out.println("来自客户端的消息:" + mes[0]);
		// 点对点发送消息
		Session session2 = map.get(mes[1]);
		if (session2 == null) {
			sendMessage("该用户不在线", session);
		} else {
			if (session2.equals(session)) {
				sendMessage("不能给自己发消息", session);
			} else {
				sendMessage(mes[0], session2);
			}
		}
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message, Session session) throws IOException {
		if (session.isOpen()) {
			// 发送一条消息
			session.getBasicRemote().sendText(message);
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketService.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketService.onlineCount--;
	}

}
