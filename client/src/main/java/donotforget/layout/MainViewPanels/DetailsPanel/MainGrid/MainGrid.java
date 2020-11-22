package donotforget.layout.MainViewPanels.DetailsPanel.MainGrid;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

import donotforget.components.Button.CalendarButton;
import donotforget.components.CalendarLabel.CalendarLabel;
import donotforget.layout.MainView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MainGrid extends GridPane {
    public CalendarButton[][] labels = new CalendarButton[7][5];
    private MainView parent = null;

    public MainGrid(MainView mv) {
        this();
        this.parent = mv;
          
        this.updateGrid();
    }

    public MainGrid() {
        super();
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 5; y++) {
                labels[x][y] = new CalendarButton("", "label-button");
            }
        }

        ColumnConstraints[] columns = {
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints()
        };

        RowConstraints[] rows = {
            new RowConstraints(),
            new RowConstraints(),
            new RowConstraints(),
            new RowConstraints(),
            new RowConstraints()
        };

        for (RowConstraints row : rows) {
            row.setPercentHeight(100 / 5.0);
            this.getRowConstraints().add(row);
        }

        for (ColumnConstraints column : columns) {
            column.setPercentWidth(100 / 7.0);
            this.getColumnConstraints().add(column);
        }        
        
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 5; y++) {
                this.labels[x][y].setMaxHeight(Double.MAX_VALUE);
                this.labels[x][y].setMaxWidth(Double.MAX_VALUE);
                this.add(labels[x][y], x, y);
            }
        }

        this.setMaxHeight(Double.MAX_VALUE);
    }
    public void updateGrid() {
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 5; y++) {
                // this.parent.navigationDate.
                this.labels[x][y].setText(String.valueOf(x + 1 + y * 7));
            }
        }
    }
}
