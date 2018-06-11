
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observer;

public class ServerSocketStream {

	public static void main(String[]args) {
		
		ArrayList<Thread> hilos = new ArrayList<Thread>();
		try {
				System.out.println("Creando socket server");
				ServerSocket serversocket = new ServerSocket();
				System.out.println("bind");
				String recibido = "mensaje recibido";
				
				InetSocketAddress addr = new InetSocketAddress("localhost",1234);
				serversocket.bind(addr);
			while(true) {
				
				System.out.println("aceptando conexiones");

				Socket newSocket = serversocket.accept();
				
				Cliente cliente = new Cliente(newSocket);
				hilos.add(cliente);
				GestorMensajes gestorMensajes = new GestorMensajes();
				for(int i=0;i<hilos.size();i++) {
					if(((Cliente)hilos.get(i)).getActivo())
						gestorMensajes.addObserver((Observer) hilos.get(i));
					else
						hilos.remove(i);
				}
				for(int i=0;i<hilos.size();i++) {
					Thread hilo = hilos.get(i);
					((Cliente)hilo).setGestorMensajes(gestorMensajes);
				}
				cliente.setGestorMensajes(gestorMensajes);
				cliente.start();
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}