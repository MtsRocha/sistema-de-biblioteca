package controller;

import java.sql.SQLException;

//import dao.LivroDao;
import domain.Livro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.LivroService;

public class LivroController {

    @FXML
    private Button btnDel;

    @FXML
    private Button btnatualiza;

    @FXML
    private Button btnsalvar;

    @FXML
    private Label lbautor;

    @FXML
    private TextField tfedicao;

    @FXML
    private Label lbdesc;

    @FXML
    private Label lbedicao;

    @FXML
    private Label lbisbn;

    @FXML
    private Label lbtitulo;

    @FXML
    private TextField tfautor;

    @FXML
    private TextField tfisbn;

    @FXML
    private TextField tftitulo;

    @FXML
    private TextArea txdesc;

    @FXML
    void HandlerAtualizar(ActionEvent event) {
        
    }

    @FXML
    void handlerDeletar(ActionEvent event) {

        tftitulo.setText("");
        tfisbn.setText("");
        tfedicao.setText("");
        tfautor.setText("");
        txdesc.setText("");

    
    }

    @FXML
    void handlerSalvar(ActionEvent event) throws SQLException {
        
        Livro livro = new Livro();
        LivroService lService = new LivroService();
            
        livro.setTitulo(tftitulo.getText());
        livro.setIsbn(tfisbn.getText());
        livro.setEdicao(tfedicao.getAnchor());
        livro.setAutor(tfautor.getText());
        livro.setDescricao(txdesc.getText());

        lService.insert(livro);
        handlerDeletar(event);
        
    }

}
