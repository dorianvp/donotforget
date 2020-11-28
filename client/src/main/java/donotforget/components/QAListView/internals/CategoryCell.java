package donotforget.components.QAListView.internals;

import donotforget.commons.Categoria;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class CategoryCell extends ListCell<Categoria> {
    private BorderPane bp = new BorderPane();
    private CheckBox cb = new CheckBox();
    private Button btnRemove = new Button("X");
    private Categoria lastItem;

    public CategoryCell() {
        super();


        this.btnRemove.addEventHandler(MouseEvent.MOUSE_CLICKED, onRemove);

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
            setGraphic(null);
        } else {
            lastItem = item;
            cb.setText(item != null ? item.getNombre() : "<null>");
            setGraphic(this.bp);
        }
    }

    public Categoria getLastItem() {
        return this.lastItem;
    }

    public EventHandler<MouseEvent> onRemove = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            System.out.println(((CategoryCell)((Button) e.getSource()).getParent().getParent()).getLastItem().getId());
        }
    };

    public CheckBox getCb() {
        return cb;
    }

    public void setCb(CheckBox cb) {
        this.cb = cb;
    }

    public void setLastItem(Categoria lastItem) {
        this.lastItem = lastItem;
    }
}
