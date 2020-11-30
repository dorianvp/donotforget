package donotforget.components.QAListView;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ListView;
import donotforget.commons.Categoria;
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;

public class QAListView extends ListView<Categoria> {

    private MainGrid gridUpdater;

    public QAListView() {
        super();
    }

    public void setItems(List<Categoria> elements) {
        List<Categoria> l = new ArrayList<Categoria>();
        for (Categoria c : elements) {
            l.add(c);
        }
        this.getItems().clear();
        this.getItems().addAll(l);
    }

    public MainGrid getGridUpdater() {
        return gridUpdater;
    }

    public void setGridUpdater(MainGrid gridUpdater) {
        this.gridUpdater = gridUpdater;
    }
}
