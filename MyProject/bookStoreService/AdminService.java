package MyProject.bookStoreService;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStore.entities.TicketReturnBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService implements IActionService<Book> {
// chap nhan yeu cau mua truyen
    public void approveTicket( Scanner scanner, List<Ticket> tickets,ArrayList<Book> books) {
        if (tickets.isEmpty()) {
            System.out.println("Không có yêu cầu nào để duyệt.");
            return;
        }

        for (Ticket ticket : tickets) {
            if (ticket.isApproved()) {
                System.out.println("Yêu cầu thuê truyện " + ticket );
                continue;  // bo qua yeu cau da duoc duyet
            }
            System.out.println(ticket);
            System.out.print("Chấp nhận yêu cầu này? (y/n): ");
            String choice = scanner.nextLine();

            if ("y".equalsIgnoreCase(choice)) {
                Book book = findBookByTitle(ticket.getBookTitle(),books);
                if (book != null && book.getStock() >= ticket.getQuantity()) {
                    book.setStock(book.getStock() - ticket.getQuantity());
                    ticket.setApproved(true);
                    System.out.println("Yêu cầu đã được chấp nhận.");
                } else {
                    System.out.println("Yêu cầu không thể chấp nhận (không đủ số lượng trong kho).");
                }
            } else {
                System.out.println("Yêu cầu không được chấp nhận.");
            }
        }
    }
    // Chap nhan yeu cau thue truyen
    public void approveRentBook(Scanner scanner, ArrayList<Book> books, List<TicketRentBook> ticketRentBooks) {
        if (ticketRentBooks.isEmpty()) {
            System.out.println("Không có yêu cầu nào để duyệt.");
            return;
        }

        for (TicketRentBook ticketRentBook : ticketRentBooks) {
            // Kiểm tra xem yêu cầu đã được duyệt chưa
            if (ticketRentBook.isApproved()) {
                System.out.println("Yêu cầu thuê truyện " + ticketRentBook );
                continue;  // Bỏ qua yêu cầu đã được duyệt
            }

            // Hiển thị yêu cầu
            System.out.println(ticketRentBook);
            System.out.println("Ngày trả sách: " + ticketRentBook.getReturnDate());

            System.out.print("Chấp nhận yêu cầu này? (y/n): ");
            String choice = scanner.nextLine();

            if ("y".equalsIgnoreCase(choice)) {
                Book book = findBookByTitle(ticketRentBook.getBookTitle(), books);

                if (book != null && book.getStock() >= ticketRentBook.getQuantity()) {
                    // Giam so luong va cap nhat trang thai
                    book.setStock(book.getStock() - ticketRentBook.getQuantity());
                    ticketRentBook.setApproved(true);  // Danh dau yeu cau da duoc duyet
                    System.out.println("Yêu cầu đã được chấp nhận.");
                } else {
                    System.out.println("Yêu cầu không thể chấp nhận (không đủ số lượng trong kho).");
                    ticketRentBook.setApproved(false);  // yeu cau khong duoc chap nhan
                }
            } else {
                System.out.println("Yêu cầu không được chấp nhận.");
                ticketRentBook.setApproved(false);
            }
        }
    }
    // chap nhan yeu cau tra sach
    public void approveReturnBook(Scanner scanner, ArrayList<Book> books, List<TicketReturnBook> ticketReturnBooks) {
        if (ticketReturnBooks.isEmpty()) {
            System.out.println("Không có yêu cầu trả sách nào để duyệt.");
            return;
        }

        for (TicketReturnBook returnTicket : ticketReturnBooks) {
            if (returnTicket.isProcessed()) {
                System.out.println("Yêu cầu trả sách này đã được xử lý.");
                continue;
            }

            System.out.println("Yêu cầu trả sách: " + returnTicket);
            System.out.print("Chấp nhận yêu cầu này? (y/n): ");
            String choice = scanner.nextLine();

            if ("y".equalsIgnoreCase(choice)) {
                Book book = findBookByTitle(returnTicket.getBookTitle(), books);
                if (book != null) {
                    // Tang so luong sach trong kho
                    book.setStock(book.getStock() + returnTicket.getQuantity());

                    // Tinh tien phat dua tren condition
                    double fineAmount = 0;
                    if (returnTicket.getCondition() >= 1 && returnTicket.getCondition() <= 3) {
                        fineAmount = returnTicket.getQuantity() * book.getPrice() * 0.5; // 50% giá trị
                    }
                    returnTicket.setFineAmount(fineAmount);

                    returnTicket.setProcessed(true);
                    System.out.println("Yêu cầu trả sách đã được chấp nhận.");
                    System.out.printf("Tiền phạt: %.2f VND\n", fineAmount);
                } else {
                    System.out.println("Không tìm thấy sách trong danh sách.");
                }
            } else {
                System.out.println("Yêu cầu trả sách không được chấp nhận.");
            }
        }
    }



    // tim truyen theo tieu de
    public static Book findBookByTitle(String title,ArrayList<Book> books) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

// them truyen moi
    @Override
    public void insert(ArrayList<Book> objects) {
        System.out.println(objects);
        Scanner scanner = new Scanner(System.in);
        Book book = inputInfo(scanner);

        objects.add(book);
        System.out.println("Thêm truyện thành công!");
    }

    @Override
    public Book inputInfo(Scanner scanner) {
        System.out.print("Nhập tên truyện mới: ");
        String title = scanner.nextLine();

        System.out.print("Nhập thể loại: ");
        String genre = scanner.nextLine();

        System.out.print("Nhập giá bán: ");
        double price = Double.parseDouble(scanner.nextLine());

        double rating = 0;

        System.out.print("Nhập số lượng trong kho: ");
        int stock = Integer.parseInt(scanner.nextLine());

        int condition;
        while (true) {
            try {
                System.out.print("Nhập tình trạng truyện (1-5): ");
                condition = Integer.parseInt(scanner.nextLine());

                if (condition >= 1 && condition <= 5) {
                    break;
                } else {
                    System.out.println("Tình trạng phải trong khoảng từ 1 đến 5. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên từ 1 đến 5.");
            }
        }

        return new Book(title, genre, price, rating, stock, condition);
    }
// sua truyen
    @Override
    public void update(ArrayList<Book> objects) {
        if (objects.isEmpty()) {
            System.out.println("Chưa có truyện nào trong kho!");
        } else {
            System.out.println(objects);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id truyện muốn sửa: ");
        int id = Integer.parseInt(scanner.nextLine());
        BookService bookService = new BookService();
        Book book = bookService.findBookById(id, objects);

        if (book != null) {
            System.out.println(book);
            System.out.print("Nhập tên truyện: ");
            String title = scanner.nextLine();
            System.out.print("Nhập thể loại: ");
            String genre = scanner.nextLine();
            System.out.print("Nhập giá bán: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Nhập số lượng trong kho: ");
            int stock = Integer.parseInt(scanner.nextLine());

            int condition;
            while (true) {
                try {
                    System.out.print("Nhập tình trạng truyện (1-5): ");
                    condition = Integer.parseInt(scanner.nextLine());

                    if (condition >= 1 && condition <= 5) {
                        book.setTitle(title);
                        book.setGenre(genre);
                        book.setPrice(price);
                        book.setStock(stock);
                        book.setCondition(condition);
                        System.out.println("Sửa truyện thành công!");
                        break;
                    } else {
                        System.out.println("Tình trạng truyện phải trong khoảng từ 1-5. Vui lòng nhập lại.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập một số nguyên từ 1 đến 5.");
                }
            }
        } else {
            System.out.println("Truyện không tồn tại!");
        }
    }

// xoa truyen
    @Override
    public void delete(ArrayList<Book> objects) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id truyện muốn xoá: ");
        BookService bookService = new BookService();
        int id = Integer.parseInt(scanner.nextLine());

        Book book = bookService.findBookById(id,objects);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Truyện không tồn tại!");
        }
        System.out.println("Bạn chắc chắn muốn xoá chứ?");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            objects.remove(book);
            System.out.println("Xoá truyện thành công!");
        }


    }

}
