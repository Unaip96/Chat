import java.util.Observable;

public class GestorGrupos extends Observable{

	Grupo grupo;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
        this.setChanged();
        this.notifyObservers(this.getGrupo());
	}
}
