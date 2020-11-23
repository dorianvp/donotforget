package donotforget.layout.QuickActionsPanel.NewElementDialog;

import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class NewElementDialog extends VBox {

    private JFXTextField titulo;

    public NewElementDialog() {
        this.titulo = new JFXTextField();
        this.titulo.setPromptText("Título");

        JFXTabPane tabPane = new JFXTabPane();
        tabPane.setPrefSize(300, 200);
        Tab tab = new Tab();
        tab.setText("Tab 1");
        tab.setContent(new Label("Content"));

        Tab tab2 = new Tab();
        tab2.setText("Tab 2");
        tab2.setContent(new Label("Content"));
        
        tabPane.getTabs().add(tab);
        tabPane.getTabs().add(tab2);

        this.getChildren().add(this.titulo);
        this.getChildren().add(tabPane);
    }
}
