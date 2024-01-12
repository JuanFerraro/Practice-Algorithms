""" Title: So Many Permutations!
    In this kata, your task is to create all permutations of a non-empty input string and remove duplicates, if present.
    Create as many "shufflings" as you can!
    Examples:
    With input 'a':
    Your function should return: ['a']
    With input 'ab':
    Your function should return ['ab', 'ba']
    With input 'abc':
    Your function should return ['abc','acb','bac','bca','cab','cba']
    With input 'aabb':
    Your function should return ['aabb', 'abab', 'abba', 'baab', 'baba', 'bbaa'] 
"""

def permutations(s):
    all_permutations = []

    if len(s) == 1:
        all_permutations.append(s[0])
        return all_permutations
    
    elements = list(s)
    word = ''
    
    for i in range (len(s)):
        sec_elements = []
        flag = 0

        for k in range (len(elements)):
            if k != i:
                sec_elements.append(elements[k])


        while True:
            word += elements[i]
            j = 0
            flag += 1
            while True:
                word += sec_elements[j]
                if len(word) == len(s):
                    if not word in all_permutations:
                        all_permutations.append(word)
                    word = ''
                    break
                
                j += 1
            
                if j == len(s) - 1:
                    j = 0

            sec_elements = [sec_elements[-1]] + sec_elements[:-1]
            if flag == len(sec_elements):
                flag = 0
                break

    return all_permutations




print('So Many Permutations!')
print(permutations('a'))
print(permutations('abcd'))
print(permutations('aabb'))

