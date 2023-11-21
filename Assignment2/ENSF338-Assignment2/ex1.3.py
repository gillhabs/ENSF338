def func(n, cache={0:0, 1:1}):
    if n in cache:
        return cache[n]
    else:
        cache[n] = func(n-1) + func(n-2)
        return cache[n]
