

def fibonaccire(n):
    if(n==1 or n==0):
        return 1
    else:
        return fibonaccire(n-1)+fibonaccire(n-2)


numero=input(" ingrese un numero")
print fibonaccire(numero)