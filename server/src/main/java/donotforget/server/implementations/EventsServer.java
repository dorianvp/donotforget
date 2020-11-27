package donotforget.server.implementations;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import donotforget.commons.Evento;
import donotforget.remote.Eventos;
import donotforget.wrappers.DatabaseWrapper;

public class EventsServer implements Eventos {

    @Override
    public List<Evento> getEventos() throws RemoteException {
        Connection c;
        List<Evento> eventos = new ArrayList<Evento>();

        try {
            DatabaseWrapper dw = new DatabaseWrapper();

            c = dw.connect();

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Evento");
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            while(rs.next()) {
                eventos.add(new Evento(
                    rs.getInt("id_evento"), 
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    LocalDateTime.parse(rs.getString("fecha_inicio"), formatter),
                    LocalDateTime.parse(rs.getString("fecha_finalizacion"), formatter)
                ));
            }

            rs.close();
            s.close();
            dw.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventos;
    }

    @Override
    public List<Evento> getEventosFromCategoria(int id_categoria) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Evento getEventById(int id) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean addEvent(Evento e) throws RemoteException {
        try {
            DatabaseWrapper d = new DatabaseWrapper();
            Connection c = d.connect();
            PreparedStatement s = c.prepareStatement("INSERT INTO Evento (id_categoria, titulo, descripcion, fecha_inicio, fecha_finalizacion, tiempo_aviso_previo) VALUES (?, ?, ?, ?, ?, ?)");
            s.setInt(1, e.getCategoria());
            s.setString(2, e.getTitulo());
            s.setString(3, e.getDescripcion());
            s.setString(4, e.getFechaInicio().format(DateTimeFormatter.ISO_DATE_TIME));
            s.setString(5, e.getFechaFinalizacion().format(DateTimeFormatter.ISO_DATE_TIME));
            s.setString(6, e.getTiempoAvisoPrevio().format(DateTimeFormatter.ISO_DATE_TIME));

            s.executeUpdate();
            s.close();
            d.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeEvent(int id) throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }
    
}
