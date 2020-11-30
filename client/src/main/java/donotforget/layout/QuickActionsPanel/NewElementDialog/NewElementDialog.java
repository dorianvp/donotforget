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
import donotforget.layout.MainViewPanels.DetailsPanel.MainGrid.MainGrid;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
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
    private ComboBox<Categoria> cmbCategorias = new ComboBox<Categoria>();

    private HBox desde = new HBox();
    private Label lblDesde = new Label("Desde: ");

    private HBox hasta = new HBox();
    private Label lblHasta = new Label("Hasta: ");

    private StackPane container;
    private MainGrid updater;

    public NewElementDialog(StackPane container, DialogTransition content, MainGrid updater) {
        super(container, null, content, false);

        this.container = container;
        this.updater = updater;
        this.setDialogContainer(container);
        this.setTransitionType(JFXDialog.DialogTransition.CENTER);
        this.setContent(this.layout);

        this.loadCategorias();


        this.btnAgregar.setId("btn-agregar");
        this.btnCancelar.setId("btn-cancelar");

        this.titulo.setPromptText("Título");
        this.cmbCategorias.setPrefWidth(300);

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
                Evento e = new Evento(
                    ((Categoria) NewElementDialog.this.cmbCategorias.getValue()).getId(),
                    NewElementDialog.this.titulo.getText(),
                    NewElementDialog.this.txtDescripcion.getText(),
                    NewElementDialog.this.dateDesde.getValue().atTime(LocalTime.now()),
                    NewElementDialog.this.dateHasta.getValue().atTime(LocalTime.now())
                );
                ServerWrapper sw = new ServerWrapper();
                if (!sw.addEvent(e)) {
                    JFXDialog dlgError = new JFXDialog(
                        NewElementDialog.this.container, 
                        new Label("Se produjo un error al agregar el elemento indicado."),
                        DialogTransition.CENTER
                    );
                    dlgError.show();
                } else {
                    NewElementDialog.this.updater.updateGrid();
                };
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

        this.btnAgregar.disableProperty().bind((
            titulo.textProperty().isNotEmpty()
            .and(txtDescripcion.textProperty().isNotEmpty())
            .and(dateDesde.valueProperty().isNotNull())
            .and(dateHasta.valueProperty().isNotNull())
            .and(cmbCategorias.valueProperty().isNotNull())
        ).not());

        this.contentLayout.getChildren().addAll(titulo, cmbCategorias, desde, hasta, txtDescripcion);


        this.cmbCategorias.setCellFactory(new Callback<ListView<Categoria>, ListCell<Categoria>>(){
            @Override
            public ListCell<Categoria> call(ListView<Categoria> l) {
                return new ListCell<Categoria>() {

                    @Override
                    protected void updateItem(Categoria item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                            setText("Selecciona una categoría");
                            NewElementDialog.this.cmbCategorias.setPromptText("Seleccione una Categoría");
                        } else {
                            setText(item.getNombre());
                        }
                    }
                } ;
            }
        });
        this.cmbCategorias.setButtonCell(this.cmbCategorias.getCellFactory().call(null));

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
            this.cmbCategorias.getItems().add(categoria);
        }
        
    }
}
