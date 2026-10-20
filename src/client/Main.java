package client;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		Socket socket = new Socket("127.0.0.1",8080);
		String message = new String("hello Im client");
		MySender.send(socket,message);
		while(true){
			String input=scanner.nextLine();
			String mes= new String(input);
			MySender.send(socket,mes);
			if("exit".equalsIgnoreCase(input)){
				break;
			}
			byte [] buffer = MySender.writing_socket_byte(socket);
			String sms = new String(buffer, StandardCharsets.UTF_8);
			System.out.println(sms);
		}
	}
}