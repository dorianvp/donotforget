package donotforget.layout.MainViewPanels.HeaderBar;

import donotforget.components.Button.CalendarButton;
import donotforget.layout.MainViewPanels.HeaderBar.NavigationButtons.NavigationButtons;
import donotforget.layout.MainViewPanels.HeaderBar.ScaleButtons.ScaleButtons;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class HeaderBar extends GridPane {
    private CalendarButton btnConfig = new CalendarButton("O", "white-button");

    private NavigationButtons nav = new NavigationButtons();
    private ScaleButtons sv = new ScaleButtons();
    

    public HeaderBar() {
        super();

        this.add(this.btnConfig, 0, 0, 1, 1);

        this.add(nav, 1, 0, 1, 1);
        this.add(sv, 2, 0, 1, 1);

        // this.add(this.btnDay, 2, 0, 1, 1);
        // this.add(this.btnMonth, 3, 0, 1, 1);
        // this.add(this.btnYear, 4, 0, 1, 1);
        


        RowConstraints r1 = new RowConstraints();
        r1.setFillHeight(true);
        r1.setValignment(VPos.CENTER);
        r1.setVgrow(Priority.ALWAYS);
        this.getRowConstraints().add(r1);

        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(20);
        c1.setFillWidth(true);

        ColumnConstraints c2 = new ColumnConstraints();
        c2.setFillWidth(true);
        c2.setPercentWidth(40);

        ColumnConstraints c3 = new ColumnConstraints();
        c3.setFillWidth(true);
        c3.setPercentWidth(40);

        
        this.getColumnConstraints().addAll(c1, c2, c3);

        
        this.setPadding(new Insets(0, 50, 0, 50));

        String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
        this.getStylesheets().clear();
        this.getStyleClass().add("header");
        this.getStylesheets().add(css);
        this.setMaxHeight(Double.MAX_VALUE);
        this.setAlignment(Pos.CENTER);
    }
}
