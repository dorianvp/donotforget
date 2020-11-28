package donotforget.components.DayDialog;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import donotforget.commons.Evento;
import donotforget.components.list.EventButtonCell;
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
    private StackPane container;
    private VBox contentLayout = new VBox();

    public DayDialog(StackPane container, DialogTransition transition) {
        super(container, null, transition, false);

        this.container = container;
        this.setDialogContainer(container);
        this.setTransitionType(JFXDialog.DialogTransition.CENTER);
        this.setContent(this.layout);

        this.btnCancelar.setId("btn-cancelar");

        

        this.lista.setCellFactory(new Callback<ListView<Evento>, ListCell<Evento>>() {
            @Override
            public ListCell<Evento> call(ListView<Evento> param) {
                return new EventButtonCell();
            }
        });

        this.btnCancelar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                DayDialog.this.close();
            }
        });

        this.contentLayout.setSpacing(10);
        this.setPrefSize(400, 300);

        this.contentLayout.getChildren().addAll(lista, btnCancelar);

        layout.setBody(contentLayout);

    }
}
