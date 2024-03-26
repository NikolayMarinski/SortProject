import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        getContentPane().add(new Panel());
        setTitle("Sorting visualizer");
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
