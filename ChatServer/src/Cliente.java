import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Cliente extends Thread implements Observer{

	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	GestorMensajes gestorMensajes = new GestorMensajes();
	GestorGrupos gestorGrupos;
	Boolean activo = true;
	
	public Cliente(Socket socket) {
		this.socket = socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.println("Cliente conectado");
		
		while(activo) {
			try {
				String strMensaje="";
				strMensaje = dis.readUTF();
				System.out.println(strMensaje);
				Mensaje mensaje = new Mensaje(new String(strMensaje));
				gestorMensajes.setMensaje(mensaje);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				activo=false;
				System.out.println("Cliente desconectado");
				
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			dos.writeUTF(((Mensaje)arg1).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public GestorMensajes getGestorMensajes() {
		return gestorMensajes;
	}

	public void setGestorMensajes(GestorMensajes gestorMensajes) {
		this.gestorMensajes = gestorMensajes;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
