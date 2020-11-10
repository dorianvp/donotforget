package donotforget.components.Button;

import javafx.scene.control.Button;

public class CalendarButton extends Button {
    public CalendarButton(String display) {
        super(display);
        this.getStyleClass().clear();
        this.getStyleClass().add("button");
    }
}
