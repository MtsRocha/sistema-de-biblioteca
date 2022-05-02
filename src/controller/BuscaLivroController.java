package controller;
import dao.LivroDao;
import domain.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class BuscaLivroController {

    @FXML
    private Button btnBuscar;

    @FXML
    private Label lbIdentifica;

    @FXML
    private TableView<Livro> tabBusca;
    
    @FXML
    private TableColumn<Livro, String> txDescri;

    @FXML
    private TableColumn<Livro, Integer> txEdicao;

    @FXML
    private TextField txIdentifica;

    @FXML
    private TableColumn<Livro, String> txIsbn;

    @FXML
    private TableColumn<Livro, String> txTitulo;

    @FXML
    private TableColumn<Livro, String> txutor;

    ObservableList<Livro> livros = FXCollections.observableArrayList();
    
    @FXML
    void handlerBuscar(ActionEvent event) {
      
        String titulo;
        LivroDao livroDao = new LivroDao();

        if(txTitulo.getText().trim().length() > 0){

            titulo = txTitulo.getText();
            livros = FXCollections.observableArrayList(livroDao.findByTitle(titulo));
            tabBusca.setItems(livros);
            System.out.println(livros);
        }
    }

    public void initialize(){
        
        txTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        txIsbn.setCellValueFactory(new PropertyValueFactory<Livro, String>("isbn"));
        txEdicao.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("edicao"));
        txutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        txDescri.setCellValueFactory(new PropertyValueFactory<Livro, String>("descricao"));
    }
}




