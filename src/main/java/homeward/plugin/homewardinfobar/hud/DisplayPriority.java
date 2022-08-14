package homeward.plugin.homewardinfobar.hud;

import homeward.plugin.homewardinfobar.hud.component.HUDComponent;

import java.util.LinkedList;

public class DisplayPriority {

    private LinkedList<HUDComponent> loadSequence = new LinkedList<>();

    public LinkedList<HUDComponent> getLoadSequence() {
        return loadSequence;
    }

    public void setLoadSequence(LinkedList<HUDComponent> loadSequence) {
        this.loadSequence = loadSequence;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        loadSequence.forEach(K -> {

            stringBuffer.append(K.getContent()).append(" ");

        });
        return stringBuffer.toString();
    }
}
