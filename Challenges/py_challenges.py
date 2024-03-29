
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
print('Bracket Combinations')
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
def find_intersection(strArr):
    
    list1 = [int(num) for num in strArr[0].split(",")]
    list2 = [int(num) for num in strArr[1].split(",")]

    set1 = set(list1)
    set2 = set(list2)
    
    intersection = set1.intersection(set2)

    if intersection:

        result = ",".join(map(str, sorted(intersection)))
        return result
    else:
        return "false"

# Using example
print('Find Intersection:')
print(find_intersection(["1, 3, 4, 7, 13", "1, 2, 4, 13, 15"]))
print(find_intersection(["1, 5, 6, 7, 10, 11, 12", "5, 6, 8, 11, 17"]))
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
print('Question Marks:')
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

print('First Reverse:')
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

print('Tree Constructor:')
print(tree_constructor(["(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"]))
print(tree_constructor(["(5,6)", "(6,3)", "(2,3)", "(12,5)"]))
print(tree_constructor(["(10,20)"]))
print('***************************************************************************************')

""" Challenge #8:
Bracket Matcher
take the str parameter being passed and return 1 if the brackets are correctly matched 
and each one is accounted for. Otherwise return 0. For example: if str is "(hello (world))", 
then the output should be 1, but if str is "((hello (world))" 
the the output should be 0 because the brackets do not correctly match up. 
Only "(" and ")" will be used as brackets. If str contains no brackets return 1. """

def bracket_matcher(str):
    """ return 1 if str.count("(") == str.count(")") else 0 """
    brackets = ''
    open = 0
    close = 0

    for i in range (len(str)):
        if str[i] == '(' or str[i] == ')':
            brackets = brackets + str[i]
    
    if len(brackets) == 0:
        return 1

    for i in range (len(brackets)):
        if brackets[i] == '(':
            open = open + 1
        else:
            close = close + 1
        
        if close > open:
            return 0

    if open != close:
        return 0

    return 1

print("Bracket Matcher:")
print(bracket_matcher("(coder)(byte))"))
print(bracket_matcher("(c(oder)) b(yte)"))
print(bracket_matcher("dogs and cats"))
print(bracket_matcher("the color re(d))()(()"))
print('********************************************************************************************')

""" Challenge #9:
Jaden Casing Strings:
Your task is to convert strings to how they would be written by Jaden Smith. 
The strings are actual quotes from Jaden Smith, 
but they are not capitalized in the same way he originally typed them.
Not Jaden-Cased: "How can mirrors be real if our eyes aren't real"
Jaden-Cased:     "How Can Mirrors Be Real If Our Eyes Aren't Real" """

def jaden_casing_strings(noStrJaden):
    str_jaden = ''

    for i in range (len(noStrJaden)):
        if noStrJaden[i].isalpha() and noStrJaden[i - 1] == ' ':
            str_jaden += noStrJaden[i].upper()
        elif noStrJaden[i].isalpha() and  i == 0:
            str_jaden += noStrJaden[i].upper()
        else:
            str_jaden += noStrJaden[i].lower()

    return str_jaden

print('Jaden Casing Strings')
print(jaden_casing_strings("another tweet just for 4un"))
print(jaden_casing_strings(" a normal non jaden tweet"))
print(jaden_casing_strings("SOmetHing tO Eat"))
print('********************************************************************************************')

""" Challenge #10:
Descending Order
Your task is to make a function that can take any non-negative integer as an argument 
and return it with its digits in descending order. 
Essentially, rearrange the digits to create the highest possible number.
Example
Input: 42145 Output: 54421 """

def descending_order(num):
    
    str_num = str(num)
    str_num = sorted(str_num, reverse=True)

    return int(''.join(map(str, str_num)))

print('Descending Order')
print(descending_order(12873))
print('********************************************************************************************')

""" Challenge # 11
Find The Parity Outlier
You are given an array (which will have a length of at least 3, but could be very large) 
containing integers. The array is either entirely comprised of odd integers 
or entirely comprised of even integers except for a single integer N. 
Write a method that takes the array as an argument and returns this "outlier" N. """

def find_the_parity_outlier(integers):
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
print('************************************************************************************')

""" Challenge #12
Camel case
Complete the method/function so that it converts dash/underscore delimited words into camel casing. 
The first word within the output should be capitalized only 
if the original word was capitalized (known as Upper Camel Case, also often referred to as Pascal case). 
The next words should be always capitalized. """

def to_camel_case(text):

    if len(text) > 0:
        text = list(text)
        camel_text = ''
        for i in range (len(text)):
            if text[i] == ' ' or text[i] == '_' or text[i] == '-':
                text[i + 1] = text[i + 1].upper()
            else:
                camel_text += text[i]
        return camel_text
    return ""

