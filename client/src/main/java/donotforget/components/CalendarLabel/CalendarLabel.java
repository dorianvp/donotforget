package donotforget.components.CalendarLabel;

import javafx.scene.control.Label;

public class CalendarLabel extends Label {
    public CalendarLabel(String display, String cssClass) {
        super(display);
        this.getStyleClass().clear();
        this.getStyleClass().add(cssClass);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);
    }
}
