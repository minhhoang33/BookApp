package MyProject.bookStore.entities;

public class TicketReturnBook {
    private int id;
    private String customerName;
    private String customerPhone;
    private String bookTitle;
    private int quantity;
    private int condition;
    private double fineAmount;
    private boolean isApproved;
    private boolean isProcessed;
   private static int idAuto;


    public TicketReturnBook(String customerName, String customerPhone, String bookTitle, int quantity, int condition) {
        this.id = ++idAuto;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.condition = condition;
        this.isApproved = false;
        this.isProcessed = false;
        this.fineAmount = fineAmount;
    }

    // Getter và Setter...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    @Override
    public String toString() {
        return "TicketReturnBook{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", quantity=" + quantity +
                ", condition=" + condition +
                ", isApproved=" + isApproved +
                ", isProcessed=" + isProcessed +
                ", fineAmount=" + fineAmount +
                '}';
    }
}
