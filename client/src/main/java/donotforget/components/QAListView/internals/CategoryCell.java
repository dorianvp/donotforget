package donotforget.components.QAListView.internals;

import donotforget.commons.Categoria;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

public class CategoryCell extends ListCell<Categoria> {
    private BorderPane bp = new BorderPane();
    private CheckBox cb = new CheckBox();
    private Button btnRemove = new Button("X");
    Categoria lastItem;

    public CategoryCell() {
        super();

        this.bp.setLeft(this.cb);
        this.bp.setRight(this.btnRemove);

        BorderPane.setAlignment(this.cb, Pos.CENTER_LEFT);
    }

    @Override
    protected void updateItem(Categoria item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
        } else {
            lastItem = item;
            cb.setText(item != null ? item.getNombre() : "<null>");
            setGraphic(this.bp);
        }
    }
}
