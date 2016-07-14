package staticfile;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {
	public static void main(String[] args) throws Exception {
		int port = 20012;// 标准HTTP端口
		ServerSocket server = new ServerSocket(20012);
		Socket client = null;
		System.out.println("静态文件服务器正在运行,端口:" + port);
		System.out.println();
		while (true) {
			client = server.accept();
			System.out.println(client + "连接到HTTP服务器");
			Handler handler = new Handler(client);
			new Thread(handler).start();
		}
	}
}
