import threading

threading.stack_size(33554432)

import json
import sys
import timeit

from matplotlib import pyplot as plt

with open("ex2.json", "r") as inF:
    content = json.load(inF)

# functions provided
sys.setrecursionlimit(20000)
def func1(arr, low, high):
    if low < high:
        pi = func2(arr, low, high)
        func1(arr, low, pi-1)
        func1(arr, pi + 1, high)

def func2(array, start, end):
    p = array[start]
    low = start + 1
    high = end  
    while True:
        while low <= high and array[high] >= p:
            high = high - 1
        while low <= high and array[low] <= p:
            low = low + 1
        if low <= high:
                array[low], array[high] = array[high], array[low]
        else:
            break
    array[start], array[high] = array[high], array[start]
    return high

time = []

for i in range(len(content)): # test on all inputs of given json file
    time_list = []
    n = len(content[i])

    for k in range(5):  # perform 5 trials
        time_list.append(timeit.timeit(lambda:func1(content[i], 0, n-1), number = 1))
    
    time.append(time_list)

# get minimum values
min_func = [min(x) for x in time]


# plot results
plt.plot(min_func)
plt.suptitle("func1 performance")
plt.ylabel("Time in s")
plt.xlabel("Input size n")
plt.show()