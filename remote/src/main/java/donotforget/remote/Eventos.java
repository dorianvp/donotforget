package donotforget.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import donotforget.commons.Categoria;
import donotforget.commons.DayContainer;
import donotforget.commons.Evento;

public interface Eventos extends Remote {
    public List<Evento> getEventos() throws RemoteException;
    public List<Evento> getEventosFromCategoria(int id_categoria) throws RemoteException;
    public Evento getEventById(int id) throws RemoteException;
    public boolean addEvent(Evento e) throws RemoteException;
    public boolean removeEvent(int id) throws RemoteException;
    public List<Evento> getEventsFromMonth(LocalDate month, List<Categoria> categorias) throws RemoteException;
    public List<Evento> getEventsFromDay(LocalDate day, List<Categoria> categorias) throws RemoteException;
}
