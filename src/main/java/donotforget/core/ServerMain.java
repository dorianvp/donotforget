package donotforget.core;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import donotforget.remote.ServerChecker;

public class ServerMain {
    public static void main(String[] args) {
        ServerMain sm = new ServerMain();

        sm.startRmiServer();
        try {
            ServerChecker sc = new ServerChecker();
            ServerMain stub = (ServerMain) UnicastRemoteObject.exportObject(sc, 1099);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void startRmiServer() {
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException re) {
            System.out.println("Cannot create registry.");
            System.out.println("Error: " + re);
        }
        
    }

}