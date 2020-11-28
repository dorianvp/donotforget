package donotforget.layout.MainViewPanels.DetailsPanel.MainGrid;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.jfoenix.controls.JFXDialog.DialogTransition;

import donotforget.ServerWrapper.ServerWrapper;
import donotforget.commons.Categoria;
import donotforget.commons.Evento;
import donotforget.components.Button.CalendarButton;
import donotforget.components.CalendarLabel.CalendarLabel;
import donotforget.components.DayDialog.DayDialog;
import donotforget.components.QAListView.internals.CategoryCell;
import donotforget.layout.MainView;
import donotforget.layout.Navigation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainGrid extends GridPane {
    public CalendarButton[][] labels = new CalendarButton[7][6];
    private MainView parent = null;
    private Navigation categoryController;

    public MainGrid(MainView mv, Navigation n) {
        this();
        this.parent = mv;
        this.categoryController = n;
        this.updateGrid();
    }

    public MainGrid() {
        super();
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                labels[x][y] = new CalendarButton("", "label-button");
            }
        }

        ColumnConstraints[] columns = {
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints(),
            new ColumnConstraints()
        };

        RowConstraints[] rows = {
            new RowConstraints(),
            new RowConstraints(),
            new RowConstraints(),
            new RowConstraints(),
            new RowConstraints(),
            new RowConstraints()
        };

        for (RowConstraints row : rows) {
            row.setPercentHeight(100 / 6.0);
            this.getRowConstraints().add(row);
        }

        for (ColumnConstraints column : columns) {
            column.setPercentWidth(100 / 7.0);
            this.getColumnConstraints().add(column);
        }        
        
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                this.labels[x][y].setMaxHeight(Double.MAX_VALUE);
                this.labels[x][y].setMaxWidth(Double.MAX_VALUE);
                this.add(labels[x][y], x, y);
            }
        }

        this.setMaxHeight(Double.MAX_VALUE);
    }
    public void updateGrid() {
        //System.out.println(parent.navigationDate.withDayOfMonth(1).getDayOfWeek().getValue());
        int counter = 1;

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

        List<Evento> e = sw.getEventsFromMonth(
            this.parent.navigationDate.toLocalDate(), 
            c
        );

        for (Evento evento : e) {
            System.out.println(evento.getCategoria());
            System.out.println(evento.getTitulo());
            System.out.println(evento.getDescripcion());
        }
        // Esto (^) extrae las categorias seleccionadas y trae los eventos necesarios.

        for (CalendarButton[] b : this.labels) {
            for (CalendarButton cb : b) {
                cb.setText("");
                cb.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        // TODO: show dialog with list of events
                        // System.out.println(MainGrid.this.getParent().getParent().getParent().toString());
                        DayDialog d = new DayDialog(
                            (StackPane) MainGrid.this.getParent().getParent().getParent(),
                            DialogTransition.CENTER
                        );
                        d.show();
                        // ServerWrapper sw = new ServerWrapper();
                        // if (!sw.addEvent(e)) {
                        //     JFXDialog dlgError = new JFXDialog(
                        //         NewElementDialog.this.container, 
                        //         new Label("Se produjo un error al agregar el elemento indicado."),
                        //         DialogTransition.CENTER
                        //     );
                        //     dlgError.show();
                        // };
                        // NewElementDialog.this.close();
                    }
                });
            }
        }
        GregorianCalendar g = new GregorianCalendar();
        for (int y = 0; y < 6; y++) { 
            for (int x = 0; x < 7; x++) {
                labels[x][y].getStyleClass().removeIf(style -> style.equals("current-day"));
                labels[x][y].getStyleClass().removeIf(style -> style.equals("event-day"));
                if (counter > parent.navigationDate.getMonth().length(g.isLeapYear(parent.navigationDate.getYear()))) { 

                } else {
                    if (y == 0 && x == 0) {
                        x = parent.navigationDate.withDayOfMonth(1).getDayOfWeek().getValue() - 1;
                    }                    
                    // parent.navigationDate.withDayOfMonth(1).getDayOfWeek().getValue();
                    int eventCount = 0;
                    for (int j = 0; j < e.size(); j++) {
                        if (e.get(j).getFechaInicio().getDayOfMonth() == counter) {
                            eventCount++;
                            System.out.println(eventCount);
                        }
                    }
                    if (eventCount > 0) {
                        labels[x][y].setText(String.valueOf(counter + " (" + eventCount + ")"));
                        labels[x][y].getStyleClass().addAll("event-day");
                    } else {
                        labels[x][y].setText(String.valueOf(counter));
                    }
                    labels[x][y].setDay(counter);
                    if (
                        LocalDateTime.now().getDayOfMonth() == counter 
                        && parent.navigationDate.getYear() == LocalDate.now().getYear()
                        && parent.navigationDate.getMonth() == LocalDate.now().getMonth())
                    {
                        labels[x][y].getStyleClass().addAll("current-day");
                        // System.out.println(counter);
                    }
                }
                
                counter++;
            }
            
        }
    }
}
