package donotforget.layout;

import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import donotforget.components.Button.CalendarButton;
import donotforget.layout.QuickActionsPanel.QuickActions;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class Navigation extends GridPane {
    private QuickActions qa = new QuickActions();
    public Navigation() {
        super();
        this.setMinHeight(200);


        RowConstraints c1 = new RowConstraints();
        c1.setPercentHeight(50);
        c1.setFillHeight(true);
        c1.setVgrow(Priority.ALWAYS);

        RowConstraints c2 = new RowConstraints();
        c2.setPercentHeight(50);
        c2.setFillHeight(true);
        c2.setVgrow(Priority.ALWAYS);

        this.getRowConstraints().add(c1);
        this.getRowConstraints().add(c2);
        


        this.add(qa, 0, 1);
        this.setMinWidth(0);
        this.setPrefWidth(300);


        this.setStyle("-fx-background-color: blue;");
    }

}