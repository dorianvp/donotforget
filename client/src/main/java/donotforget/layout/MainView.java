package donotforget.layout;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 

public class MainView extends VBox {
    public MainView() {
        super();
        
        Text t = new Text("zurdooo");
        this.setMinHeight(200);
        this.setMinWidth(200);

        this.getChildren().add(t);

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        this.getStylesheets().clear();
        this.getStyleClass().add("root");
        this.getStylesheets().add(css);
    }

}
