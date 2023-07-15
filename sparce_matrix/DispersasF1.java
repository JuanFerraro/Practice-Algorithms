package proyectodispersas;

import java.util.Scanner;

public class DispersasF1 {
	static Scanner captura=new Scanner(System.in);
	//ATTRIBUTES
	private NodoForma1 Punta=new NodoForma1();
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//CLASS BUILDER
	public DispersasF1() {
		this.Punta=null;
	}
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------
	//METHODS:
	//METHOD: BUILD LIST
	public void ConstruirMatriz(int mat[][],int filas,int columnas) {
		int mayor=0,i=0,j=0,k=0,columna=0,band=0;
		
		if(Punta==null) {
			NodoForma1 x=new NodoForma1();
			if(Punta==null) {
				Punta=x;
				x.setColumna(columnas);
				x.setFila(filas);
			}else {
				if(filas>columnas) 
					mayor=filas;
				else
					mayor=columnas;
				while(i<mayor) {									//CICLO PARA LOS REGISTROS CABEZA
					NodoForma1 p=new NodoForma1();
					NodoForma1 reco=new NodoForma1();
					p.setColumna(i);
					p.setFila(i);
					if(Punta.getLiga()==null) {
						Punta.setLiga(p);
						p.setLiga(Punta);
					}else {
						reco=Punta;									//NODO RECO PARA RECORRER LOS REGISTRO CABEZA
						while(reco.getLiga()!=Punta) {				//CICLO PARA UBICAR A RECO AL FINAL DE LOS RC E INSERTAR Y LIGAR AHÍ
							reco=reco.getLiga();
						}
						reco.setLiga(p);
						p.setLiga(Punta);
					}	
					i++;
				}													//FIN CICLO PARA REGISTROS CABEZA
				i=0;
				while(i<filas) {									
					j=0;
					while(j<columnas) {								//CICLO PARA AGREGAR LOS DATOS != DE 0 A LA LISTA
						if(mat[i][j]!=0) {					
							NodoForma1 nuevo=new NodoForma1();
							nuevo.setDato(mat[i][j]);
							nuevo.setColumna(j);
							nuevo.setFila(i);
							NodoForma1 r=new NodoForma1();					//NODO R PARA RECORRER LOS REGISTROS CABEZA
							r=Punta.getLiga();
							while(r.getLiga()!=Punta) {
								if(r.getFila()==i) {
									if(r.getLF()==null) {
										r.setLF(nuevo);				//INSERTO Y LIGO NUEVO NODO CON LOS DATOS
										nuevo.setLF(r);
									}
									else {
										NodoForma1 w=new NodoForma1();		//NODO W PARA RECORRER EN CASO DE QUE YA LA LISTA TENGA DATOS (LIGA FILA)
										w=r;
										while(w.getLF()!=r) {		//CICLO PARA UBICAR W AL FINAL DE LA LISTA
											w=w.getLF();
										}
										w.setLF(nuevo);				//INSERTO Y LIGO NUEVO NODO CON LOS DATOS
										nuevo.setLF(r);
									}
								}
								r=r.getLiga();
							}
						}
						j++;
					}
					i++;
				}
				NodoForma1 a=new NodoForma1();                                                        //RECORRE LOS REGISTROS CABEZA(RG)
				NodoForma1 b=new NodoForma1();								//RECORRE LAS LIGA FILAS (LF) DE CADA RG
				NodoForma1 aux=new NodoForma1();							//PARA POSICIONARLO EN X LUGAR EN BUSCA DE OTRO NODO CON = COLUMNA
				NodoForma1 p=new NodoForma1();
				NodoForma1 ante=new NodoForma1();
				a=Punta.getLiga();
                                p=Punta.getLiga();
                                while(p != Punta){
                                    
                                while(a != Punta){
                                    b=a.getLF();
                                    while(b != a){
                                    if(b.getColumna() == p.getColumna()){
                                        ante=b;
                                        p.setLC(ante);
                                        band=1;
                                    }
                                    b=b.getLF();
                                    }
                                    if(band == 1){                  
                                       a=Punta.getLiga();
                                       while(a != Punta)
                                       {
                                       b=a.getLF();
                                    while(b != a){
                                        if(b != ante){
                                       if(b.getColumna() == p.getColumna()){
                                           ante.setLC(b);
                                           ante=b;
                                              }  
                                        }
                                        b=b.getLF();
                                               }
                                    a=a.getLiga();
                                       }
                                    }else{
                                      a=a.getLiga();
                                    }
                                        }
                                    }
                                p=p.getLiga();
			}
			
		}else
			System.out.println("| Ya existe una matriz.");
	}


/////////////////////////SHOW OF TWO LIST
        public void MostrarForma1(DispersasF1 F1L1)
        {
           NodoForma1 a=new NodoForma1();
           NodoForma1 b=new NodoForma1();
           
           if(Punta == null){
               System.out.println("| DEBE INGRESAR UNA MATRIZ |");
           }else{
           a=Punta.getLiga();
           while(a != Punta){
               b=a;
               System.out.println("Datos De La Fila: "+b.getFila());
               b=b.getLF();
               while(b != a){
                   System.out.println("-> "+b.getDato());
                   b=b.getLF();
               }
               a=a.getLiga();
           }
           }
        }
        
        
        //SUM OF EACH ROW
        public void SumarCadaFilaForma1(DispersasF1 F1L1){
            int Acu=0;
        
           NodoForma1 a= new NodoForma1();                                  //NODO QUE RECORRE LAS CABEZAS DE LAS PUNTAS
           NodoForma1 b= new NodoForma1();                                  //NODO QUE RECORRE CADA FILA DE LAS CABEZAS DE LAS PUNTAS
           
           a=Punta.getLiga();
           
           while(a != Punta)
           {
               Acu=0;
               b=a.getLF();
                while(b != a)
                {
                   Acu=Acu + b.getDato();
                   b=b.getLF();
                }
                System.out.println("La Suma De La Fila: "+a.getFila()+"Es: "+Acu);
            a=a.getLiga();
           }
        }
        
