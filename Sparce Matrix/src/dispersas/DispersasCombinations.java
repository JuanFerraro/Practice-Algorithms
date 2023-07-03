package dispersas;

import java.util.Scanner;

public class DispersasCombinations {
	static Scanner captura = new Scanner(System.in);
	// ATTRIBUTES
	private Nodo Punta = new Nodo();

	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	// CLASS BUILDER
	public DispersasCombinations() {
		this.Punta = null;
	}

	public Nodo getPunta() {
		return Punta;
	}

	public void setPunta(Nodo punta) {
		Punta = punta;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	// METHODS:
	// JOIN THE COLUMNS

	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	public void LigarColumnas() {
		int NF = Punta.getColumna();
		int Col = 0;
		Nodo An; // Puntero Anterior Para ir Ligando Las Columnas
		Nodo Juanse; // Puntero que Recorre Comparando
		An = Punta;
		Juanse = An.getLF();
		An.setLC(Juanse);
		An = Juanse;
		Juanse = Juanse.getLF();
		while (Col < NF) {
			Juanse = Punta.getLF();
			while (Juanse != Punta) {
				if (Juanse.getColumna() == Col) {
					An.setLC(Juanse);
					An = Juanse;
					An.setLC(Punta);
				}
				Juanse = Juanse.getLF();
			}
			Col++;
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	public void MostrarFormaCombi(DispersasCombinations COMF2) {
		Nodo P;

		System.out.println("|---  MOSTRAR MATRIZ FORMA 2 ---|");
		if (COMF2.Punta == null) {
			System.out.println("| Debes Ingresar Una Matriz Primero");
			System.out.println("------------------------------------");
		} else {
			P = COMF2.Punta;
			System.out.println("  |----- Filas [" + P.getFila() + "] ------|");
			System.out.println("  |---- Columnas [" + P.getColumna() + "] ----|");
			System.out.println(" ");
			P = P.getLF();
			while (P != COMF2.Punta) {
				if (P.getDato() != 0) {
					System.out.println("|Fila: [" + P.getFila() + "] Columna: [" + P.getColumna() + "] Dato: "
							+ P.getDato() + "|");
				}
				P = P.getLF();
			}
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	//METODO: TRIPLETA + FORMA1 = FORMA 2 
	public void TriMasF1IguF2(DispersasTriplete M4, DispersasF1 CF1, DispersasCombinations COMF2) {
		int k = 1, ST = 0, band = 0, band1 = 0;
		Nodo P = CF1.getPunta().getLiga();
		Nodo An = null, Des = null;
		Nodo a;
		Nodo R = null;
		int mat[][];
		mat = M4.getMat();

		if (COMF2.Punta == null) {
			Nodo x = new Nodo();
			if (COMF2.Punta == null) { // PONEMOS LA PUNTA
				x.setColumna(M4.getColumnas());
				x.setFila(M4.getFilas());
				COMF2.setPunta(x);

				while (P != CF1.getPunta()) {
					a = P.getLF();
					while (a != P) {
						Nodo s = new Nodo();
						s.setColumna(a.getColumna());
						s.setFila(a.getFila());
						s.setDato(a.getDato());
						x.setLF(s);
						s.setLF(COMF2.Punta);
						x = s;
						a = a.getLF();
					}
					P = P.getLiga();
				}

				while (k <= M4.getDatos()) {
					ST = 0;
					band = 0;
					R = COMF2.Punta.getLF();
					while (R != COMF2.Punta) {
						if (R.getFila() == mat[k][0] && R.getColumna() == mat[k][1]) {
							ST = R.getDato() + mat[k][2];
							R.setDato(ST);
							band = 1;
						}
						R = R.getLF();
					}
					if (band == 0) { // NO HAY NINGUNO EN LA LISTA CON ESA LISTA FILA Y COLUMNA
						Nodo p = new Nodo();
						p.setColumna(mat[k][1]);
						p.setFila(mat[k][0]);
						p.setDato(mat[k][2]);

						Nodo L = COMF2.Punta.getLF();
						band1 = 0;
						while (L != COMF2.Punta) {
							if (p.getFila() == L.getFila()) {
								band1 = 1; // EXISTEN DATOS CON ESA FILA
							}
							L = L.getLF();
						}
						if (band1 == 1) { // COMO EXISTEN DATOS EN ESA FILA BUSCAMOS LA COLUMNA
							L = COMF2.Punta.getLF();
							An = COMF2.Punta;
							Des = L.getLF();

							while (L != COMF2.Punta) {
								if (L.getFila() == p.getFila()) { // BUSCA LA FILA EN LA LISTA PARA INSERTAR EL DATO
									if (p.getColumna() > L.getColumna()) { // COMPARA LAS COLUMNAS PARA INSERTARLO
										L = L.getLF();
										An = An.getLF();
										Des = Des.getLF();
									}
									An.setLF(p);
									//L=p;
									//p.setLF(Des);
									p.setLF(L);
								}
								L = L.getLF();
							}

						} else if (band == 0) { // NO TIENE FILAS EN COMUN ENTONCES LO INSERTAMOS AL FINAL
							L = COMF2.Punta;
							while (L.getLF() != COMF2.Punta) {
								L = L.getLF();
							}
							L.setLF(p);
							p.setLF(COMF2.Punta);
						}
					}
					k++;
				}
			}

		} else {
			System.out.println("| Ya existe una matriz.");
		}
		COMF2.LigarColumnas();
		COMF2.MostrarFormaCombi(COMF2);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}//CIERRA PROGRAMA
