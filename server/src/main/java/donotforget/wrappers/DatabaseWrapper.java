package donotforget.wrappers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.sqlite.SQLiteConfig;

public class DatabaseWrapper {
    private Connection c;

    public Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String p = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent()
                    .toString();
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            c = DriverManager.getConnection("jdbc:sqlite:" + p + File.separator + "local.db", config.toProperties());
            // System.out.println(p);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        try {
            this.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