        //SUM OF EACH COLUMN
        public void SumaCadaColumnaForma1(DispersasF1 F1L1){
            int Acu=0;
           NodoForma1 a= new NodoForma1();                                  //NODO QUE RECORRE LAS CABEZAS DE LAS PUNTAS
           NodoForma1 b= new NodoForma1();                                  //NODO QUE RECORRE CADA FILA DE LAS CABEZAS DE LAS PUNTA  
           NodoForma1 p= new NodoForma1();                                  //NODO QUE RECORRE LAS CABEZAS 1 POR 1 
           
           a=Punta.getLiga();
           p=Punta.getLiga();
           
           while(p != Punta)
           {
               Acu=0;
               a=Punta.getLiga();
                while(a != Punta)
                {
                    b=a.getLF();
                       while(b != a){
                           if(b.getColumna() == p.getColumna()){
                               Acu=Acu + b.getDato();
                           }
                             b=b.getLF();
                        }
                       a=a.getLiga();
                 }
                System.out.println("La Suma De La Columna: "+p.getColumna()+"Es: "+Acu);
                p=p.getLiga();
           }
        }
        
        //DELETE
        public void EliminarForma1(DispersasF1 F1L1){
            int Columna=0,Fila=0,Dato=0,band=0;
            NodoForma1 a;
            NodoForma1 b;
            NodoForma1 Ad;
            NodoForma1 An;
            
            System.out.print("| Ingrese La Fila De El Dato A Eliminar: ");
            Fila=captura.nextInt();
            System.out.print("| Ingrese La Columna De El Dato A Eliminar: ");
            Columna=captura.nextInt();
            System.out.print("| Ingrese El Dato A Eliminar: ");
            Dato=captura.nextInt();
            
            
            a=Punta.getLiga();
            while(a != Punta){
                b=a;
                An=b;
                b=a.getLF();
                Ad=b.getLF();
                while(b != a){
                    if(b.getFila() == Fila && b.getColumna() == Columna && b.getDato() == Dato){
                        An.setLF(Ad);
                        band=1;
                        //ELIMINAR b
                    }
                    b=b.getLF();
                }
                a=a.getLiga();
            }
            if(band == 0){
                System.out.println("| El Dato No Se Encuentra |");
            }
        }
        
        
}