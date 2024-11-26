package MyProject.login_logout;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStore.entities.TicketReturnBook;
import MyProject.login_logout.entities.User;
import MyProject.login_logout.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Book> books = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();
    private static List<TicketRentBook> ticketRentBooks = new ArrayList<>();
    private static List<TicketReturnBook> ticketReturnBooks = new ArrayList<>();

    static {
        // tai khoan admin
        users.add(new User("admin", "admin@example.com", "123", true));
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu(scanner, users,books,tickets,ticketRentBooks,ticketReturnBooks);
    }
}

