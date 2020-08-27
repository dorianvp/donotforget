package donotforget.remote;

public interface Checker extends java.rmi.Remote {
    public boolean isRunning() throws java.rmi.RemoteException;
}