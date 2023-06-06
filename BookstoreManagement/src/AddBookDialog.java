import javax.swing.*;

public class AddBookDialog extends JDialog {

	private static final long serialVersionUID = -8274393330079767455L;
	private BookStore bookStore;
	private JTextField titleField;
	private JTextField authorField;
	private JTextField publisherField;
	private JTextField isbnField;
	private JTextField quantityField;

	public AddBookDialog(JFrame parent, BookStore bookStore) {
		super(parent, "Add Book", true);
		this.bookStore = bookStore;

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		titleField = new JTextField(20);
		authorField = new JTextField(20);
		publisherField = new JTextField(20);
		isbnField = new JTextField(20);
		quantityField = new JTextField(20);

		add(new JLabel("Title:"));
		add(titleField);

		add(new JLabel("Author:"));
		add(authorField);

		add(new JLabel("Publisher:"));
		add(publisherField);

		add(new JLabel("ISBN:"));
		add(isbnField);

		add(new JLabel("Quantity:"));
		add(quantityField);

		JButton submitButton = new JButton("Add Book");
		submitButton.addActionListener(e -> {

			String title = titleField.getText();
			String author = authorField.getText();
			String publisher = publisherField.getText();
			String isbn = isbnField.getText();
			String quantityText = quantityField.getText();

			int quantity;
			try {
				double quantityDouble = Double.parseDouble(quantityText);
				if (quantityDouble != Math.floor(quantityDouble) || quantityDouble != Math.ceil(quantityDouble)) {
					JOptionPane.showMessageDialog(this, "Quantity must be a whole number.");
					return;
				}
				quantity = (int) quantityDouble;
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Quantity must be a valid number.");
				return;
			}
			if (quantity < 0) {
				JOptionPane.showMessageDialog(this, "Quantity must be a positive number.");
				return;
			}

			Book book = new Book(title, author, publisher, isbn, quantity);
			this.bookStore.addBook(book);

			this.dispose();
		});
		add(submitButton);

		pack();
	}
}
