package donotforget.commons;

import java.time.LocalDateTime;

public class Recordatorio {
    private LocalDateTime dt;

    public String nombre;

    public void setDateTime(LocalDateTime dt) {
        this.dt = dt;
    }

    public LocalDateTime getDateTime() {
        return this.dt;
    } 

    public Recordatorio(LocalDateTime dt) {
        this.setDateTime(dt);
    }
}