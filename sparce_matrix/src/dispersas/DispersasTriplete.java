package dispersas;

import java.util.Scanner;

public class DispersasTriplete {
	static Scanner captura=new Scanner(System.in);
	//ATTRIBUTES
	private int filas;
	private int columnas;
	private int mat[][];
	private int datos;
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//CLASS BUILDER
	public void DispersasTriplete(int Filas,int Columnas,int Datos) {
		this.filas=Filas;
		this.columnas=Columnas;
		this.datos=Datos;
		mat=new int[Datos+1][3];
		mat[0][0]=Filas;
		mat[0][1]=Columnas;
		mat[0][2]=Datos;
	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//GETTERS AND SETTERS
	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public int[][] getMat() {
		return mat;
	}

	public void setMat(int[][] mat) {
		this.mat = mat;
	}
	
	public int getDatos() {
		return datos;
	}

	public void setDatos(int datos) {
		this.datos = datos;
	}

	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHODS:
	
	//METHOD: SHOW MATRIZ ON SCREEN
	public void MostrarMatriz(int mat[][],int datos) {
		int i=0,j=0;
		System.out.println("| MATRIZ EN TRIPLETA: ");
		while(i<=datos) {
			j=0;
			while(j<3) {
				System.out.print("|"+mat[i][j]);
				j++;
			}
			System.out.print("|");
			System.out.println("");
			i++;
		}
		System.out.println("");
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: CREATE TRIPLET
	public void CrearTriple(int mat[][],int m1[][],int filas,int columnas,int datos) {
		int i=0,j=0,k=1;
		
		while(i<filas) {
			j=0;
			while(j<columnas) {
				if(mat[i][j]!=0) {
					m1[k][0]=i;
					m1[k][1]=j;
					m1[k][2]=mat[i][j];
					k++;
				}
				j++;
			}
			i++;
		}
		System.out.println("| Matriz representada en tripleta creada con exito.");
		System.out.println(" ");
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: INCREASE SIZE (para aumentar el tama�o de la matriz tripleta utilizando una matriz auxiliar, se utiliza para insertar algun dato)
	public DispersasTriplete AumentarTamano(int aumento,int mat[][],int datos,DispersasTriplete M1) {
		int i=0,j=0,aux[][],nuevosDatos=0;
		DispersasTriplete MAUX=new DispersasTriplete();
		nuevosDatos=datos+aumento;
		MAUX.DispersasTriplete(M1.getFilas(), M1.getColumnas(), datos+aumento);
		aux=MAUX.getMat();
		while(i<=datos) {
			j=0;
			while(j<3) {
				aux[i][j]=mat[i][j];
				j++;
			}
			i++;
		}
		aux[0][2]=nuevosDatos;
		MAUX.setMat(aux);
		M1=MAUX;
		return M1;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: SUM OF ROWS
	public void SumarFila(int mat[][],int filas,int datos) {
		int k=1,sfila=0,suma=0,bandera=0;
		
		do {
			System.out.println("| Ingrese la fila que desea sumar: ");
			sfila=captura.nextInt();
			if(sfila>filas) {
				System.out.println("| La matriz no tiene la fila que has ingresado.");
				System.out.println(" ");
				bandera=0;
			}else {
				bandera=1;
				while(k<=datos) {
					if(sfila==mat[k][0]) {
						suma=suma+mat[k][2];
					}
					k++;
				}
				System.out.println("| La suma de la fila "+sfila+" es "+suma);
				System.out.println(" ");
			}
		}while(bandera==0);
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: SUM OF COLUMNS
	public void SumarColumna(int mat[][],int columnas,int datos) {
		int k=1,scolumnas=0,suma=0,bandera=0;
		
		do {
			System.out.println("| Ingrese la columna que desea sumar: ");
			scolumnas=captura.nextInt();
			if(scolumnas>columnas) {
				System.out.println("| La matriz no tiene la columna que has ingresado.");
				System.out.println(" ");
				bandera=0;
			}else {
				bandera=1;
				while(k<=datos) {
					if(scolumnas==mat[k][1]) {
						suma=suma+mat[k][2];
					}
					k++;
				}
				System.out.println("| La suma de la columna "+scolumnas+" es "+suma);
				System.out.println(" ");
			}
		}while(bandera==0);
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: INSERT BY POSITION 
	public void InsertarPosicion(int ndfila,int ndcolumna,int nuevodato,int mat[][],DispersasTriplete M1) {
		int k=1,fin=0,aux=0,controlador=0,band=0;
		controlador=M1.getDatos()-1;
		fin=controlador;
		while(k<=controlador && band==0) {
			if(ndfila==mat[k][0]) {
				if(ndcolumna<mat[k][1]) {
					aux=k-1;
					while(fin>aux) {
						mat[fin+1][0]=mat[fin][0];
						mat[fin+1][1]=mat[fin][1];
						mat[fin+1][2]=mat[fin][2];
						fin--;
					}
				}else {
					if(ndcolumna>mat[k][1]) {
						while(ndcolumna>mat[k][1] && ndfila==mat[k][0]) {
							k++;
						}
						while(fin>k-1) {
							mat[fin+1][0]=mat[fin][0];
							mat[fin+1][1]=mat[fin][1];
							mat[fin+1][2]=mat[fin][2];
							fin--;
						}
					}
				}
				mat=Insertar(mat,k,ndfila,ndcolumna,nuevodato);											
				M1.setMat(mat);
				band=1;
			}else {
				if(ndfila<mat[k][0]) {			
					while(fin>k-1) {
						mat[fin+1][0]=mat[fin][0];
						mat[fin+1][1]=mat[fin][1];
						mat[fin+1][2]=mat[fin][2];
						fin--;
					}
					mat=Insertar(mat,k,ndfila,ndcolumna,nuevodato);											
					M1.setMat(mat);
					band=1;
				}
			}
			k++;
		}
		if(band==0) {
				mat=Insertar(mat,k,ndfila,ndcolumna,nuevodato);						
				M1.setMat(mat);
		}
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//SUBMETHOD: INSERT (Utilizado para no escribir las 4 veces respectivas estas lineas en el Insertar por Posicion)
	public int[][] Insertar(int mat[][],int k,int ndfila,int ndcolumna,int nuevodato){
		mat[k][0]=ndfila;							
		mat[k][1]=ndcolumna;						
		mat[k][2]=nuevodato;
		return mat;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: DELETE BY DATA 
	public void EliminarDato(int delim,DispersasTriplete M1,int mat[][]) {
		int k=1,contde=0,c=0,fin=0,nuevosDatos=0;
		int aux[][];
		while(k<=M1.getDatos()) {
			fin=M1.getDatos();
			if(delim==mat[k][2]) {
				mat[k][0]=0;							//LES DAMOS EL VALOR DE CERO Y MOVEMOS LOS DEMAS DATOS UNA FILA ARRIBA.
				mat[k][1]=0;
				mat[k][2]=0;
				contde++;
				c=k;
				while(c<fin) {
					mat[c][0]=mat[c+1][0];
					mat[c][1]=mat[c+1][1];
					mat[c][2]=mat[c+1][2];
					c++;
				}
				mat[fin][0]=0;					
				mat[fin][1]=0;
				mat[fin][2]=0;
			}
			k++;
			if(mat[k-1][2]==delim) {
				k--;
			}
		}
		nuevosDatos=M1.getDatos()-contde;
		aux=new int[nuevosDatos][3];
		aux=mat;
		aux[0][2]=nuevosDatos;
		M1.setMat(aux);
		M1.setDatos(nuevosDatos);
		System.out.println("| Eliminacion exitosa.");
		System.out.println("| Se han elimidado "+contde+" datos.");
		System.out.println(" ");
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: REBUILD MATRIZ
	public void Reconstruir(int mat[][],DispersasTriplete M1) {
		int reconstruida[][],k=1,i=0,j=0;
		reconstruida=new int [mat[0][0]][mat[0][1]];
		
		while(k<=M1.getDatos()) {
			reconstruida[mat[k][0]][mat[k][1]]=mat[k][2];
			k++;
		}
		
		System.out.println("");
		System.out.println("|MATRIZ RECONSTRUIDA: ");
		while(i<filas) {
			j=0;
			while(j<columnas) {
				System.out.print("|  "+reconstruida[i][j]);
				System.out.print("  |");
				j++;
			}
			System.out.println("");
			i++;
		}
		System.out.println(" ");
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: SUM MAIN DIAGONAL
	public void DiagonalPrincipal(DispersasTriplete M1) {
		int mat[][]=M1.getMat(),k=1,suma=0;
		while(k<=M1.getDatos()) {
			if(mat[k][0]==mat[k][1])
				suma=suma+mat[k][2];
			k++;
		}
		System.out.println("| El resultado de la suma de la diagonal principal es: "+suma);
		System.out.println(" ");
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: SUM SECONDARY DIAGONAL 
	public void DiagonalSecundaria(DispersasTriplete M1) {
		int mat[][]=M1.getMat(),k=1,i=0,suma=0,j=1,datos=M1.getDatos(),bandera=0;
		
		while(i<mat[0][0]) {
			k=1;
			while(k<=datos) {
				if(mat[k][0]==i && mat[k][1]==mat[0][1]-j) {
					suma=suma+mat[k][2];
					j++;
					bandera=1;
				}
				k++;
			}
			i++;
			if(bandera==0) {
				j++;
			}
			bandera=0;
		}
		
		System.out.println("| La suma de la diagonal secundaria es: "+suma);
		System.out.println(" ");
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//METHOD: SUM OF TWO MAT
	public void SumaDosMatrices(DispersasTriplete M1,DispersasTriplete M2,DispersasTriplete M3) {
		int aumento=1,k=1,h=1,s=1,fila=0,colum=0,dato=0,datos=0,bandera=0;
		M3.DispersasTriplete(M1.getFilas(), M1.getColumnas(), 0);
		int mat1[][]=M1.getMat(),mat2[][]=M2.getMat(),mat3[][];
		mat3=new int[20][3];
		mat3[0][0]=mat1[0][0];
		mat3[0][1]=mat1[0][1];
		
		while(k<=M1.getDatos()) {
			fila=mat1[k][0];
			colum=mat1[k][1];
			h=1;
			while(h<=M2.getDatos() && bandera==0) {
				if(fila==mat2[h][0] && colum==mat2[h][1]) {
					dato=mat1[k][2]+mat2[h][2];
					datos++;
					bandera=1;
					mat3[s][0]=fila;
					mat3[s][1]=colum;
					mat3[s][2]=dato;
					s++;
				}
				h++;
			}
			if(bandera==0) {
				dato=mat1[k][2];
				datos++;
				mat3[s][0]=fila;
				mat3[s][1]=colum;
				mat3[s][2]=dato;
				s++;
			}
			bandera=0;
			k++;
		}
		M3.setDatos(datos);
		mat3[0][2]=datos;
		M3.setMat(mat3);
		h=1;
		bandera=0;
		while(h<=M2.getDatos()) {
			fila=mat2[h][0];
			colum=mat2[h][1];
			dato=mat2[h][2];
			k=1;
			mat3=M3.getMat();
			while(k<=M3.getDatos()) {
				if(fila==mat3[k][0] && colum==mat3[k][1]) {
					bandera=1;
				}
				k++;
			}
			if(bandera==0) {
				M3=M3.AumentarTamano(aumento, M3.getMat(), M3.getDatos(), M3);
				M3.InsertarPosicion(fila, colum, dato, M3.getMat(), M3);
			}
			h++;
			bandera=0;
		}
		M3.MostrarMatriz(M3.getMat(),M3.getDatos());
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	
}
