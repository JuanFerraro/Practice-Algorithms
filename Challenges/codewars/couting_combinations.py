"""Counting Change Combinations
    Write a function that counts how many different ways you can make change 
    for an amount of money, given an array of coin denominations. 
    For example, there are 3 ways to give change for 4 if you have coins 
    with denomination 1 and 2: 1+1+1+1, 1+1+2, 2+2.
    The order of coins does not matter: 1+1+2 == 2+1+1
    Also, assume that you have an infinite amount of coins.
    Your function should take an amount to change and an array of unique denominations 
    for the coins:
    count_change(4, [1,2]) # => 3
    count_change(10, [5,2,3]) # => 4
    count_change(11, [5,7]) # => 0
"""


def count_change(money, coins):
    """
    Calculates the number of possible combinations to reach a specific amount
    using the provided coins.

    Args:
        money (int): The target amount for which combinations are sought.
        coins (list): A list of values representing the available coins.

    Returns:
        int: The number of possible combinations to reach the target amount.
    """
    def change_combinations(m, c, i):
        """
        Recursive internal function to calculate the number of possible combinations.

        Args:
            m (int): The remaining amount to reach the target.
            c (list): The list of available coins.
            i (int): Current index in the list of coins.

        Returns:
            int: The number of possible combinations to reach the remaining amount.
        """
        if m == 0:
            return 1
        if m < 0 or i == len(c):
            return 0

        with_actual_coin = change_combinations(m - c[i], c, i)
        without_actual_coin = change_combinations(m, c, i + 1)

        return with_actual_coin + without_actual_coin

    return change_combinations(money, coins, 0)

    
print('Change counting combinations:')
print(count_change(10, [5, 2, 3]))
