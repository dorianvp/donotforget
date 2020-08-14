package donotforget.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerChecker extends UnicastRemoteObject implements Checker {
    private static final long serialVersionUID = 1L;

    public ServerChecker() throws RemoteException {
        super();

    }

    @Override
    public boolean isRunning() throws RemoteException {
        return true;
    }
}