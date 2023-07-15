package roundRobin;
import java.util.*;

public class RoundRobin {
	
	static Scanner teclado = new Scanner(System.in);
	
	static void ingreso_datos(int cant_procesos, int[] vec_es, LinkedList<Proceso> proceso) {
		//Variables auxiliares:
		int cant_es=0, ned_q=0, longitud=0, tiempo=0;
		
		System.out.println("\nAlgoritmo de planificacion ROUND ROBIN. ");
		System.out.println("\nPor favor ingresar numeros enteros.");
		System.out.println("\nIngrese la cantidad de Procesos que llegan: ");
        cant_procesos = teclado.nextInt();
        if (cant_procesos <= 0) {
        	while (cant_procesos <=0) {
        		System.out.println("\nEl numero ingresado es erroneo.");
        		System.out.println("\nIngrese la cantidad de Procesos que llegan: ");
                cant_procesos = teclado.nextInt();
        	}
        }
        for (int i = 0; i < cant_procesos; i++) {
            System.out.println("\nIngrese el tiempo de llegada de P" + i + " en milisegundos");
            tiempo = teclado.nextInt();
            if (tiempo < 0) {
            	while (tiempo <=0) {
            		System.out.println("\nEl tiempo no puede ser negativo.");
            		System.out.println("\nIngrese el tiempo de llegada de P" + i);
                    tiempo = teclado.nextInt();
            	}
            }
            System.out.println("\nIngrese la cantidad de E/S de P" + i);
            cant_es = teclado.nextInt();
            if (cant_es < 0) {
            	while (cant_es <=0) {
            		System.out.println("\nNumero ingresado erroneo.");
            		System.out.println("\nIngrese la cantidad de E/S de P" + i);
                    cant_es = teclado.nextInt();
            	}
            }
            //Si el P no tiene E/S se le pregunta una sola vez:
            if (cant_es == 0) { 
                System.out.println("\nIngrese la necesidad de CPU en quantum de P"+ i );
                ned_q = teclado.nextInt();
                if (ned_q < 0) {
                	while (ned_q <=0) {
                		System.out.println("\nNumero ingresado erroneo.");
                		System.out.println("\nIngrese la necesidad de CPU en quantum de P" + i);
                        ned_q = teclado.nextInt();
                	}
                }
                vec_es = new int[1];
                vec_es[0] = ned_q;
            //Si P tiene E/S se le pregunta por el tiempo en E/S y por la NedQ al salirde esta E/S
            } else {
                longitud = 1 + cant_es * 2;
                vec_es = new int[longitud];
                for (int j = 0; j < vec_es.length; j++) {
                    if (j % 2 == 0) {
                        System.out.println("\nIngrese la necesidad de CPU en quantum de P" + i);
                        ned_q = teclado.nextInt();
                        if (ned_q < 0) {
                        	while (ned_q <=0) {
                        		System.out.println("\nNumero ingresado erroneo.");
                        		System.out.println("\nIngrese la necesidad de CPU en quantum de P" + i);
                                ned_q = teclado.nextInt();
                        	}
                        }
                        vec_es[j] = ned_q;
                    } else {
                        System.out.println("\nIngrese el tiempo que estará en E/S P" + i);
                        ned_q = teclado.nextInt();
                        if (ned_q < 0) {
                        	while (ned_q <=0) {
                        		System.out.println("\nNumero ingresado erroneo.");
                        		System.out.println("\nIngrese el tiempo que estará en E/S P" + i);
                                ned_q = teclado.nextInt();
                        	}
                        }
                        vec_es[j] = ned_q;
                    }
                }
            }
            proceso.addLast(new Proceso(String.valueOf(i), tiempo, vec_es));
        }
		
	}
	
