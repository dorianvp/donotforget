package donotforget.launcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Launcher {
    public static void main(String[] args) {
        startServer();
        /* 
        if(isServerRunning()) {
            startClient();
        } else {
            startServer();
        } */
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
        
            Path p = Paths.get(Launcher.class.getProtectionDomain().getCodeSource().getLocation()
            .toURI());
            System.out.println(p.getParent().toString());
            Process proc = Runtime.getRuntime().exec("java -jar " + p.getParent() + "\\server-1.0.jar");
            StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");            
            
            // any output?
            StreamGobbler outputGobbler = new 
                StreamGobbler(proc.getInputStream(), "OUTPUT");
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();

    
        } catch (IOException ioe) {
            System.out.println("Cannot start server.jar");
            System.out.println("Error: " + ioe);
            return false;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return true;
    }

    private static void startClient() {
        try {
            Path p = Paths.get(Launcher.class.getProtectionDomain().getCodeSource().getLocation()
            .toURI());
            System.out.println(p.getParent().toString());
            Process proc = Runtime.getRuntime().exec("java -jar " + p.getParent() + "\\Client.jar");
            StreamGobbler errorGobbler = new 
                StreamGobbler(proc.getErrorStream(), "ERROR");            
            
            // any output?
            StreamGobbler outputGobbler = new 
                StreamGobbler(proc.getInputStream(), "OUTPUT");
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();


        } catch (IOException ioe) {
            System.out.println("Cannot start client.jar");
            System.out.println("Error: " + ioe);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

class StreamGobbler extends Thread
{
    InputStream is;
    String type;
    
    StreamGobbler(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
    }
    
    public void run()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
                System.out.println(type + ">" + line);    
            } catch (IOException ioe)
              {
                ioe.printStackTrace();  
              }
    }
}