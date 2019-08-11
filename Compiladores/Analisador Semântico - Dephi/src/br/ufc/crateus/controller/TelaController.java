/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.crateus.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufc.crateus.analise.Lexico;
import br.ufc.crateus.analise.Sintatico;
import br.ufc.crateus.analise.Token;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author
 */
public class TelaController implements Initializable {

    @FXML
    private TableView<Token> table = new TableView();
    @FXML
    private TableColumn<Token, String> tipo;
    @FXML
    private TableColumn<Token, String> valor;
    @FXML
    private TableColumn<Token, String> lexema;
    @FXML
    private TableColumn<Token, String> linha;
    @FXML
    private Button analise;
    @FXML
    private TextArea codigo;
    @FXML
    private TextArea quantLinha;
    @FXML
    private TextArea erros;
    int line = 1;
    int linhaT = 1;

    ObservableList<Token> tokenstable = FXCollections.observableArrayList();

    @FXML
    private void quant(KeyEvent event) {
        String aux = quantLinha.getText();
        if (event.getCode() == event.getCode().ENTER) {
            line++;
            quantLinha.setText(aux + "\n" + line);
        }
    }

    @FXML
    private void analise(ActionEvent event) {
        tokenstable = FXCollections.observableArrayList();
        analiseLexica(codigo.getText());
        Sintatico s = new Sintatico(tokenstable);
        s.iniciaAnalise();
        erros.setText("Executando...\n");
        erros.setText(erros.getText()+s.getErros());
        table.setItems(tokenstable);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipo.setCellValueFactory(new PropertyValueFactory<Token, String>("tipo"));
        lexema.setCellValueFactory(new PropertyValueFactory<Token, String>("lexema"));
        valor.setCellValueFactory(new PropertyValueFactory<Token, String>("valor"));
        linha.setCellValueFactory(new PropertyValueFactory<Token, String>("linha"));
        quantLinha.setText("1");
    }

    public void analiseLexica(String codigo) {
        Lexico tokens = new Lexico("Inicia");
        String aux = "";
        String lexema = "";
        erros.setText("");
        String erroText = "";
        Token novoT;
        int chave = 0;
        int linhaT = 1;
        String comentario = "";
        String auxC = "";

        for (char c : codigo.toCharArray()) {
            if (c == '{') {
                chave = 1;
            }
            if (c == '}' && chave == 1) {
                chave = 2;
            }
            if (c == '}' && chave == 0) {
                chave = 3;
            }
            if (c != '\n' && c != ' ' && c != '\t' && c != '}') {
                aux = "" + c;
                lexema = lexema + aux;
            } else {
                if (chave == 1) {
                    lexema = "";
                }
                if (lexema != "") {
                    if (tokens.autoReservadas(lexema) != null) {
                        novoT = tokens.autoReservadas(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoDelimitadores(lexema) != null) {
                        novoT = tokens.autoDelimitadores(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoOpAritmeticos(lexema) != null) {
                        novoT = tokens.autoOpAritmeticos(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoOpRelacionais(lexema) != null) {
                        novoT = tokens.autoOpRelacionais(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoTipos(lexema) != null) {
                        novoT = tokens.autoTipos(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoId(lexema) != null) {
                        novoT = tokens.autoId(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoDigitos(lexema) != null) {
                        novoT = tokens.autoDigitos(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoNumReal(lexema) != null) {
                        novoT = tokens.autoNumReal(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else if (tokens.autoString(lexema) != null) {
                        novoT = tokens.autoString(lexema);
                        novoT.setLinha(String.valueOf(linhaT));
                        tokenstable.add(novoT);
                    } else {
                        erroText = erros.getText();
                        erros.setText(erroText + "Token deconhecido - ' " + lexema + " ',linha:" + linhaT + "\n");
                    }

                }
                lexema = "";

            }

            if (c == '\n') {
                linhaT++;
            }

        }
        if (chave == 1) {
            erros.setText("Comentário não foi fechado com - }");
        } else if (chave == 3) {
            erros.setText("Comentário não foi aberto com - {");
        }
    }
}
