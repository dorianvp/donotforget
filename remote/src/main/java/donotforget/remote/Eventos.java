package donotforget.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import donotforget.commons.Evento;

public interface Eventos extends Remote {
    public List<Evento> getEventos() throws RemoteException;
    public List<Evento> getEventosFromCategoria(int id_categoria) throws RemoteException;
    public Evento getEventById(int id) throws RemoteException;
    public boolean addEvent(Evento e) throws RemoteException;
    public boolean removeEvent(int id) throws RemoteException;
}
