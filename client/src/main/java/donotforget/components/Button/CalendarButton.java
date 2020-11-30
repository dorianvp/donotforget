package donotforget.components.Button;

import javafx.scene.control.Button;

public class CalendarButton extends Button {
    private int day;

    public CalendarButton(String display) {
        super(display);
        this.getStyleClass().clear();
        this.getStyleClass().add("button");
    }

    public CalendarButton(String display, int day) {
        super(display);
        this.getStyleClass().clear();
        this.getStyleClass().add("button");
        if (day != 0) {
            this.setDay(day);
        }
    }

    public CalendarButton(String display, String cssClass) {
        super(display);
        this.getStyleClass().clear();
        this.getStyleClass().add(cssClass);   

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
