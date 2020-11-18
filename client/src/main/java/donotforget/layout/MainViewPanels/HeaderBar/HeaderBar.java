package donotforget.layout.MainViewPanels.HeaderBar;

import donotforget.components.Button.CalendarButton;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class HeaderBar extends GridPane {
    private CalendarButton btnConfig = new CalendarButton("O");

    public HeaderBar() {
        super();

        this.add(this.btnConfig, 0, 0, 1, 1);


        RowConstraints r1 = new RowConstraints();
        r1.setFillHeight(true);
        r1.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().add(r1);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setFillWidth(true);

        
        this.getColumnConstraints().add(c1);

        GridPane.setValignment(this.btnConfig, VPos.CENTER);
        GridPane.setHalignment(this.btnConfig, HPos.LEFT);
        this.setPadding(new Insets(0, 50, 0, 50));

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        this.getStylesheets().clear();
        this.getStyleClass().add("header");
        this.getStylesheets().add(css);
    }
}
