"""Challenge #1
Capital indexes
Write a function named capital_indexes. The function takes a single parameter, which is a string. 
Your function should return a list of all the indexes in the string that have capital letters.
For example, calling capital_indexes("HeLlO") should return the list [0, 2, 4]."""

def capital_indexes(my_string):
    my_list = []
    for i in range (len(my_string)):
        if my_string[i].isupper():
            my_list.append(i)
    return my_list

"""Challenge #2 
Convert a decimal number into binary
Write a function in Python that accepts a decimal number and returns the equivalent binary number. 
To make this simple, the decimal number will always be less than 1,024, 
so the binary number returned will always be less than ten digits long """

def decimal_to_binary(decimal: int):
    if decimal == 0:
        return "0"  
    binary = ""
    while decimal > 0:
        binary = str(decimal % 2) + binary
        decimal //= 2 # Division entera, redondea hacia abajo
    return binary

""" Challenge #3 
Caesar Cipher
Create a function that can encrypt and decrypt messages using the Caesar cipher. 
The Caesar cipher is a type of substitution cipher in which each letter in the original text 
is shifted a certain number of positions to the right in the alphabet.
For example, if the offset is 3, "A" becomes "D", "B" becomes "E", and so on. """

def caesar_cipher(text):
    alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    #for i, letter in enumerate(text):
    i = 0
    cipher_text = ''
    while i < len(text):
        letter = text[i]
        if not letter.isupper():
            letter = letter.upper()

        for index, x in enumerate(alphabet):
            if letter == ' ':
                cipher_text += ' '
                break
            elif x == letter:
                cipher_letter = index + 3
                if cipher_letter >= len(alphabet):
                    cipher_letter = cipher_letter - len(alphabet) 
                cipher_text = cipher_text + alphabet[cipher_letter]
                break
        i += 1
    return cipher_text

    