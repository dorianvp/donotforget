package donotforget.components.list;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Evento;
import donotforget.components.DayDialog.DayDialog;
import donotforget.layout.Navigation;
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

public class EventButtonCell extends ListCell<Evento> {
    private BorderPane bp = new BorderPane();
    private JFXButton btnDetails = new JFXButton();
    private JFXButton btnDelete = new JFXButton("x");
    private Evento lastItem;
    private DayDialog dialog;
    private MainGrid updater;

    public EventButtonCell(
            DayDialog dialog, 
            MainGrid gridUpdater, 
            Navigation c
        ) {
        super();

        this.dialog = dialog;
        this.updater = gridUpdater;
        this.btnDetails.addEventHandler(MouseEvent.MOUSE_CLICKED, onDetails);

        this.btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, onDelete);

        this.bp.setCenter(this.btnDetails);
        this.bp.setRight(this.btnDelete);
        this.btnDetails.setButtonType(ButtonType.FLAT);
        this.btnDelete.setButtonType(ButtonType.FLAT);

        this.btnDelete.setTextAlignment(TextAlignment.CENTER);

        this.btnDetails.setMaxWidth(Double.MAX_VALUE);
        this.btnDetails.setMaxHeight(Double.MAX_VALUE);

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

    public EventHandler<MouseEvent> onDetails = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            System.out.println(((EventButtonCell)((JFXButton) e.getSource()).getParent().getParent()).getLastItem().getId_evento());
        }
    };

    public EventHandler<MouseEvent> onDelete = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            ServerWrapper sw = new ServerWrapper();
            if (!sw.removeEvent(
                ((EventButtonCell)((JFXButton) e.getSource()).getParent().getParent()).getLastItem().getId_evento())
            ) {
                // TODO: show error dialog.
                System.out.println("Error");
            } else {
                
                EventButtonCell.this.dialog.loadEventos();
                EventButtonCell.this.updater.updateGrid();                
            }

            System.out.println();
        }
    };

    public void setLastItem(Evento lastItem) {
        this.lastItem = lastItem;
    }
}
