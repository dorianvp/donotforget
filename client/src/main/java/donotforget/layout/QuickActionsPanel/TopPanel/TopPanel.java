package donotforget.layout.QuickActionsPanel.TopPanel;

import donotforget.components.Button.CalendarButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class TopPanel extends BorderPane {
    private CalendarButton btnCategorias = new CalendarButton("Categorías");
    private CalendarButton btnAddCategoria = new CalendarButton("+");

    private EventHandler<ActionEvent> onAdd = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            System.out.println("AddEvent");
        }
    };

    public TopPanel() {
        super();

        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);
        
        this.btnCategorias.setMaxWidth(Double.MAX_VALUE);
        BorderPane.setAlignment(btnCategorias, Pos.CENTER);


        this.setCenter(this.btnCategorias);
        this.setRight(this.btnAddCategoria);
        
        this.btnAddCategoria.setOnAction(this.onAdd);

        
    }
}
