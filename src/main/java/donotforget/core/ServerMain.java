package donotforget.core;

import java.rmi.Naming;
import java.rmi.RemoteException;

import donotforget.remote.ServerChecker;

public class ServerMain {
    public static void main(String[] args) {
        ServerMain sm = new ServerMain();

        sm.initRmiServer();
        try {
            ServerChecker sc = new ServerChecker();
            Naming.rebind("rmi://" + java.net.InetAddress.getLocalHost().getHostAddress() +
                             ":" + args[0] + "/PruebaRMI", sc);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void initRmiServer() {
        if(System.getProperty("os.name").startsWith("Windows")) {
            Thread cmdThread = new Thread(() -> {
                ProcessBuilder builder = new ProcessBuilder("rmiregistry");
                builder.redirectErrorStream(true);
                try {
                    System.out.println("Iniciando...");
                    Process p = builder.start();
                    System.out.println("Registro Iniciado :)");
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            });
            cmdThread.setDaemon(true);
            cmdThread.start();  
           
        }
    }

}