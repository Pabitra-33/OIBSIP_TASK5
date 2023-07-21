package mylibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  //used to take user input

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {  //constructor
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {  //getter method
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available: " + available;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }
}

class UserModule {
    private Library library;
    private Scanner scanner;

    public UserModule(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void displayBooks() {
        List<Book> books = library.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books available in the library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void borrowBook() {
        displayBooks();
        if (library.getBooks().isEmpty()) {
            return;
        }
        System.out.print("Enter the title of the book you want to borrow: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (!book.isAvailable()) {
            System.out.println("The book is not available for borrowing.");
        } else {
            book.setAvailable(false);
            System.out.println("You have successfully borrowed the book: " + book.getTitle());
        }
    }

    public void returnBook() {
        System.out.print("Enter the title of the book you want to return: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isAvailable()) {
            System.out.println("The book is already available in the library.");
        } else {
            book.setAvailable(true);
            System.out.println("You have successfully returned the book: " + book.getTitle());
        }
    }

    private Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

class AdminModule {
    private Library library;
    private Scanner scanner;

    public AdminModule(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void addBook() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        Book book = new Book(title, author);
        library.addBook(book);
        System.out.println("The book has been added to the library.");
    }

    public void removeBook() {
        System.out.print("Enter the title of the book you want to remove: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            library.removeBook(book);
            System.out.println("The book has been removed from the library.");
        }
    }

    public void displayBooks() {
        List<Book> books = library.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books available in the library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private Book findBookByTitle(String title) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        UserModule userModule = new UserModule(library);
        AdminModule adminModule = new AdminModule(library);

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========= Digital Library Management System =========");
            System.out.println("1. User Module");
            System.out.println("2. Admin Module");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: "); //take input from user
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the input buffer

            switch (choice) {
                case 1:
                    userMenu(userModule);
                    break;
                case 2:
                    adminMenu(adminModule);
                    break;
                case 0:
                    System.out.println("Thank you for using the Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    private static void userMenu(UserModule userModule) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========= User Module =========");
            System.out.println("1. Display available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("0. Go back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the input buffer

            switch (choice) {
                case 1:
                    userModule.displayBooks();
                    break;
                case 2:
                    userModule.borrowBook();
                    break;
                case 3:
                    userModule.returnBook();
                    break;
                case 0:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }

    private static void adminMenu(AdminModule adminModule) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("========= Admin Module =========");
            System.out.println("1. Add a book");
            System.out.println("2. Remove a book");
            System.out.println("3. Display available books");
            System.out.println("0. Go back");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear the input buffer

            switch (choice) {
                case 1:
                    adminModule.addBook();
                    break;
                case 2:
                    adminModule.removeBook();
                    break;
                case 3:
                    adminModule.displayBooks();
                    break;
                case 0:
                    System.out.println("Returning to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);
    }
}