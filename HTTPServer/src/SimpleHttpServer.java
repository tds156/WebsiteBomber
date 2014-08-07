import java.io.*;
import java.net.*;
import java.util.StringTokenizer;


//猜测：浏览器发送了一串请求，当请求内容被读取之后会返回某种信号（被封装起来了我们看不到），然后再开始接受服务器发送的数据

public class SimpleHttpServer implements Runnable {

	ServerSocket serverSocket;

	public SimpleHttpServer() {
		try {
			serverSocket = new ServerSocket(9999);
		} catch (Exception e) {
			System.out.println("无法启动HTTP服务器:" + e.getLocalizedMessage());
		}
		new Thread(this).start();

	}

	public void run() {
		while (true) {
			try {
				Socket client = null;
				client = serverSocket.accept();
				if (client != null) {
					System.out.println("连接到服务器的用户:" + client);
					try {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(client.getInputStream()));
						String line = in.readLine();
						while ((line = in.readLine()) != null) {
							System.out.println(line);
							if (line.equals(""))
								break;
						}
						PrintWriter out = new PrintWriter(
								client.getOutputStream(), true);
						out.println("HTTP/1.0 200 OK");
						out.println("Content-Type:text/html;charset=GBK");
						out.println();

						out.println(readFileByLines("/home/june/hello2.html"));
						out.close();

						closeSocket(client);
					} catch (Exception e) {
						System.out.println("HTTP服务器错误:"
								+ e.getLocalizedMessage());
					}
				}

			} catch (Exception e) {
				System.out.println("HTTP服务器错误:" + e.getLocalizedMessage());
			}
		}
	}

	void closeSocket(Socket socket) {
		try {
			socket.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new SimpleHttpServer();
	}

	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		String string = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;

			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				string += tempString;
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return string;
	}

}