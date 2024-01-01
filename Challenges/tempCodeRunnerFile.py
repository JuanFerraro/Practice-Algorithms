def descending_order(num):
    
    str_num = str(num)
    str_num = sorted(str_num, reverse=True)
    desc_num = int(''.join(map(str, str_num)))
    return desc_num

print(descending_order(12873))