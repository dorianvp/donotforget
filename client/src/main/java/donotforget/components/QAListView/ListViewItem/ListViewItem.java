package donotforget.components.QAListView.ListViewItem;

import donotforget.commons.Categoria;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ListViewItem {
    private Categoria c = null;
    private boolean on = false;

    public ListViewItem(Categoria n, boolean on) {
        setCategory(n);
        setOn(on);
    }

    public final Categoria getCategory() {
        return this.c;
    }

    public final void setCategory(final Categoria c) {
        this.c = c;
    }
    public final boolean isOn() {
        return this.on;
    }

    public final void setOn(boolean b) {
        this.on = b;
    }
    

    @Override
    public String toString() {
        System.out.println("to string executed");
        return this.c.getNombre();
    }
}