	static void organizacion(int[] tiempos, LinkedList<Proceso> lista_proceso, LinkedList<Proceso> lista_ordenada) {
		//ORGANIZAMOS LOS PROCEOSO DE FORMA DE MENOR A MEYOR SEGUN SU TIEMPO DE 
        //LLEGADA Y LOS ENCOLAMOS EN LA COLA DE PROCESOS
		int contador, auxiliar;
        tiempos = new int[lista_proceso.size()];

        for (int i = 0; i < lista_proceso.size(); i++) {
            Proceso proceso2 = lista_proceso.get(i);
            tiempos[i] = proceso2.getTiempo_llegada();
        }
        Arrays.sort(tiempos);
        contador = tiempos.length;
        auxiliar = 0;
        while (contador != 0) {
            for (int i = 0; i < lista_proceso.size(); i++) {
                if (lista_proceso.get(i).getTiempo_llegada() == tiempos[auxiliar]) {
                	lista_ordenada.addLast(lista_proceso.get(i));
                }
            }
            auxiliar++;
            contador--;
        }//Cierra while
	}//Cierra funcion.
	
	static void termina_proceso(Proceso proceso_aux, LinkedList<Proceso> finalizados, LinkedList<Proceso> cola_procesos, int tiempo_ant,int tiempo_saliente) {
		proceso_aux.setEstado("OFF");					//Ya acabo el proceso
    	proceso_aux.setTiempo_ant(tiempo_ant);			//Envio tiempo ant
    	proceso_aux.setTiempo_salida(tiempo_saliente);	//Envio tiempo en que sale
        finalizados.addLast(proceso_aux);				//Envio a la lista resultado este proceso.
        cola_procesos.removeFirst();					//Quitamos de la lista ordenada al que ya le asignamos Q
        System.out.println(proceso_aux.toString());		//Imprimo el estado y tiempo de salida pa' Gantt
	}
	
	static void imprimir_resultados(LinkedList<Proceso> finalizados, int quantum) {
		int calculo_tiempos = 0;
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("\tRESULTADOS:");
        for (int i = 0; i < finalizados.size(); i++) {
            Proceso p_auxiliar = finalizados.getFirst();
            int vec_es3[] = p_auxiliar.getVec_es();
            int contador3 = 0;
            for (int j = 0; j < vec_es3.length; j++) {
                if (j % 2 != 0) {
                    contador3 = contador3 + vec_es3[j];
                }
            }
            int t_v = p_auxiliar.getTiempo_salida() - contador3 * quantum - p_auxiliar.getTiempo_llegada();
            calculo_tiempos = calculo_tiempos + t_v;
            System.out.println("TV P" + p_auxiliar.getPid() + ": " + t_v + " milisengudos");
            finalizados.addLast(p_auxiliar);
            finalizados.removeFirst();
        }       
        int vuelta_media = calculo_tiempos / finalizados.size();
        System.out.println("TV media: " + vuelta_media + " milisengudos\n");
        calculo_tiempos = 0;
        for (int i = 0; i < finalizados.size(); i++) {
            Proceso p_auxiliar2 = finalizados.getFirst();
            int tiempo_espera = p_auxiliar2.getPrimera_asig() - p_auxiliar2.getTiempo_llegada();
            calculo_tiempos = calculo_tiempos + tiempo_espera;
            System.out.println("W de P" + p_auxiliar2.getPid() + ": " + tiempo_espera + " milisengudos");
            finalizados.addLast(p_auxiliar2);
            finalizados.removeFirst();
        }
        int espera_media = calculo_tiempos / finalizados.size();
        System.out.println("W media: " + espera_media + " milisengudos\n");
	}
	
