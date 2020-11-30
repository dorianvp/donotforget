package donotforget.layout;

import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;
import donotforget.layout.QuickActionsPanel.QuickActions;

public class Navigation extends GridPane {
    private QuickActions qa;
    private MainView m;
    private MainGrid gridUpdater;

    public Navigation() {
        super();
        this.setMinHeight(200);

        this.qa = new QuickActions();
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

    public QuickActions getQa() {
        return qa;
    }

    public void setQa(QuickActions qa) {
        this.qa = qa;
    }

    public MainGrid getGridUpdater() {
        return gridUpdater;
    }

    public void setGridUpdater(MainGrid gridUpdater) {
        this.gridUpdater = gridUpdater;
        this.qa.setGridUpdater(this.gridUpdater);
    }

}