package donotforget.components.QAListView;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.util.Callback;
import donotforget.commons.Categoria;
import donotforget.components.QAListView.ListViewItem.ListViewItem;
import donotforget.components.QAListView.internals.CategoryCell;

public class QAListView extends ListView<Categoria> {

    public QAListView() {
        //TODO: Add cell factory here
        this.setCellFactory(new Callback<ListView<Categoria>, ListCell<Categoria>>() {

            @Override
            public ListCell<Categoria> call(ListView<Categoria> param) {
                return new CategoryCell();
            }

            

        });

    }

    public void setItems(List<Categoria> elements) {
        List<Categoria> l = new ArrayList<Categoria>();
        for (Categoria c : elements) {
            l.add(c);
        }
        this.getItems().clear();
        this.getItems().addAll(l);
    }
}
