package MyProject.viewBookStore;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStore.entities.TicketReturnBook;
import MyProject.bookStoreService.AdminService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    public void displayMenuAdmin(Scanner scanner, ArrayList<Book> books, List<Ticket> tickets, List<TicketRentBook> ticketRentBooks, List<TicketReturnBook> ticketReturnBooks) {
        AdminService adminService = new AdminService();
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - Thêm truyện");
            System.out.println("2 - Sửa truyện");
            System.out.println("3 - Xóa truyện");
            System.out.println("4 - Xác nhận yêu cầu mua truyện ");
            System.out.println("5 - Xác nhận yêu cầu thuê truyện");
            System.out.println("6 - Xác nhận yêu cầu trả truyện");
            System.out.println("0 - Quay lại ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> adminService.insert(books);
                case 2 -> adminService.update(books);
                case 3 -> adminService.delete(books);
                case 4 -> adminService.approveTicket(scanner,tickets,books);
                case 5 -> adminService.approveRentBook(scanner,books,ticketRentBooks);
                case 6 -> adminService.approveReturnBook(scanner,books,ticketReturnBooks);
                case 0 -> {
                    System.out.println("Đã trở lại Menu chính.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }

    }
}
