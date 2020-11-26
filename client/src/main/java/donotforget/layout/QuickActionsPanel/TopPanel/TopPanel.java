package donotforget.layout.QuickActionsPanel.TopPanel;

import java.util.Optional;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.components.Button.CalendarButton;
import donotforget.layout.QuickActionsPanel.QuickActions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class TopPanel extends BorderPane {
    private Label lblCategorias = new Label("Categorías");
    private CalendarButton btnAddCategoria = new CalendarButton("+");
    private QuickActions p;

    private EventHandler<ActionEvent> onAdd = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            //System.out.println("AddEvent");

            Dialog<String> d = new Dialog<>();
            
            d.initStyle(StageStyle.UTILITY);

            // TODO: Agregar estilos al dialogo.

            
            d.getDialogPane().setPadding(new Insets(20, 20, 20, 20));
            
            // String css = this.getClass().getClassLoader().getResource("styles.css").toExternalForm();
            // d.getDialogPane().getStylesheets().clear();
            // d.getDialogPane().getStyleClass().add("dialog");
            
            // System.out.println(d.getDialogPane().getScene().getRoot().toString());
            // d.getDialogPane().getStylesheets().add(css);



            d.setTitle("Nueva Categoría");
            
            ButtonType btnAccept = new ButtonType("Aceptar", ButtonData.OK_DONE);
            d.getDialogPane().getButtonTypes().addAll(btnAccept, ButtonType.CANCEL);

            VBox l = new VBox();
            l.setSpacing(20);
            //l.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null))); 

            HBox h = new HBox();
            h.setSpacing(10);
            TextField nombre = new TextField();
            ComboBox c = new ComboBox();
            // c.getItems().addAll("c", "asdasd","asdasd");

            h.getChildren().addAll(nombre, c);

            
            nombre.setPromptText("Nombre de Categoría");
            l.getChildren().add(h);

            d.getDialogPane().setContent(l);

            // converter

            d.setResultConverter(dialog -> {
                if (dialog == btnAccept) {
                    return (nombre.getText());
                }
                return null;
            });


            d.getDialogPane().lookupButton(btnAccept)
            .addEventFilter(ActionEvent.ACTION, ae -> {
                ServerWrapper sw = new ServerWrapper();
                // System.out.println( (((VBox) d.getDialogPane().getContent()).getChildren()).get(0).toString());
                if (sw.addCategoria(new Categoria(((TextField) ((HBox) ((VBox) d.getDialogPane().getContent()).getChildren().get(0)).getChildren().get(0)).getText()))) {
                    System.out.println("Categoria añadida ");
                    p.loadCategories();
                } else {
                    //TODO: add JFXInputField here, validation and error messages!!!
                    //((TextField) ((HBox) ((VBox) d.getDialogPane().getContent()).getChildren().get(0)).getChildren().get(0);
                    ae.consume();
                }
            });

            d.showAndWait();


            
        }
    };

    public TopPanel(QuickActions p) {
        super();

        this.p = p;
        this.setMaxWidth(Double.MAX_VALUE);
        this.setMaxHeight(Double.MAX_VALUE);
        
        this.lblCategorias.setMaxWidth(Double.MAX_VALUE);
        this.lblCategorias.setMaxHeight(Double.MAX_VALUE);
        BorderPane.setAlignment(lblCategorias, Pos.CENTER);

        this.lblCategorias.getStyleClass().add("grey-label");

        this.setCenter(this.lblCategorias);
        this.setRight(this.btnAddCategoria);

        this.btnAddCategoria.setMaxHeight(Double.MAX_VALUE);
        
        this.btnAddCategoria.setOnAction(this.onAdd);

        
    }
}
