package staticfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Handler implements Runnable {
	Functions functions = new Functions();
	MIME mime = new MIME();
	HashMap<String, String> type = mime.getMime();
	String contentType = null;
	public String encoding = "UTF-8";
	private Socket client;
	PrintWriter out = null;

	public Handler(Socket client) {
		this.client = client;
	}

	public void run() {
		if (client != null) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String header = reader.readLine();
				System.out.println("客户端发送的请求信息: >>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(header);// 读取所有浏览器发送过来的请求参数头部的所有信息
				String resource = (String) header.split(" ")[1];// 获得请求的资源的地址
				System.out.println("客户端发送的请求信息结束 <<<<<<<<<<<<<<<<<<<<<<<<<<");
				System.out.println("用户请求的资源resource是:" + resource);
				System.out.println();
				String suffix = null;
				if (resource.equals("/")) {
					resource = "/index.html";
					String[] names = resource.split("\\.");
					suffix = names[names.length - 1];
					contentType = type.get(suffix);
				} else {
					String[] names = resource.split("\\.");
					suffix = names[names.length - 1];
					contentType = type.get(suffix);
				}
				String path = "/home/sunyan/code/public/" + resource;
				File file = new File(path);
				if (file.exists()) {
					if (suffix.equals("png") || suffix.equals("jpg") || suffix.equals("jpeg")) {
						functions.readImg(file, client, contentType);
					} else {
						functions.readFile(file, client, contentType);
					}
				} else {
					PrintWriter out = new PrintWriter(client.getOutputStream(), true);
					out.println("HTTP/1.0 404 NOTFOUND");// 返回应答消息,并结束应答
					out.println("Content-Type:text/html;charset=UTF-8");
					out.println();// 根据 HTTP 协议, 空行将结束头信息
					out.println("对不起，您寻找的资源在本服务器上不存在");
					out.close();
					functions.closeSocket(client);
				} // file.exists
			} catch (Exception e) {
				System.out.println("HTTP服务器错误:" + e.getLocalizedMessage());
			}
		}
	}
}
