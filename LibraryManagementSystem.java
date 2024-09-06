import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Issued: " + isIssued;
    }
}

public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> searchBook();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        books.add(new Book(title, author, isbn));
        System.out.println("Book added successfully.");
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void issueBook() {
        System.out.print("Enter ISBN of the book to issue: ");
        String isbn = scanner.nextLine();
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            if (!book.isIssued()) {
                book.setIssued(true);
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Book is already issued.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter ISBN of the book to return: ");
        String isbn = scanner.nextLine();
        Book book = findBookByIsbn(isbn);
        if (book != null) {
            if (book.isIssued()) {
                book.setIssued(false);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book was not issued.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void searchBook() {
        System.out.print("Enter title or author or ISBN to search: ");
        String keyword = scanner.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) ||
                book.getAuthor().equalsIgnoreCase(keyword) ||
                book.getIsbn().equalsIgnoreCase(keyword)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private static Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }
}
