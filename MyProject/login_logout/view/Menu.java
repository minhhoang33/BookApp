package MyProject.login_logout.view;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStore.entities.TicketReturnBook;
import MyProject.login_logout.entities.User;
import MyProject.login_logout.service.LoginLogOutService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public void displayMenu(Scanner scanner, List<User> users, ArrayList<Book> books, List<Ticket> tickets, List<TicketRentBook> ticketRentBooks, List<TicketReturnBook> ticketReturnBooks) {
        LoginLogOutService loginLogOutService = new LoginLogOutService();
        while (true) {
            System.out.println("Chọn lựa chọn:");
            System.out.println("1 - Đăng nhập");
            System.out.println("2 - Đăng ký");
            System.out.println("0 - Thoát chương trình");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    loginLogOutService.login(scanner, users,books,tickets,ticketRentBooks,ticketReturnBooks);
                    break;
                case 2:
                    loginLogOutService.register(scanner, users);
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }
}

