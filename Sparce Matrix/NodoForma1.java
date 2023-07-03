package proyectodispersas;

public class NodoForma1 {
	//ATTRIBUTES
	private int fila,columna,dato;
	private NodoForma1 LC,LF,Liga;
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//CLASS BUILDER 
	public NodoForma1() {
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

	public NodoForma1 getLC() {
		return LC;
	}

	public void setLC(NodoForma1 lC) {
		LC = lC;
	}

	public NodoForma1 getLF() {
		return LF;
	}

	public void setLF(NodoForma1 lF) {
		LF = lF;
	}

	public NodoForma1 getLiga() {
		return Liga;
	}

	public void setLiga(NodoForma1 liga) {
		Liga = liga;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
