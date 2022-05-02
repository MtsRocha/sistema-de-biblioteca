package controller;

import java.sql.SQLException;
import domain.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import service.LivroService;

public class ControllerPrincipal{

    @FXML
    private TabPane SistemaBiblioteca;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label lbAutor;

    @FXML
    private Label lbBuscaTitulo;

    @FXML
    private Label lbDesc;

    @FXML
    private Label lbEdi;

    @FXML
    private Label lbISBN;

    @FXML
    private Label lbTitulo;

    @FXML
    private Tab tabCad;

    @FXML
    private TableView<Livro> tabelaBusca;

    @FXML
    private TableColumn<Livro, String> tcIsbn;

    @FXML
    private TableColumn<Livro, String> tcTitulo;

    @FXML
    private TableColumn<Livro, String> tsAutor;

    @FXML
    private TableColumn<Livro, String> tsDesc;

    @FXML
    private TableColumn<Livro, Integer> tsEdicao;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtBuscaTitulo;

    @FXML
    private TextArea txtDesc;

    @FXML
    private TextField txtEdicao;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtTitulo;

    ObservableList<Livro> livros = FXCollections.observableArrayList();

    Livro livroSelecionado;

    @FXML
    void handlerEditar(ActionEvent event) throws SQLException {

        livroSelecionado =  tabelaBusca.getSelectionModel().getSelectedItem();
        SistemaBiblioteca.getSelectionModel().select(tabCad);

        if (livroSelecionado != null) {
           
            txtTitulo.setText(livroSelecionado.getTitulo());
            txtIsbn.setText(livroSelecionado.getIsbn());
            txtEdicao.setText(livroSelecionado.getEdicao().toString());
            txtAutor.setText(livroSelecionado.getAutor());
            txtDesc.setText(livroSelecionado.getDescricao());
            
        }else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("A busca não foi efetuada e nenhuma linha foi selecionada");
            alert.setContentText("Clique no botão buscar e selecione uma linha!");
            alert.showAndWait();
        }
        
    }

    @FXML
    void handlerAtualizar(ActionEvent event) throws SQLException {

        LivroService lService = new LivroService();

        livroSelecionado.setEdicao(Integer.valueOf(txtEdicao.getText()));
        livroSelecionado.setTitulo(txtTitulo.getText());
        livroSelecionado.setAutor(txtAutor.getText());
        livroSelecionado.setDescricao(txtDesc.getText());
        livroSelecionado.setIsbn(txtIsbn.getText());

        lService.update(livroSelecionado);
        
        limparTela();
    }
    

    @FXML
    void handlerBuscar(ActionEvent event) throws SQLException {

        String titulo;
        LivroService lService = new LivroService();

        if(txtBuscaTitulo.getText().trim().length() > 0){

            titulo = txtBuscaTitulo.getText();
            livros = FXCollections.observableArrayList(lService.findByTitle(titulo));
            tabelaBusca.setItems(livros);
        }else{
            livros = FXCollections.observableArrayList(lService.findAll());
            tabelaBusca.setItems(livros);
        }      
    }

    @FXML
    void handlerDeletar(ActionEvent event) throws SQLException {
        
        Livro livro =  tabelaBusca.getSelectionModel().getSelectedItem();
        LivroService lService = new LivroService();

        if (livro != null) {
            tabelaBusca.getItems().remove(livro);
            lService.delete(livro);

        } else {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Não selecionado");
        alert.setHeaderText("Nenhum item foi selecionado");
        alert.setContentText("Selecione algum item!");
        alert.showAndWait();            
        }
    }

    @FXML
    void handlerSalvar(ActionEvent event) throws SQLException {
        
        Livro livro = new Livro();
        LivroService lService = new LivroService();

        livro.setTitulo(txtTitulo.getText());
        livro.setIsbn(txtIsbn.getText());
        livro.setEdicao(Integer.valueOf(txtEdicao.getText()));
        livro.setAutor(txtAutor.getText());
        livro.setDescricao(txtDesc.getText());

        lService.insert(livro);

        limparTela();
    }
    


    public void initialize(){
        
        tcTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        tcIsbn.setCellValueFactory(new PropertyValueFactory<Livro, String>("isbn"));
        tsEdicao.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("edicao"));
        tsAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        tsDesc.setCellValueFactory(new PropertyValueFactory<Livro, String>("descricao"));
    }

    public void limparTela() {
        
        txtTitulo.setText("");
        txtIsbn.setText("");
        txtEdicao.setText("");
        txtAutor.setText("");
        txtDesc.setText("");

    }
}
