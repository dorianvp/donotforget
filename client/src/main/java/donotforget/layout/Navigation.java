package donotforget.layout;

import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import donotforget.layout.QuickActionsPanel.QuickActions;

public class Navigation extends GridPane {
    private QuickActions qa = new QuickActions();
    public Navigation() {
        super();
        this.setMinHeight(200);

        RowConstraints c2 = new RowConstraints();
        c2.setPercentHeight(100);
        c2.setFillHeight(true);
        c2.setVgrow(Priority.ALWAYS);

        this.getRowConstraints().add(c2);
        


        this.add(qa, 0, 0);
        this.setMinWidth(0);
        this.setPrefWidth(300);


        this.setStyle("-fx-background-color: blue;");
    }

}