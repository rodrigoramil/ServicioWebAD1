package paqueteServidor;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class Server {

	public static void main(String[] args) throws IOException {
		int port = 1234;
		HttpServer server = HttpServer.create(new InetSocketAddress(port),0);
		System.out.println("servidor iniciado en el puerto " + port);
		server.createContext("/", new MyHandler());
		server.setExecutor(null);
		server.start();		
	}
}
