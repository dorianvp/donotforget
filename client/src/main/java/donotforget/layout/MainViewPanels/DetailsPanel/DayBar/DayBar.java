package donotforget.layout.MainViewPanels.DetailsPanel.DayBar;

import donotforget.components.CalendarLabel.CalendarLabel;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class DayBar extends GridPane {
    private CalendarLabel[] labels = {
        new CalendarLabel("Do", "white-label"),
        new CalendarLabel("Lu", "white-label"),
        new CalendarLabel("Ma", "white-label"),
        new CalendarLabel("Mi", "white-label"),
        new CalendarLabel("Ju", "white-label"),
        new CalendarLabel("Vi", "white-label"),
        new CalendarLabel("Sa", "white-label")
    };
    public DayBar() {
        super();
        
        ColumnConstraints[] columns = {
           new ColumnConstraints(),
           new ColumnConstraints(),
           new ColumnConstraints(),
           new ColumnConstraints(),
           new ColumnConstraints(),
           new ColumnConstraints(),
           new ColumnConstraints()
        };

        for (ColumnConstraints columnConstraints : columns) {
            columnConstraints.setPercentWidth(100 / 7);
        }
        this.getColumnConstraints().addAll(columns);
        this.getChildren().addAll(labels);
        
        
    }
}
