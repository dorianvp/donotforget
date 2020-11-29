package donotforget.layout.QuickActionsPanel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.commons.Evento;
import donotforget.components.QAListView.internals.CategoryCell;
import donotforget.components.Button.CalendarButton;
import donotforget.components.QAListView.QAListView;
import donotforget.layout.MainView;
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;
import donotforget.layout.QuickActionsPanel.NewElementDialog.NewElementDialog;
import donotforget.layout.QuickActionsPanel.TopPanel.TopPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class QuickActions extends BorderPane {
    private JFXButton btnAdd = new JFXButton("Nuevo elemento");
    private TopPanel bPanelTop = new TopPanel(this);
    private QAListView lv;
    private MainGrid gridUpdater;

    private EventHandler<MouseEvent> onAdd = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            NewElementDialog d = new NewElementDialog(
                (StackPane) QuickActions.this.getParent().getParent().getParent(),
                JFXDialog.DialogTransition.CENTER,
                QuickActions.this.gridUpdater
            );
            
            d.show();

        }
    };

    public QuickActions() {
        super();

        this.lv = new QAListView();
        this.lv.setMaxHeight(Double.MAX_VALUE);
        this.lv.setMaxWidth(Double.MAX_VALUE);
        
        this.setMinHeight(200);
        this.setMaxHeight(Double.MAX_VALUE);
        this.btnAdd.setMaxWidth(Double.MAX_VALUE);
        this.btnAdd.setMaxHeight(Double.MAX_VALUE);

        
        this.setBottom(btnAdd);
        this.setTop(bPanelTop);
        this.setCenter(lv);
        BorderPane.setAlignment(btnAdd, Pos.CENTER);
        BorderPane.setAlignment(bPanelTop, Pos.TOP_CENTER);
        this.setMinWidth(0);
        this.setPrefWidth(300);
        this.setMaxWidth(Double.MAX_VALUE);
        
        this.loadCategories();
    }

    public void loadCategories() {

        ServerWrapper sw = new ServerWrapper();
        List<Categoria> l = sw.getCategorias();
        System.out.println("---------------------------------");
        for (Categoria categoria : l) {
            System.out.println("ID: " + categoria.getId());
            System.out.println("Nombre: " + categoria.getNombre());
        }
        this.lv.setItems(l);
    
    }

    public QAListView getLv() {
        return lv;
    }

    public void setLv(QAListView lv) {
        this.lv = lv;
    }

    public MainGrid getGridUpdater() {
        return gridUpdater;
    }

    public void setGridUpdater(MainGrid gridUpdater) {
        System.out.println("Llamado a setGridUpdater para QAListView");
        System.out.println("giridUpdater: " + gridUpdater);
        this.gridUpdater = gridUpdater;
        this.lv.setGridUpdater(this.gridUpdater);
        this.lv.setCellFactory(new Callback<ListView<Categoria>, ListCell<Categoria>>() {
            @Override
            public ListCell<Categoria> call(ListView<Categoria> param) {
                return new CategoryCell(QuickActions.this.lv.getGridUpdater(), QuickActions.this);
            }
        });
        this.loadCategories();
        this.btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, this.onAdd);
    }
    
}
