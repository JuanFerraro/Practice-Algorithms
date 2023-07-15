package dispersas;

import java.util.Scanner;

public class DispersasF2 {
	static Scanner captura = new Scanner(System.in);
	// ATTRIBUTES
	private Nodo Punta = new Nodo();

	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	// CLASS BUILDER
	public DispersasF2() {
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
	public void LigarColumnas() {
		int NF = Punta.getColumna();
		int Col = 0;
		Nodo An; 			// Puntero Anterior Para ir Ligando Las Columnas
		Nodo Juanse;	 	// Puntero que Recorre Comparando
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

	// METHOD: BUILD LIST
	public void ConstruirMatriz(int mat[][], int filas, int columnas) {
		int i = 0, j = 0;
		Nodo Pepe;

		if (Punta == null) {
			Nodo x = new Nodo();
			if (Punta == null) { 					// PONEMOS LA PUNTA
				x.setColumna(columnas);
				x.setFila(filas);
				Punta = x;

				while (i < filas) { 				// EMPEZAMOS EL PROCESO DE LIGAR LOS NODOS CON LOS DATOS
					j = 0;
					while (j < columnas) {
						Nodo W = new Nodo();
						W.setColumna(j);
						W.setFila(i);
						W.setDato(mat[i][j]);
						if (Punta.getLF() == null) {
							Punta.setLF(W);
							W.setLF(Punta);

						} else {
							Pepe = Punta; // PUNTERO PARA BUSCAR EL ULTIMO
							while (Pepe.getLF() != Punta) { // CICLO PARA UBICAR INSERTAR LOS NODOS Y LIGAR EL ULTIMO
								Pepe = Pepe.getLF();
							}
							Pepe.setLF(W);
							Pepe = W;
							Pepe.setLF(Punta);
						}
						j++;
					}
					i++;
				}
				LigarColumnas(); // METODO PARA LIGAR LAS COLUMNAS
			}

		} else {
			System.out.println("| Ya existe una matriz.");
		}

	}

	// SHOW THE FORM 2
	public void MostrarForma2(DispersasF2 F2L1) {
		Nodo P;

		System.out.println("|---  MOSTRAR MATRIZ FORMA 2 ---|");
		if (Punta == null) {
			System.out.println("| Debes Ingresar Una Matriz Primero");
			System.out.println("------------------------------------");
		} else {
			P = Punta;
			System.out.println("  |----- Filas [" + P.getFila() + "] ------|");
			System.out.println("  |---- Columnas [" + P.getColumna() + "] ----|");
			System.out.println(" ");
			P = P.getLF();
			while (P != Punta) {
				if (P.getDato() != 0) {
					System.out.println("|Fila: [" + P.getFila() + "] Columna: [" + P.getColumna() + "] Dato: "
							+ P.getDato() + "|");
				}
				P = P.getLF();
			}
		}
	}

	// SUM OF EACH ROW
	public void SumarCadaFilaForma2(DispersasF2 F2L1) {
		int TotalSuma = 0, Barrios = 0;
		Nodo P;

		while (Barrios < Punta.getFila()) {
			P = Punta.getLF();
			TotalSuma = 0;
			while (P != Punta) {
				if (P.getFila() == Barrios) {
					TotalSuma = TotalSuma + P.getDato();
				}
				P = P.getLF();
			}
			System.out.println("Fila: [ " + Barrios + " ] Suma Fila: " + TotalSuma);
			Barrios++;
		}

	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// SUM EACH COLUMN
	public void SumarCadaColumnaForma2(DispersasF2 F2L1) {
		Nodo S;
		int Barrios = 0, TotalSuma = 0;

		while (Barrios < Punta.getColumna()) {
			S = Punta.getLF();
			TotalSuma = 0;
			while (S != Punta) {
				if (S.getColumna() == Barrios) {
					TotalSuma = TotalSuma + S.getDato();
				}
				S = S.getLF();
			}
			System.out.println("Columna: [ " + Barrios + " ] Suma Fila: " + TotalSuma);
			Barrios++;
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// DELETE A DATA
	public void EliminarForma2(DispersasF2 F2L1) {
		Nodo R = Punta.getLF(), An = Punta, Des = R.getLF();
		int band = 0;
		int Dato = 0, Fila = 0, Columna = 0;
		System.out.println("|---- ELIMINAR DATO ----|");
		System.out.print("| Ingrese El Dato A Eliminar: ");
		Dato = captura.nextInt();
		System.out.print("| Ingrese La Fila De El Dato A Eliminar: ");
		Fila = captura.nextInt();
		System.out.print("| Ingrese La Columna De El Dato A Eliminar: ");
		Columna = captura.nextInt();
		System.out.println("----------------------------");

		while (R != Punta) {
			if (R.getDato() == Dato && R.getFila() == Fila && R.getColumna() == Columna) {
				An.setLF(Des);
				//An.setLC(Des);
				band = 1;
				System.out.println("| Se Ha Eliminado Con Exito |");
			}
			R = R.getLF();
			An = An.getLF();
			Des = Des.getLF();
		}
		LigarColumnas();
		if (band == 0)
			System.out.println("| El Dato No Se Encuentra |");
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// INSERTAR A NEW DATA
	public void InsertarDatoForma2(DispersasF2 F2L1) {
		Nodo TT = Punta.getLF(), An = Punta, Des = TT.getLF();
		int band = 0, Dato = 0, Fila = 0, Columna = 0, Op = 0;
		Nodo x = new Nodo();
		System.out.println("|---- INSERTAR DATO ----|");
		System.out.print("| Ingrese Dato: ");
		Dato = captura.nextInt();

		System.out.print("| Ingrese La Fila: ");
		Fila = captura.nextInt();
		if (Fila > Punta.getFila()) {
			System.out.println("| La Fila Ingresada Es Mayor A Las Existentes |");
			System.out.print("| Ingrese La Fila: ");
			Fila = captura.nextInt();
		}
		System.out.print("| Ingrese La Columna: ");
		Columna = captura.nextInt();
		if (Columna > Punta.getColumna()) {
			System.out.println("| La Columna Ingresada Es Mayor A Las Existentes |");
			System.out.print("| Ingrese La Columna: ");
			Columna = captura.nextInt();
		}
		System.out.println("----------------------------");

		x.setDato(Dato);
		x.setFila(Fila);
		x.setColumna(Columna);

		while (TT != Punta) {
			if (TT.getFila() == Fila && TT.getColumna() == Columna && TT.getDato() != 0) {
				band = 1; // ENCONTRO UN DATO EN ESA POSICION
				System.out.println("| Existe un Dato En Esa Posicion |");
				System.out.println(" Que Desea Hacer ");
				System.out.println("1. Remplazar Dato .");
				System.out.println("2. Sumar Datos .");
				System.out.println("3. Salir .");
				Op = captura.nextInt();

				switch (Op) {
				case 1:
					TT.setDato(Dato);
					System.out.println("| Se Ha Remplazado Con Exito |");
					break;
				case 2:
					TT.setDato(TT.getDato() + Dato);
					System.out.println("| Se Ha Sumado Con Exito |");
					break;
				default:
					break;
				}
			}
			TT = TT.getLF();
		}
		band = 3;
		if (band == 3) { // NO ENCONTRO DATO EN ESA POSICION
			TT = Punta.getLF();
			while (TT != Punta) {
				if (TT.getFila() == Fila) { // BUSCA LA FILA EN LA LISTA PARA INSERTAR EL DATO NUEVO
					band = 3;
					if (Columna > TT.getColumna()) { // COMPARA LAS COLUMNAS PARA INSERTARLO
						TT = TT.getLF();
						An = An.getLF();
						Des = Des.getLF();
					}
					An.setLF(x);
					x.setLF(Des);
				}
				TT = TT.getLF();
			}
		}

		TT = Punta;
		if (band == 0) // SI EL DATO NO TIENE NINGUNA FILA EN COMUN
		{
			TT = Punta.getLF();
			An = Punta;
			Des = TT.getLF();
			while (TT != Punta) {
				if (TT.getFila() < x.getFila()) {
					TT = TT.getLF();
					An = An.getLF();
					Des = Des.getLF();
				}
				An.setLF(x);
				x.setLF(Des);
				TT = x;
				TT.getLF();
			}
		}
//                            if(band == 0){                           
//                                 while(TT != Punta)
//                                 {
//                                     while(TT.getFila() == Fila)
//                                     {
//                                             if(TT.getFila() == Fila)
//                                             {
//                                             TT=TT.getLF();
//                                             An=An.getLF();
//                                             Des=Des.getLF();
//                                             }
//                                     TT=TT.getLF();
//                                     }
//                                     An.setLF(x);
//                                     x.setLF(Des);
//                                    
//                                 }
//                            }

		LigarColumnas();

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// SUM TWO MATRIZ
	public void SumarDosMatricesForma2(DispersasF2 F2L1, DispersasF2 F2L2, DispersasF2 F2L3) {
		Nodo P1 = F2L1.Punta.getLF();
		Nodo P2 = F2L2.Punta.getLF();
		Nodo P3 = F2L3.Punta;
		Nodo An = null;
		Nodo Des = null;
		int band = 0, band1 = 0, ST = 0;
		;

		Nodo x = new Nodo();
		x.setColumna(F2L1.Punta.getColumna());
		x.setFila(F2L1.Punta.getFila());
		F2L3.Punta = x;
		x.setLF(F2L3.Punta);

		while (P1 != F2L1.Punta) {
			Nodo k = new Nodo();
			k.setFila(P1.getFila());
			k.setColumna(P1.getColumna());
			k.setDato(P1.getDato());
			P3 = Punta;

			while (P3.getLF() != F2L3.Punta) {
				P3 = P3.getLF();
			}
			P3.setLF(k);
			k.setLF(F2L3.Punta);
			P1 = P1.getLF();
		}

		while (P2 != F2L2.Punta) {
			P3 = F2L3.Punta;
			P3 = P3.getLF();
			band = 0;

			while (P3 != F2L3.Punta) {
				ST = 0;
				if (P3.getFila() == P2.getFila() && P3.getColumna() == P2.getColumna()) {
					ST = P3.getDato() + P2.getDato();
					P3.setDato(ST);
					band = 1;

				}
				P3 = P3.getLF();
			}
			if (band == 0) { // NO HAY NINGUNO EN LA LISTA CON ESA LISTA FILA Y COLUMNA
				Nodo p = new Nodo();
				p.setColumna(P2.getColumna());
				p.setFila(P2.getFila());
				p.setDato(P2.getDato());

				Nodo L = F2L3.Punta.getLF();
				band1 = 0;
				while (L != F2L3.Punta) {
					if (p.getFila() == L.getFila()) {
						band1 = 1; // EXISTEN DATOS CON ESA FILA
					}
					L = L.getLF();
				}
				if (band1 == 1) { // COMO EXISTEN DATOS EN ESA FILA BUSCAMOS LA COLUMNA
					L = F2L3.Punta.getLF();
					An = F2L3.Punta;
					Des = L.getLF();
					while (L != F2L3.Punta) {
						if (L.getFila() == p.getFila()) { // BUSCA LA FILA EN LA LISTA PARA INSERTAR EL DATO
							if (p.getColumna() > L.getColumna()) { // COMPARA LAS COLUMNAS PARA INSERTARLO
								L = L.getLF();
								An = An.getLF();
								Des = Des.getLF();
							}
							An.setLF(p);
							x.setLF(Des);
						}
						L = L.getLF();
					}
				} else if (band == 0) { // NO TIENE FILAS EN COMUN ENTONCES LO INSERTAMOS AL FINAL
					L = F2L3.Punta;
					while (L.getLF() != F2L3.Punta) {
						L = L.getLF();
					}
					L.setLF(p);
					p.setLF(F2L3.Punta);
				}

			}
			P2 = P2.getLF();
		}
		F2L3.LigarColumnas();
		F2L3.MostrarForma2(F2L3);
	}

}
// P2=F2L2.Punta.getLF();
//           P3=F2L3.Punta.getLF();
//           
//           while(P2 != F2L2.Punta)
//           {
//               P3=F2L3.Punta.getLF();
//               band=0;
//                   while(P3 != F2L3.Punta){
//                       if(P2.getFila() == P3.getFila() && P2.getColumna() == P3.getColumna()){
//                           band=1;
//                       }
//                       P3=P3.getLF();
//                   }
//                   if(band == 0)
//                   {
//                       Nodo R= new Nodo();
//                       R.setColumna(P2.getColumna());
//                       R.setDato(P2.getDato());
//                       R.setFila(P2.getFila());
//                       
//                       Nodo L;
//                       L=F2L3.Punta.getLF();
//                       An=F2L3.Punta;
//                       Des=L.getLF();
//                       while(L != F2L3.Punta){
//                           if(R.getFila() == L.getFila()){
//                              if(L.getColumna() < R.getColumna()){
//                                  An=An.getLF();
//                                  L=L.getLF();
//                                  Des=Des.getLF();
//                              }
//                              An.setLF(R);
//                              R.setLF(Des);
//                              L=L.getLF();
//                           }
//                           L=L.getLF();
//                       } 
//                   }
//                   P2=P2.getLF();
//           }