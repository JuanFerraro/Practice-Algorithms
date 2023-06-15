import math
import scipy.stats as stats
import numpy as np
from tabulate import tabulate
import pandas as pd

#Metodo para determinar rachas 1(+) 0(-)
def determinarRachas(vectorNumeros):
    vectorAuxiliar = [] 
    for i in range(len(vectorNumeros)):
        if(vectorNumeros[i] > 0.5):
            vectorAuxiliar.append(1)
        else:
            vectorAuxiliar.append(0)
    return vectorAuxiliar

#Metodo para determinar la racha mayor
def determinarRachaMayor(vectorAuxiliar):
    rachaMayor = 0
    rachaActual = 1
    for i in range(len(vectorAuxiliar)-1):
        if(vectorAuxiliar[i] == vectorAuxiliar[i + 1]):
            rachaActual = rachaActual + 1
        else:
            if(rachaActual > rachaMayor):
                rachaMayor = rachaActual
            rachaActual = 1

    return rachaMayor

#Metodo para llenar vector de rachas
def calcularRachas(vectorAuxiliar, vectorRachas):
    rachaMayor = 0
    rachaActual = 1
    for i in range(len(vectorAuxiliar)-1):
        if(vectorAuxiliar[i] == vectorAuxiliar[i + 1]):
            rachaActual = rachaActual + 1
        else:
            vectorRachas[rachaActual - 1] = vectorRachas[rachaActual - 1] + 1 
            if(rachaActual > rachaMayor):
                rachaMayor = rachaActual
            rachaActual = 1
    vectorRachas[rachaActual - 1] = vectorRachas[rachaActual - 1] + 1 
    return vectorRachas

# Metodo para ingreso de datos:
def ingresoDeDatos():
    print("MENU")
    print("Elegir una opcion:")
    print("1. Importar datos desde excel.")
    print("2. Ingreso manualmente.")

    opcion = input("Ingrese una opcion: ")
    while opcion not in ["1", "2"]:
        print("Opcion invalida. Por favor ingrese 1 o 2.")
        opcion = input("Ingrese una opcion: ")
        print()
    print()  # Imprimir una nueva línea al final

    if opcion == "1":
        vectorNumeros = pd.read_excel('S:\\JuanB\\Semestre 8\\Modelos & Simulacion\\TrabajoOpcional\\datos.xlsx', sheet_name='media').values.flatten()
    elif opcion == "2":
        print("Ejemplo de ingreso de datos: 0,98;0,54;0,23;...")
        while True:
            entrada = input("Ingrese los números separados por punto y coma (;), con coma (,) como separador decimal: ")
            numeros = entrada.split(';')
            try:
                vectorNumeros = [float(numero.replace(',', '.')) for numero in numeros]
                break
            except ValueError:
                print("Error: ingresó un valor no numérico o con formato incorrecto. Por favor, ingrese solo valores numéricos separados por punto y coma (;) y coma (,) como separador decimal.")
            else:
                print("Opcion invalida. Por favor ingrese 1 o 2.")
    
    return vectorNumeros


# Variables a utilizar:
vectorNumeros = []  # vector con los numeros ingresados.
vectorAuxiliar = []  # vector auxiliar para contar las rachas.
vectorRachas = []  # vector auxiliar para contabilizar las rachas.
rachas = []  # vector para contabilizar rachas
n = 0  # Total de numeros ingresados
B = 0  # Cantidad total de rachas
Ub = 0  # Media de A
n1 = 0
n2 = 0

#Ingreso de datos
vectorNumeros = ingresoDeDatos()
n = len(vectorNumeros)

print()  # Imprimir una nueva línea al final

# Solicitar el alpha
alpha = input("Ingrese el alpha en %: ")
alpha = float(alpha)
alpha = alpha / 100

print()  # Imprimir una nueva línea al final

# Hallo n1 (mayores que 0,5) y n2(menores que 0,5)
# Agrego l vector auxiliar el numero y 0 o 1.
for i in range(n):
    if(float(vectorNumeros[i]) <= 0.5):
        n2 = n2 + 1
    else:
        n1 = n1 + 1

#Llenamiento de vector auxiliar
vectorAuxiliar = determinarRachas(vectorNumeros)
rachaMayor = determinarRachaMayor(vectorAuxiliar)

#Llenamiento de vector de rachas
for i in range(rachaMayor):
    vectorRachas.append(0)
vectorRachas = calcularRachas(vectorAuxiliar, vectorRachas)

#Hallar B
B = sum(vectorRachas)

# Hallo Ub
Ub = ( ( 2 * n1 * n2 ) / ( n1 + n2 ) ) + 1
Ub = round(Ub, 3)

# Hallo Varianza
n1 = float(n1)
n2 = float(n2)
varianza = (( 2 * n1 * n2 ) * (( 2 * n1 * n2 ) - n1 - n2 )) / ((( n1 + n2 )**2 ) * (n1 + n2 - 1 ))
varianza = round(varianza, 3)

# Hallo la Desviacion
desviacion = np.sqrt(varianza)
desviacion = round(desviacion, 3)

# Hallamos Z prueba
zPrueba = ( B - Ub ) / desviacion
zPrueba = round(zPrueba, 3)
if (zPrueba < 0):
    zPrueba = zPrueba * - 1

# Hallamos valor z para Z teorico
z = 1-(alpha/2)

# Hallamos el Z teorico
zTeorico = stats.norm.ppf(z)
zTeorico = round(zTeorico, 3)

# Imprimimos los datos
# Datos
datos = [['Rachas', vectorRachas],
         ['B', B],
         ['n1', n1],
         ['n2', n2],
         ['Ub', Ub],
         ['Varianza', varianza],
         ['Desviacion', desviacion],
         ['Zprueba', zPrueba],
         ['Alpha', str(alpha) + '%'],
         ['Z', z],
         ['Zteorico', zTeorico]]

# Imprimir datos en una tabla:
print("Resultados:")
print(tabulate(datos, headers=['Variable', 'Valor'], tablefmt='grid'))
print("Conclusion: ")

# Imprimir conclusion:
if(zPrueba > zTeorico):
    print("Como el Z prueba (", zPrueba, ") es mayor que es Z teorico (",
          zTeorico, ") se RECHAZA la hipotesis nula.")
    print("Los números NO se distribuyen uniformemente de 0 a 1.")
else:
    print("Como el Z prueba (", zPrueba, ") es menor que es Z teorico (",
          zTeorico, ") NO SE RECHAZA la hipotesis nula.")
    print("Los números se distribuyen uniformemente de 0 a 1.")

print() # Imprimir una nueva línea al final