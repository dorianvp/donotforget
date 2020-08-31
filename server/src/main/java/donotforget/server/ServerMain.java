package donotforget.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.sql.*;


import donotforget.remote.ServerChecker;

public class ServerMain {
    public static void main(String[] args) {
        
        
        ServerMain sm = new ServerMain();
        if(fileExists()) {
            System.out.println("DB Exists");
        } else {
            System.out.println("Db Does not Exist");
        }

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