package donotforget.layout;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 

public class MainView extends VBox {
    public MainView() {
        super();
        Text t = new Text("zurdooo");
        this.setMinHeight(200);
        this.setMinWidth(200);

        this.getChildren().add(t);
        this.setStyle("-fx-background-color: brown;");
    }

}
