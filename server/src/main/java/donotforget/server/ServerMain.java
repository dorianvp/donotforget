package donotforget.server;

import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.jdbc.ScriptRunner;

import donotforget.remote.Categorias;
import donotforget.remote.Eventos;
import donotforget.remote.ServerChecker;
import donotforget.server.implementations.CategoriasServer;
import donotforget.server.implementations.EventsServer;

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
            bindObjects();
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

    private static void bindObjects() {
        try {
            CategoriasServer cs = new CategoriasServer();
            EventsServer es = new EventsServer();

            Eventos e = (Eventos) UnicastRemoteObject.exportObject(es, 0);
            Categorias c = (Categorias) UnicastRemoteObject.exportObject(cs, 0);

            Registry registry;
            registry = LocateRegistry.getRegistry();
            registry.bind("categorias-server", c);  
            System.err.println("Binded categorias"); 

            registry.bind("events-server", e);  
            System.err.println("Binded eventos"); 

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void initDatabase() {
        try {
            System.err.println("LOG> Inicializando la base de datos");
            Connection c;
            Class.forName("org.sqlite.JDBC");
            String p = new File(ServerMain.class.getProtectionDomain().getCodeSource().getLocation()
            .toURI()).getParent().toString();
            // System.out.println(p);

            c = DriverManager.getConnection("jdbc:sqlite:" + p + File.separator + "local.db");

            
            InputStream in = ServerMain.class.getResourceAsStream("/schema.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            ScriptRunner sr = new ScriptRunner(c);
            

            // Sin esto, ScriptRunner hace la suicidación.
            sr.setEscapeProcessing(false);
            sr.runScript(reader);

            

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}