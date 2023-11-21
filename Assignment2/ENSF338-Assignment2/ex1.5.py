# original func
def func(n):
    if n == 0 or n == 1:
        return n
    else:
        return func(n-1) + func(n-2)

# optimized func
def optimized_func(n, cache={0:0, 1:1}):
    if n in cache:
        return cache[n]
    else:
        cache[n] = optimized_func(n-1) + optimized_func(n-2)
        return cache[n]

import timeit
from matplotlib import pyplot as plt

func_time = []
opt_func_time = []
for i in range(36): # test numbers 0-35
    time_list1 = []
    time_list2 = []
    
    for j in range(5):  # perform 5 trials
        time_list1.append(timeit.timeit(lambda:func(i), number=1))
        time_list2.append(timeit.timeit(lambda:optimized_func(i), number=1))
    func_time.append(time_list1)
    opt_func_time.append(time_list2)
    
    if not i%5:     # print i when i is a multiple of 5
        print(i)

# get minimum values
min_func = [min(x) for x in func_time]
min_opt_func = [min(x) for x in opt_func_time]

# plot results
fig, axs = plt.subplots(2)
axs[0].plot(min_func)
axs[0].set_title("func performance")
axs[0].set_ylabel("Time in s")
axs[0].set_xlabel("Input size n")
axs[1].plot(min_opt_func)
axs[1].set_title("optimized func performance")
axs[1].set_ylabel("Time in s")
axs[1].set_xlabel("Input size n")
plt.tight_layout()
plt.show()