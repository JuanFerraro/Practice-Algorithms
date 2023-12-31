"""Challenge #1:
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

"""Challenge #2: 
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

""" Challenge #3: 
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

""" Challenge #4:
Bracket Combinations
Have the function BracketCombinations(num) 
read num which will be an integer greater than or equal to zero, 
and return the number of valid combinations that can be formed with num pairs of parentheses. 
For example, if the input is 3, then the possible combinations of 3 pairs of parenthesis, 
namely: ()()(), are ()()(), ()(()), (())(), ((())), and (()()). 
There are 5 total combinations when the input is 3, so your program should return 5. """

def bracket_combinations(num):
    total = 0
    def pair_maker(a, c=0):
        nonlocal total
        if a == 0 and c == 0:
            total += 1
        if a > 0:
            pair_maker(a - 1, c + 1)
        if c > 0:
            pair_maker(a, c - 1)
    pair_maker(num)
    return total

# Using example
resultado = bracket_combinations(3)
print(resultado)
print('***************************************************************************************')

""" Challenge #5:
Find Intersection 
read the array of strings stored in strArr which will contain 2 elements: 
the first element will represent a list of comma-separated numbers sorted in ascending order, 
the second element will represent a second list of comma-separated numbers (also sorted). 
Your goal is to return a comma-separated string containing the numbers 
that occur in elements of strArr in sorted order. If there is no intersection, 
return the string false.
"""
def find_intersection(strarray):
    output = ''

    str_1 = strarray[0].split(',')
    str_2 = strarray[1].split(',')
    
    for i in range (len(str_1)):
        for j in range (len(str_2)):
            if str_1[i] == str_2[j]:
                output = output + str_1[i] + ','
    output = output[:-1]
    output = output.replace(' ', '')

    return output

# Using example
print(find_intersection(["1, 3, 4, 7, 13", "1, 2, 4, 13, 15"]))
print('***************************************************************************************')

""" Challenge #6:
Question Marks
take the str string parameter, which will contain single digit numbers, 
letters, and question marks, 
and check if there are exactly 3 question marks between every pair of two numbers that add up to 10. 
If so, then your program should return the string true, 
otherwise it should return the string false. 
If there aren't any two numbers that add up to 10 in the string, 
then your program should return false as well. """

def question_marks(str):
    output = False
    pos_num_1 = -1
    pos_num_2 = -1
    q_marks = 0
    flag = 0

    for i in range (len(str)):
        if pos_num_1 != -1 and str[i].isdigit() == True:
            pos_num_2 = i
        elif str[i].isdigit() == True:
            pos_num_1 = i

        if pos_num_1 != -1 and pos_num_2 != -1:
            if int(str[pos_num_1]) + int(str[pos_num_2]) == 10 and flag == 0:
                for j in range (pos_num_1, pos_num_2):
                    if str[j] == '?':
                        q_marks = q_marks + 1
                if q_marks == 3:
                    output = True  
                else:
                    flag = 1
                    output = False  
            pos_num_1 = pos_num_2
            pos_num_2 = -1
            q_marks = 0

    return output

# Using example
print(question_marks("9???1???9??1???9"))
print(question_marks("aa6?9"))
print(question_marks("acc?7??sss?3rr1??????5"))
print(question_marks("5??aaaaaaaaaaaaaaaaaaa?5?5"))
print('***************************************************************************************')

""" Challenge #7:
First Reverse
take the str parameter being passed and return the string in reversed order. 
For example: if the input string is "Hello World and Coders" 
then your program should return the string sredoC dna dlroW olleH. """

def first_reverse(str):
    reverse = ''
    long = (len(str))

    for i in range (long):
        reverse = reverse + str[long - 1 - i]
    
    return reverse

print(first_reverse('Hola Mundo'))
print('***************************************************************************************')

""" Challenge #7:
Tree Constructor
take the array of strings stored in strArr, which will contain pairs of integers 
in the following format: (i1,i2), where i1 represents a child node in a tree 
and the second integer i2 signifies that it is the parent of i1.
For example: if strArr is ["(1,2)", "(2,4)", "(7,2)"], 
which you can see forms a proper binary tree. 
Your program should, in this case, return the string true because a valid binary tree can be formed. 
If a proper binary tree cannot be formed with the integer pairs, 
then return the string false. All of the integers within the tree will be unique, 
which means there can only be one node in the tree with the given integer value. """

def tree_constructor(strArr):
    
    parents = [int(s.split(',')[1].replace(')', '')) for s in strArr]
    
    for i in range (len(parents)):
        childs = 0
        for j in range (len(parents)):
            if parents[i] == parents[j]:
                childs = childs + 1
        if childs > 2:
            return False

    return True

print(tree_constructor(["(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"]))
print(tree_constructor(["(5,6)", "(6,3)", "(2,3)", "(12,5)"]))
print(tree_constructor(["(10,20)"]))
print('***************************************************************************************')
