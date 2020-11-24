package donotforget.layout.QuickActionsPanel.NewElementDialog;

import java.util.stream.Stream;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

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
    
    public NewElementDialog(StackPane container, Region layout, DialogTransition content) {
        super(container, layout, content);
    }


    public NewElementDialog(StackPane container) {
        super();

        this.setDialogContainer(container);
        this.setTransitionType(JFXDialog.DialogTransition.RIGHT);

        layout.setHeading(new Label("Header"));
        layout.setBody(new Label("Lorem ipsum"));

        JFXButton button=new JFXButton("Okay");
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                NewElementDialog.this.close();
            }
        });
        this.setPrefSize(400, 300);
        layout.setActions(button);


    }
}
