package donotforget.server.implementations;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import donotforget.commons.Categoria;
import donotforget.remote.Categorias;
import donotforget.wrappers.DatabaseWrapper;

public class CategoriasServer implements Categorias {

    @Override
    public List<Categoria> getCategorias() throws RemoteException {
        Connection c;
        List<Categoria> cat = new ArrayList<Categoria>();
        
        try {

            DatabaseWrapper dbw = new DatabaseWrapper();

            c = dbw.connect();

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Categoria");

            while(rs.next()) {
                cat.add(new Categoria(rs.getString("nombre"), rs.getInt("id_categoria")));
            }

            rs.close();
            s.close();
            dbw.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cat;
    }

    public void addCategoria(Categoria c) {
        
        try {
            DatabaseWrapper dbw = new DatabaseWrapper();
            Connection con = dbw.connect();
            PreparedStatement p = con.prepareStatement("INSERT INTO Categoria (nombre) VALUES (?)");
            p.setString(0, c.getNombre());
            p.executeUpdate();
            p.close();
            dbw.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
