package donotforget.layout;

import java.time.LocalDateTime;

import donotforget.layout.MainViewPanels.DetailsPanel.DetailsPanel;
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;
import donotforget.layout.MainViewPanels.HeaderBar.HeaderBar;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class MainView extends GridPane {
    private HeaderBar header;
    private DetailsPanel dt = new DetailsPanel();
    public MainGrid mg;
    public LocalDateTime navigationDate = LocalDateTime.now();

    public MainView() {
        super();
        this.setMaxWidth(Double.MAX_VALUE);

        this.mg = new MainGrid(this);
        this.header = new HeaderBar(this);

        this.setMinHeight(100);
        this.setMinWidth(200);

        RowConstraints r1 = new RowConstraints();
        r1.setPrefHeight(75);
        r1.setValignment(VPos.CENTER);
        GridPane.setHgrow(this.header, Priority.ALWAYS);

        RowConstraints r2 = new RowConstraints();
        r2.setValignment(VPos.CENTER);
        GridPane.setHgrow(this.dt, Priority.ALWAYS);

        RowConstraints r3 = new RowConstraints();
        r3.setValignment(VPos.CENTER);
        GridPane.setVgrow(this.mg, Priority.ALWAYS);
        GridPane.setHgrow(this.mg, Priority.ALWAYS);
        r3.setMaxHeight(Double.MAX_VALUE);

        
        
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setHgrow(Priority.ALWAYS);
        c1.setMaxWidth(Double.MAX_VALUE);

        this.add(this.header, 0, 0);
        this.add(this.dt, 0, 1);
        this.add(this.mg, 0, 2);
        this.getRowConstraints().addAll(r1, r2, r3);
        this.getColumnConstraints().add(c1);

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        this.getStylesheets().clear();
        this.getStyleClass().add("root");
        this.getStylesheets().add(css);
    }


}

