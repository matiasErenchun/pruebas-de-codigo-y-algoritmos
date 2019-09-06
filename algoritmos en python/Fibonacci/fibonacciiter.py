
def fibonaciiiter(n):
    if(n==1 or 0):
        return 1
    else:
        i=1
        first=1
        second=1;
        resultado=0;
        while(i<n):
            resultado=first+second;
            second=first
            first=resultado
            i+=1

        return resultado


numero=input(" ingrese un numero")

print fibonaciiiter(numero)