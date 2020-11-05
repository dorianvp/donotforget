package donotforget.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import donotforget.commons.Categoria;

public interface Categorias extends Remote {
    public List<Categoria> getCategorias() throws RemoteException;
}
