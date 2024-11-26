package MyProject.bookStoreService;

import MyProject.InvalidPhoneNumberException;
import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStore.entities.TicketReturnBook;
import MyProject.login_logout.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerService {
    // xem truyen
    public void viewBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Chưa có truyện nào trong kho T_T");
        }
        books.sort(Comparator.comparingDouble(Book::getRating).reversed());
        System.out.println("Danh sách truyện:");
        for (Book book : books) {
            System.out.println("ID: "+ book.getId() +  " ,Tên: " + book.getTitle() + ", Thể loại: " + book.getGenre() +
                    ", Giá: " + book.getPrice() + ", Đánh giá: " + book.getRating() +
                    ", Số lượng trong kho: " + book.getStock() + ",Tình trạng truyện:" + book.getCondition());
        }
    }
    // danh gia truyen
    public void rateBook(Scanner scanner, ArrayList<Book> books) {
        System.out.print("Nhập tên truyện muốn đánh giá: ");
        String title = scanner.nextLine();
        Book book = AdminService.findBookByTitle(title,books);

        if (book != null) {
            System.out.print("Nhập đánh giá của bạn (từ 1 đến 5): ");
            double customerRating = Double.parseDouble(scanner.nextLine());

            if (customerRating >= 1 && customerRating <= 5) {
                book.addRating(customerRating);
                System.out.println("Cảm ơn bạn đã đánh giá! Đánh giá trung bình hiện tại của truyện là: " + book.getRating());
            } else {
                System.out.println("Đánh giá không hợp lệ. Vui lòng nhập giá trị từ 1 đến 5.");
            }
        } else {
            System.out.println("Truyện không tồn tại.");
        }
    }

