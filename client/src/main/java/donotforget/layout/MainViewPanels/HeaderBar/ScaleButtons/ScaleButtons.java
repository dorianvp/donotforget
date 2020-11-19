package donotforget.layout.MainViewPanels.HeaderBar.ScaleButtons;
import donotforget.components.Button.CalendarButton;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ScaleButtons extends HBox {
    private CalendarButton btnDay = new CalendarButton("Día", "white-button");
    private CalendarButton btnMonth = new CalendarButton("Mes", "white-button");
    private CalendarButton btnYear = new CalendarButton("Año", "white-button");
    
    public ScaleButtons() {
        this.getChildren().addAll(btnDay, btnMonth, btnYear);
        this.setAlignment(Pos.CENTER_LEFT);
    }
}
