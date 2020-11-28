package donotforget.components.list;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import donotforget.commons.Evento;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class EventButtonCell extends ListCell<Evento> {
    private BorderPane bp = new BorderPane();
    private JFXButton btnDetails = new JFXButton();
    private Evento lastItem;

    public EventButtonCell() {
        super();


        this.btnDetails.addEventHandler(MouseEvent.MOUSE_CLICKED, onClick);

        this.bp.setCenter(this.btnDetails);
        this.btnDetails.setButtonType(ButtonType.FLAT);

    }

    @Override
    protected void updateItem(Evento item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            btnDetails.setText(item != null ? item.getTitulo() : "<null>");
            setGraphic(this.bp);
        }
    }

    public Evento getLastItem() {
        return this.lastItem;
    }

    public EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            // System.out.println(((CategoryCell)((Button) e.getSource()).getParent().getParent()).getLastItem().getId());
        }
    };

    public void setLastItem(Evento lastItem) {
        this.lastItem = lastItem;
    }
}
