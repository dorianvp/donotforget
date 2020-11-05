package donotforget.server.implementations;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import donotforget.commons.Categoria;
import donotforget.remote.Categorias;

public class CategoriasServer implements Categorias {

    @Override
    public List<Categoria> getCategorias() throws RemoteException {
        Connection c;
        List<Categoria> cat = new ArrayList<Categoria>();
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:local.db");

            Statement s = c.createStatement();

            ResultSet rs = s.executeQuery("SELECT * FROM Categoria");

            

            while(rs.next()) {
                cat.add(new Categoria(rs.getString("nombre"), rs.getInt("id_categoria")));
            }

            rs.close();
            s.close();
            c.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return cat;
    }
    
}
