import random
import timeit

import numpy as np
from matplotlib import pyplot as plt


# inefficient implementation
def linearsearch(n, arr):
    for i in range(len(arr)):
        if arr[i] == n:
            return True
    return False

# efficient implementation
def binarysearch(arr, low, high, key):
    if low <= high:
        mid = int((low + high)/2)
        if key == arr[mid]:
            return mid
        elif key < arr[mid]:
            return binarysearch(arr, low, mid-1, key)
        elif key > arr[mid]:
            return binarysearch(arr, mid+1, high, key)
    else:
        return -1


linearavgtimes = []
binaryavgtimes = []
# Create a list of array lengths
listlengths = [1000, 2000, 5000, 10000, 15000, 20000, 25000]
for listlength in listlengths:
    # Create a list of the input length
    numbers = [x for x in range(listlength)]
    lineartime = []
    binarytime = []
    # Run the test 100 times for each test of the numbers list 
    # and use timeit to get the time for each test
    for i in range(1000):
        random_number = random.randint(0, listlength - 1)

        lineartm = timeit.timeit("linearsearch(random_number, numbers)", setup="from __main__ import linearsearch, numbers, random_number", number=100)
        lineartime.append(lineartm/100)

        binarytm = timeit.timeit("binarysearch(numbers, 0, len(numbers) - 1, random_number)", setup = "from __main__ import binarysearch, numbers, random_number", number=100)
        binarytime.append(binarytm/100)

    # Computing the average execution times across all trials
    linearavg = sum(lineartime) / len(lineartime)
    linearavgtimes.append(linearavg)
    print("Average time for linearsearch for a list of length %d: %f" % (listlength, linearavg))

    # Computing the average execution times across all trials
    binaryavg = sum(binarytime) / len(binarytime)
    binaryavgtimes.append(binaryavg)
    print("Average time for binarysearch for a list of length %d: %f" % (listlength, binaryavg))

# Creating plot
plt.plot(linearavgtimes, label = "linear search")
plt.plot(binaryavgtimes, label = "binary search")
plt.legend()
plt.suptitle("Performance Comparisons")
plt.ylabel("Time in s")
plt.xlabel("List length")
plt.show()