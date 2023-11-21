import random
import threading
import time


class CircularQueue:
    def __init__(self, size):
        self.size = size
        self.queue = [None for i in range(size)]
        self.head = self.tail = -1
        self._lock = threading.Lock()

    def lock(self):
        self._lock.acquire()
 
    def unlock(self):
        self._lock.release()

    def enqueue(self, data):
        while True:
            
            # Lock 
            self.lock()

            # If queue is full wait one second and try again
            if (((self.tail + 1) % self.size) == self.head): 
                self.unlock()
                time.sleep(1)
                continue
            
            # If queue isempty intialize self.head to 0
            if (self.head == -1): 
                self.head = 0

            # Increment tail mod n    
            self.tail = (self.tail + 1) % self.size

            # Add data to queue
            self.queue[self.tail] = data

       
            # Unlock
            self.unlock()
            break
    
    def dequeue(self):
        while True:
            
            # Lock
            self.lock()

            # If queue is empty wait 1 second and try again
            if (self.head == -1): 
                self.unlock()
                time.sleep(1)
                continue
            
            # Get data to dequeue and dequeue the value
            dequeued = self.queue[self.head]
            self.queue[self.head] = None

            # If there is only one element make list empty
            if (self.head == self.tail):
                self.head = self.tail = -1

            # Else increment head mod n
            else:
                self.head = (self.head + 1) % self.size

    
            # Unlock
            self.unlock()
            return dequeued

def producer():
    while True:
        number = random.randint(1, 10)
        time.sleep(number)
        q.enqueue(number)


def consumer():
    while True:
        number = random.randint(1, 10)
        time.sleep(number)
        dequeued = q.dequeue()
        print(dequeued)


if __name__ == '__main__':
    q = CircularQueue(5)
    t1 = threading.Thread(target=producer)
    t2 = threading.Thread(target=consumer)
    t1.start()
    t2.start()
    t1.join()
    t2.join()
