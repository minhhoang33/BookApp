package MyProject.viewBookStore;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStore.entities.TicketReturnBook;
import MyProject.bookStoreService.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    public void displayMenuCustomer(Scanner scanner, ArrayList<Book> books, List<Ticket> tickets, List<TicketRentBook> ticketRentBooks, List<TicketReturnBook> ticketReturnBooks) {
        CustomerService customerService = new CustomerService();
        while (true) {
            System.out.println("=====Chào mừng bạn đến với cửa hàng sách !=====");
            System.out.println("1 - Xem danh sách truyện");
            System.out.println("2 - Đánh giá truyện");
            System.out.println("3 - Mua truyện");
            System.out.println("4 - Thuê truyện");
            System.out.println("5 - Trả truyện");
            System.out.println("6 - Xem lịch sử giao dịch");
            System.out.println("7 - Xem trạng thái đơn hàng");
            System.out.println("0 - Quay lại");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> customerService.viewBooks(books);
                case 2 -> customerService.rateBook(scanner, books);
                case 3 -> customerService.purchaseOrRentBook(scanner,tickets,books);
                case 4 -> customerService.RenBook(scanner,books,ticketRentBooks);
                case 5 -> customerService.returnBook(scanner,books,ticketRentBooks);
                case 6 -> {
                    System.out.print("Nhập tên của bạn: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số điện thoại của bạn: ");
                    String phone = scanner.nextLine();
                    customerService.viewTransactionHistory(name, phone, tickets, ticketRentBooks, ticketReturnBooks);
                }
                case 7 -> {
                    System.out.print("Nhập tên của bạn: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số điện thoại của bạn: ");
                    String phone = scanner.nextLine();
                    customerService.viewOrderStatus(name, phone, tickets, ticketRentBooks, ticketReturnBooks);
                }
                case 0 -> {
                    System.out.println("Đã trở lại Menu chính.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
