package donotforget.layout;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 

public class MainView extends VBox {
    public MainView() {
        super();
        this.setMaxWidth(Double.MAX_VALUE);
        Text t = new Text("zurdooo");
        this.setMinHeight(100);
        this.setMinWidth(200);

        this.getChildren().add(t);

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        this.getStylesheets().clear();
        this.getStyleClass().add("root");
        this.getStylesheets().add(css);
    }

}
