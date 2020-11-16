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

public class QAListView extends ListView<ListViewItem> {

    public QAListView() {
        //TODO: Add cell factory here
        this.setCellFactory(CheckBoxListCell.forListView(new Callback<ListViewItem, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(ListViewItem param) {
                
                ListCell<ListViewItem> cell = new ListCell<ListViewItem>() {

                    @Override
                    protected void updateItem(ListViewItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getCategory().getNombre());
                        }
                    }
                };
                return null;
            }

        }));

    }

    public void setItems(List<Categoria> elements) {
        List<ListViewItem> l = new ArrayList<ListViewItem>();
        for (Categoria c : elements) {
            l.add(new ListViewItem(c, false));
        }
        this.getItems().addAll(l);
    }
}
