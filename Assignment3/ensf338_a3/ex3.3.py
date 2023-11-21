import sys

myList = []
emptyListBytes = sys.getsizeof(myList)
listBytes = sys.getsizeof(myList)

for i in range(64):
    myList.append(i)
    if sys.getsizeof(myList) != listBytes:
        # Assumes that the size of an int is 4 bytes
        oldCap = (listBytes - emptyListBytes) // 4
        print("Old capacity:", oldCap)
        
        listBytes = sys.getsizeof(myList)
        newCap = (listBytes - emptyListBytes) // 4
        print("New capacity:", newCap)

        if oldCap != 0:
            print("( Growth factor:", newCap/oldCap,")")
        print()
