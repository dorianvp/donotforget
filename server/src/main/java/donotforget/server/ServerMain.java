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

            //inicio el registro e inicio base de datos si no existe
            sm.startRmiServer();   
            if(fileExists()) {
                System.err.println("LOG> DB Exists");
            } else {
                System.err.println("LOG> DB Does not Exist");
                //inicializar base de datos
                initDatabase();
            }
            //Localizo el registro y bindeo checker
            //asi el cliente se puede conectar

            try {
                ServerChecker sc = new ServerChecker();
                System.err.println("LOG> Bindeando sc a checker");
                sm.r.bind("checker", sc);  
                System.err.println("LOG> Server ready"); 
            } catch (Exception e) {
                System.err.println("ERROR: " + e);
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
    }

    public void startRmiServer() {
        try {
            System.err.println("LOG> Creando registro");
            this.r = LocateRegistry.createRegistry(PORT);
        } catch (RemoteException re) {
            System.out.println("Cannot create registry.");
            System.out.println("ERROR: " + re);
        }        
    }

    public void stopRmiRegistry() {
        if (this.r != null) {
            try {
                System.out.println("Unbind checker");
                this.r.unbind("checker");
                System.out.println("unexport this");
                UnicastRemoteObject.unexportObject(this.r, true);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            } finally {
                System.out.println("Ended");
                System.exit(0);
            }
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
            System.err.println("LOG> Inicializando la base de datos");
            Connection c;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:local.db");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}