tabla = {}
tabla[0] = 1
tabla[1] = 1
def fibonaciicontabla(n):
    if(n==1 or n==0):
        return 1
    else:
        if(tabla.get(n-1,False)== False):
            tabla[n-1] = fibonaciicontabla(n-1)
        if(tabla.get(n-2,False)== False):
            tabla[n-2] = fibonaciicontabla(n-2)

        return tabla[n-1]+tabla[n-2]


numero=input(" ingrese un numero")
print fibonaciicontabla(numero)