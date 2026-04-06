//100% not AI generated guaranteed.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;



public class Project02Q02 extends Application {
    private Library library = new Library(); //Library to store books

    private TextArea outputArea = new TextArea(); //Textbox area
    private ImageView imageView = new ImageView(); // ImageView for displaying book cover


    @Override
    public void start(Stage primaryStage) {
        library.loadBooksFromFile("books.txt");

        // TextFields
        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        TextField imageISBNField = new TextField(); // input field for displaying image
        imageISBNField.setPromptText("Enter ISBN for image");


        // Buttons for the different library functions
        Button addButton = new Button("Add Book");
        Button removeButton = new Button("Remove Book");
        Button searchISBNButton = new Button("Search by ISBN");
        Button searchTitleButton = new Button("Search by Title");
        Button listAllButton = new Button("List All Books");
        Button saveButton = new Button("Save to File");
        Button imageButton = new Button("Display Image by ISBN"); // New button


        // Output TextArea
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200);


        // Configure ImageView
        imageView.setFitHeight(250);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);


        // Layouts
        VBox inputBox = new VBox(10, isbnField, titleField, authorField);
        VBox buttonBox = new VBox(10, addButton, removeButton, searchISBNButton, searchTitleButton, listAllButton, saveButton);
        VBox imageBox = new VBox(10, new Label("Book Cover Image:"), imageISBNField, imageButton, imageView);
        imageBox.setPadding(new Insets(10)); //border of container

        VBox outputBox = new VBox(10, new Label("Output:"), outputArea);
        outputBox.setPadding(new Insets(10));

        HBox mainLayout = new HBox(15, inputBox, buttonBox, outputBox, imageBox);
        mainLayout.setPadding(new Insets(15));

        // Event Handlers
        addButton.setOnAction(e -> {
            library.addBook(isbnField.getText(), titleField.getText(), authorField.getText());
            outputArea.setText("Book added: " + isbnField.getText() + ", " + titleField.getText() + ", " + authorField.getText());
            clearFields(isbnField, titleField, authorField);
        }); //This gets the book fields, gets their text, and makes a new book object with that field. Clears the textbox fields so new books can be added.

        removeButton.setOnAction(e -> { 
            library.removeBook(isbnField.getText());
            outputArea.setText("Attempted to remove book with ISBN: " + isbnField.getText());
            clearFields(isbnField, titleField, authorField);
        }); //Remove book from library if the book exists

        searchISBNButton.setOnAction(e -> {
            StringBuilder result = new StringBuilder(); //Stringbuilder to get the book

            for (Book book : library.getBooks()) {
                if (book.getIsbn().equalsIgnoreCase(isbnField.getText())) { //i the book is found in the library (by looping)
                    result.append("Book found: ").append(book).append("\n"); //appends the book with a string + the name + \n
                }
            }
            if (result.isEmpty()) result.append("No book found with ISBN: ").append(isbnField.getText());
            outputArea.setText(result.toString()); //if the book isnt found, output the textbox with the isbn you entered
        });

        searchTitleButton.setOnAction(e -> {
            StringBuilder result = new StringBuilder();
            boolean found = false;

            for (Book book : library.getBooks()) {
                if (book.getTitle().toLowerCase().contains(titleField.getText().toLowerCase())) { //lowercase so its easier to search for
                    result.append("Book found: ").append(book).append("\n"); // look for title by looking through
                    found = true;
                }
            }
            if (!found) result.append("No books found with title containing: ").append(titleField.getText()); //if no found, output textarea with what you looked for
            outputArea.setText(result.toString());
        });

        listAllButton.setOnAction(e -> {
            StringBuilder result = new StringBuilder();

            if (library.getBooks().isEmpty()) { //self explanatory
                result.append("No books in the library at the moment.");
            } 
            
            else {
                result.append("List of all books:\n"); //show all the books in the library
                for (Book book : library.getBooks()) {
                    result.append("ISBN: ").append(book.getIsbn())
                          .append(", Title: ").append(book.getTitle())
                          .append(", Author: ").append(book.getAuthor()).append("\n");
                }
            }
            
            outputArea.setText(result.toString());
        });


        saveButton.setOnAction(e -> {
            library.saveBooksToFile("books.txt");
            outputArea.setText("Books saved to books.txt successfully!");
        }); //save them to a file


        imageButton.setOnAction(e -> {
            String isbn = imageISBNField.getText().trim();
            File file = new File("images/" + isbn + ".jpg");

            if (file.exists()) {
                Image img = new Image(file.toURI().toString());
                imageView.setImage(img);
                outputArea.setText("Displaying image for ISBN: " + isbn);
            } 
            
            else {
                imageView.setImage(null);
                outputArea.setText("Image file not found for ISBN: " + isbn);
            }
        }); //image functions

        // Final setup
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(mainLayout, 1000, 500));
        primaryStage.show();
    }

    private void clearFields(TextField isbn, TextField title, TextField author) { //function to clear the input textfields for reuse
        isbn.clear();
        title.clear();
        author.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//This uses the same code for Question 01
//The Library class maintains a list of Book objects and provides methods to add, remove, search, and list books. It also handles loading and saving the book data from/to a text file.
class Library {
    // An ArrayList to store all Book objects in the library
    //PRIVATE
    private List<Book> books;
    
     //Constructor for the Library class. Initializes the books list to an empty ArrayList.
     
    //PUBLIC
    public Library() {
        books = new ArrayList<>();
    }


    //Function to add new book to library.
    public void addBook(String isbn, String title, String author) {
        // Create a new Book object
        Book newBook = new Book(isbn, title, author);
        // Add it to the books list
        books.add(newBook);
        System.out.println("Book added successfully!");
    }


    //Removes a book from that library based on the isbn
    public void removeBook(String isbn) {
        // Iterate over the list to find the book to remove
        for (int i = 0; i < books.size(); i++) {
            // Compare ISBN to find a match
            if (books.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                books.remove(i);
                System.out.println("Book removed successfully!");
                return; // Exit once the book is removed
            }
        }
        // If no book is found, print message
        System.out.println("No book found with ISBN: " + isbn);
    }


    //Similar code that removes the books
    public void searchByISBN(String isbn) {
        // Go through each book in the list
        for (Book book : books) {
            // Check if the ISBN matches (ignoring case)
            if (book.getIsbn().equalsIgnoreCase(isbn)) { //equalsignorecase used to comapre two strings, ignoring the case of the characters in there.
                System.out.println("Book found: " + book);
                return;
            }
        }
        // If no match was found, print message
        System.out.println("No book found with ISBN: " + isbn);
    }


    //Similar to remove and search function, but by title.
    public void searchByTitle(String title) {
        // Convert the search term to lowercase for case-insensitive matching
        String lowerCaseTitle = title.toLowerCase();

        // A bool to track if we found any matches
        boolean foundAny = false; //Bool variable for clean

        for (Book book : books) { //for each books in the boobs-array
            // Compare lower-case versions of the titles
            if (book.getTitle().toLowerCase().contains(lowerCaseTitle)) {
                System.out.println("Book found: " + book);
                foundAny = true;
            }
        }

        // If after checking all books, no matches were found, inform the user
        if (!foundAny) {
            System.out.println("No books found with title containing: " + title);
        }
    }

    //Lists all books that are in the libary (file). If its empty, print a message.
    public void listAllBooks() {
        if (books.isEmpty()) { //condition to check if libary empty
            System.out.println("No books in the library at the moment.");
            return;
        }

        System.out.println("List of all books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }


    //Loads book data from a file. The file is expected to have lines in the format: ISBN,Title,Author
    public void loadBooksFromFile(String filename) {
        File file = new File(filename); //make new file object
        // Check if the file exists before trying to read
        if (!file.exists()) {
            // If the file doesn't exist, there's nothing to load, so just return
            System.out.println("File " + filename + " not found. Starting with an empty library.");
            return;
        }

        // Use a Scanner object to read the file line by line
        try (Scanner scanner = new Scanner(file)) { //create scanner

            while (scanner.hasNextLine()) { //checks to make sure if another line of input is avaliable, hence the name "hasnextLine"
                String line = scanner.nextLine(); // Read the entire line and store it in this line variable
                String[] parts = line.split(",");// Split each line made by a comma

                if (parts.length == 3) { //isbn, name, author
                    // Create a new Book object using the split parts
                    Book book = new Book(parts[0].trim(), parts[1].trim(), parts[2].trim()); //trim used to remove whitespace so they are close together, and seperated by comma. Create these book objects...
                    
                    books.add(book);// And Add it to our list of books-array
                }
            }
            System.out.println("Books loaded from file: " + filename); //It is finished
        } 
        
        catch (IOException e) {
            System.out.println("Error reading file " + filename + ": " + e.getMessage());

        }
    }


    //Saves the current list of books to the specified file.Each book is saved in the format: ISBN,Title,Author
    public void saveBooksToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) { // Use a FileWriter in a try-with-resources block to handle closing
            for (Book book : books) { // Write each book in books-array on a new line
                writer.write(book.toString() + "\n"); //writes the book object followed by a new line using the filewriter object
            }

            System.out.println("Books saved to file: " + filename);

        } 
        
        catch (IOException e) {
            System.out.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }

    public List<Book> getBooks() { //Getter moethod for books
        return books;
    }
}


class Book { //Book class represents a book object in a library
    // Attributes for each book
    //PRIVATE
    private String isbn;
    private String title;
    private String author;


    //PUBLIC
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }


    public void setIsbm(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        return getIsbn() + "," + getTitle() + "," + getAuthor();
    }
}