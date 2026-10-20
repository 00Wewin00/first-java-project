package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Vector;


public class  MySender {
	public static Vector<Socket> socketVector= new Vector<>();
	public static int send(Socket clientsocket, String message)throws IOException{
		byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
		DataOutputStream dos = new DataOutputStream(clientsocket.getOutputStream());
		dos.writeInt(bytes.length);
		dos.write(bytes);
		dos.flush();
	return 0;
	}
	public static byte[] writing_socket_byte(Socket socket) throws IOException{
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		int size = dis.readInt();
		byte []bytes = new byte[size];
		dis.readFully(bytes);
		return bytes;
	}
	public static final ExecutorService threadpool = Executors.newCachedThreadPool();
	public static void broadcast(Socket clientsocket,String message) throws IOException{
		System.out.println(message);
		for (Socket socketsend:socketVector){
			if(clientsocket!=socketsend){
				try{
					send(socketsend,message);
				} catch (IOException e) {
					socketsend.close();
					System.out.println("client disconnect or error in socket: "+socketsend);
				}
			}
		}
	}
	private static void closeSocket(Socket socket) {
		try {
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void clienthandler (Socket clientsocket){
		try {
			while (true) {
				Thread.sleep(100);
				byte [] buffer_message= writing_socket_byte(clientsocket);
				String message = new String(buffer_message,StandardCharsets.UTF_8);
				broadcast(clientsocket,message);
				}

			}

		catch (IOException ex) {
			System.out.println("disconnect :" + clientsocket);
			closeSocket(clientsocket);
		}
		catch (InterruptedException e){
			System.out.println("thread close");
			closeSocket(clientsocket);
			Thread.currentThread().interrupt();
		}
	}
}


