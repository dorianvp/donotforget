package donotforget.layout.MainViewPanels.HeaderBar.ScaleButtons;

import donotforget.components.Button.CalendarToggleButton;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ScaleButtons extends HBox {
    
    private PersistentButtonToggleGroup toggleGroup = new PersistentButtonToggleGroup();
    private CalendarToggleButton btnDay = new CalendarToggleButton("Día", "toggle-button");
    private CalendarToggleButton btnMonth = new CalendarToggleButton("Mes", "toggle-button");
    private CalendarToggleButton btnYear = new CalendarToggleButton("Año", "toggle-button");
    
    public ScaleButtons() {
        this.btnDay.setToggleGroup(this.toggleGroup);
        this.btnMonth.setToggleGroup(this.toggleGroup);
        this.btnYear.setToggleGroup(this.toggleGroup);

        this.getChildren().addAll(btnDay, btnMonth, btnYear);
        this.setAlignment(Pos.CENTER_LEFT);
    }
}


class PersistentButtonToggleGroup extends ToggleGroup {
    PersistentButtonToggleGroup() {
      super();
      getToggles().addListener(new ListChangeListener<Toggle>() {
          @Override public void onChanged(Change<? extends Toggle> c) {
          while (c.next()) {
            for (final Toggle addedToggle : c.getAddedSubList()) {
              ((CalendarToggleButton) addedToggle).addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent mouseEvent) {
                  if (addedToggle.equals(getSelectedToggle())) mouseEvent.consume();
                }
              });
            }
          }
        }
      });
    }
  }