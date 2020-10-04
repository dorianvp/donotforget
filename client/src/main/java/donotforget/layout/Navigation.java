package donotforget.layout;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 

public class Navigation extends VBox {
    public Navigation() {
        super();
        Text t = new Text("derechaa");
        this.setMinHeight(200);
        this.setMinWidth(200);

        this.getChildren().add(t);
        this.setStyle("-fx-background-color: blue;");
    }

}
