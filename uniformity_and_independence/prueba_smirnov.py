import math
import scipy.stats as stats
import scipy.stats
import numpy as np
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
        vectorNumeros = pd.read_excel('S:\\JuanB\\Semestre 8\\Modelos & Simulacion\\TrabajoOpcional\\datos.xlsx', sheet_name='smirnov').values.flatten()
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

vectorNumeros = None
while vectorNumeros is None or len(vectorNumeros) < 30:
    vectorNumeros = ingresoDeDatos()
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
vectorSumOi = []
vectorSx = []
for i in range(k):
    vectorIntervalos.append(0)
    vectorSumOi.append(0)
    vectorSx.append(0)


#Determino los Oi
vectorIntervalos = calcularOi(vectorIntervalos, tamañoIntervalo, vectorNumeros, k)

#Hallo Sumatoria de Oi
for i in range(k):
    vectorSumOi[i] = vectorIntervalos[i]
    for j in range(i):
        vectorSumOi[i] = vectorSumOi[i] + vectorIntervalos[j]

#Hallar S(x)
for i in range(k):
    vectorSx[i] = vectorSumOi[i] / n
    vectorSx[i] = round(vectorSx[i], 4)

#Hallar F(x)
vectorFx = []
vectorResta = [] #Vector para hallar Fx - Sx
aux = 1
for i in range(k):
    vectorFx.append(tamañoIntervalo * aux)
    vectorFx[i] = round(vectorFx[i], 4)
    aux = aux + 1
    vectorResta.append(vectorFx[i] - vectorSx[i]) #Hallar F(x) - S(x)
    vectorResta[i] = round(vectorResta[i], 4)
    if(vectorResta[i] < 0):
        vectorResta[i] = vectorResta[i] * - 1

#Hallar Dmax de prueba
dMaxPrueba = max(vectorResta)

# Cálculo del valor teórico Dmax
dMaxTeorico = scipy.stats.kstwobign.ppf(1 - alpha) / np.sqrt(n)
dMaxTeorico = round(dMaxTeorico, 4)

#Impresion de resultados: 
datos = [['n', n],
         ['Oi', vectorIntervalos],
         ['Sumatoria Oi', vectorSumOi],
         ['S(x)', vectorSx],
         ['F(x)', vectorFx],
         ['| F(x) - S(x) |', vectorResta],
         ['Dmax Prueba', dMaxPrueba],
         ['Alpha', str(alpha) + '%'],
         ['Dmax Teorico', dMaxTeorico]]

# Imprimir datos en una tabla:
print("Resultados:")
print(tabulate(datos, headers=['Variable', 'Valor'], tablefmt='grid'))
print("Conclusion: ")
# Imprimir conclusion:
if(dMaxPrueba > dMaxTeorico):
    print("Como el valor Damx de prueba (", dMaxPrueba, ") es mayor que el valor Dmax teorico (",
          dMaxTeorico, ") se RECHAZA la hipotesis nula.")
    print("Los números NO se distribuyen uniformemente de 0 a 1.")
else:
    print("Como el valor Dmax de prueba (", dMaxPrueba, ") es menor que el valor Dmax teorico (",
          dMaxTeorico, ") NO SE RECHAZA la hipotesis nula.")
    print("Los números se distribuyen uniformemente de 0 a 1.")

print() # Imprimir una nueva línea al final