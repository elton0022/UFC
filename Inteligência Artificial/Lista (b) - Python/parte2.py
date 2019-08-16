#------------------------Repetições encaixadas----------
# Exercício 1
    n = int(input("Digite o valor de n: "))
    fator = 0
    mult = 0
    
    fator = 2
    while n > 1:
        mult = 0
        while n % fator == 0:
            mult+=1
            n = n / fator 
        if mult != 0: 
            print('fator ', fator,' multiplicidade ', mult)
        fator+=1

# Exercício 2
    n = int(input("Digite o valor de n: "))
    quant = n
    mdc = 0
    nums = []
    divisor = 2
    deuCerto = True
    
    while n > 0:
        nums.append(int(input("Digite um valor: ")))
        n-=1 
    for x in range(0,quant):
        for i in range(0,quant):
            if nums[i] % divisor != 0 and nums[i] > 1:
                deuCerto = False
            if nums[i] % divisor == 0 and nums[i] > 1:    
                nums[i] /= divisor
        if deuCerto:
            mdc = divisor
        deuCerto = True
        divisor+=1
    print(mdc)

# Exercício 3

    n = int(input("Digite a quantidade de numeros: "))
    m = n
    nums = []
    total = 1
    while n > 0:
        nums.append(int(input("Digite o valor de n: ")))
        n-=1
    for i in range(0,m):
        aux = nums[i]
        while aux > 0:
            total*=aux
            aux-=1
        nums[i] = total
        total = 1
    print(nums)

# Exercício 4

    n = int(input("Digite a quantidade de numeros: "))
    m = n
    Ehprimo = True
    nums = []
    count = 0
    
    while n > 0:
        x = int(input("Digite o valor: "))
        if x <= 1:
            print('Digite valores > 1')
        else:
            nums.append(x)
            n-=1
    
    for i in range(0,m):
        if nums[i] % 10 == 0:
            t = nums[i] - 1
            while t > 1:
                if nums[i] % t == 0:
                    Ehprimo = False
                t-=1
            if(Ehprimo == True):
                count+=1
                Ehprimo = True
            Ehprimo = True
    print(count)

# Exercício 6

    n = int(input("Digite o numero: "))
    
    for i in range(1,n+1):
        for j in range(1,n+1):
            print(i,'*',j,'=', i * j)




