package donotforget.layout.MainViewPanels.HeaderBar.NavigationButtons;

import java.time.LocalDateTime;

import donotforget.components.Button.CalendarButton;
import donotforget.layout.MainView;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class NavigationButtons extends HBox {
    private CalendarButton btnPrevious = new CalendarButton("<", "white-button");
    private CalendarButton btnNext = new CalendarButton(">", "white-button");
    private Label lblCurrentMonth = new Label("");
    private MainView dateOwner;

    private EventHandler<MouseEvent> onNext = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            dateOwner.navigationDate = dateOwner.navigationDate.plusMonths(1);
            System.out.println(dateOwner.navigationDate.getMonth().toString()); 
            lblCurrentMonth.setText(
                dateOwner.navigationDate.getMonth().toString().substring(0, 1).toUpperCase() + 
                (dateOwner.navigationDate.getMonth().toString().substring(1).toLowerCase()) + " " +
                dateOwner.navigationDate.getYear()
            );
            dateOwner.mg.updateGrid();
         } 
    };

    private EventHandler<MouseEvent> onPrevious = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            dateOwner.navigationDate = dateOwner.navigationDate.plusMonths(-1);
            System.out.println(dateOwner.navigationDate.getMonth().toString()); 
            lblCurrentMonth.setText(
                dateOwner.navigationDate.getMonth().toString().substring(0, 1).toUpperCase() + 
                (dateOwner.navigationDate.getMonth().toString().substring(1).toLowerCase()) + " " +
                dateOwner.navigationDate.getYear()
            );
            dateOwner.mg.updateGrid();
         } 
    };
    

    public NavigationButtons(MainView p) {
        this.dateOwner = p;

        this.getChildren().addAll(btnPrevious, lblCurrentMonth, btnNext);
        this.setAlignment(Pos.CENTER_LEFT);

        this.lblCurrentMonth.getStyleClass().clear();
        this.lblCurrentMonth.getStyleClass().add("label");
        this.lblCurrentMonth.setPrefWidth(200);
        this.lblCurrentMonth.setAlignment(Pos.CENTER);

        System.out.println(this.dateOwner.navigationDate);

        lblCurrentMonth.setText(
            dateOwner.navigationDate.getMonth().toString().substring(0, 1).toUpperCase() + 
            (dateOwner.navigationDate.getMonth().toString().substring(1).toLowerCase()) + " " +
            dateOwner.navigationDate.getYear()
        );

        this.btnNext.addEventHandler(MouseEvent.MOUSE_CLICKED, this.onNext);
        this.btnPrevious.addEventHandler(MouseEvent.MOUSE_CLICKED, this.onPrevious);
    }

}
