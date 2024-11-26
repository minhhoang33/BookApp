package MyProject.login_logout.service;

import MyProject.bookStore.entities.Book;
import MyProject.bookStore.entities.Ticket;
import MyProject.bookStore.entities.TicketRentBook;
import MyProject.bookStore.entities.TicketReturnBook;
import MyProject.login_logout.entities.User;
import MyProject.viewBookStore.AdminMenu;
import MyProject.viewBookStore.CustomerMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LoginLogOutService {
    // Dang nhap
    public void login(Scanner scanner, List<User> users, ArrayList<Book> books, List<Ticket> tickets,List<TicketRentBook> ticketRentBooks,List<TicketReturnBook> ticketReturnBooks) {
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();
        User user = findUserByUsername(username,users);

        if (user == null) {
            System.out.println("Kiểm tra lại username.");
            return;
        }

        System.out.print("Nhập password: ");
        String password = scanner.nextLine();

        if (!user.getPassword().equals(password)) {
            System.out.println("Sai mật khẩu. Chọn lựa chọn:");
            System.out.println("1 - Đăng nhập lại");
            System.out.println("2 - Quên mật khẩu");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                login(scanner,users,books,tickets,ticketRentBooks,ticketReturnBooks);
            } else if (choice == 2) {
                forgotPassword(user, scanner);
            }
        } else {
            System.out.println("Chào mừng! Bạn có thể thực hiện các công việc sau:");
            showSettings(user, scanner,users,books,tickets,ticketRentBooks,ticketReturnBooks);
        }
    }
// Dang ky
    public void register(Scanner scanner, List<User> users) {
        System.out.print("Nhập username: ");
        String username = scanner.nextLine();

        if (findUserByUsername(username,users) != null) {
            System.out.println("Username đã tồn tại.");
            return;
        }

        System.out.print("Nhập email: ");
        String email = scanner.nextLine();

        if (!isValidEmail(email) || findUserByEmail(email,users) != null) {
            System.out.println("Email không hợp lệ hoặc đã tồn tại.");
            return;
        }

        System.out.print("Nhập password (7-15 ký tự, ít nhất 1 ký tự in hoa, 1 ký tự đặc biệt): ");
        String password = scanner.nextLine();

        if (!isValidPassword(password)) {
            System.out.println("Password không hợp lệ.");
            return;
        }

        users.add(new User(username, email, password, false));
        System.out.println("Đăng ký thành công!");
    }
//SettingMenu
    private static void showSettings(User user, Scanner scanner, List<User> users, ArrayList<Book> books, List<Ticket> tickets, List<TicketRentBook> ticketRentBooks, List<TicketReturnBook> ticketReturnBooks) {
        AdminMenu adminMenu = new AdminMenu();
        CustomerMenu customerMenu = new CustomerMenu();
        while (true) {
            System.out.println("1 - Cài đặt");
                System.out.println("2 - BookStoreForUser");
            if (user.isAdmin()) {
                System.out.println("3 - BookStoreForAdmin");
            }
            if (user.isAdmin()) {
                System.out.println("4 - Xoá người dùng");
            }
            System.out.println("0 - Đăng xuất");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 : {
                        settingsMenu(user, scanner, users);
                }
                break;
                case 2 : {
                    customerMenu.displayMenuCustomer(scanner,books,tickets,ticketRentBooks,ticketReturnBooks);
                }
                case 3 : {
                    if (choice == 3) {
                        adminMenu.displayMenuAdmin(scanner,books,tickets,ticketRentBooks,ticketReturnBooks);

                    }
                    break;

                }
                case 4 : {
                    deleteUser(scanner, users);


                }
                case 0 : {
                   if (choice == 0){
                       System.out.println("Đã đăng xuất.");
                   }
                    return;
                }
                default : System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
//Setting
    private static void settingsMenu(User user,Scanner scanner,List<User> users) {
        while (true) {
            System.out.println("Cài đặt:");
            System.out.println("1 - Thay đổi username");
            System.out.println("2 - Thay đổi email");
            System.out.println("3 - Thay đổi mật khẩu");
            System.out.println("0 - Quay lại");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> changeUsername(user, scanner,users);
                case 2 -> changeEmail(user, scanner,users);
                case 3 -> changePassword(user, scanner);

                case 0 -> {
                    return;  // Quay lại menu cài đặt chính
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
// thay doi username
    private static void changeUsername(User user,Scanner scanner,List<User> users) {
        System.out.print("Nhập username mới: ");
        String newUsername = scanner.nextLine();
        if (findUserByUsername(newUsername,users) != null) {
            System.out.println("Username đã tồn tại.");
        } else {
            user.setUsername(newUsername);
            System.out.println("Thay đổi username thành công.");
        }
    }
// Thay doi email
    private static void changeEmail(User user,Scanner scanner,List<User> users) {
        System.out.print("Nhập email mới: ");
        String newEmail = scanner.nextLine();
        if (!isValidEmail(newEmail) || findUserByEmail(newEmail,users) != null) {
            System.out.println("Email không hợp lệ hoặc đã tồn tại.");
        } else {
            user.setEmail(newEmail);
            System.out.println("Thay đổi email thành công.");
        }
    }
// thay doi mat khau
    private static void changePassword(User user,Scanner scanner) {
        System.out.print("Nhập mật khẩu mới: ");
        String newPassword = scanner.nextLine();
        if (isValidPassword(newPassword)) {
            user.setPassword(newPassword);
            System.out.println("Thay đổi mật khẩu thành công.");
        } else {
            System.out.println("Mật khẩu không hợp lệ.");
        }
    }
// Xu ly khi quen mat khau
    private static void forgotPassword(User user,Scanner scanner) {
        System.out.print("Nhập email của bạn: ");
        String email = scanner.nextLine();
        if (email.equals(user.getEmail())) {
            System.out.print("Nhập mật khẩu mới: ");
            String newPassword = scanner.nextLine();
            if (isValidPassword(newPassword)) {
                user.setPassword(newPassword);
                System.out.println("Thay đổi mật khẩu thành công. Đăng nhập lại.");
            } else {
                System.out.println("Mật khẩu không hợp lệ.");
            }
        } else {
            System.out.println("Email không tồn tại.");
        }
    }
// Tim user bang ten
    public static User findUserByUsername(String username,List<User> users) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
// tim user bang email
    private static User findUserByEmail(String email,List<User> users) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
// check email
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return Pattern.matches(emailRegex, email);
    }
// check password
    private static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[A-Z])(?=.*[.\\-_!@#$%^&*()])[A-Za-z\\d.\\-_!@#$%^&*()]{7,15}$";
        return Pattern.matches(passwordRegex, password);
    }
    // Xoa user
    private static void deleteUser(Scanner scanner,List<User> users) {
        System.out.print("Nhập username của người dùng cần xóa: ");
        String username = scanner.nextLine();
        User userToDelete = findUserByUsername(username,users);

        if (userToDelete == null) {
            System.out.println("Không tìm thấy người dùng.");
        } else if (userToDelete.isAdmin()) {
            System.out.println("Không thể xóa tài khoản admin.");
        } else {
            users.remove(userToDelete);
            System.out.println("Đã xóa người dùng thành công.");
        }
    }
}