print('Camel Case')
print(to_camel_case('to_camel-Case'))
print(to_camel_case('A-pippi-Is Hungry'))
print('************************************************************************************')

""" Challenge #13:
Isomorphic
Two strings a and b are called isomorphic if there is a one to one mapping possible 
for every character of a to every character of b. And all occurrences of every character 
in a map to same character in b.
In this kata you will create a function that return True if two given strings are isomorphic to each other, 
and False otherwise. Remember that order is important. """

def isomorph(a, b):

    positions_a = []
    positions_b = []

    if len(a) == len(b):
        for i in range(len(a)):
            x = a.count(a[i])
            y = b.count(b[i])
            if x == y:
                for j in range (len(a)):
                    if a[j] == a[i]:
                        positions_a.append(j)
                    if b[j] == b[i]:
                        positions_b.append(j)
                if positions_a != positions_b:
                    return False
            else: 
                return False
            
        return True
                    
    return False

print('Isomorphic')
print(isomorph("SEE", "SAW"))
print('************************************************************************************')

""" Challenge #14:
Count the number of Duplicates
Write a function that will return the count of distinct case-insensitive alphabetic characters 
and numeric digits that occur more than once in the input string. 
The input string can be assumed to contain only alphabets (both uppercase and lowercase) 
and numeric digits. """

def duplicate_count(text):
    
    aux_text = []
    count = 0

    for i in range (len(text)):
        x = text[i]
        if not x in aux_text and not x.upper() in aux_text:
            aux_text.append(x)
            aux_text.append(x.upper())
            x_times = 0
            for j in range (len(text)):
                if x == text[j] or x.upper() == text[j].upper():
                    x_times += 1
            if x_times > 1:
                count += 1
    
    return count

print('Duplicate Count')
print(duplicate_count("abcdeaa"))
print(duplicate_count("Indivisibilities"))
print(duplicate_count("abcdeaB"))
print('************************************************************************************')

""" Challenge #15:
Binary
Implement a function that adds two numbers together and returns their sum in binary. 
The conversion can be done before, or after the addition.
The binary number returned should be a string. """

def add_binary(a,b):
    """_takes two integers and returns their binary sum in string_

    Args:
        a (_int_): _number #1_
        b (_int_): _number #2_

    Returns:
        _str_: _binary number of the sum_
    """
    sum = a + b
    binary = []
    aux = sum
    
    while aux >= 1:
        if aux % 2 != 0 or aux == 1:
            binary.append("1")
        else:
            binary.append("0")
        aux = aux // 2
    
    return ''.join(reversed(binary))

print('Binary Add')
print(add_binary(51,12))
print(add_binary(1,1))
print('************************************************************************************')

""" Challenge #16:
ATM Pin 
ATM machines allow 4 or 6 digit PIN codes and
PIN codes cannot contain anything but exactly 4 digits or exactly 6 digits.
If the function is passed a valid PIN string, return true, else return false. """

def validate_pin(pin):
    """_Takes a str pin and return true or false if it contains exactly 4 or 6 digits_

    Args:
        pin (_str_): _string with the code_

    Returns:
        _boolen_: _True if its valid and False if its invalid_
    """
    if len(pin) == 4 or len(pin) == 6:
        for i in range (len(pin)):
            if not pin[i].isdigit():
                return False
        return True
    else:
        return False
    
print('ATM PIN')
print(validate_pin("1010"))
print(validate_pin("1a10"))
print(validate_pin("131000"))
print('************************************************************************************')

""" Challenge #17:
Factory Printer
In a factory a printer prints labels for boxes. 
For one kind of boxes the printer has to use colors which, 
for the sake of simplicity, are named with letters from a to m.
the colors used by the printer are recorded in a control string. 
For example a "good" control string would be aaabbbbhaijjjm meaning that the printer used 
three times color a, four times color b, one time color h then one time color a...
Sometimes there are problems: lack of colors, technical malfunction and 
a "bad" control string is produced e.g. aaaxbbbbyyhwawiwjjjwwm with letters not from a to m.
You have to write a function printer_error which given a string will return 
the error rate of the printer as a string representing a rational whose numerator 
is the number of errors and the denominator the length of the control string. 
Don't reduce this fraction to a simpler expression.
The string has a length greater or equal to one and contains only letters from ato z. """

