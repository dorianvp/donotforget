package donotforget.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import donotforget.commons.Categoria;
import donotforget.layout.MainView;
import donotforget.layout.Navigation;
import donotforget.remote.Categorias;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainUI extends Application {

    private BorderPane root = new BorderPane();

    @Override
    public void start(Stage stage) {

        Rectangle2D r = Screen.getPrimary().getBounds();



        Scene s = new Scene(this.root, r.getWidth() - r.getWidth() * 0.3, r.getHeight() - r.getHeight() * 0.3);
        Color c = new Color(0, 1, 0, 1);
        s.setFill(c);

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        s.getStylesheets().clear();
        s.getStylesheets().add(css);

        Navigation n = new Navigation();
        MainView mv = new MainView();

        //root.setMaxWidth(Double.MAX_VALUE);

        root.setCenter(mv);
        root.setRight(n);
        stage.setScene(s);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}