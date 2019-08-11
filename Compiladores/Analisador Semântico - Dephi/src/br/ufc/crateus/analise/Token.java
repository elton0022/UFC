/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.crateus.analise;

/**
 *
 * @author
 */
public class Token {

    private String tipo;
    private String valor;
    private String lexema;
    private String linha;

    public Token(String tipo, String valor, String Lexema, String linha) {
        this.tipo = tipo;
        this.valor = valor;
        this.lexema = Lexema;
        this.linha = linha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String Lexema) {
        this.lexema = Lexema;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

	@Override
	public String toString() {
		return "[" + tipo + "," + valor + "," + lexema + "," + linha + "]";
	}
    
    

}
