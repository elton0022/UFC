//Nesse arquivo contêm as implementações de forma separada do projeto, a fim de uma melhor visualização
//Os metódos encontram-se implementados na classe SeparateChainingHashMap do projeto "TableHash"

	//Questão 1 - (Folha)

    //@Questao 2 - a)
	public void polynomials(LinkedList<Term> p1, LinkedList<Term> p2) {
		SeparateChainingHashMap <Integer,String> hash = new SeparateChainingHashMap<>(100);
		LinkedList<Term> L = new LinkedList<>();

		for (Term a :p1) {
			for (Term b :p2) {
				if(a.coef.equals(b.coef)) L.add(new Term(a.exp + b.exp,b.coef));
				else L.add(new Term(a.exp + b.exp,a.coef+b.coef));
			}
		}
		for(Term t :L) hash.put(t.exp,t.coef);
		hash.print();
	}

	//Questão 3-c
    public int verify(String A, String P) {
		return verify(A,P,0,P.length()-1);
	}
	
	
	private int verify(String A, String P, int init, int fim) {	
		char[] a = A.toCharArray();
		String work = "";
		if(fim >= A.length()) return -1;
		
		for(int i = init; i <=fim;i++)work += a[i];

		if(hash((K)work) == hash((K)P)) {
			if(work.equals(P)) return init;
			else return -1;
		}
		else return verify(A,P,init+1,fim+1);
	}

	//@Questão 4
	public void analyze() {
		SeparateChainingHashMap<String,String> hash = loadingWorks();
		try {
		      FileReader arq = new FileReader("dicionarioPessoal.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = ""; 
		      while (linha != null) {
		    	  linha = lerArq.readLine();
		    	  if(linha!=null && hash.get(linha) == null)hash.cases(linha);
		      }
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
	}
	
	private SeparateChainingHashMap<String,String> loadingWorks() {
		SeparateChainingHashMap<String,String> hash = new SeparateChainingHashMap<>(100);
		try {
		      FileReader arq = new FileReader("dicionario.txt");
		      BufferedReader lerArq = new BufferedReader(arq);
		 
		      String linha = ""; 
		      while (linha != null) {
		    	linha = lerArq.readLine();
		    	if(linha != null)hash.put(linha,linha);
		      }
		      arq.close();
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		return hash;
	}
	
	private void cases(String a){
		for(int i = 0; i < table.length;i++) {
			for(Node x = table[i]; x!=null; x = x.next) {
				compara1(a, (String)x.key);
				compara2(a, (String)x.key);
				compara3(a, (String)x.key);
			}
		}
	}
	
	//@a)
	private void compara1 (String a, String b) {
		Character x = null;
		String aux = "";
		if(a.length() == b.length() + 1) {
			for(Character c : a.toCharArray()) {
				if(b.contains(String.valueOf(c))) aux = aux+c;
				else x = c;
			}
		}
		if(b.equals(aux)) System.out.println("A letra -"+ x +"- não pertence a palavra "+b);
	}
	//@b)
	private void compara2 (String a, String b) {
		Character x = null;
		String aux = "";
		if(a.length() + 1 == b.length()) {
			for(Character c : b.toCharArray()) {
				if(a.contains(String.valueOf(c))) aux = aux+c;
				else x = c;
			}
		}
		if(a.equals(aux)) System.out.println("Ausencia da letra -"+ x +"- na palavra "+a +" = "+b);
	}
	//@c)
	private void compara3 (String a, String b) {
		char[] a1 = a.toCharArray();
		char[] b1 = b.toCharArray();
		String part = "";
		String part2 = "";
		Character l1 = ' ';
		Character l2 = ' ';
		int aux = 0;
		if(a.length() == b.length()) {
			for(int i = 0; i < a.length();i++) {
				if(i+1 == a.length()) break;
				if(aux == 1) part2+=a1[i+1]; 
				if(a1[i] != b1[i] && a1[i+1] != b1[i+1] && aux == 0) {
					l1 = a1[i+1];
					l2 = a1[i];
					aux++;
				}else if(a1[i] == b1[i] && aux == 0) part+=a1[i];
			}
		}
		String result = part+l1+l2+part2;
		if(b.equals(result)) System.out.println("Caracteres invertidos-"+l2+"-"+l1+" na palavra "+a+" == "+b);
	}

	//@Questão 5
		/*
        a) Não necessariamente, porque além da função hash o mesmo teria que ter acesso a senha e login em si,para
           que depois utilizase a hash(senha) para efetuar a entrada na conta.
        b) Sim, porque é levado em conta o login e a autenticação pela hash da senha, ou seja, como no enuciado
            (login,hash(senha)) 
        */

    //Questão 6

	//@Questão 7
	@Override
	public void remove(K key) {	
		int i = hash(key);		
		Node tmp = new Node(null, null, table[i]);
		for(Node n = tmp; n.next!=null; n=n.next) {
			
			if(key.equals(n.next.key)) {
				n.next = n.next.next;
				this.size--;
				if((this.size / table.length) == 2) resize(table.length/2);
				break;
			}
		}
		table[i] = tmp.next;
	}

	//@Questão 8

	remove(K key) {
		int p = hash(key);
		while(keys[p] != null && !key.equals(keys[p])) p = (p + 1) % keys.length;
		values[p] = null;
	}

	
	//@Questao 10
	public void lists(int N) {
		SeparateChainingHashMap <Integer,Integer> tmp = new SeparateChainingHashMap<>(100);
		Random r = new Random();
		int bigger = 0;
		int smaller = N;
		for(int  i = 0; i < N;i++) {
			int value = r.nextInt();
			tmp.put(value,value);
		}
		for(int  i = 0; i < table.length;i++) {
			if(goThrough(tmp.table[i]) < smaller) smaller = goThrough(tmp.table[i]);
			if(goThrough(tmp.table[i]) > bigger) bigger = goThrough(tmp.table[i]);
		}
		tmp.print();
		System.out.println("Maior lista: "+ bigger +"\n Menor lista: "+smaller);
	}
	
	private int goThrough(Node i) {
		int d = 0;
		for(Node x = i; x != null; x = x.next) d++;
		return d;
	}

	/*									Relatório
			No método lists geramos N chaves aleatórias através da primeira estrutura
		de repetição presente no método, e inserimos na tabela. Repare que para o
		primeiro caso de teste (10^3) teremos o total de 1000 chaves, ou seja isso
		"quebra" a condição da quantidade de nós que teremos em cada possição da
		tabela (N/100 == 10 > 8) assim a tabela teria que dobrar seu tamanho para
		uma melhor distribuição. Assim para 10^x tal que x > 2 teriamos que conti-
		nuar a  dobrar o tamanho da tabela.
			Na segunda estrutura de repetição presente no método iriamos percorrer
		cada lista de todas as posições da tabela a fim de armazenar nas variáveis
		smaller e bigger, o tamanho da menor lista e da maior lista respectivamente
		através do método goThrough.

	*/
	//@Questão 11
		public Iterable<String> keysThatMatch (String pat){
			Queue<String> q = new LinkedList<>();
			collectN(root,"",pat,q);
			return q;
		} 
		

		private void collectN(Node x,String pre,String pat,Queue<String>q) {
			int d = pre.length();
			if(x == null) return ;
			if(d == pat.length() && x.value != null) q.add(pre);
			if(d == pat.length()) return;
			
			char next = pat.charAt(d);
			for(char c = 0; c < R; c++) {
				if(next == '.' || next == c) {
					collectN(x.next[c], pre+c,pat,q);
				}
			}
		}

	//Questão 12

	public String max() {
		String aux =  "";
		for(String ks : keys()) {
				if(aux.compareTo(ks) <= -1)aux = ks;
		}
		return aux;
	}
	
	public String min() {
		String aux =  max();
		for(String ks : keys()) {
				if(ks.compareTo(aux) <= -1)aux = ks;
		}
		return aux;
	}
	
	public String ceiling(String key) {
		String aux = max();
		for(String ks : keys()) {
			if((ks.compareTo(key) == 1 || ks.compareTo(key) == 0) && ks.compareTo(aux) <= -1) {
				aux = ks;
			}
		}
		return aux;
	}
	
	public String floor(String key) {
		String aux = min();
		for(String ks : keys()) {
			if((ks.compareTo(key) == -1 || ks.compareTo(key) == 0) && ks.compareTo(aux) >= 1) {
				aux = ks;
			}
		}
		return aux;
	}
	
	public int rank(String key) {
		int i = 0;
		for(String ks : keys()) {
			if(ks.compareTo(key) <= -1) i++;
		}
		return i;
	}
	
	public String select(int rk) {
		for(String ks : keys()) {
			if(rk == rank(ks)) return ks;
		}
		return "Error";
	}


	//Questão 13
	public int subString(int L, String work) {
		int i = subString(work,0,L-1);
		return i;
	}
	
	private int  subString(String work, int i, int L) {
		if(L >= work.length()) return sizeKeys();
		char[] p = work.toCharArray();
		String aux = "";
		
		for(int j = i;  j<=L ; j++) aux+=p[j];
		put(aux,(V)"value");

		return subString(work,i+1,L+1);
	}
	
	private int sizeKeys() {
		int i = 0;
		for(String k :keys())i++;
		return i;
	}

	//Questao 14


	public void putN(String key, V value) {
		root = putN(root, key, value, 0);
	}
	
	private Node putN(Node r, String key, V value, int i) {
		if (r == null) r = new Node();
		if (i == key.length()) {
			r.value = (int) r.value+1;
			return r;
		}
		char ch = key.charAt(i);
		r.next[ch] = putN(r.next[ch], key, value, i + 1);
		return r;
	}
	
	
	private void sizeKeysValues(int L) {
		for(String k :keys()) {
			
			if(get(k) != null && k.length() == L) {
				System.out.println(k+":"+get(k));
			}
		}
	}
	
	public void subStringOc(String text, int L) {
		subStringOc(text,0, L-1);
		sizeKeysValues(L);
	}

	private void subStringOc(String text, int i, int L) {
		if(L >= text.length()) return;
		char[] p = text.toCharArray();
		String aux = "";
		
		for(int j = i;  j<=L ; j++) aux+=p[j];
		putN(aux,(V)"vaue");

		subStringOc(text,i+1,L+1);

	}

	//Questão 15

	//Questão 16

	public void extractTitle() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(
					"File"));
			while (br.ready()) {
				String line = br.readLine();

				if (line.contains("Titulo: ")) {
					String title[] = line.split("Titulo: ");
					put(convertToNewABC(title[1]), (V) convertToNewABC(title[1]));
				}

			}
			br.close();
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public String convertToNewABC(String title) {
		String newTitle = "";

		for (char c : title.toCharArray()) {
			if (c >= 65 && c <= 90)
				newTitle += ((char) (c + 32));
			else if (c == 32)
				newTitle += (' ');
			else if (c == 46)
				newTitle += ('.');
			else if (c >= 0 && c <= 31 || c >= 33 && c <= 47 || c >= 58 && c <= 64)
				newTitle += ('?');
			else
				newTitle += ((char) (c));
		}

		return newTitle;
	}