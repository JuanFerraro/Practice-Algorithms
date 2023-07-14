import py_challenges

if __name__ == '__main__':
    print('Challenge #1: ')
    my_string = "Aca VA un StrING"
    test_capital_indexes = py_challenges.capital_indexes(my_string)
    print(f'-> Capital indexes: {my_string} -> {test_capital_indexes}')
    print('-' * 100)

    print('Challenge #2:')
    decimal = 44
    decimal_2 = 12
    test_decimal_to_binary = py_challenges.decimal_to_binary(decimal)
    test_decimal_to_binary_2 = py_challenges.decimal_to_binary(decimal_2)
    print(f'-> Decimal to Binary: {decimal} -> {test_decimal_to_binary}')
    print(f'-> Decimal to Binary: {decimal_2} -> {test_decimal_to_binary_2}')
    print('-' * 100)

    