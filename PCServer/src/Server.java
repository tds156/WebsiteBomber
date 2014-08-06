import java.io.*;
import java.net.*;

public class Server {
	public Server() {
	    ServerSocket serverSocket = null;  
	    try {    
	        serverSocket = new ServerSocket(9999);
	        System.out.println("服务器开启");  
	        while(true){
	        	Socket socket = serverSocket.accept();
	        	System.out.println("连接客户端");  
	        	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	        	oos.writeObject("a");
	        	System.out.println("发送");  
	        }
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
	}
	public static void main(String[] args) {
		new Server();
	}
}