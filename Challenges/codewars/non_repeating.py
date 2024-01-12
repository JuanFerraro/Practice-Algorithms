""" Title: First non-repeating character
    Write a function named first_non_repeating_letter that takes a string input, 
    and returns the first character that is not repeated anywhere in the string.
    For example, if given the input 'stress', the function should return 't', 
    since the letter t only occurs once in the string, and occurs first in the string.
    As an added challenge, upper- and lowercase letters are considered the same character, 
    but the function should return the correct case for the initial letter. 
    For example, the input 'sTreSS' should return 'T'.
    If a string contains all repeating characters, it should return an empty string ("") or None -- see sample tests.
"""

def first_non_repeating_letter(s):
    """_takes a string input, and returns the first character that is not repeated anywhere in the string_

    Args:
        s (_str_): _any string_

    Returns:
        _str_: _first character that is not repeated in the str_
    """
    text = list(s.lower())
    aux_text = list(s)

    for i in range (len(text)):
        if text.count(text[i]) == 1:
            if aux_text[i].isupper():
                return text[i].upper()
            else:
                return text[i]
    
    return ''


print(first_non_repeating_letter('sTreSS'))
print(first_non_repeating_letter('hello world, eh?'))
print(first_non_repeating_letter('~><#~><'))