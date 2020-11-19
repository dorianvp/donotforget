package donotforget.layout.MainViewPanels.HeaderBar.NavigationButtons;

import donotforget.components.Button.CalendarButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class NavigationButtons extends HBox {
    private CalendarButton btnPrevious = new CalendarButton("<", "white-button");
    private CalendarButton btnNext = new CalendarButton(">", "white-button");
    private Label lblCurrentMonth = new Label("Julio");
    
    public NavigationButtons() {
        this.getChildren().addAll(btnPrevious, lblCurrentMonth, btnNext);
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
