package donotforget.layout.QuickActionsPanel.NewElementDialog;

import java.util.stream.Stream;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class NewElementDialog extends JFXDialog {

    private JFXDialogLayout layout = new JFXDialogLayout();
    
    public NewElementDialog(StackPane container, DialogTransition content) {
        super(container, null, content, false);

        this.setDialogContainer(container);
        this.setTransitionType(JFXDialog.DialogTransition.RIGHT);
        this.setContent(this.layout);


        layout.setBody(new Label("Lorem ipsum"));

        JFXButton button = new JFXButton("Okay");
        
        button.setButtonType(ButtonType.RAISED);
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                NewElementDialog.this.close();
            }
        });
        this.setPrefSize(400, 300);
        layout.setActions(button);


        JFXTabPane tabs = new JFXTabPane();

        tabs.getStyleClass().add("tab-pane");

        Tab evento = new Tab();
        evento.setText("Evento");
        evento.setContent(new Label("Content"));

        Tab recordatorio = new Tab();
        recordatorio.setText("Recordatorio");
        recordatorio.setContent(new Label("Content"));
        
        tabs.getTabs().addAll(evento, recordatorio);

        layout.setBody(tabs);
    }


    public NewElementDialog(StackPane container) {
        super();

        


    }
}
