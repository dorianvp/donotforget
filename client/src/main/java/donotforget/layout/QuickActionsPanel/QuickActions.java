package donotforget.layout.QuickActionsPanel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.components.Button.CalendarButton;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class QuickActions extends BorderPane {
    private CalendarButton btnAdd = new CalendarButton("Nuevo elemento");
    private CalendarButton btnCategorias = new CalendarButton("Categorias");

    private EventHandler<ActionEvent> onExpand = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            ServerWrapper sw = new ServerWrapper();
            List<Categoria> l = sw.getCategorias();
            for (Categoria categoria : l) {
                System.out.println("ID: " + categoria.getId());
                System.out.println("Nombre: " + categoria.getNombre());
            }
            
        }
    };

    public QuickActions() {
        super();

        this.setMinHeight(200);
        this.setMaxHeight(Double.MAX_VALUE);
        this.btnAdd.setMaxWidth(Double.MAX_VALUE);
        this.btnAdd.setMaxHeight(Double.MAX_VALUE);

        this.btnCategorias.setMaxWidth(Double.MAX_VALUE);
        this.btnCategorias.setMaxHeight(Double.MAX_VALUE);
        this.btnCategorias.setOnAction(this.onExpand);

        this.setBottom(btnAdd);
        this.setTop(btnCategorias);
        BorderPane.setAlignment(btnAdd, Pos.CENTER);
        BorderPane.setAlignment(btnCategorias, Pos.TOP_CENTER);
        this.setMinWidth(0);
        this.setPrefWidth(300);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setStyle("-fx-background-color: green;");
    }
}
