package donotforget.layout.QuickActionsPanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.commons.Evento;
import donotforget.components.Button.CalendarButton;
import donotforget.components.QAListView.QAListView;
import donotforget.layout.QuickActionsPanel.NewElementDialog.NewElementDialog;
import donotforget.layout.QuickActionsPanel.TopPanel.TopPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class QuickActions extends BorderPane {
    private CalendarButton btnAdd = new CalendarButton("Nuevo elemento");
    private TopPanel bPanelTop = new TopPanel(this);
    private QAListView lv = new QAListView();

    private EventHandler<MouseEvent> onAdd = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {

            Dialog<Evento> d = new Dialog<>();
            d.initStyle(StageStyle.DECORATED);
            d.getDialogPane().setContent(new NewElementDialog());

            d.getDialogPane().setPadding(new Insets(20, 20, 20, 20));

            d.setTitle("Nuevo Elemento");

            

            d.show();

        }
    };

    public QuickActions() {
        super();

        this.setMinHeight(200);
        this.setMaxHeight(Double.MAX_VALUE);
        this.btnAdd.setMaxWidth(Double.MAX_VALUE);
        this.btnAdd.setMaxHeight(Double.MAX_VALUE);

        this.lv.setMaxHeight(Double.MAX_VALUE);
        this.lv.setMaxWidth(Double.MAX_VALUE);

        this.setBottom(btnAdd);
        this.setTop(bPanelTop);
        this.setCenter(lv);
        BorderPane.setAlignment(btnAdd, Pos.CENTER);
        BorderPane.setAlignment(bPanelTop, Pos.TOP_CENTER);
        this.setMinWidth(0);
        this.setPrefWidth(300);
        this.setMaxWidth(Double.MAX_VALUE);
        this.btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, this.onAdd);
        this.loadCategories();
    }

    public void loadCategories() {
        ServerWrapper sw = new ServerWrapper();
        List<Categoria> l = sw.getCategorias();
        for (Categoria categoria : l) {
            System.out.println("ID: " + categoria.getId());
            System.out.println("Nombre: " + categoria.getNombre());
        }
        this.lv.setItems(l);
    }

    
}
