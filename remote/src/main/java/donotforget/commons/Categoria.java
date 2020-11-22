package donotforget.commons;

import java.io.Serializable;

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int id;

    public Categoria(String n, int id) {
        this.setNombre(n);
        this.setId(id);
    }

    
    public Categoria(String n) {
        this.setNombre(n);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public int getId() {
        return this.id;
    }
    private void setId(int id) {
        this.id = id;
    }
}
