package donotforget.components.Button;

import javafx.scene.control.ToggleButton;

public class CalendarToggleButton extends ToggleButton {
    public CalendarToggleButton(String display, String cssClass) {
        super(display);
        this.getStyleClass().clear();
        this.getStyleClass().add(cssClass);   
    }
}
