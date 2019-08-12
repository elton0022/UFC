
# Exercício 1

quant = int(input("Digite a quantidade de numeros: "))
somaPares = 0;

for i in range (0,quant):
    numero = int(input("Digite um numero: "))
    if numero % 2 == 0:
        somaPares+=numero
    
print(somaPares)

# Exercício 2

quant = int(input("Digite a quantidade de numeros: "))
quantPares = 0;
quantImpares = 0;
    
for i in range (0,quant):
    numero = int(input("Digite um numero: "))
if numero % 2 == 0:
    quantPares+=1
else:
    quantImpares+=1
    
print('Quantidade de pares: '+str(quantPares))
print('Quantidade de Impares: '+str(quantPares))

# Exercício 3

n = int(input("Digite o valor de n (n > 0): "))
d = int(input("Digite o valor de d (0<=d<=9): "))

conta_digito = 0
n_salvo = n
while n > 0:
    dig = n % 10
    n = n // 10
    if dig == d:
        conta_digito = conta_digito + 1

print(conta_digito)


# Exercicio 4

n = int(input("Digite o numero: "))
verificador = 0

for i in range(0,n):
    for j in range(0,n):
        for l in range(0,n):
            if (i * j * l) == n:
               verificador = 1
                
if verificador == 1:
    print('E triangular')
else:
    print('Nao e triagular')

#Exercício 5

aux = 0

a = int(input("Digite o primeiro numero: "))
b = int(input("Digite o segundo numero: "))
c = int(input("Digite o terceiro numero: "))

# coloca o maior dos numeros dados no a
if b > a:
    aux = a
    a = b
    b = aux
if c > a:
    aux = a
    a = c
    c = aux
  
# verifica se b e c sao catetos de um triangulo com a de hipotenusa
if a * a == b * b + c * c:
    print("O triangulo e retangulo")
else: 
    print("O triangulo nao e retangulo")

# Exercício 6

a = int(input("Digite o primeiro numero: "))
b = int(input("Digite o segundo numero: "))
c = int(input("Digite o terceiro numero: "))

num1,num2,num3 = 0,0,0

if a > b and a > c:
    num1 = a
    if b > c:
        num2 = b
        num3 = c
    else:
        num2 = c
        num3 = b
if b > a and b > c:
    num1 = b
    if a > c:
        num2 = a
        num3 = c
    else:
        num2 = c
        num3 = a
if c > a and c > b:
    num1 = c
    if b > a:
        num2 = b
        num3 = a
    else:
        num2 = a
        num3 = b
            
print(num3)
print(num2)
print(num1)



