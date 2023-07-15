package dispersas;

import java.util.Scanner;

public class DispersasF1 {
	static Scanner captura = new Scanner(System.in);
	// ATTRIBUTES
	private Nodo Punta = new Nodo();

	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	// CLASS BUILDER
	public DispersasF1() {
		this.Punta = null;
	}

	public Nodo getPunta() {
		return Punta;
	}

	public void setPunta(Nodo punta) {
		Punta = punta;
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHODS:

	// METHOD: BUILD LIST
	public void ConstruirMatriz(int mat[][], int filas, int columnas) {
		int mayor = 0, i = 0, j = 0, band = 0, flag = 0;

		if (Punta == null) {
			Nodo x = new Nodo();
			if (Punta == null) {
				Punta = x;
				x.setColumna(columnas);
				x.setFila(filas);
				if (filas > columnas)
					mayor = filas;
				else
					mayor = columnas;
				while (i < mayor) { // CICLO PARA LOS REGISTROS CABEZA
					Nodo p = new Nodo();
					Nodo reco = new Nodo();
					p.setColumna(i);
					p.setFila(i);
					p.setLF(p);
					if (Punta.getLiga() == null) {
						Punta.setLiga(p);
						p.setLiga(Punta);
					} else {
						reco = Punta; // NODO RECO PARA RECORRER LOS REGISTRO CABEZA
						while (reco.getLiga() != Punta) { // CICLO PARA UBICAR A RECO AL FINAL DE LOS RC E INSERTAR Y
															// LIGAR AHÍ
							reco = reco.getLiga();
						}
						reco.setLiga(p);
						p.setLiga(Punta);
					}
					i++;
				} // FIN CICLO PARA REGISTROS CABEZA
				i = 0;
				while (i < filas) {
					j = 0;
					flag = 0;
					while (j < columnas) { // CICLO PARA AGREGAR LOS DATOS != DE 0 A LA LISTA
						if (mat[i][j] != 0) {
							Nodo nuevo = new Nodo();
							nuevo.setDato(mat[i][j]);
							nuevo.setColumna(j);
							nuevo.setFila(i);
							Nodo r = new Nodo(); // NODO R PARA RECORRER LOS REGISTROS CABEZA
							r = Punta.getLiga();
							while (r.getLiga() != Punta) {
								if (r.getFila() == i) {
									if (r.getLF() == null) {
										r.setLF(nuevo); // INSERTO Y LIGO NUEVO NODO CON LOS DATOS
										nuevo.setLF(r);
										flag = 1;
									} else {
										Nodo w = new Nodo(); // NODO W PARA RECORRER EN CASO DE QUE YA LA LISTA TENGA
																// DATOS (LIGA FILA)
										w = r;
										while (w.getLF() != r) { // CICLO PARA UBICAR W AL FINAL DE LA LISTA
											w = w.getLF();
										}
										w.setLF(nuevo); // INSERTO Y LIGO NUEVO NODO CON LOS DATOS
										nuevo.setLF(r);
										flag = 1;
									}
								}
								r = r.getLiga();
							}
							if (flag == 0) {
								if (r.getLiga() == Punta) {
									if (r.getLF() == null) {
										r.setLF(nuevo);
										nuevo.setLF(r);
									} else {
										Nodo w = new Nodo();
										w = r;
										while (w.getLF() != r) {
											w = w.getLF();
										}
										w.setLF(nuevo);
										nuevo.setLF(r);
									}
								}
							}
						}
						j++;
					}
					i++;
				}
				Nodo a = new Nodo(); // RECORRE LOS REGISTROS CABEZA(RG)
				Nodo b = new Nodo(); // RECORRE LAS LIGA FILAS (LF) DE CADA RG
				Nodo p = new Nodo();
				Nodo z = new Nodo(); // POSICIONARLO EN EL ANTERIOR
				a = Punta.getLiga();
				p = Punta.getLiga();
				while (p != Punta) {
					a = Punta.getLiga();
					band = 0;
					while (a != Punta) {
						if (band == 0) {
							b = a.getLF();
							while (b != a) {
								if (b.getColumna() == p.getColumna()) {
									p.setLC(b);
									b.setLC(p);
									z = b;
									band = 1;
								}
								b = b.getLF();
							}
						} else {
							if (band == 1) {
								b = a.getLF();
								while (b != a) {
									if (b.getColumna() == p.getColumna()) {
										b.setLC(p);
										z.setLC(b);
										z = b;
									}
									b = b.getLF();
								}
							}
						}
						a = a.getLiga();
					}
					p = p.getLiga();
				}
				System.out.println("| Matriz Dispersa forma 1 creada exitosamente.");
			}
		} else
			System.out.println("| Ya existe una matriz.");
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: INSERT DATA
	public void InsertarDatoF1(DispersasF1 F1L1, int columna, int fila, int dato) {
		int bandera = 0;
		Nodo a = new Nodo(); // RECORRE RC
		Nodo b = new Nodo(); // RECORRE LF
		Nodo x = new Nodo(); // A INSERTAR
		Nodo sig = new Nodo(); // POSICIONARLO EN EL SIGUIENTE LF

		a = Punta.getLiga();

		x.setColumna(columna); // ASIGNACION A NUEVO NODO
		x.setDato(dato); // ASIGNACION A NUEVO NODO
		x.setFila(fila); // ASIGNACION A NUEVO NODO

		while (a != Punta) {
			if (a.getFila() == fila) {
				b = a.getLF();
				if (b == a) { 															// **
					x.setLC(a); 														// **
					a.setLF(x); 														// SI NO HAY DATOS EN ESA FILA**
					bandera = 1; 														// **
				}
				while (b != a && bandera == 0) { 										// --
					if (b.getLF() == a) { 												// --
						if (b.getColumna() < columna) { 								// --
							x.setLF(a); 												// --
							b.setLF(x); 												// --
							bandera = 1; 												// SI SOLO HAY UN DATO EN ESA FILA--
						} else { 														// --
							if (b.getColumna() > columna) { 							// --
								x.setLF(b); 											// --
								a.setLF(x); 											// --
								bandera = 1; 											// --
							}
						}
					} else {
						sig = b.getLF(); 												// ¿¿
						if (columna > b.getColumna() && columna < sig.getColumna()) { 	// ¿¿
							x.setLF(sig); 												// PARA LO DEMAS¿¿
							b.setLF(x); 												// ¿¿
							bandera = 1; 												// ¿¿
						}
					}
					b = b.getLF();
				}
			}
			a = a.getLiga();
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: SHOW SCATTERED LIST
	public void MostrarForma1(DispersasF1 F1L1) {
		Nodo a = new Nodo();
		Nodo b = new Nodo();

		if (Punta == null) {
			System.out.println("| DEBE INGRESAR UNA MATRIZ |");
		} else {
			a = Punta.getLiga();
			while (a != Punta) {
				b = a;
				System.out.println("Datos De La Fila: " + b.getFila());
				b = b.getLF();
				while (b != a) {
					System.out.println("-> " + b.getDato());
					b = b.getLF();
				}
				a = a.getLiga();
			}
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: SUM OF EACH ROW
	public void SumarCadaFilaForma1(DispersasF1 F1L1) {
		int Acu = 0;

		Nodo a = new Nodo(); // NODO QUE RECORRE LAS CABEZAS DE LAS PUNTAS
		Nodo b = new Nodo(); // NODO QUE RECORRE CADA FILA DE LAS CABEZAS DE LAS PUNTAS

		a = Punta.getLiga();
		while (a != Punta) {
			Acu = 0;
			b = a.getLF();
			while (b != a) {
				Acu = Acu + b.getDato();
				b = b.getLF();
			}
			System.out.println("La Suma De La Fila: " + a.getFila() + " es: " + Acu);
			a = a.getLiga();
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: SUM OF EACH COLUMN
	public void SumaCadaColumnaForma1(DispersasF1 F1L1) {
		int Acu = 0;
		Nodo a = new Nodo(); // NODO QUE RECORRE LAS CABEZAS DE LAS PUNTAS
		Nodo b = new Nodo(); // NODO QUE RECORRE CADA FILA DE LAS CABEZAS DE LAS PUNTA
		Nodo p = new Nodo(); // NODO QUE RECORRE LAS CABEZAS 1 POR 1

		a = Punta.getLiga();
		p = Punta.getLiga();

		while (p != Punta) {
			Acu = 0;
			a = Punta.getLiga();
			while (a != Punta) {
				b = a.getLF();
				while (b != a) {
					if (b.getColumna() == p.getColumna()) {
						Acu = Acu + b.getDato();
					}
					b = b.getLF();
				}
				a = a.getLiga();
			}
			System.out.println("La Suma De La Columna: " + p.getColumna() + " es: " + Acu);
			p = p.getLiga();
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: DELETE
	public void EliminarForma1(DispersasF1 F1L1) {
		int Columna = 0, Fila = 0, Dato = 0, band = 0;
		Nodo a;
		Nodo b;
		Nodo Ad;
		Nodo An;

		System.out.print("| Ingrese La Fila De El Dato A Eliminar: ");
		Fila = captura.nextInt();
		System.out.print("| Ingrese La Columna De El Dato A Eliminar: ");
		Columna = captura.nextInt();
		System.out.print("| Ingrese El Dato A Eliminar: ");
		Dato = captura.nextInt();

		a = Punta.getLiga();
		while (a != Punta) {
			b = a;
			An = b;
			b = a.getLF();
			Ad = b.getLF();
			while (b != a) {
				if (b.getFila() == Fila && b.getColumna() == Columna && b.getDato() == Dato) {
					An.setLF(Ad);
					band = 1;
				}
				b = b.getLF();
			}
			a = a.getLiga();
		}
		if (band == 0) {
			System.out.println("| El Dato No Se Encuentra |");
		} else {
			System.out.println("| Dato eliminado exitosamente.");
		}
	}
	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: SUM 2 MAT F1
	public void SumarDosMatricesForma1(DispersasF1 F1L1, DispersasF1 F1L2, DispersasF1 F1L3) {
		F1L3 = F1L1;
		Nodo p = F1L2.Punta;
		p = p.getLiga();
		Nodo a = null;
		Nodo h = F1L3.Punta;
		h = h.getLiga();
		Nodo b = null;
		int ST = 0, band = 0, band1 = 0;

		while (p != F1L2.Punta && h != F1L3.Punta) {
			a = p.getLF();
			while (a != p) {
				b = h.getLF();
				ST = 0;
				band = 0;
				while (b != h) {
					if (b.getColumna() == a.getColumna() && b.getFila() == a.getFila()) {
						ST = b.getDato() + a.getDato();
						b.setDato(ST);
						band = 1;
					}
					b = b.getLF();
				}
				if (band == 0) { 					// NO HAY NINGUNO EN LA LISTA CON ESA LISTA FILA Y COLUMNA

					Nodo j = new Nodo();
					j.setColumna(a.getColumna());
					j.setFila(a.getFila());
					j.setDato(a.getDato());

					Nodo An = h;
					b = h.getLF();
					Nodo Des = b.getLF();
					if (h.getLF() == h)
						band1 = 0;
					else
						band1 = 1;
					if (band1 == 1) {
						while (b != h) {

							if (b.getColumna() > j.getColumna()) { // COMPARA LAS COLUMNAS PARA INSERTARLO
								An.setLF(j);
								j.setLF(Des);
							}
							b = b.getLF();
							An = An.getLF();
							Des = Des.getLF();
						}
					}
					if (band1 == 0) {
						h.setLF(j);
						j.setLF(h);
					}

				}
				a = a.getLF();
			}
			p = p.getLiga();
			h = h.getLiga();
		}
		F1L3.MostrarForma1(F1L3);
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------

	//METHOD: SEARCH DATA BY POSITION
	public boolean BuscarPosicion(DispersasF1 F1L1, int fila, int columna) {
		Nodo a = new Nodo(); // RECORRE RG
		Nodo b = new Nodo(); // RECORRE LF
		boolean bandera = false;

		a = Punta.getLiga();
		while (a != Punta) {
			b = a;
			if (a.getFila() == fila) { // BUSCA LA FILA
				b = b.getLF();
				while (b != a) {
					if (b.getFila() == fila && b.getColumna() == columna) // SI EXISTE ALGO EN ESA FILA/COLUMNA REGRESA
																			// TRUE
						bandera = true; // SI NO LO ENCUENTRA REGRESA FALSE
					b = b.getLF();
				}
			}
			a = a.getLiga();
		}
		return bandera;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
}
