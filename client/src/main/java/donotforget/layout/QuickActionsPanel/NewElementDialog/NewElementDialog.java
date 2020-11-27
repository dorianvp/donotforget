package donotforget.layout.QuickActionsPanel.NewElementDialog;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton.ButtonType;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.commons.Evento;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tornadofx.control.DateTimePicker;

public class NewElementDialog extends JFXDialog {

    private JFXDialogLayout layout = new JFXDialogLayout();
    private JFXTextField titulo = new JFXTextField();
    private JFXTextArea txtDescripcion = new JFXTextArea();
    private JFXButton btnAgregar = new JFXButton("Agregar");
    private JFXButton btnCancelar = new JFXButton("Cancelar");
    private VBox contentLayout = new VBox();
    private DateTimePicker dateDesde = new DateTimePicker();
    private DateTimePicker dateHasta = new DateTimePicker();
    private JFXCheckBox isEvent = new JFXCheckBox("Evento");
    private JFXComboBox categorias = new JFXComboBox<>();

    private HBox desde = new HBox();
    private Label lblDesde = new Label("Desde: ");

    private HBox hasta = new HBox();
    private Label lblHasta = new Label("Hasta: ");
    
    public NewElementDialog(StackPane container, DialogTransition content) {
        super(container, null, content, false);

        this.setDialogContainer(container);
        this.setTransitionType(JFXDialog.DialogTransition.CENTER);
        this.setContent(this.layout);

        


        this.btnAgregar.setId("btn-agregar");
        this.btnCancelar.setId("btn-cancelar");

        this.titulo.setPromptText("Título");

        this.lblDesde.setPrefWidth(120);
        this.lblHasta.setPrefWidth(120);
        
        btnAgregar.setButtonType(ButtonType.FLAT);
        this.btnCancelar.setButtonType(ButtonType.FLAT);

        btnCancelar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                NewElementDialog.this.close();
            }
        });

        btnAgregar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if (NewElementDialog.this.isEvent.isSelected()) {
                    Evento e = new Evento(
                        (int) NewElementDialog.this.categorias.getValue(),
                        NewElementDialog.this.titulo.getText(),
                        NewElementDialog.this.txtDescripcion.getText(),
                        NewElementDialog.this.dateDesde.getValue().atTime(LocalTime.now()),
                        NewElementDialog.this.dateHasta.getValue().atTime(LocalTime.now())
                    );
                    ServerWrapper sw = new ServerWrapper();
                    if (!sw.addEvent(e)) {
                        //MOstrar un error
                    };
                } else {
                    // Si el usuario quiere agregar un recordatorio

                }
                NewElementDialog.this.close();
            }
        });

        this.setPrefSize(400, 300);
        layout.setActions(btnCancelar, btnAgregar);


        this.dateDesde.setPromptText("Desde");
        this.dateHasta.setPromptText("Hasta");
        this.txtDescripcion.setPromptText("Descripción");

        this.txtDescripcion.setId("input-descripcion");
        
        this.txtDescripcion.setMaxWidth(Double.MAX_VALUE);
        //datePicker.setOverLay(true);
        this.desde.setAlignment(Pos.CENTER_LEFT);
        this.desde.getChildren().addAll(lblDesde, dateDesde);

        this.hasta.getChildren().addAll(lblHasta, dateHasta);
        this.hasta.setAlignment(Pos.CENTER_LEFT);

        //datePicker.setDefaultColor(Color.valueOf("#3f51b5"));
        this.contentLayout.setSpacing(10);

        this.contentLayout.getChildren().addAll(titulo, isEvent, categorias, desde, hasta, txtDescripcion);



        layout.setBody(contentLayout);
    }


    public NewElementDialog(StackPane container) {
        super();
    }

    public void loadCategorias() {
        ServerWrapper sw = new ServerWrapper();
        List<Categoria> l = sw.getCategorias();
        for (Categoria categoria : l) {
            System.out.println("ID: " + categoria.getId());
            System.out.println("Nombre: " + categoria.getNombre());
            this.categorias.getItems().add(categoria.getNombre());
        }
        
    }
}
