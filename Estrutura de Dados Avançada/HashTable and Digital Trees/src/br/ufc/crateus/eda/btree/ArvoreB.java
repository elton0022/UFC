package br.ufc.crateus.eda.btree;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class ArvoreB implements Serializable {
	private static final long serialVersionUID = 1L;
	Covert_B_A c = new Covert_B_A();
	private RandomAccessFile arquivo;     // arquivo contendo a árvore.
    private String nomeArquivo; // nome do arquivo;
    private int ordem;
    
    
    public ArvoreB(int ordem, String nomeArquivo) throws IOException {
        this.ordem = ordem;
        this.nomeArquivo = nomeArquivo;
        arquivo = new RandomAccessFile(nomeArquivo, "rw");
        
        if (arquivo.length() == 0) arquivo.writeLong(-1);
  
    }
    
    public void inserir (int chave, long valor) throws IOException {
    	 arquivo.seek(0);
    	 long raiz = arquivo.readLong();
         if (raiz != -1) {
        	 ArvoreB_Pagina pa = new ArvoreB_Pagina(ordem);
             byte[] buffer = new byte[pa.TAMANHO_PAGINA];
             arquivo.read(buffer);
             pa = (ArvoreB_Pagina)c.convertByteArrayToObject(buffer);
        	 System.out.println(pa.n);
         }else {
        	 arquivo.setLength(0);
        	 arquivo.writeLong(0);
        	 ArvoreB_Pagina novaPagina = new ArvoreB_Pagina(ordem);
        	 novaPagina.chaves[0] = chave;
        	 novaPagina.dados[0] = valor;
        	 novaPagina.n += 1;
        	 arquivo.write(c.convertObjectToByteArray(novaPagina));
         }
    }
    
    private void inserir(int chave, long valor, long pagina) throws IOException {
    	arquivo.seek(pagina);
    	long prox = arquivo.readLong();
    	ArvoreB_Pagina pa = new ArvoreB_Pagina(ordem);
        byte[] buffer = new byte[pa.TAMANHO_PAGINA];
        arquivo.read(buffer);
        pa.setBytes(buffer);	
    }

    
    public long buscar(int chaveBuscada) throws IOException {
        arquivo.seek(0); // Vai para a raiz da árvore.
        long raiz = arquivo.readLong(); // Lê a raiz
        long registro = (raiz != -1) ? buscar(chaveBuscada, raiz) // Busca registro
                                     : -1; // Se árvore vazia, retorna -1;
        return registro; // Retorn o registro, encontrado ou não.
    }
    
    private long buscar(int chaveBuscada, long pagina) throws IOException {
        if (pagina == -1)
            return -1; // Pagina inexistente.
            
        // Carregando página atual.
        System.out.println(0);
        arquivo.seek(0);
        ArvoreB_Pagina pa = new ArvoreB_Pagina(ordem);
        byte[] buffer = new byte[pa.TAMANHO_PAGINA];
        arquivo.read(buffer);
        pa.setBytes(buffer);
        
        // Encontrando a página da chave que está sendo buscada.
        int i = 0;
        while (i < pa.n && chaveBuscada > pa.chaves[i]) {
            i++;
        }
        
        if (i < pa.n) {
            if (chaveBuscada == pa.chaves[i]) // registro encontrado.
                return pa.dados[i];
                
            return buscar(chaveBuscada, pa.filhos[i]); // Desce para página filha.
        } else {
            return buscar(chaveBuscada, pa.filhos[i]); // Ultimo filho da página.
        }
    }
//    
//    
//    
    
    
    /*
    
    Inserçao(ponteiroRaiz, key, chavePromovida)
    {
     se(ponteiroRaiz == -1)//se ponteiroRaiz nao aponta para nenhuma pagina
     {
     chavePromovida = key
     return(flag que indica que houve promoção de chave)
     }
     senao
     {
     carregue a página P apontada por ponteiroRaiz em memoria primária
     busque por key nessa página P
     posicao = página no qual key poderia estar
     }

     se(key foi encontrada)
     {
     //chave ja esta na arvore, retorne uma flag de erro
     return(flag de erro)
     }

     flagRetorno = Inserçao(posicao, key, chavePromovida)//procedimento recursivo

     se(flagRetorno indica que nao houve promoçao de chave ou que ocorreu um erro)
     {
     return(conteudo de flagRetorno)
     }
     senao se(há espaço na página P para chavePromovida)
     {
     insere chavePromovida na página P
     escreve página P em arquivo
     return(flag que indica que nao houve promocao de chave)
     }
     senao //nao ha espaço em P para key
     {
     realize operação de split em P
     escreva em arquivo  a nova página e a página P
     return(flag que indica que houve promoçao de chave)
     }
    }
    */
    // ...
}
