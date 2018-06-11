import java.util.Observable;

public class GestorMensajes extends Observable{

	private Mensaje mensaje;

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
        this.setChanged();
        this.notifyObservers(this.getMensaje());
        System.out.println("Mensaje recibido: "+mensaje.mensaje);
        System.out.println("Clientes activos:"+this.countObservers());
	}
	
}
