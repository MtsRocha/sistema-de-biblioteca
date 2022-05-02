
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{

    public void start(Stage stageCenaLivro) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("javafx/cenaprincipal.fxml"));

        Scene scene= new Scene(root);

        stageCenaLivro.setTitle("Sistema de Biblioteca");
        stageCenaLivro.setScene(scene);
        stageCenaLivro.show();

    }


    public static void main(String[] args){

        launch(args);

    }

}
