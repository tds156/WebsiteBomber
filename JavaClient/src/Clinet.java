import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clinet {
	public static void main(String[] args){
		 try {
	        	Socket s = new Socket("192.168.1.102",9999);
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				try {
					System.out.print(ois.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
	        } catch (UnknownHostException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	  
	}
}
