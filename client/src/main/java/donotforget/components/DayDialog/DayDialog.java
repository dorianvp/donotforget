package donotforget.components.DayDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.commons.Evento;
import donotforget.components.QAListView.internals.CategoryCell;
import donotforget.components.list.EventButtonCell;
import donotforget.layout.Navigation;
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class DayDialog extends JFXDialog {
    private JFXDialogLayout layout = new JFXDialogLayout();
    private JFXButton btnCancelar = new JFXButton("Cancelar");
    private ListView<Evento> lista = new ListView<Evento>();
    // private StackPane container;
    private VBox contentLayout = new VBox();
    private Navigation categoryController; 
    private MainGrid parent;
    private int day;

    public DayDialog(
                StackPane container, 
                DialogTransition transition, 
                Navigation categoryController,
                MainGrid parent,
                int day
            ) {
        super(container, null, transition, false);

        // this.container = container;
        this.parent = parent;
        this.day = day;
        this.categoryController = categoryController;
        this.setDialogContainer(container);
        this.setTransitionType(JFXDialog.DialogTransition.CENTER);
        this.setContent(this.layout);

        this.btnCancelar.setId("btn-cancelar");

        

        this.lista.setCellFactory(new Callback<ListView<Evento>, ListCell<Evento>>() {
            @Override
            public ListCell<Evento> call(ListView<Evento> param) {
                return new EventButtonCell(
                    DayDialog.this, 
                    DayDialog.this.parent, 
                    DayDialog.this.categoryController
                );
            }
        });

        this.btnCancelar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                //TODO: abrir dialogo con detalles del evento.
                DayDialog.this.close();
            }
        });

        this.contentLayout.setSpacing(10);
        this.setPrefSize(400, 300);

        this.contentLayout.getChildren().addAll(lista, btnCancelar);

        layout.setBody(contentLayout);

        this.loadEventos();

    }

    public void loadEventos() {
        ServerWrapper sw = new ServerWrapper();

        List<Object> categorias = Arrays.asList(this.categoryController
        .getQa()
        .getLv()
        .lookupAll(".cell").toArray());

        ArrayList<Categoria> c = new ArrayList<Categoria>();

        for (Object categoria : categorias) {
            if (((CategoryCell) categoria).getCb().isSelected()) {
                System.out.println(((CategoryCell) categoria).getLastItem().toString());
                c.add(((CategoryCell) categoria).getLastItem());
            }
        }

        List<Evento> e = sw.getEventsFromDay(
            this.parent.getMGParent().navigationDate.withDayOfMonth(this.day).toLocalDate(),
            c
        );

        for (Evento evento : e) {
            System.out.println("cat: " + evento.getCategoria());
            System.out.println("Titulo: " + evento.getTitulo());
            System.out.println("Descripcion: " + evento.getDescripcion());
            System.out.println("ID: " + evento.getId_evento());
        }

        this.lista.getItems().clear();
        this.lista.getItems().addAll(e);
    }
}
