package donotforget.commons;

import java.time.DayOfWeek;
import java.time.MonthDay;
import java.util.List;

public class DayContainer {
    private List<Evento> eventos;
    private MonthDay dia;

    public DayContainer(MonthDay dia, List<Evento> eventos) {
        this.setDia(dia);
        this.setEventos(eventos);
    }

    public MonthDay getDia() {
        return dia;
    }

    public void setDia(MonthDay dia) {
        this.dia = dia;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
