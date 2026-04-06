//This code 100% guaranteed to not have been made with AI
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



//Library management system provides a menu and loads books on the startup from the code upon running. When user exists, the data is saved automatically
public class Project02Q1 {
    public static void main(String[] args) {
        // Create a Library object to manage our books
        Library library = new Library();

        // Load existing books from the file at startup
        String dataFilename = "library_data.txt"; //This file has to be in the same folder
        library.loadBooksFromFile(dataFilename); //loads on startup

        // Create a Scanner for reading user input
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;  // This flag will track whether the user wants to exit

        while (!exit) { // Continue showing the menu until the user chooses to exit
            //Menu (This can be a function)
            System.out.println("\n----- Library Management System -----");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by ISBN");
            System.out.println("4. Search Book by Title");
            System.out.println("5. List All Books");
            System.out.println("6. Save Books to File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");

            String choice = scanner.nextLine().trim(); //to remove whitespace

            // Perform different actions depending on the choice
            switch (choice) {
                case "1":
                    // Adding a new book
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine().trim();

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine().trim();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine().trim();

                    // Add the book to the library
                    library.addBook(isbn, title, author);
                    break;

                case "2":
                    // Removing a book
                    System.out.print("Enter the ISBN of the book to remove: ");
                    String isbnToRemove = scanner.nextLine().trim();
                    library.removeBook(isbnToRemove);
                    break;

                case "3":
                    // Search by ISBN
                    System.out.print("Enter ISBN to search: ");
                    String isbnToSearch = scanner.nextLine().trim();
                    library.searchByISBN(isbnToSearch);
                    break;

                case "4":
                    // Search by Title
                    System.out.print("Enter title to search: ");
                    String titleToSearch = scanner.nextLine().trim();
                    library.searchByTitle(titleToSearch);
                    break;

                case "5":
                    // List all books
                    library.listAllBooks();
                    break;

                case "6":
                    // Save books to file
                    library.saveBooksToFile(dataFilename);
                    break;

                case "7":
                    // Exit the program
                    System.out.println("Exiting the program...");
                    exit = true;
                    break;

                default:
                    // Invalid choice handling
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        // Before the program completely exits, save the books to the file
        // to ensure all changes are persisted
        library.saveBooksToFile(dataFilename);

        // Close the scanner to release system resources
        scanner.close();
        System.out.println("Program has been terminated. Thank you for using the Library Management System!");
    }
}


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
