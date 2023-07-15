package dispersas;

public class Nodo {
	//ATTRIBUTES
	private int fila,columna,dato;
	private Nodo LC,LF,Liga;
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//CLASS BUILDER 
	public Nodo() {
		fila=0;
		columna=0;
		dato=0;
		LC=null;
		LF=null;
		Liga=null;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS SETTERS
	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public Nodo getLC() {
		return LC;
	}

	public void setLC(Nodo lC) {
		LC = lC;
	}

	public Nodo getLF() {
		return LF;
	}

	public void setLF(Nodo lF) {
		LF = lF;
	}

	public Nodo getLiga() {
		return Liga;
	}

	public void setLiga(Nodo liga) {
		Liga = liga;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
