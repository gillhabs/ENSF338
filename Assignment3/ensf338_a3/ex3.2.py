import json
import sys
import timeit

from matplotlib import pyplot as plt

with open("ex2data.json", "r") as inF:
    data = json.load(inF)

with open("ex2tasks.json", "r") as inF:
    tasks = json.load(inF)


def binarysearch(arr, low, mid, high, key):
    if low <= high:
        if key == arr[mid]:
            return mid
        elif key < arr[mid]:
            return binarysearch(arr, low, ((mid - 1) - low)//2, mid-1, key)
        elif key > arr[mid]:
            return binarysearch(arr, mid+1, ((mid + 1) + ((high - (mid + 1))//2)), high, key)
    else:
        return -1

# test midpoints are at the beginning and end of arr, 1/4, 1/2 and 3/4
one = 0    
two = len(data) // 4
three = len(data) // 2
four = len(data) * 3 // 4
five = len(data) - 1

test_midpoints = [one, two, three, four, five]

time = []
midpoints = []

for i in range(len(tasks)): # perform search for each number in tasks 
 

    for j in test_midpoints: # perform timing on each test midpoint
        temp = timeit.timeit(lambda:binarysearch(data, 0, j, len(data) - 1, tasks[i]), number = 1)

        # Set final midpoint on first runthrough
        if j == 0:
            num = temp
            mid = j

        # Only make the final midpoint the j-th midpoint if the time is less
        if j != 0:
            if temp < num:
                num = temp
                mid = j

    time.append(num)
    midpoints.append(mid)
    

# plot results
plt.scatter(midpoints, tasks)
plt.suptitle("binarysearch Performance of Midpoints vs Tasks")
plt.ylabel("Task")
plt.xlabel("Midpoint")
plt.show()