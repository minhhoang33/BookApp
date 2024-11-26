package MyProject.bookStore.entities;

import java.time.LocalDate;

public class Ticket {
    private String customerName;
    private String customerPhone;
    private String bookTitle;
    private int quantity;
    private boolean approved = false;
    private LocalDate requestDate;
    private double totalAmount;

    public Ticket(String customerName, String customerPhone, String bookTitle, int quantity) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.requestDate = LocalDate.now();
        this.totalAmount = 0;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Yêu cầu từ: " + customerName + " | Tên truyện: " + bookTitle + " | Số lượng: " + quantity +
                " | Ngày yêu cầu: " + requestDate + " | Trạng thái: " + (approved ? "Đã chấp nhận" : "Chờ duyệt");
    }
}
