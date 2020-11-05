package donotforget.client;

import donotforget.layout.MainView;
import donotforget.layout.Navigation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainUI extends Application {

    private GridPane root = new GridPane();

    @Override
    public void start(Stage stage) {

        Scene s = new Scene(this.root, 640, 480);
        Color c = new Color(0, 1, 0, 1);
        s.setFill(c);

        Navigation n = new Navigation();
        MainView mv = new MainView();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(80);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(20);

        RowConstraints r1 = new RowConstraints();
        r1.setPercentHeight(100);

        root.getColumnConstraints().addAll(col1, col2);
        root.getRowConstraints().addAll(r1);

        root.add(mv, 0, 0);
        root.add(n, 1, 0);

        // try {
        //     Registry r = LocateRegistry.getRegistry(null);
        //     Categorias cat = (Categorias) r.lookup("Hello");
         
        //     List<Categoria> lista = cat.getCategorias();

        //     for (Categoria categoria : lista) {
        //         System.out.println("ID: " + categoria.getId());
        //         System.out.println("Nombre: " + categoria.getNombre());
        //     }
            
            

        // } catch (RemoteException e) {
        //     e.printStackTrace();
        // } catch (NotBoundException e) {
        //     e.printStackTrace();
        // }

        stage.setScene(s);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}