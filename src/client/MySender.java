package client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
public class MySender {
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
}