    public static void main(String[] args) {

        LinkedList<Proceso> lista_proceso = new LinkedList();	//Lista de como entran los procesos.
        LinkedList<Proceso> lista_ordenada = new LinkedList();	//Lista de procesos ordenada por tiempo de llegada.
        LinkedList<Proceso> cola_procesos = new LinkedList();	//Lista de la cola de procesos esperando por Q.
        LinkedList<Proceso> lista_espera = new LinkedList();	//Lista de procesos que estan en E/S
        LinkedList<Proceso> finalizados = new LinkedList();		//Lista de los procesos finalizados.
        
        //Declaración de variables Quantum por ejercicio en 100 e intercambio en 10 milisegundos cada uno.
        int quantum = 100, intercambio = 10, cant_procesos = 0, 
            	contador = 0, //Contador con tamaño del total de NCPU que tiene un proceso
            	tiempo_ant = 0, tiempo_siguiente = 0, tiempo_saliente = 0, calculo_tiempos = 0;
        int[] vec_es = null,    	//Vector para E/S y NCPU
        	  tiempos = null;		//Vector con los tiempos de llegada de los P
        
        //Llamamiento de funcion para ingresar los datos a la lista de procesos.
        ingreso_datos(cant_procesos, vec_es, lista_proceso);
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("\n\t\t DIAGRAMA DE GANTT\n");
        //Calculo de # veces que pasarán por la cola_procesos.
        for (int i = 0; i < lista_proceso.size(); i++) {
            Proceso proceso_aux = lista_proceso.get(i);
            int[] vec_es2 = proceso_aux.getVec_es();
            for (int j = 0; j < vec_es2.length; j++) {
                if (j % 2 == 0)
                {
                    contador = contador + vec_es2[j]; 
                }
            }
        }

        //Llamamiento funcion para organizar en orden los procesos.
        organizacion(tiempos, lista_proceso, lista_ordenada);
        cola_procesos.add(lista_ordenada.getFirst());
        lista_ordenada.removeFirst();

        int[] vec_es2 = {};
        int flag = 0;
        Proceso proceso_aux = new Proceso();
        proceso_aux = cola_procesos.getFirst(); //Creo proceso auxiliar que toma el primero de la cola
        proceso_aux.setPrimera_asig(0);			//Tiempo de primera asignacion
        proceso_aux.setBandera(Boolean.TRUE);
        vec_es2 = proceso_aux.getVec_es();	
        tiempo_saliente = tiempo_ant + quantum;	//Sale en 0 + Q por ser el primero
        for (int i = 0; i < contador * 2; i++) {
            if (i % 2 != 0) { //Si esto esta en E/S
                System.out.println("\t\t -intercambio- " + intercambio);
                tiempo_siguiente = tiempo_saliente + intercambio;
                tiempo_ant = tiempo_siguiente;
                tiempo_saliente = tiempo_ant + quantum;
                if (cola_procesos.size() == 0 && lista_ordenada.size() > 0 && (tiempo_siguiente) > lista_ordenada.getFirst().getTiempo_llegada()) 
                {//Si tamaño de cola == 0 Y lista ordenada por tiempo > 0 Y tiempo siguiente > que el tiempo de llegada del prox proceso
                    if (lista_ordenada.getFirst().getBandera() == false) {
                    	lista_ordenada.getFirst().setBandera(Boolean.TRUE);
                    	lista_ordenada.getFirst().setPrimera_asig(tiempo_siguiente);
                    }
                    cola_procesos.addLast(lista_ordenada.getFirst()); //Agregamos al final de la cola al que ya le asignamos 1Q
                    lista_ordenada.removeFirst(); //Quitamos de la lista ordenada al que ya le asignamos Q
                }
            } else {
                flag = 0;
                proceso_aux = cola_procesos.getFirst();	//Proceso auxiliar que toma el primero de la cola
                if (proceso_aux.getBandera() == false) 
                {
                	proceso_aux.setBandera(Boolean.TRUE);
                	proceso_aux.setPrimera_asig(tiempo_siguiente);
                }
                vec_es2 = proceso_aux.getVec_es();				//Vector aux == vector e/s del proceso.
                proceso_aux.setEstado("CPU");					//Le asigno Procesador
                proceso_aux.setTiempo_ant(tiempo_ant);			//Envio tiempo ant
                proceso_aux.setTiempo_salida(tiempo_saliente);	//Envio tiempo en que sale (L:176)
                System.out.println(proceso_aux.toString());		//Imprimo el estado y tiempo de salida pa' Gantt

                vec_es2[proceso_aux.getPos()] = vec_es2[proceso_aux.getPos()] - 1;

                if (vec_es2[proceso_aux.getPos()] == 0 && vec_es2.length > 1 && proceso_aux.getPos() == vec_es2.length - 1)
                	//Si vector e/s = 0 Y longitud de vector e/s > 1 Y es igual a longitud de vector -1 es porque es el ultimo (acabó)
                {
                	termina_proceso(proceso_aux, finalizados, cola_procesos, tiempo_ant, tiempo_saliente);
                	
                } else {
                				//En que momento de entrada de Q estamos
                    if (vec_es2[proceso_aux.getPos()] == 0 && vec_es2.length == 1) 
                    {	//Si vector e/s == 0 y su longitud es 1 => acabó
                    	termina_proceso(proceso_aux, finalizados, cola_procesos, tiempo_ant, tiempo_saliente);
                    } else {
                        if (vec_es2[proceso_aux.getPos()] == 0 && vec_es2.length > 1 && vec_es2[proceso_aux.getPos() + 1] > 0) {	
                        	//Si 
                        	proceso_aux.setEstado("E/S");					//Lo paso a E/S
                        	proceso_aux.setTiempo_ant(tiempo_ant);			//Envio tiempo anterior
                        	proceso_aux.setTiempo_salida(tiempo_saliente);	//Envio tiempo de salida
                        	proceso_aux.setTiempo_regreso(tiempo_saliente + intercambio + vec_es2[proceso_aux.getPos() + 1] * quantum); //Envio tiempo de regreso cuando acaba la E/S
                        	proceso_aux.setPos(proceso_aux.getPos() + 2);	//Le asigno la posicion donde está la NCPU cuando sale de e/s
                            System.out.println(proceso_aux.toString());		//Imprimo el estado (E/S) y tiempo de salida

                            lista_espera.addLast(proceso_aux);				//Cuando llega de e/s.
                            cola_procesos.removeFirst();					//Sale de la cola de procesos
                            if (cola_procesos.size() == 0 && (tiempo_saliente + intercambio) > lista_ordenada.getFirst().getTiempo_llegada()) {
                            	cola_procesos.addLast(lista_ordenada.getFirst());
                                lista_ordenada.removeFirst();
                            }
                        } else {
                        	//Revisar si algun proceso ha salido de su E/S
                            if (lista_espera.size() > 0) {
                                Proceso proceso_auxiliar;
                                for (int j = 0; j < lista_espera.size(); j++) {
                                	proceso_auxiliar = lista_espera.getFirst();
                                    if (proceso_auxiliar.getTiempo_regreso() < tiempo_saliente) {
                                    //Si el tiempo de regreso es menor al saliente del proceso actual -> vuelve
                                    	cola_procesos.addLast(proceso_auxiliar);
                                    	cola_procesos.addLast(proceso_aux);
                                    	lista_espera.removeFirst();
                                        cola_procesos.removeFirst();
                                        flag = 1; //Bandera en 1 para no removerlo de nuevo.
                                    } else {
                                    	lista_espera.addLast(proceso_auxiliar);
                                    	lista_espera.removeFirst();
                                    }
                                }//Cierra ciclo
                            }
                            if (lista_ordenada.size() > 0 && (tiempo_saliente + intercambio) > lista_ordenada.getFirst().getTiempo_llegada()) {
                            //Si en lista ordenada hay P y tiempo+intercambio es mayor que el siguiente tiempo de llegada del proceso siguiente.
                            	cola_procesos.addLast(lista_ordenada.getFirst());
                                lista_ordenada.removeFirst();
                                cola_procesos.addLast(proceso_aux);
                                cola_procesos.removeFirst();
                            } else {
                                if (flag == 0) {
                                	cola_procesos.addLast(proceso_aux);
                                	cola_procesos.removeFirst();
                                }
                            }
                        }
                    }
                }
            }//Cierra primer else
        }//Cierra ciclo
        imprimir_resultados(finalizados, quantum); 
    }//Cierra MAIN
}
