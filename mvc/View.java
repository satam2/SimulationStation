package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {

    protected Model model;

    public View(Model model) {
        this.model = model;
        this.model.subscribe(this);
    }

    @Override
    public void update() {
        repaint();
    }

    protected void setModel(Model model) {
        this.model.unsubscribe(this);
        this.model = model;
        this.model.subscribe(this);
        repaint();
    }
}
