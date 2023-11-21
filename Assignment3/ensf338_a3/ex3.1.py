# W23 ENSF338 A3
# ex3.1.py
# 30141743

class Stack():
    def __init__(self):
        self.list = []
    
    def push(self, val):
        self.list.append(val)
    
    def pop(self):
        return self.list.pop()
    
    def peek(self):
        return self.list[-1]
    
    def isEmpty(self):
        if (len(self.list) == 0):
            return True
        return False

def tokenize(expr):
    new_expr = ""
    # add spaces in front of or behind brackets
    for char in expr:
        if char == ')':
            new_expr += ' '
        new_expr += char
        if char == '(':
            new_expr += ' '
    
    return new_expr.split()

import sys

if (len(sys.argv) > 1):
    args = sys.argv[1:]
    arg_str = " ".join(args)
    if (arg_str[0] == arg_str[-1] == "'"):
        arg_str = arg_str[1:-1] 
      
    expr_list = tokenize(arg_str)

    bracket_stack = Stack()
    operator_stack = Stack()
    num_stack = Stack()

    operator_list = ['+', '-', '*', '/']

    for char in expr_list:
        if char == '(':
            bracket_stack.push(char)
        elif char == ')':
            # when see a closing bracket, compute the operation
            bracket_stack.pop()
            operator = operator_stack.pop()
            num2 = num_stack.pop()
            num1 = num_stack.pop()

            # compute expression and push result back to num_stack
            expr = str(num1) + operator + str(num2)
            try:
                num_stack.push(eval(expr))
            except ZeroDivisionError:
                print("Error: You cannot divide by 0.")
                exit(1)
            except Exception:
                print("An error occurred. Program exited.")
                exit(1)

        elif char in operator_list:
            operator_stack.push(char)
        else:
            num_stack.push(char)
    
    result = num_stack.pop()
    print(result)
    
else:
    print("Please provide command-line args")
