package donotforget.components.QAListView.internals;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;
import donotforget.layout.QuickActionsPanel.QuickActions;
import javafx.beans.value.ObservableValue;
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
    private MainGrid gridUpdater;
    private QuickActions categoryController;

    public CategoryCell(MainGrid m, QuickActions q) {
        super();
        this.gridUpdater = m;
        this.categoryController = q;
        this.btnRemove.addEventHandler(MouseEvent.MOUSE_CLICKED, onRemove);

        this.bp.setLeft(this.cb);
        this.bp.setRight(this.btnRemove);

        this.cb.selectedProperty().addListener(
            (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                this.gridUpdater.updateGrid();
            } 
        );

        BorderPane.setAlignment(this.cb, Pos.CENTER_LEFT);
    }

    @Override
    protected void updateItem(Categoria item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
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
            ServerWrapper sw = new ServerWrapper();
            if (!sw.removeCategoria(CategoryCell.this.lastItem.getId())) {
                // Algo en el futuro
            } else {
                CategoryCell.this.cb.setSelected(false);
                CategoryCell.this.categoryController.loadCategories();
                CategoryCell.this.gridUpdater.updateGrid();
            }
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
