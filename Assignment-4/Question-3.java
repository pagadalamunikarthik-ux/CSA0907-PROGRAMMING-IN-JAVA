
import java.awt.*;
import java.awt.event.*;

public class LibrarySearch extends Frame {

    public LibrarySearch() {

        setTitle("Campus Library Search");

        setLayout(new BorderLayout(10, 10));

        // Top title
        Label title = new Label("Campus Library Search", Label.CENTER);
        add(title, BorderLayout.NORTH);

        // Search row
        Panel searchPanel = new Panel(new FlowLayout());

        TextField searchField = new TextField(20);
        Choice category = new Choice();

        category.add("Books");
        category.add("Authors");
        category.add("Subjects");

        Button searchButton = new Button("Search");

        searchPanel.add(searchField);
        searchPanel.add(category);
        searchPanel.add(searchButton);

        // Results area
        TextArea results = new TextArea();
        results.setEditable(false);

        // Bottom buttons
        Panel buttonPanel = new Panel(new FlowLayout());

        Button prev = new Button("Prev");
        Button next = new Button("Next");
        Button reserve = new Button("Reserve");

        buttonPanel.add(prev);
        buttonPanel.add(next);
        buttonPanel.add(reserve);

        // Center section
        Panel centerPanel = new Panel(new BorderLayout());

        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(results, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        // Status message
        Label status = new Label("Ready to search");
        add(status, BorderLayout.SOUTH);

        // Search button action
        searchButton.addActionListener(e -> {
            results.setText("Searching for: " + searchField.getText()
                    + "\nCategory: " + category.getSelectedItem());
            status.setText("Search completed");
        });

        // Close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(600, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LibrarySearch();
    }
}
