package dispersas;

import java.util.Scanner;

public class MainDispersas {
	static Scanner captura = new Scanner(System.in);
	static DispersasTriplete M1 = new DispersasTriplete();
	static DispersasTriplete M2 = new DispersasTriplete();
	static DispersasTriplete M3 = new DispersasTriplete();
	static DispersasTriplete M4 = new DispersasTriplete();
	static DispersasF1 F1L1 = new DispersasF1();
	static DispersasF1 F1L2 = new DispersasF1();
	static DispersasF1 F1L3 = new DispersasF1();
	static DispersasF1 CF1 = new DispersasF1();
	static DispersasF2 F2L1 = new DispersasF2();
	static DispersasF2 F2L2 = new DispersasF2();
	static DispersasF2 F2L3 = new DispersasF2();
	static DispersasF2 CF2 = new DispersasF2();
	static DispersasCombinations COMF2= new DispersasCombinations();

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METHOD: ENTRY AND VALIDATION OF MATRIZ BELOW 70%
	public static int[][] EntradaMatriz(int filas, int columnas, int mat[][]) {
		int contDatos = 0, i = 0, j = 0, dato = 0; // Variables

		double n = filas * columnas;
		double porce = 70;
		double porcentaje = (n * porce) / 100; // Lecture percentage

		while (i < filas && contDatos < porcentaje) { // Process
			j = 0;
			while (j < columnas && contDatos < porcentaje) {
				System.out.println("| Ingrese el dato el la posicion [" + i + "][" + j + "] |");
				dato = captura.nextInt();
				if (dato != 0) {
					contDatos++;
					if (contDatos < porcentaje) {
						mat[i][j] = dato;
					}
				}
				j++;
			}
			i++;
		}
		System.out.println("");
		System.out.println("| Matriz ingresada exitosamente.");
		return mat;
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: SHOW MATRIZ ON SCREEN
	public static void MostrarMatriz(int filas, int columnas, int mat[][]) {
		int i = 0, j = 0;
		System.out.println("");
		System.out.println("|MATRIZ: ");
		while (i < filas) {
			j = 0;
			while (j < columnas) {
				System.out.print("|  " + mat[i][j]);
				System.out.print("  |");
				j++;
			}
			System.out.println("");
			i++;
		}
		System.out.println(" ");
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: COUNTS DATA IN A MATRIZ
	public static int CuentaDatosM(int mat[][], int contDatos, int filas, int columnas) {
		int i = 0, j = 0; // VARIABLES

		while (i < filas) {
			j = 0;
			while (j < columnas) {
				if (mat[i][j] != 0) {
					contDatos++;
				}
				j++;
			}
			i++;
		}
		return contDatos;
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: MENU TRIPLETE
	public static void MenuTriplete(DispersasTriplete M1) {
		int opcion = 0, filas = 0, columnas = 0, mat[][], contDatos = 0, aumento = 0; // Variables
		// DispersasTriplete M1=new DispersasTriplete();
		do {
			System.out.println("| MENU: REPRESENTACION EN TRIPLETAS.    |");
			System.out.println("| 1. Ingreso de matriz.                 |");
			System.out.println("| 2. Muestra de Matriz.                 |");
			System.out.println("| 3. Ingresar nuevo dato.               |");
			System.out.println("| 4. Suma de filas.                     |");
			System.out.println("| 5. Suma de columnas.                  |");
			System.out.println("| 6. Eliminar termino.                  |");
			System.out.println("| 7. Reconstruir Matriz.                |");
			System.out.println("| 8. Suma / Principal.                  |");
			System.out.println("| 9. Suma / Secundaria.                 |");
			System.out.println("| 10. Salir.                            |");
			System.out.println("| Elija una opcion:                     |");
			opcion = captura.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("| Ingrese numero de filas:              |");
				filas = captura.nextInt();
				System.out.println("| Ingrese numero de columnas:           |");
				columnas = captura.nextInt();
				mat = new int[filas][columnas];
				EntradaMatriz(filas, columnas, mat); // MATRIZ INPUT
				contDatos = CuentaDatosM(mat, contDatos, filas, columnas); // COUNT DATA IN MATRIZ
				MostrarMatriz(filas, columnas, mat); // SHOW THE ENTERED MATRIZ
				M1.DispersasTriplete(filas, columnas, contDatos); // CREATE CLASS OBJECT
				M1.CrearTriple(mat, M1.getMat(), filas, columnas, M1.getDatos()); // CREATE TRIPLET MATRIZ
				System.out.println(" ");
				break;
			case 2:
				if (M1.getDatos() == 0) {
					System.out.println("| Por favor ingrese una matriz primero.");
					System.out.println(" ");
				} else {
					M1.MostrarMatriz(M1.getMat(), M1.getDatos()); // SHOW TRIPLET MATRIZ
				}
				break;
			case 3:
				if (M1.getDatos() != 0) {
					aumento = 1;
					M1 = M1.AumentarTamano(aumento, M1.getMat(), M1.getDatos(), M1);
					System.out.println("| Ingrese la fila: ");
					int ndfila = captura.nextInt();
					System.out.println("| Ingrese la columna: ");
					int ndcolumna = captura.nextInt();
					if (ndfila > M1.getFilas() || ndcolumna > M1.getColumnas()) {
						System.out.println("| La fila o columna ingresada no existe en la matriz.");
					} else {
						System.out.println("| Ingrese el nuevo dato: ");
						int nuevodato = captura.nextInt();
						M1.InsertarPosicion(ndfila, ndcolumna, nuevodato, M1.getMat(), M1);
						System.out.println("| Se ha insertado exitosamente.");
						System.out.println(" ");
					}
				} else {
					System.out.println("| Por favor ingrese una matriz primero.");
					System.out.println(" ");
				}
				break;
			case 4:
				M1.SumarFila(M1.getMat(), M1.getFilas(), M1.getDatos());
				break;
			case 5:
				M1.SumarColumna(M1.getMat(), M1.getColumnas(), M1.getDatos());
				break;
			case 6:
				if (M1.getDatos() != 0) {
					System.out.println("| Ingrese el dato a eliminar: ");
					int datoelim = captura.nextInt();
					M1.EliminarDato(datoelim, M1, M1.getMat());

				} else {
					System.out.println("| Por favor ingrese una matriz primero.");
					System.out.println(" ");
				}
				break;
			case 7:
				if (M1.getDatos() != 0) {
					M1.Reconstruir(M1.getMat(), M1);
				} else {
					System.out.println("| Por favor ingrese una matriz primero.");
					System.out.println(" ");
				}
				break;
			case 8:
				if (M1.getDatos() != 0) {
					M1.DiagonalPrincipal(M1);
				} else {
					System.out.println("| Por favor ingrese una matriz primero.");
					System.out.println(" ");
				}
				break;
			case 9:
				if (M1.getDatos() != 0) {
					M1.DiagonalSecundaria(M1);
				} else {
					System.out.println("| Por favor ingrese una matriz primero.");
					System.out.println(" ");
				}
				break;
			}

		} while (opcion < 10 && opcion > 0);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: MENU TRIPLETAS
	public static void MenuTriPrincipal() {
		int opcion = 0;

		do {
			System.out.println("____________________________________");
			System.out.println("|          MENU TRIPLETAS           |");
			System.out.println("| 1. Matriz 1.                      |");
			System.out.println("| 2. Matriz 2.                      |");
			System.out.println("| 3. Suma de matrices.              |");
			System.out.println("| 4. Multiplicacion de matrices.    |");
			System.out.println("| 5. Salir.                         |");
			System.out.println("| Escoja una opcion:                |");
			opcion = captura.nextInt();
			System.out.println(" ");
			switch (opcion) {
			case 1:
				MenuTriplete(M1);
				break;
			case 2:
				MenuTriplete(M2);
				break;
			case 3:
				if (M1.getDatos() == 0 || M2.getDatos() == 0) {
					System.out.println("| Aun no has ingresado las dos matrices.");
					System.out.println(" ");
				} else {
					if (M1.getFilas() != M2.getFilas() || M1.getColumnas() != M2.getColumnas()) {
						System.out.println("| Las matrices tienen un tama�o diferente, no se pueden sumar.");
						System.out.println(" ");
					} else {
						M3.SumaDosMatrices(M1, M2, M3);
					}
				}
				break;
			case 4:
				if (M1.getDatos() == 0 || M2.getDatos() == 0) {
					System.out.println("| Aun no has ingresado las dos matrices.");
					System.out.println(" ");
				} else {
					int opc3 = 0;
					do {
						System.out.println("| 1. Matriz 1 * Matriz 2.");
						System.out.println("| 2. Matriz 2 * Matriz 1.");
						System.out.println("| 3. Atras.");
						opc3 = captura.nextInt();
						switch (opc3) {
						case 1:
							if (M1.getFilas() != M2.getColumnas())
								System.out.println(
										"| Esta operacion no se puede realizar (Incongruencia entre filas y columnas).");
							else
								// Llamar Metodo.
								break;
						case 2:
							if (M2.getFilas() != M1.getColumnas())
								System.out.println(
										"| Esta operacion no se puede realizar (Incongruencia entre filas y columnas).");
							else
								// Llamar Metodo.
								break;
						}
					} while (opc3 > 0 && opc3 < 3);
				}
				break;
			}
		} while (opcion > 0 && opcion < 5);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: MENU F1
	public static void MenuF1(DispersasF1 F1L1) {
		int opcion = 0, filas = 0, columnas = 0, mat[][], dato = 0;

		do {
			System.out.println(" ");
			System.out.println("|     MENU DISPERSAS FORMA 1    |");
			System.out.println("| 1. Ingreso de matriz.         |");
			System.out.println("| 2. Mostrar matriz dispersa.   |");
			System.out.println("| 3. Suma de cada fila.         |");
			System.out.println("| 4. Suma de cada columna.      |");
			System.out.println("| 5. Insertar nuevo dato.       |");
			System.out.println("| 6. Eliminar dato.             |");
			System.out.println("| 7. Salir.                     |");
			System.out.println("| Opcion:                       |");
			opcion = captura.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("| Ingrese numero de filas:              |");
				filas = captura.nextInt();
				System.out.println("| Ingrese numero de columnas:           |");
				columnas = captura.nextInt();
				mat = new int[filas][columnas];
				EntradaMatriz(filas, columnas, mat);
				MostrarMatriz(filas, columnas, mat);
				F1L1.ConstruirMatriz(mat, filas, columnas);
				break;
			case 2:
				if (F1L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F1L1.MostrarForma1(F1L1);
				break;
			case 3:
				if (F1L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F1L1.SumarCadaFilaForma1(F1L1);
				break;
			case 4:
				if (F1L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F1L1.SumaCadaColumnaForma1(F1L1);
				break;
			case 5:
				if (F1L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else {
					System.out.println("| Ingrese la columna: ");
					columnas = captura.nextInt();
					System.out.println("| Ingrese la fila: ");
					filas = captura.nextInt();
					System.out.println("| Ingrese el dato: ");
					dato = captura.nextInt();
					F1L1.InsertarDatoF1(F1L1, columnas, filas, dato);
					System.out.println("| Dato insertado con exito.");
					System.out.println(" ");
				}
				break;
			case 6:
				if (F1L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F1L1.EliminarForma1(F1L1);
				break;
			}
		} while (opcion > 0 && opcion < 7);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: MAIN MENU F1
	public static void MenuPrinciF1() {
		int opcion = 0;

		do {
			System.out.println(" ______________________________");
			System.out.println("|  MENU REPRESENTACION FORMA 1 |");
			System.out.println("| 1. Matriz 1.                 |");
			System.out.println("| 2. Matriz 2.                 |");
			System.out.println("| 3. Suma de Matrices.         |");
			System.out.println("| 4. Salir.                    |");
			System.out.println("| Opcion:                      |");
			opcion = captura.nextInt();
			switch (opcion) {
			case 1:
				MenuF1(F1L1);
				break;
			case 2:
				MenuF1(F1L2);
				break;
			case 3:
				if (F1L1.getPunta() == null || F1L2.getPunta() == null) {
					System.out.println("| Aun no has ingresado las dos matrices.");
					System.out.println(" ");
				} else {
					if (F1L1.getPunta().getFila() != F1L2.getPunta().getFila()
							|| F1L1.getPunta().getColumna() != F1L2.getPunta().getColumna()) {
						System.out.println("| Las matrices tienen un tama�o diferente, no se pueden sumar.");
						System.out.println(" ");
					} else {
						F1L3.SumarDosMatricesForma1(F1L1, F1L2, F1L3);
					}
				}

				break;
			}
		} while (opcion < 4 && opcion > 0);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: MENU F2
	public static void MenuF2(DispersasF2 F2L1) {
		int opcion = 0, filas = 0, columnas = 0, mat[][];

		do {
			System.out.println(" ");
			System.out.println("|     MENU DISPERSAS FORMA 2    |");
			System.out.println("| 1. Ingreso de matriz.         |");
			System.out.println("| 2. Mostrar Forma 2.           |");
			System.out.println("| 3. Suma de cada fila.         |");
			System.out.println("| 4. Suma de cada columna.      |");
			System.out.println("| 5. Insertar nuevo dato.       |");
			System.out.println("| 6. Eliminar dato.             |");
			System.out.println("| 7. Salir.                     |");
			System.out.println("| Opcion:                       |");
			opcion = captura.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("| Ingrese numero de filas:              |");
				filas = captura.nextInt();
				System.out.println("| Ingrese numero de columnas:           |");
				columnas = captura.nextInt();
				mat = new int[filas][columnas];
				EntradaMatriz(filas, columnas, mat);
				MostrarMatriz(filas, columnas, mat);
				F2L1.ConstruirMatriz(mat, filas, columnas);
				break;
			case 2:
				if (F2L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F2L1.MostrarForma2(F2L1);
				break;
			case 3:
				if (F2L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F2L1.SumarCadaFilaForma2(F2L1);
				break;
			case 4:
				if (F2L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F2L1.SumarCadaColumnaForma2(F2L1);
				break;
			case 5:
				if (F2L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F2L1.InsertarDatoForma2(F2L1);
				break;
			case 6:
				if (F2L1.getPunta() == null) {
					System.out.println("| Por favor ingrese una matriz primero.");
				} else
					F2L1.EliminarForma2(F2L1);
				break;
			}
		} while (opcion > 0 && opcion < 7);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// METHOD: MAIN MENU F2
	public static void MenuPrinciF2() {
		int opcion = 0;

		do {
			System.out.println(" ______________________________");
			System.out.println("|  MENU REPRESENTACION FORMA 2 |");
			System.out.println("| 1. Matriz 1.                 |");
			System.out.println("| 2. Matriz 2.                 |");
			System.out.println("| 3. Suma de Matrices.         |");
			System.out.println("| 4. Salir.                    |");
			System.out.println("| Opcion:                      |");
			opcion = captura.nextInt();
			switch (opcion) {
			case 1:
				MenuF2(F2L1);
				break;
			case 2:
				MenuF2(F2L2);
				break;
			case 3:
				if (F2L1.getPunta() == null || F2L2.getPunta() == null) {
					System.out.println("| Aun no has ingresado las dos matrices.");
					System.out.println(" ");
				} else {
					if (F2L1.getPunta().getFila() != F2L2.getPunta().getFila()
							|| F2L1.getPunta().getColumna() != F2L2.getPunta().getColumna()) {
						System.out.println("| Las matrices tienen un tama�o diferente, no se pueden sumar.");
						System.out.println(" ");
					} else {
						F2L3.SumarDosMatricesForma2(F2L1, F2L2, F2L3);
					}
				}
				break;
			}
		} while (opcion < 4 && opcion > 0);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	// METHOD: MAIN COMBINACIONES
	public static void MenuCombi(DispersasTriplete M4, DispersasF1 CF1, DispersasCombinations COMF2) {
		int opcion = 0, FilasT = 0, ColumnasT = 0, mat1[][] = null, contDatos = 0, aumento = 0, FilasF1 = 0,
				ColumnasF1 = 0;
		int mat2[][] = null;

		do {
			System.out.println(" ____");
			System.out.println("|  MENU COMBINACIONES          |");
			System.out.println("| 1. Tripleta.                 |");
			System.out.println("| 2. Matriz Forma 1.           |");
			System.out.println("| 3. Matriz Forma 2.           |");
			System.out.println("| 4. Suma de Matrices T+F1=F2. |");
			System.out.println("| 5. Salir.                    |");
			System.out.println("| Opcion:                      |");
			opcion = captura.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("|            MATRIZ TRIPLETA            |");
				System.out.println("| Ingrese numero de filas:              |");
				FilasT = captura.nextInt();
				System.out.println("| Ingrese numero de columnas:           |");
				ColumnasT = captura.nextInt();
				mat1 = new int[FilasT][ColumnasT];
				EntradaMatriz(FilasT, ColumnasT, mat1); // MATRIZ INPUT
				contDatos = CuentaDatosM(mat1, contDatos, FilasT, ColumnasT); // COUNT DATA IN MATRIZ
				MostrarMatriz(FilasT, ColumnasT, mat1); // SHOW THE ENTERED MATRIZ
				M4.DispersasTriplete(FilasT, ColumnasT, contDatos); // CREATE CLASS OBJECT
				M4.CrearTriple(mat1, M4.getMat(), FilasT, ColumnasT, M4.getDatos()); // CREATE TRIPLET MATRIZ
				System.out.println(" ");
				break;
			case 2:
				System.out.println("|            MATRIZ FORMA 1             |");
				System.out.println("| Ingrese numero de filas:              |");
				FilasF1 = captura.nextInt();
				System.out.println("| Ingrese numero de columnas:           |");
				ColumnasF1 = captura.nextInt();
				mat2 = new int[FilasF1][ColumnasF1];
				EntradaMatriz(FilasF1, ColumnasF1, mat2);
				MostrarMatriz(FilasF1, ColumnasF1, mat2);
				CF1.ConstruirMatriz(mat2, FilasF1, ColumnasF1);
				break;
			case 3:
				break;

			case 4:
				if (M4.getDatos() == 0 || CF1.getPunta() == null) {
					System.out.println("| Aun no has ingresado las dos matrices.");
					System.out.println(" ");
				} else {
					if (M4.getFilas() != CF1.getPunta().getFila() || M4.getColumnas() != CF1.getPunta().getColumna()) {
						System.out.println("| Las matrices tienen un tama�o diferente, no se pueden sumar.");
						System.out.println(" ");
					} else {
						COMF2.TriMasF1IguF2(M4, CF1, COMF2);

					}
				}
				break;
			}
		} while (opcion < 6 && opcion > 0);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	// MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN--MAIN
	public static void main(String[] args) {
		// DECLARACION DE VARIABLES
		int opcion = 0;

		do {
			System.out.println(" _______________________________________");
			System.out.println("| MENU PRINCIPAL MATRICES DISPERSAS     |");
			System.out.println("| 1.Representacion en Tripletas.        |");
			System.out.println("| 2.Representacion en Forma 1.          |");
			System.out.println("| 3.Representacion en Forma 2.          |");
			System.out.println("| 4.Combinaciones.                      |");
			System.out.println("| 5.Salir.                              |");
			System.out.println("| Opcion:                               |");
			opcion = captura.nextInt();
			System.out.println("|---------------------------------------|");
			switch (opcion) {
			case 1:
				MenuTriPrincipal();
				break;
			case 2:
				MenuPrinciF1();
				break;
			case 3:
				MenuPrinciF2();
				break;
			case 4:
				MenuCombi(M4,CF1,COMF2);
				break;
			}
		} while (opcion < 5 && opcion > 0);
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
}