// mua truyen
  public void purchaseOrRentBook(Scanner scanner,List<Ticket> tickets,ArrayList<Book> books) {
        System.out.print("Nhập tên truyện muốn mua: ");
        String title = scanner.nextLine();
        Book book = AdminService.findBookByTitle(title, books);

      if (book != null) {
          System.out.print("Nhập tên của bạn: ");
          String customerName = scanner.nextLine();

          String customerPhone;
          while (true) {
              System.out.print("Nhập số điện thoại của bạn: ");
              customerPhone = scanner.nextLine();
              try {
                  validatePhoneNumber(customerPhone);
                  break;
              } catch (InvalidPhoneNumberException e) {
                  System.out.println(e.getMessage());
              }
          }

            System.out.print("Nhập số lượng truyện muốn mua: ");
            int quantity = Integer.parseInt(scanner.nextLine());
          if (quantity > 0 && quantity <= book.getStock()) {
              Ticket ticket = new Ticket(customerName, customerPhone, title, quantity);
              double totalAmount = book.getPrice() * quantity; // Tong tien
              ticket.setTotalAmount(totalAmount);
              tickets.add(ticket);

              System.out.println("Số tiền bạn phải thanh toán là: " + totalAmount);
              System.out.println("Đỗ Minh Hoàng || Techcombank || 19037623898018");
              System.out.println("Khi bạn chuyển khoản thành công admin sẽ chấp nhận yêu cầu mua hàng.");
          } else {
              System.out.println("Số lượng không hợp lệ. Vui lòng kiểm tra lại.");
          }
        } else {
            System.out.println("Truyện không tồn tại.");
        }
    }
    // thue truyen
    public void RenBook(Scanner scanner, ArrayList<Book> books, List<TicketRentBook> ticketRentBooks) {
        System.out.print("Nhập tên truyện muốn thuê: ");
        String title = scanner.nextLine();
        Book book = AdminService.findBookByTitle(title, books);

        if (book != null) {
            System.out.print("Nhập tên của bạn: ");
            String customerName = scanner.nextLine();

            String customerPhone;
            while (true) {
                System.out.print("Nhập số điện thoại của bạn: ");
                customerPhone = scanner.nextLine();
                try {
                    validatePhoneNumber(customerPhone);
                    break;
                } catch (InvalidPhoneNumberException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Nhập số lượng truyện muốn thuê: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            if (quantity > 0 && quantity <= book.getStock()) {
                LocalDate returnDate;
                while (true) {
                    System.out.print("Nhập ngày trả sách (yyyy-MM-dd): ");
                    String returnDateString = scanner.nextLine();
                    try {
                        returnDate = LocalDate.parse(returnDateString, DateTimeFormatter.ISO_LOCAL_DATE);
                        if (returnDate.isAfter(LocalDate.now())) {
                            break; // Ngay tra hop le, thoat khoi vong lap
                        } else {
                            System.out.println("Ngày trả phải lớn hơn ngày hiện tại. Vui lòng nhập lại.");
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Định dạng ngày không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
                    }
                }

                TicketRentBook ticketRentBook = new TicketRentBook(customerName,customerPhone, title, quantity, returnDate);
                ticketRentBooks.add(ticketRentBook);
                // tinh tien coc
                double deposit = ((book.getPrice() *50/100))*quantity;
                // tinh phi thue truyen
                long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), returnDate);
                int retalFee = Math.toIntExact((daysBetween * 5000)* quantity);
                System.out.println("Số tiền bạn phải cọc trước là(phải cọc trước 50% gtri đơn hàng): " + deposit);
                System.out.println("Đỗ Minh Hoàng || Techcombank || 19037623898018");
                System.out.println("Số ngày bạn thuê truyện là " + daysBetween + " ngày");
                System.out.println("Phí thuê truyện của bạn là: " + retalFee + " Vui lòng thanh toán khi trả sách");
                System.out.println("Khi bạn chuyển khoản thành công admin sẽ chấp nhận yêu cầu thuê truyện");

            } else {
                System.out.println("Số lượng không hợp lệ. Vui lòng kiểm tra lại.");
            }
        } else {
            System.out.println("Truyện không tồn tại.");
        }
    }
    // tra truyen
    public void returnBook(Scanner scanner, ArrayList<Book> books, List<TicketRentBook> ticketRentBooks) {
        System.out.print("Nhập tên truyện muốn trả: ");
        String title = scanner.nextLine();
        System.out.println("Nhập sdt:");
        String customerPhone = scanner.nextLine();

        TicketRentBook ticketRentBook = findTicketByTitleAndPhone(title,customerPhone,ticketRentBooks);

        if (ticketRentBook != null) {

            System.out.println("Thông tin thuê truyện: ");
            System.out.println("Tên khách hàng: " + ticketRentBook.getCustomerName());
            System.out.println("Số điện thoại: " + ticketRentBook.getCustomerPhone());
            System.out.println("Số lượng truyện: " + ticketRentBook.getQuantity());
            System.out.println("Ngày trả dự kiến: " + ticketRentBook.getReturnDate());

            // Kiem tra khach hang co tra dung han hay k
            LocalDate returnDate = LocalDate.now();
            long daysLate = ChronoUnit.DAYS.between(ticketRentBook.getReturnDate(), returnDate);

            if (daysLate > 0) {
                // Tra muon
                System.out.println("Truyện trả muộn. Số ngày trễ: " + daysLate);
                // Tien phat
                long fine = daysLate * 5000;
                System.out.println("Số tiền phạt bạn phải trả: " + fine + " VND");
            } else {
                System.out.println("Truyện trả đúng hạn.");
            }
            // Cap nhat so luong sach trong kho
            Book book = AdminService.findBookByTitle(title, books);
            if (book != null) {
                System.out.println("Hãy đợi Admin chấp nhận yêu cầu của bạn!");
            } else {
                System.out.println("Truyện không tồn tại trong hệ thống.");
            }
        } else {
            System.out.println("Không tìm thấy thông tin thuê sách với tên truyện và số điện thoại đã nhập.");
        }
    }

    // Tim ticket thue sach theo ten va sdt
    private TicketRentBook findTicketByTitleAndPhone(String title, String customerPhone, List<TicketRentBook> ticketRentBooks) {
        for (TicketRentBook ticketRent : ticketRentBooks) {
            if (ticketRent.getBookTitle().equalsIgnoreCase(title) && ticketRent.getCustomerPhone().equals(customerPhone)) {
                return ticketRent;
            }
        }
        return null;
    }
    // lich su
    public void viewTransactionHistory(String customerName, String customerPhone,
                                       List<Ticket> tickets,
                                       List<TicketRentBook> ticketRentBooks,
                                       List<TicketReturnBook> ticketReturnBooks) {
        System.out.println("===== Lịch sử giao dịch của bạn =====");

        // Lich su mua sach
        System.out.println("\nLịch sử mua sách:");
        boolean hasPurchase = false;
        for (Ticket ticket : tickets) {
            if (ticket.getCustomerName().equalsIgnoreCase(customerName) && ticket.getCustomerPhone().equals(customerPhone)) {
                // Chi hien thi yeu cau khi duoc admin duyet
                if (ticket.isApproved()) {
                    System.out.println("- Tên sách: " + ticket.getBookTitle() +
                            ", Số lượng: " + ticket.getQuantity() +
                            ", Tổng tiền: " + ticket.getTotalAmount() +
                            ", Trạng thái: Đã chấp nhận");
                    hasPurchase = true;
                }
            }
        }
        if (!hasPurchase) {
            System.out.println("Không có giao dịch mua sách.");
        }

        // Lich su thue sach
        System.out.println("\nLịch sử thuê sách:");
        boolean hasRental = false;
        for (TicketRentBook rentTicket : ticketRentBooks) {
            if (rentTicket.getCustomerName().equalsIgnoreCase(customerName) && rentTicket.getCustomerPhone().equals(customerPhone)) {
                if (rentTicket.isApproved()) {
                    System.out.println("- Tên sách: " + rentTicket.getBookTitle() +
                            ", Số lượng: " + rentTicket.getQuantity() +
                            ", Ngày trả: " + rentTicket.getReturnDate() +
                            ", Trạng thái: " + (rentTicket.isApproved() ? "Đã chấp nhận" : "Từ chối"));
                    hasRental = true;
                }
            }
        }
        if (!hasRental) {
            System.out.println("Không có giao dịch thuê sách.");
        }

        // Lich su tra sach
        System.out.println("\nLịch sử trả sách:");
        boolean hasReturn = false;
        for (TicketReturnBook returnTicket : ticketReturnBooks) {
            if (returnTicket.isApproved()) {
                if (returnTicket.getCustomerName().equalsIgnoreCase(customerName) && returnTicket.getCustomerPhone().equals(customerPhone)) {
                    System.out.println("- Tên sách: " + returnTicket.getBookTitle() +
                            ", Số lượng: " + returnTicket.getQuantity() +
                            ", Ngày trả: " + returnTicket.getBookTitle());
                    hasReturn = true;
                }
            }
        }
        if (!hasReturn) {
            System.out.println("Không có giao dịch trả sách.");
        }
    }


    // check phone number
    private static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {

        String phonePattern = "\\d{10,15}";
        if (!Pattern.matches(phonePattern, phoneNumber)) {
            throw new InvalidPhoneNumberException("Số điện thoại không hợp lệ. Vui lòng nhập lại (chỉ chứa chữ số, từ 10 đến 15 ký tự).");
        }
    }
    public void viewOrderStatus(String customerName, String customerPhone,
                                List<Ticket> tickets,
                                List<TicketRentBook> ticketRentBooks,
                                List<TicketReturnBook> ticketReturnBooks) {
        System.out.println("Trạng thái đơn hàng mua sách:");
        if (tickets.isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng mua sách nào.");
        } else {
            for (Ticket ticket : tickets) {
                String status = ticket.isApproved() ? "Đã duyệt" : "Chưa được duyệt";
                System.out.println("- Đơn hàng: " + ticket.getBookTitle() + ", Số lượng: " + ticket.getQuantity() + ", Trạng thái: " + status);
            }
        }

        System.out.println("\nTrạng thái đơn hàng thuê sách:");
        if (ticketRentBooks.isEmpty()) {
            System.out.println("Bạn chưa có đơn hàng thuê sách nào.");
        } else {
            for (TicketRentBook ticket : ticketRentBooks) {
                String status = ticket.isApproved() ? "Đã duyệt" : "Chưa được duyệt";
                System.out.println("- Đơn hàng: " + ticket.getBookTitle() + ", Số lượng: " + ticket.getQuantity() + ", Trạng thái: " + status);
            }
        }
        System.out.println("\nTrạng thái đơn hàng trả sách:");
        if (ticketReturnBooks.isEmpty()) {
            System.out.println("Bạn chưa có đơn trả sách nào.");
        } else {
            for (TicketReturnBook ticketReturnBook : ticketReturnBooks) {
                String status = ticketReturnBook.isApproved() ? "Đã duyệt" : "Chưa được duyệt";
                System.out.println("- Đơn hàng: " + ticketReturnBook.getBookTitle() + ", Số lượng: " + ticketReturnBook.getQuantity() + ", Trạng thái: " + status);
            }
        }
    }

}
