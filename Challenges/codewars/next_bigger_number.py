""" Next bigger number with the same digits
    Create a function that takes a positive integer and returns 
    the next bigger number that can be formed by rearranging its digits. For example:
    12 ==> 21
    513 ==> 531
    2017 ==> 2071
    If the digits can't be rearranged to form a bigger number, return -1 
    (or nil in Swift, None in Rust):
    9 ==> -1
    111 ==> -1
    531 ==> -1
"""

def next_bigger(n):
    """
    Finds the next bigger number that can be formed by rearranging the digits of a given positive integer.

    Args:
        n (int): The positive integer for which the next bigger number is to be found.

    Returns:
        int: The next bigger number formed by rearranging the digits. If not possible, returns -1.
    """

    numbers = list(str(n))

    if len(numbers) <= 1:
        return -1

    i = len(numbers) - 2
    j = len(numbers) - 1

    while i >= 0:
        if numbers[i] >= numbers[i + 1]:
            i -= 1
        else:
            break

    if i == -1:
        return -1

    while numbers[j] <= numbers[i]:
        j -= 1

    numbers[i], numbers[j] = numbers[j], numbers[i]

    numbers[i + 1:] = sorted(numbers[i + 1:])

    next_num = int(''.join(numbers))

    if next_num > n:
        return next_num
    else:
        return -1


    
print(next_bigger(59884848459853))
print(next_bigger(20173))
print(next_bigger(2017))
print(next_bigger(111))
print(next_bigger(414))
print(next_bigger(3))

