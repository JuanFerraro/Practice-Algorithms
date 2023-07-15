import java.text.DecimalFormat;
import java.util.Scanner;

public class random_numers {

    // Metodo para validar entrada de número entero
    static public int validacionEntero() {
        Scanner teclado = new Scanner(System.in);
        boolean entero = false;
        int numero = 0;
        do {
            if (teclado.hasNextInt()) {
                numero = teclado.nextInt();
                entero = true;
            } else {
                System.out.println("Valor ingresado no valido.");
                System.out.println("Por favor ingrese un número entero.");
                teclado.next();
            }
        } while (!entero);

        return numero;
    }

    // Metodo para validar la cantidad de cifras sean iguales
    public static int validacionCantidadCifras(int numero) {
        String cantCifras = Integer.toString(numero);
        int cifras = cantCifras.length();
        return cifras;
    }

    // Capturar caracteres centrales
    public static String caracteresCentrales(String str, int kCifras) {
        int mitad = str.length() / 2;
        int inicio = mitad - (kCifras / 2);
        String centrados = str.substring(inicio, inicio + kCifras);
        return centrados;
    }

    // Metodo de Cuadrados Centrales
    public static void cuadradosCentrales() {
        Scanner teclado = new Scanner(System.in);

        // *----------Ingreso de datos por usuario----------*
        System.out.println("--METODO DE CUADRADOS CENTRALES--");
        System.out.println("Ingrese la Semilla: ");
        int semilla = validacionEntero(); // Ingresa Semilla
        System.out.println("Ingrese la cantidad de números a generar");
        int aGenerar = validacionEntero(); // Ingresa cantidad de números aleatorios a generar.
        int kCifras = Integer.toString(semilla).length(); // Determino cantidad de cifras centrales.
        int M = (int) Math.pow(10, kCifras); // Hallo maximo de cifras que puedo llegar a generar

        System.out.println("--Números generados: ");
        for (int i = 1; i <= aGenerar; i++) { // Ciclo para generar numeros aleatorios hasta los pedidos o hasta que no
                                              // se pueda avanzar mas.
            long cuadrado = (long) Math.pow(semilla, 2); // Cuadrado se la semilla.
            String cadenaCuadrado = Long.toString(cuadrado); // Convierto a String el entero para poder contar sus
                                                             // caracteres.
            if (kCifras % 2 == 0) { // Si las cifras de la semilla son pares:
                if (cadenaCuadrado.length() % 2 != 0) { // Si las cifras del cuadrado no son pares.
                    cadenaCuadrado = cadenaCuadrado + 0; // Agrego 0 al FINAL del número.
                }
            } else { // Si las cifras de la semilla no son pares.
                if (cadenaCuadrado.length() % 2 == 0) { // Si las cifras del cuadrado son pares
                    cadenaCuadrado = cadenaCuadrado + 0; // Agrego 0 al FINAL del numero.
                }
            }
            String cifrasCentrales = caracteresCentrales(cadenaCuadrado, kCifras); // Hallo las cifras centrales del
                                                                                   // cuadrado con el metodo.
            int nextSemilla = Integer.valueOf(cifrasCentrales); // Tomo el valor numerico de las cifras centrales.
            double resultado = (float) nextSemilla / M; // Hallo el numero aleatorio
            System.out.println("X" + i + ": " + resultado); // Muestro el resultado.
            semilla = nextSemilla; // Le doy a la semilla el valor de la siguiente semilla.
            if (nextSemilla == 0) {// Por si el metodo no puede avanzar más por causa de ceros.
                System.out.println("Hasta acá puede avanzar el metodo :c.");
                break;
            }
        }
        System.out.println("Presione Enter para continuar...");
        teclado.nextLine();
        System.out.println("Continuando la ejecucion del programa...");
    }

