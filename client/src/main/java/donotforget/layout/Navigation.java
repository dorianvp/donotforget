package donotforget.layout;

import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class Navigation extends BorderPane {
    public Navigation() {
        super();
        Button t = new Button("derechaa");
        this.setMinHeight(200);
        

        t.getStyleClass().add("boton");

        t.setStyle("-fx-background-color: blue; -fx-text-fill: white");

        this.setBottom(t);
        BorderPane.setAlignment(t, Pos.CENTER);
        this.setMinWidth(0);
        this.setPrefWidth(300);


        this.setStyle("-fx-background-color: blue;");
    }

}