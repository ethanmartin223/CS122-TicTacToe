import javax.swing.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}