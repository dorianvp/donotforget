package donotforget.server.implementations;

import java.rmi.RemoteException;
import java.sql.Connection;
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

            // cat.clear();
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

    public Categoria getCategoriaById(int id) {
        Connection c;
        Categoria cat = null;
        try {

            DatabaseWrapper dbw = new DatabaseWrapper();

            c = dbw.connect();

            PreparedStatement s = c.prepareStatement("SELECT * FROM Categoria WHERE id_categoria = ?");
            s.setInt(0, id);
            ResultSet rs = s.executeQuery();

            while(rs.next()) {
                cat = new Categoria(rs.getString("nombre"), rs.getInt("id_categoria"));
            }

            rs.close();
            s.close();
            dbw.disconnect();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cat;
    }

    public boolean addCategoria(Categoria c) {
        
        try {
            DatabaseWrapper dbw = new DatabaseWrapper();
            Connection con = dbw.connect();
            PreparedStatement p = con.prepareStatement("INSERT INTO Categoria (nombre) VALUES (?)");
            p.setString(1, c.getNombre());
            p.executeUpdate();
            p.close();
            dbw.disconnect();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // TODOOOOOOO
    public boolean removeCategoria(int id) {
        try {
            DatabaseWrapper d = new DatabaseWrapper();
            Connection c = d.connect();
            String sqlStatement = "DELETE FROM Categoria where id_categoria = ?";
            String sqlStatement2 = "DELETE FROM Evento where id_categoria = ?";

            PreparedStatement p = c.prepareStatement(sqlStatement);
            PreparedStatement p2 = c.prepareStatement(sqlStatement2);
            
            p.setInt(1, id);
            p2.setInt(1, id);

            System.out.println("Borrando: " + id);

            p.executeUpdate();
            p.close();
            p2.executeUpdate();
            p2.close();

            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
