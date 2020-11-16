package donotforget.layout.QuickActionsPanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.components.Button.CalendarButton;
import donotforget.components.QAListView.QAListView;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.BorderPane;

public class QuickActions extends BorderPane {
    private CalendarButton btnAdd = new CalendarButton("Nuevo elemento");
    private CalendarButton btnCategorias = new CalendarButton("Categorias");
    private QAListView lv = new QAListView();

    private EventHandler<ActionEvent> onExpand = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            
            
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

        this.lv.setMaxHeight(Double.MAX_VALUE);
        this.lv.setMaxWidth(Double.MAX_VALUE);

        

        this.setBottom(btnAdd);
        this.setTop(btnCategorias);
        this.setCenter(lv);
        BorderPane.setAlignment(btnAdd, Pos.CENTER);
        BorderPane.setAlignment(btnCategorias, Pos.TOP_CENTER);
        this.setMinWidth(0);
        this.setPrefWidth(300);
        this.setMaxWidth(Double.MAX_VALUE);
        this.setStyle("-fx-background-color: green;");
        this.loadCategories();
    }

    private void loadCategories() {
        ServerWrapper sw = new ServerWrapper();
            List<Categoria> l = sw.getCategorias();
            for (Categoria categoria : l) {
                System.out.println("ID: " + categoria.getId());
                System.out.println("Nombre: " + categoria.getNombre());
            }
            this.lv.setItems(l);
    }
}
