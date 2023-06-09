import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new BookstoreFrame("Bookstore Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
