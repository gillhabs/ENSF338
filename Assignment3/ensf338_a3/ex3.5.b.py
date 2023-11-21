import random
import timeit

from matplotlib import pyplot as plt


def shift(arr, n, i):

    largest = i
    l = 2 * i + 1
    r = 2 * i + 2

    if l < n and arr[i] < arr[l]:
        largest = l

    if r < n and arr[largest] < arr[r]:
        largest = r

    if largest != i:
        arr[i], arr[largest] = arr[largest], arr[i]
        shift(arr, n, largest)


def array_enqueue(arr, data):

    # Enqueue data to arr
    size = len(arr)

    if size == 0:
        arr.append(data)

    else:
        arr.append(data)
        for i in range((len(arr) // 2) - 1, -1, -1):
            shift(arr, len(arr), i)


# Pop element with highest priority
def array_dequeue(arr):
    max_val = 0
    for i in range(len(arr)):
        if arr[i] > arr[max_val]:
            max_val = i
    item = arr[max_val]
    del arr[max_val]
    return item


def binary_heap_enqueue(arr, data):

    # Append down
    arr.append(data)

    # Perlocate down if needed
    i = len(arr) - 1
    while i // 2 > 0:
        if arr[i] < arr[i // 2]:
            arr[i // 2], arr[i] = \
                arr[i], arr[i // 2]
        i = i // 2
    
    
def binary_heap_dequeue(arr):
    
    # Get dequeued value to return
    return_value = arr[1]
    arr[1] = arr[len(arr) - 1]

    # Delete value
    arr.pop()

    # Perlocate down if needed
    i = 1
    while i * 2 <= len(arr) - 1:

        # Get minimum child
        if i * 2 + 1 > len(arr) - 1:
            mc = i * 2

        elif arr[i * 2] < arr[i * 2 + 1]:
            mc = i * 2

        else:
            mc = i * 2 + 1

        if arr[i] > arr[mc]:
            arr[i], arr[mc] = arr[mc], arr[i]

        i = mc

    return return_value


binary_avgtimes_enqueue = []
binary_avgtimes_dequeue = []
arr_avgtimes_enqueue = []
arr_avgtimes_dequeue = []

# Create a list of lengths
listlengths = [1000, 2000, 5000, 10000, 15000, 20000, 25000]


for listlength in listlengths:

     # Create a list of the input length
    numbers = [x for x in range(listlength)]
    head = 0
    tail = len(numbers) - 1


    binary_time_enqueue = []
    binary_time_dequeue = []
    arr_time_enqueue = []
    arr_time_dequeue = []

    # Run the test 100 times for each test of a random shuffle of the numbers list 
    # and use timeit to get the time for each test
    for i in range(1000):
        random.shuffle(numbers)

        binarytm_enqueue = timeit.timeit("binary_heap_enqueue(numbers, 5)", setup = "from __main__ import numbers, binary_heap_enqueue", number=100)
        binary_time_enqueue.append(binarytm_enqueue/100)
	
        binarytm_dequeue = timeit.timeit("binary_heap_dequeue(numbers)", setup="from __main__ import numbers, binary_heap_dequeue", number=100)
        binary_time_dequeue.append(binarytm_dequeue/100)
	
        arrtm_enqueue = timeit.timeit("array_enqueue(numbers, 5)", setup="from __main__ import array_enqueue, numbers", number=100)
        arr_time_enqueue.append(arrtm_enqueue/100)
	
        arrtm_dequeue = timeit.timeit("array_dequeue(numbers)", setup = "from __main__ import array_dequeue, numbers", number=100)
        arr_time_dequeue.append(arrtm_dequeue/100)

    # Computing the average execution times across all trials
    binaryavg_enqueue = sum(binary_time_enqueue) / len(binary_time_enqueue)
    binary_avgtimes_enqueue.append(binaryavg_enqueue)
    print("Average time for enqueue for a binary heap priority queue of length %d: %f" % (listlength, binaryavg_enqueue))

    # Computing the average execution times across all trials
    binaryavg_dequeue = sum(binary_time_dequeue) / len(binary_time_dequeue)
    binary_avgtimes_dequeue.append(binaryavg_dequeue)
    print("Average time for dequeue for a binary heap priority queue of length %d: %f" % (listlength, binaryavg_dequeue))

    # Computing the average execution times across all trials
    arravg_enqueue = sum(arr_time_enqueue) / len(arr_time_enqueue)
    arr_avgtimes_enqueue.append(arravg_enqueue)
    print("Average time for enqueue for an array implementation priority queue of length %d: %f" % (listlength, arravg_enqueue))

    # Computing the average execution times across all trials
    arravg_dequeue = sum(arr_time_dequeue) / len(arr_time_dequeue)
    arr_avgtimes_dequeue.append(arravg_dequeue)
    print("Average time for dequeue for an array implementation priority queue of length %d: %f" % (listlength, arravg_dequeue))



# Creating plot
plt.plot(binary_avgtimes_enqueue, label = "binary heap enqueue")
plt.plot(binary_avgtimes_dequeue, label = "binary heap dequeue")
plt.plot(arr_avgtimes_enqueue, label = "array enqueue")
plt.plot(arr_avgtimes_dequeue, label = "array dequeue")
plt.legend()
plt.suptitle("Performance Comparisons")
plt.ylabel("Time in s")
plt.xlabel("Queue length")
plt.show()