def printer_error(s):
    """_Takes a string and return how many invalid letters are there_

    Args:
        s (_str_): _string of printed colors_

    Returns:
        _str_: _fraction of errors_
    """
    letters = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"]
    errors = 0
    output = ''
    
    for i in range (len(s)):
        if not s[i] in letters:
            errors += 1
    
    output = output + str(errors) + "/" + str(len(s))
    
    return output

print('Factory Printer')
print(printer_error("aaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbmmmmmmmmmmmmmmmmmmmxyz"))
print(printer_error("kkkwwwaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbmmmmmmmmmmmmmmmmmmmxyzuuuuu"))
print('************************************************************************************')

""" Challenge #18:
Tribonacci Sequence
As the name may already reveal, it works basically like a Fibonacci, 
but summing the last 3 (instead of 2) numbers of the sequence to generate the next.
Well, you may have guessed it by now, but to be clear: 
you need to create a fibonacci function that given a signature array/list, 
returns the first n elements - signature included of the so seeded sequence.
Signature will always contain 3 numbers; n will always be a non-negative number; 
if n == 0, then return an empty array. """

def tribonacci(signature, n):
    """_Takes a signature and and integer n and generate the TRIbonacci sequence n times_

    Args:
        signature (_arr_): _array of integers to start the sequence_
        n (_int_): _number of required sequence_

    Returns:
        _arr_: _sequence array_
    """
    sequence = signature
    
    if n == 0:
        return []
    else:      
        for i in range (n - 3):
            sequence.append(sequence[i] + sequence[i + 1] + sequence[i + 2])
        
        return sequence[:n]

print("Tribonacci")
print(tribonacci([1, 1, 1], 10))
print(tribonacci([1, 1, 1], 1))
print(tribonacci([300, 200, 100], 0))
print('************************************************************************************')

""" Challenge #19:
Equal Sides Of An Array
You are going to be given an array of integers. Your job is to take that array and 
find an index N where the sum of the integers to the left of N 
is equal to the sum of the integers to the right of N. 
If there is no index that would make this happen, return -1. 
Index 0 is the place where the left side and right side are equal.  """

def find_even_index(arr):
    """_takes an array and 
        find an index N where the sum of the int to the left of N 
        is equal to the sum of the int to the right of N_

    Args:
        arr (_array_): _integers array_

    Returns:
        _int_: _index when the sums are equal or -1 if it doesnt exist_
    """

    total_sum = sum(arr)
    left_sum = 0

    for i, num in enumerate(arr):
        total_sum -= num

        if left_sum == total_sum:
            return i

        left_sum += num

    return -1
            
    
print("Equal Sides of an Array:")
print(find_even_index([-3,2,1,0]))
print(find_even_index([1,2,3,4,3,2,1]))
print(find_even_index([0,0,0,0,0]))
print('************************************************************************************')

""" Challenge #20:
Roman Numeral
Create a function taking a positive integer between 1 and 3999 (both included) 
as its parameter and returning a string containing 
the Roman Numeral representation of that integer."""

def roman_representation(n):
    """_Takes a positive integer and return a string with the
        Roman numeral representation of that integer_

    Args:
        n (_int_): _integer to representate in Roman Numeral_

    Returns:
        _str_: _Roman Numeral Representation of that integer_
    """

    if n >= 1 and n <= 3999:
        symbols = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I']
        values = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
        roman_numeral = ""

        for i in range(len(values)):
            while n >= values[i]:
                roman_numeral += symbols[i]
                n -= values[i]

        return roman_numeral
    else:
        return False  


print('Roman Numeral Representation')
print(roman_representation(21))
print(roman_representation(44))
print(roman_representation(1012))
print('************************************************************************************')

""" Challenge #21
Smallest Value of an Array
Write a function that can return the smallest value of an array or the index of that value.
The function's 2nd parameter will tell whether it should return the value or the index.
Assume the first parameter will always be an array filled with at least 1 number 
and no duplicates. Assume the second parameter will be a string holding one of two values: 
'value' and 'index'. """

def find_smallest(numbers, to_return):
    """_It returns the smallest value of an array or the index of that value_

    Args:
        numbers (_array_): _integres array to find the smallest_
        to_return (_str_): _value or index to return_

    Returns:
        _int_: _value or index where the smallest number its located_
    """

    if to_return == 'value':
        return min(numbers)
    elif to_return == 'index':
        return numbers.index(min(numbers))
    else:
        return False
    
print('Smallest Value Array:')
print(find_smallest([ 1, 1, 0, 0, 1, 1],"value"))
print(find_smallest([ 8, 0, 9], "index"))
print(find_smallest([ 0], "index"))
print('************************************************************************************')

