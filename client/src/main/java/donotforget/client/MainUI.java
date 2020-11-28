package donotforget.client;

import donotforget.layout.MainView;
import donotforget.layout.Navigation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainUI extends Application {

    private StackPane stack = new StackPane();
    private BorderPane root = new BorderPane();    
    public Navigation n = new Navigation();
    private MainView mv = new MainView(n);


    @Override
    public void start(Stage stage) {

        Rectangle2D r = Screen.getPrimary().getBounds();



        Scene s = new Scene(this.stack, r.getWidth() - r.getWidth() * 0.3, r.getHeight() - r.getHeight() * 0.3);
        Color c = new Color(0, 1, 0, 1);
        s.setFill(c);

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        s.getStylesheets().clear();
        s.getStylesheets().add(css);

        

        //root.setMaxWidth(Double.MAX_VALUE);

        root.setCenter(mv);
        root.setRight(n);
        stack.getChildren().add(root);
        stage.setScene(s);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}