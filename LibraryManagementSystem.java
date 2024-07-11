import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
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
}


class User {
    private String name;
    private int userId;
    private List<Book> borrowedBooks;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

  
    public void addBook(Book book) {
        books.add(book);
    }

    
    public void registerUser(User user) {
        users.add(user);
    }

    
    public void borrowBook(int userId, String bookTitle) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        Book book = findBookByTitle(bookTitle);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.isAvailable()) {
            user.borrowBook(book);
            System.out.println(user.getName() + " has borrowed: " + book.getTitle());
        } else {
            System.out.println("Book is not available for borrowing.");
        }
    }

    public void returnBook(int userId, String bookTitle) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        Book book = findBookByTitle(bookTitle);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (user.getBorrowedBooks().contains(book)) {
            user.returnBook(book);
            System.out.println(user.getName() + " has returned: " + book.getTitle());
        } else {
            System.out.println("User has not borrowed this book.");
        }
    }

  
    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private Book findBookByTitle(String bookTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                return book;
            }
        }
        return null;
    }
}


public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("1984", "George Orwell"));

        User user1 = new User("Alice", 1);
        User user2 = new User("Bob", 2);
        library.registerUser(user1);
        library.registerUser(user2);

        library.borrowBook(1, "To Kill a Mockingbird");
        library.borrowBook(2, "1984");
        library.returnBook(1, "To Kill a Mockingbird");
        library.returnBook(2, "1984");
    }
}