
def add_binary(a,b):
    sum = a + b
    binary = []
    aux = sum
    
    while aux > 1:
        aux = aux / 2
        print(aux)
        binary.append(aux)
    
    return ''.join(binary.reverse())

print(add_binary(20,22))