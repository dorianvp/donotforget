package donotforget.layout.MainViewPanels.DetailsPanel;

import donotforget.layout.MainViewPanels.DetailsPanel.DayBar.DayBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class DetailsPanel extends GridPane {
    private DayBar days = new DayBar();

    public DetailsPanel() {
        super();

        ColumnConstraints r1 = new ColumnConstraints();
        
        r1.setFillWidth(true);
        r1.setHgrow(Priority.ALWAYS);
        this.addRow(0, this.days);
        this.getColumnConstraints().add(r1);

        this.setMaxWidth(Double.MAX_VALUE);

        //System.out.println(this.getWidth());

    }
}
