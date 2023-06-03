import math
import scipy.stats as stats
from tabulate import tabulate
import pandas as pd

#Metodo para calcular los Oi de cada intervalo
def calcularOi(vectorIntervalos, tamañoIntervalo, vectorNumeros, k):
    limInferior = 0
    limSuperior = limInferior + tamañoIntervalo
    limSuperior = round(limSuperior, 4)

    for i in range(k):
        for j in range(len(vectorNumeros)):
            if(vectorNumeros[j] >= limInferior and vectorNumeros[j] < limSuperior):
                vectorIntervalos[i] = vectorIntervalos[i] + 1
        
        limInferior = limSuperior
        limSuperior = limSuperior + tamañoIntervalo
        limSuperior = round(limSuperior, 4)

    for j in range(len(vectorNumeros)):
            if(vectorNumeros[j] == 1):
                vectorIntervalos[k-1] = vectorIntervalos[k-1] + 1

    return vectorIntervalos

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
        vectorNumeros = pd.read_excel('S:\\JuanB\\Semestre 8\\Modelos & Simulacion\\TrabajoOpcional\\datos.xlsx', sheet_name='chi').values.flatten()
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

vectorNumeros = ingresoDeDatos()
#determino n (numero total de numeros)
n = len(vectorNumeros)

print()  # Imprimir una nueva línea al final

#Solicitar número de intervalos
k = input("Ingrese el número de intervalos K: ")
k = int(k)

# Solicitar el alpha
alpha = input("Ingrese el alpha en %: ")
alpha = float(alpha)
alpha = alpha / 100
print()  # Imprimir una nueva línea al final

#Hallar tamaño del intervalo
tamañoIntervalo = 1 / k
vectorIntervalos = []   #Vector para contabilizar Oi 
for i in range(k):
    vectorIntervalos.append(0)

#Determino los Oi
vectorIntervalos = calcularOi(vectorIntervalos, tamañoIntervalo, vectorNumeros, k)
#print(vectorIntervalos) #Mostrar la cantidad de Oi en cada intervalo

#Determinar Ei
Ei = n / k
Ei = round(Ei, 3)
vectorEi = []
for i in range(k):
    vectorEi.append(Ei)

#Hacer sumatoria y hallar chiPrueba:
chiDePrueba = 0
for i in range(len(vectorIntervalos)):
    chiDePrueba = chiDePrueba + ((vectorIntervalos[i]-Ei)**2/Ei)
chiDePrueba = round(chiDePrueba, 3)
#print(chiDePrueba)

#Hallar chiTeorico
valor1 = k - 1
valor2 = 1 - alpha
chiPorTeoria = stats.chi2.ppf(valor2, valor1)
chiPorTeoria = round(chiPorTeoria, 3)
#print(chiPorTeoria)

#Impresion de resultados: 
datos = [['n', n],
         ['Oi', vectorIntervalos],
         ['Ei', vectorEi],
         ['Valor ChiPrueba', chiDePrueba],
         ['Alpha', str(alpha) + '%'],
         ['Valor ChiTeorico', chiPorTeoria]]

# Imprimir datos en una tabla:
print("Resultados:")
print(tabulate(datos, headers=['Variable', 'Valor'], tablefmt='grid'))
print("Conclusion: ")

# Imprimir conclusion:
if(chiDePrueba > chiPorTeoria):
    print("Como el valor chi-Cuadrado de prueba (", chiDePrueba, ") es mayor que el valor chi-Cuadrado teorico (",
          chiPorTeoria, ") se RECHAZA la hipotesis nula.")
    print("Los números NO se distribuyen uniformemente de 0 a 1.")
else:
    print("Como el valor chi-Cuadrado de prueba (", chiDePrueba, ") es menor que el valor chi-Cuadrado teorico (",
          chiPorTeoria, ") NO SE RECHAZA la hipotesis nula.")
    print("Los números se distribuyen uniformemente de 0 a 1.")

print() # Imprimir una nueva línea al final