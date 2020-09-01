package donotforget.server;

import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.sql.*;


import donotforget.remote.ServerChecker;
import donotforget.remote.Checker;

public class ServerMain {
    private Registry r;
    private final static int PORT = 1099;
    public static void main(String[] args) {
        
        ServerMain sm = new ServerMain();

        try {
            sm.stopRmiRegistry();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        if(fileExists()) {
            System.out.println("DB Exists");
        } else {
            System.out.println("Db Does not Exist");
        }

        sm.startRmiServer();
        try {
            ServerChecker sc = new ServerChecker();
            //Checker stub = (Checker) UnicastRemoteObject.exportObject(sc, 1099);
            // Binding the remote object (stub) in the registry 
            sm.r = LocateRegistry.getRegistry(PORT); 
            
            sm.r.bind("checker", sc);  
            System.out.println("Server ready"); 
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        sm.stopRmiRegistry();
    }

    public void startRmiServer() {
        try {
            this.r = LocateRegistry.createRegistry(PORT);
        } catch (RemoteException re) {
            System.out.println("Cannot create registry.");
            System.out.println("Error: " + re);
        }        
    }

    public void stopRmiRegistry() {
        try {
            UnicastRemoteObject.unexportObject(this.r, true);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static boolean fileExists() {
        try {
            Path p = Paths.get(ServerMain.class.getProtectionDomain().getCodeSource().getLocation()
            .toURI());
            File f = new File(p.getParent().toString() + "\\local.db");
            if(f.isFile()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    private static void initDatabase() {
        try {
            Connection c;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:local.db");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}