package server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		ServerSocket serverSocket = new ServerSocket(8080);
		while(true) {
			Socket Clientsocket = serverSocket.accept();

			MySender.socketVector.add(Clientsocket);
			MySender.threadpool.execute(()->{MySender.clienthandler(Clientsocket);});
		}

	}
}
