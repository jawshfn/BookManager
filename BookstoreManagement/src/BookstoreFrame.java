import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookstoreFrame extends JFrame {

	private static final long serialVersionUID = -3894592697083509969L;
	private BookStore bookStore;
	private DefaultTableModel tableModel;

	public BookstoreFrame(String title) {
		super(title);

		this.bookStore = new BookStore();

		JPanel panel = new JPanel();
		this.setContentPane(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// Set up table model and table
		this.tableModel = new DefaultTableModel() {

			private static final long serialVersionUID = 7623590577496233415L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		this.tableModel.addColumn("Title");
		this.tableModel.addColumn("Author");
		this.tableModel.addColumn("Publisher");
		this.tableModel.addColumn("ISBN");
		this.tableModel.addColumn("Quantity");
		JTable table = new JTable(this.tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);

		JButton addButton = new JButton("Add Book");
		addButton.addActionListener(e -> {

			JDialog dialog = new AddBookDialog(this, bookStore);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);

			this.updateTable();
		});

		JButton removeButton = new JButton("Remove Book");

		removeButton.addActionListener(e -> {

			int selectedRow = table.getSelectedRow();

			if (selectedRow >= 0) {

				String isbn = (String) tableModel.getValueAt(selectedRow, 3); // Assuming ISBN is the fourth column

				bookStore.removeBook(isbn);

				updateTable();

				// If the removed book was not the last one in the list, restore the selection
				if (selectedRow < tableModel.getRowCount()) {
					table.setRowSelectionInterval(selectedRow, selectedRow);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please select a book to remove.");
			}
		});

		JButton removeAllButton = new JButton("Remove All");
		removeAllButton.addActionListener(e -> {

			int selectedRow = table.getSelectedRow();

			if (selectedRow >= 0) {
				String isbn = (String) tableModel.getValueAt(selectedRow, 3);

				bookStore.removeAllBooks(isbn);

				updateTable();
			} else {
				JOptionPane.showMessageDialog(this, "Please select a book to remove.");
			}
		});

		JButton userGuideButton = new JButton("User Guide");
		userGuideButton.addActionListener(e -> {

			Desktop desktop = Desktop.getDesktop();

			String userGuideUrl = "https://jawshfn.github.io/SimpleBookstoreManagementProgram/";

			try {

				desktop.browse(new URI(userGuideUrl));
			} catch (IOException | URISyntaxException ex) {

				ex.printStackTrace();
			}
		});

		ImageIcon logoIcon = new ImageIcon("book-icon.png");
		this.setIconImage(logoIcon.getImage());

		panel.add(userGuideButton);
		panel.add(addButton);
		panel.add(removeButton);
		panel.add(removeAllButton);
		panel.setBackground(Color.LIGHT_GRAY);
		addButton.setForeground(Color.WHITE);
		addButton.setBackground(new Color(70, 130, 180)); // SteelBlue
		removeButton.setForeground(Color.WHITE);
		removeButton.setBackground(new Color(70, 130, 180));
		removeAllButton.setForeground(Color.WHITE);
		removeAllButton.setBackground(new Color(70, 130, 180));
		userGuideButton.setForeground(Color.WHITE);
		userGuideButton.setBackground(new Color(70, 130, 180));
		table.setGridColor(Color.DARK_GRAY);
		table.setSelectionBackground(new Color(70, 130, 180));
		table.setSelectionForeground(Color.WHITE);
	}

	private void updateTable() {

		this.tableModel.setRowCount(0);

		for (Book book : this.bookStore.getAllBooks()) {
			this.tableModel.addRow(new Object[] { book.getTitle(), book.getAuthor(), book.getPublisher(),
					book.getISBN(), book.getQuantity() });
		}
	}
}
