package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {

    protected Model model;
    protected JPanel controlPanel;
    protected View view;
    protected AppFactory factory;
    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        model = factory.makeModel();
        model.subscribe(this);
        view = factory.makeView(model);
        controlPanel = new JPanel();

        setLayout(new GridLayout(1, 2));
        add(controlPanel);
        add(view);

        frame = new SafeFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    public void display() {
        frame.setVisible(true);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model.unsubscribe(this);
        this.model = model;
        model.subscribe(this);
        view.setModel(model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            switch (ae.getActionCommand()) {
                case "New" -> {
                    Utilities.saveChanges(model);
                    setModel(factory.makeModel());
                }
                case "Save" -> Utilities.save(model, false);
                case "SaveAs" -> Utilities.save(model, true);
                case "Open" -> {
                    Model model = Utilities.open(this.model);
                    if (model != null) setModel(model);
                }
                case "Quit" -> {
                    Utilities.saveChanges(model);
                    System.exit(0);
                }
                case "About" -> Utilities.inform(factory.about());
                case "Help" -> Utilities.inform(factory.getHelp());
                default -> {
                    Command command = factory.makeEditCommand(model, ae.getActionCommand(), ae.getSource());
                    if (command == null) throw new Exception("Unrecognized command " + ae.getActionCommand());
                    command.execute();
                }
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}