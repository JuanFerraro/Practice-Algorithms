import time
inicio = time.time()
def find_the_parity_outlier(integers):
    """ pares = list(filter(lambda x: x % 2 == 0, integers))
    impares = list(filter(lambda x: x % 2 != 0, integers))

    return pares[0] if len(pares) == 1 else impares[0] """
    x = 0
    y = 0
    odds = 0
    evens = 0

    for i in range (len(integers)):
        if integers[i] % 2 == 0:
            evens += 1
            x = integers[i]
        else:
            odds += 1
            y = integers[i]
    
    if evens > odds: 
        return y 
    else: 
        return x
    
print('FIND PARITY OUTLIER')
print(find_the_parity_outlier([2, 4, 6, 8, 10, 3]))
fin = time.time()
tiempo_total = fin - inicio
print(f"Tiempo de ejecución: {tiempo_total} segundos")