import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryChatbot {
    static class Book {
        String title;
        String author;
        boolean isAvailable;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isAvailable = true; 
        }

        @Override
        public String toString() {
            return "\"" + title + "\" by " + author + (isAvailable ? " (Available)" : " (Checked Out)");
        }
    }
    private List<Book> books;
    private Scanner scanner;

    public LibraryChatbot() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeBooks();
    }
    private void initializeBooks() {
        books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book("Pride and Prejudice", "Jane Austen"));
    }
    public void start() {
        System.out.println("Hello! I am your Library Chatbot. How may I assist you today?");
        printHelp();

        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("exit") || input.equals("quit")) {
                System.out.println("Chatbot: Thank you for visiting the library. Goodbye!");
                break;
            } else if (input.equals("help")) {
                printHelp();
            } else if (input.equals("list books") || input.equals("show books")) {
                listBooks();
            } else if (input.startsWith("search ")) {
                String query = input.substring(7).trim();
                searchBooks(query);
            } else {
                System.out.println("Chatbot: Sorry, I didn't understand that. Type 'help' to see commands.");
            }
        }
    }
    private void printHelp() {
        System.out.println("You can use the following commands:");
        System.out.println("- 'list books' or 'show books': to see all available books");
        System.out.println("- 'search <keyword>': to search books by title or author");
        System.out.println("- 'help': to see this help message again");
        System.out.println("- 'exit' or 'quit': to exit the chatbot");
    }
    private void listBooks() {
        System.out.println("Chatbot: Here are the books in the library:");
        for (Book book : books) {
            System.out.println(" - " + book);
        }
    }
    private void searchBooks(String keyword) {
        System.out.println("Chatbot: Searching for books matching \"" + keyword + "\"...");
        boolean found = false;
        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword.toLowerCase()) ||
                book.author.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(" - " + book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Chatbot: No books found matching \"" + keyword + "\".");
        }
    }
    public static void main(String[] args) {
        LibraryChatbot chatbot = new LibraryChatbot();
        chatbot.start();
    }
}
