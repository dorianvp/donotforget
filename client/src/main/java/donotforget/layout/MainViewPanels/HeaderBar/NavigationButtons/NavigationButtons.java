package donotforget.layout.MainViewPanels.HeaderBar.NavigationButtons;

import java.time.LocalDateTime;

import donotforget.components.Button.CalendarButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NavigationButtons extends HBox {
    private CalendarButton btnPrevious = new CalendarButton("<", "white-button");
    private CalendarButton btnNext = new CalendarButton(">", "white-button");
    private Label lblCurrentMonth = new Label("");
    
    public NavigationButtons() {
        this.getChildren().addAll(btnPrevious, lblCurrentMonth, btnNext);
        this.setAlignment(Pos.CENTER_LEFT);

        this.lblCurrentMonth.getStyleClass().clear();
        this.lblCurrentMonth.getStyleClass().add("label");

        LocalDateTime d = LocalDateTime.now();
        this.lblCurrentMonth.setText(d.getDayOfWeek().toString().substring(0, 1).toUpperCase() + (d.getDayOfWeek().toString().substring(1).toLowerCase()));
    }
}
