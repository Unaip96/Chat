import java.util.ArrayList;
import java.util.Date;

public class Grupo {

	String nombre;
	ArrayList<Mensaje> mensajes;
	ArrayList<String> miembros;
	Date fecha_creacion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(ArrayList<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	public ArrayList<String> getMiembros() {
		return miembros;
	}
	public void setMiembros(ArrayList<String> miembros) {
		this.miembros = miembros;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	
	
	
}
