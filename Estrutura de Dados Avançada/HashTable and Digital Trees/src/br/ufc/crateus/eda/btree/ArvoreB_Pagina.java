package br.ufc.crateus.eda.btree;

import java.io.IOException;
import java.io.Serializable;

public class ArvoreB_Pagina implements Serializable {
	Covert_B_A c = new Covert_B_A();
	private static final long serialVersionUID = 1L;
    private   final int TAMANHO_REGISTRO = 12; // Tamanho de cada registro 
    protected final int TAMANHO_PAGINA;        // Tamanho de cada página (dependente da ordem)
    protected int    n;       // Quantidade de registros contidos na página
    protected int[]  chaves;  // Array contendo as chaves de cada registro 
    protected long[] dados;   // Array contendo os dados de cada registro 
    protected long[] filhos;  // Array contendo os enderecos das páginas filhas
    private int ordem;
 
    
    public ArvoreB_Pagina(int ordem)  {
        TAMANHO_PAGINA = 4 + (ordem - 1) * 20 + 8;
        this.ordem = ordem;
        n = 0;
        chaves = new int[ordem - 1];
        dados = new long[ordem - 1];
        filhos = new long[ordem];
        
        for (int i = 0; i < ordem - 1; i++) {
            chaves[i] = -1;
            dados[i] = -1;
            filhos[i] = -1;
            
        }
        filhos[ordem -1 ] = -1;
    }
    

    protected byte[] getBytes() throws IOException {
    	ArvoreB_Pagina p = new ArvoreB_Pagina(ordem);
    	p.dados = this.dados;
    	p.chaves = this.chaves;
    	p.filhos = this.filhos;
    	p.n = this.n;
    	p.ordem = this.ordem;
        return c.convertObjectToByteArray(p);
    }
    
    protected void setBytes(byte[] buffer) throws IOException {
    	ArvoreB_Pagina p = new ArvoreB_Pagina(ordem);
    	p = (ArvoreB_Pagina) c.convertByteArrayToObject(buffer);
    	this.chaves = p.chaves;
    	this.dados = p.dados;
    	this.filhos = p.filhos;
    	this.n = p.n;
    	this.ordem = p.ordem;
    }


}
