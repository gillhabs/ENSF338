import threading

threading.stack_size(33554432)

import json
import sys
import timeit

from matplotlib import pyplot as plt

with open("ex2.json", "r") as inF:
    content = json.load(inF)

#optimized functions (insertion sort)
sys.setrecursionlimit(20000)

def func(array):
    for i in range(1, len(array)):
        k = array[i]
        j = i - 1
        while j >= 0 and k < array[j]:
            array[j + 1] = array[j]
            j -= 1
        array[j + 1] = k

time = []

for i in range(len(content)): # test on all inputs of given json file
    time_list = []

    for k in range(5):  # perform 5 trials
        time_list.append(timeit.timeit(lambda:func(content[i]), number = 1))
    
    time.append(time_list)

# get minimum values
min_func = [min(x) for x in time]


# plot results
plt.plot(min_func)
plt.suptitle("optimized func performance")
plt.ylabel("Time in s")
plt.xlabel("Input size n")
plt.show()