    // Metodo de Productos Centrales
    public static void productosCentrales() {
        Scanner teclado = new Scanner(System.in);
        int semilla1 = 0;
        int semilla2 = 0;
        boolean bandera = false;

        // *----------Ingreso de datos por usuario----------*
        System.out.println("--METODO DE PRODUCTOS CENTRALES--");

        while (bandera == false) {
            System.out.println("Ingrese Semilla 1: ");
            semilla1 = validacionEntero(); // Ingresa Semilla 1
            System.out.println("Ingrese Semilla 2: ");
            semilla2 = validacionEntero(); // Ingresa Semilla 2
            if (validacionCantidadCifras(semilla1) != validacionCantidadCifras(semilla2)) {
                System.out.println("Las cifras no son iguales, reingrese de nuevo los numeros.");
                System.out.println("Presione Enter para continuar...");
                teclado.nextLine();
            } else {
                bandera = true;
            }
        }
        ;

        System.out.println("Ingrese la cantidad de números a generar");
        int aGenerar = validacionEntero(); // Ingresa cantidad de números aleatorios a generar.
        int kCifras = Integer.toString(semilla1).length(); // Determino cantidad de cifras centrales.
        int M = (int) Math.pow(10, kCifras); // Hallo maximo de cifras que puedo llegar a generar

        System.out.println("--Números generados: ");
        for (int i = 1; i <= aGenerar; i++) { // Ciclo para generar numeros aleatorios hasta los pedidos o hasta que no
                                              // se pueda avanzar mas.
            long producto = (long) semilla1 * semilla2; // Producto se las semillas.
            String cadenaProducto = Long.toString(producto); // Convierto a String el entero para poder contar sus
                                                             // caracteres.
            if (kCifras % 2 == 0) { // Si las cifras de la semilla son pares:
                if (cadenaProducto.length() % 2 != 0) { // Si las cifras del producto no son pares.
                    cadenaProducto = cadenaProducto + 0; // Agrego 0 al FINAL del número.
                }
            } else { // Si las cifras de la semilla no son pares.
                if (cadenaProducto.length() % 2 == 0) { // Si las cifras del producto son pares
                    cadenaProducto = cadenaProducto + 0; // Agrego 0 al FINAL del numero.
                }
            }
            String cifrasCentrales = caracteresCentrales(cadenaProducto, kCifras); // Hallo las cifras centrales del
                                                                                   // producto con el metodo.
            int nextSemilla = Integer.valueOf(cifrasCentrales); // Tomo el valor numerico de las cifras centrales.
            double resultado = (float) nextSemilla / M; // Hallo el numero aleatorio.
            System.out.println("X" + i + ": " + resultado); // Muestro el resultado.
            semilla1 = semilla2; // Le doy a la semilla el valor de la siguiente semilla.
            semilla2 = nextSemilla;
            if (nextSemilla == 0) { // Por si el metodo no puede avanzar más por causa de ceros.
                System.out.println("Hasta acá puede avanzar el metodo :c.");
                break;
            }
        }
        System.out.println("Presione Enter para continuar...");
        teclado.nextLine();
        System.out.println("Continuando la ejecucion del programa...");
    }

    // Principal
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("-----------------");
            System.out.println("Menu Principal");
            System.out.println("-----------------");
            System.out.println("1. Cuadrados Centrales");
            System.out.println("2. Productos Centrales");
            System.out.println("3. Salir");
            System.out.println("Opcion: ");
            try {
                opcion = Integer.parseInt(teclado.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println("Ha elegido la opcion 1: Cuadrados Centrales");
                        cuadradosCentrales();
                        break;
                    case 2:
                        System.out.println("Ha elegido la opcion 2: Productos Centrales");
                        productosCentrales();
                        break;
                    case 3:
                        System.out.println("Ha elegido la opcion 3: Salir");
                        break;
                    default:
                        System.out.println("La opcion elegida no es valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero valido");
                opcion = 0;
            }
        } while (opcion != 3);
    }
}
