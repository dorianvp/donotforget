package donotforget.layout;

import donotforget.layout.MainViewPanels.DetailsPanel.DetailsPanel;
import donotforget.layout.MainViewPanels.HeaderBar.HeaderBar;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class MainView extends GridPane {
    private HeaderBar header = new HeaderBar();
    private DetailsPanel dt = new DetailsPanel();

    public MainView() {
        super();
        this.setMaxWidth(Double.MAX_VALUE);

        this.setMinHeight(100);
        this.setMinWidth(200);

        RowConstraints r1 = new RowConstraints();
        r1.setPrefHeight(75);
        r1.setValignment(VPos.CENTER);
        GridPane.setHgrow(this.header, Priority.ALWAYS);

        RowConstraints r2 = new RowConstraints();
        r2.setPrefHeight(75);
        r2.setValignment(VPos.CENTER);
        GridPane.setHgrow(this.dt, Priority.ALWAYS);

        
        
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);
        c1.setMaxWidth(Double.MAX_VALUE);

        this.add(this.header, 0, 0);
        this.add(this.dt, 0, 1);
        this.getRowConstraints().addAll(r1, r2);
        this.getColumnConstraints().add(c1);

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        this.getStylesheets().clear();
        this.getStyleClass().add("root");
        this.getStylesheets().add(css);
    }

}
