package donotforget.layout;

import donotforget.layout.MainViewPanels.HeaderBar.HeaderBar;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text; 

public class MainView extends GridPane {
    private HeaderBar header = new HeaderBar();
    public MainView() {
        super();
        this.setMaxWidth(Double.MAX_VALUE);

        this.setMinHeight(100);
        this.setMinWidth(200);

        RowConstraints r1 = new RowConstraints();
        r1.setPrefHeight(100);
        r1.setValignment(VPos.CENTER);
        GridPane.setHgrow(this.header, Priority.ALWAYS);
        // System.out.println("Height: " + );

        this.addRow(0, this.header);
        this.getRowConstraints().add(r1);

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        this.getStylesheets().clear();
        this.getStyleClass().add("root");
        this.getStylesheets().add(css);
    }

}
