""" Title: Number of trailing zeros of N!
    Write a program that will calculate the number of trailing zeros 
    in a factorial of a given number.
    N! = 1 * 2 * 3 *  ... * N
    Be careful 1000! has 2568 digits...
    Examples
    zeros(6) = 1
    # 6! = 1 * 2 * 3 * 4 * 5 * 6 = 720 --> 1 trailing zero
    zeros(12) = 2
    # 12! = 479001600 --> 2 trailing zeros 
"""

def zeros(n):
    if n == 0:
        return 0
    
    factorial = 1
    for i in range(2, n + 1):
        factorial *= i
    
    zeros_count = str(factorial).count("0")
    
    return zeros_count

print('Trailing Zeros:')
print(zeros(30))
