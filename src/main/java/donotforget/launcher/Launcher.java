package donotforget.launcher;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Launcher {
    public static void main(String[] args) {
        if(isServerRunning()) {
            startClient();
        } else {
            startServer();
        }
    }

    private static boolean isServerRunning() {
        try {
            Registry r = LocateRegistry.getRegistry(1099);
            r.lookup("checker");
            return true;
        } catch (NotBoundException nbe) {
            System.out.println("Cannot find remote object");
            //System.out.println("Error: " + nbe);
        } catch (RemoteException re) {
            System.out.println("Cannot connect to RmiRegistry.");
            //System.out.println("Error: " + re);
        }
        return false;
    }

    private static boolean startServer() {
        try {
            Runtime.getRuntime().exec("java -jar server.jar");
            System.out.println("Starting server...");
        } catch (IOException ioe) {
            System.out.println("Cannot start server.jar");
            System.out.println("Error: " + ioe);
            return false;
        }
        return true;
    }

    private static void startClient() {
        try {
            Runtime.getRuntime().exec("java -jar client.jar");
            System.out.println("Starting client...");
        } catch (IOException ioe) {
            System.out.println("Cannot start client.jar");
            System.out.println("Error: " + ioe);
        }
    }
}