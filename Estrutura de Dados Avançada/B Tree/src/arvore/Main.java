package arvore;

public class Main {

	public static void main(String[] args) {
		ArvoreB b = new ArvoreB(2);
		
		
		b.insere(1);
		b.insere(5);
		b.insere(8);
		b.insere(9);
		
		b.print();
		
		System.out.println(b.BuscaChave(b.getRaiz(), 8).getChave());
		
		b.Remove(9);
		
		b.print();

	}

}