""" Challenge 26:
Human Readable Time
Write a function, which takes a non-negative integer (seconds) as 
input and returns the time in a human-readable format (HH:MM:SS)
HH = hours, padded to 2 digits, range: 00 - 99
MM = minutes, padded to 2 digits, range: 00 - 59
SS = seconds, padded to 2 digits, range: 00 - 59 """

def make_readable(seconds):
    """_Takes # seconds and make it a readable time for humans_

        Args:
            seconds (_int_): _Seconds quantity_

        Returns:
            _str_: _Time_
    """

    hours = seconds // 60 // 60
    minutes = (seconds - (hours * 60 * 60)) // 60 
    secs = seconds - ((hours * 60 * 60) + (minutes * 60))

    if hours < 10:
        hours = "0" + str(hours)
    if minutes < 10:
        minutes = "0" + str(minutes)
    if secs < 10:
        secs = "0" + str(secs)

    output = str(hours) + ":" + str(minutes) + ":" + str(secs)

    return output

print('Human Time:')
print(make_readable(86399)) #23:59:59
print('************************************************************************************')

""" Challenge #27:
Two Sum
Write a function that takes an array of numbers (integers for the tests) and a target number. 
It should find two different items in the array that, when added together, give the target value. 
The indices of these items should then be returned in a tuple / list (depending on your language) 
like so: (index1, index2). """

def two_sum(numbers, target):
    """_Take an array of integers and return the first 2 indexes where added 
        together they give the value of the target_

    Args:
        numbers (_array_): _Integers array_
        target (_int_): _Sum to find_

    Returns:
        _tuple_: _tuple with the 2 first indexes where added 
                together they give the value of the target_
    """

    output = []
    
    for i in range (len(numbers)):
        for j in range (len(numbers)):
            if i != j:
                if numbers[i] + numbers[j] == target:
                    if not i in output and not j in output:
                        output.append(i)
                        output.append(j)
                        return tuple(output)

print('Two Sum')
print(two_sum([1, 2, 3], 4))
print('************************************************************************************')

""" Challenge #28:
Human readable duration format
Your task in order to complete this Kata is to write a function which formats a duration, 
given as a number of seconds, in a human-friendly way.
The function must accept a non-negative integer. If it is zero, it just returns "now". 
Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds. """

def format_duration(seconds):
    """_Formats a duration given in seconds into a human-friendly string_.

    Args:
        seconds (_int_): _The non-negative integer representing the duration in seconds_.

    Returns:
        str: _A human-readable string expressing the duration in terms of years, days, hours, minutes, and seconds.
             The string follows the specified rules for formatting and uses appropriate units, plurals, and separators.
             If the duration is 0 seconds, the function returns 'now_'.
    """

    if seconds == 0:
        return 'now'
    
    output = []

    time_units = [('year', 365 * 24 * 60 * 60),
                  ('day', 24 * 60 * 60),
                  ('hour', 60 * 60),
                  ('minute', 60),
                  ('second', 1)]

    for unit, duration_in_seconds in time_units:
        if seconds >= duration_in_seconds:
            num_units = seconds // duration_in_seconds
            seconds = seconds - (duration_in_seconds * num_units)
            if num_units > 1:
                output.append(str(num_units) + " " + unit + "s")
            else:
                output.append(str(num_units) + " " + unit)

    if len(output) > 1:
        final_string = ', '.join(output[:-1]) + ' and ' + output[-1]
    else:
        final_string = output[0]

    return final_string

print('Human readable duration format')
print(format_duration(242062374))
print(format_duration(3662))
print('************************************************************************************')

""" Challenge #29:
Nesting Structure Comparison
Complete the function/method (depending on the language) to return True/False 
when its argument is an array that has the same nesting structures 
and same corresponding length of nested arrays as the first array. """

def same_structure_as(original,other):
    """_It returna True/False 
        when its argument is an array that has the same nesting structures_

    Args:
        original (_list_): _The original list or element that serves as the reference for structure comparison_
        other (_list_): _The other list or element to be compared with the original for structure similarity_

    Returns:
        _bool_: _True if the structures are similar, False otherwise_
    """
    if (type(original) == str and type(other) in (int, str)) or (type(original) == int and type(other) == str):
        return True
    elif type(original) != type(other):
        return False
    
    if type(original) == list and type(other) == list:

        if len(original) != len(other):
            return False
        
        for i in range(len(original)):
            if not same_structure_as(original[i], other[i]):
                return False
            
        return True
    else:
        return True
            

print('Same Structure')
print(same_structure_as([1,[1,1]],[2,[2,2]]))
print(same_structure_as([1,[1,1]],[[2,2],2]))
print(same_structure_as([1,'[',']'],['[',']',1]))
print('************************************************************************************')
