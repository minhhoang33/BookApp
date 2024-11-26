package MyProject.bookStore.entities;

import java.time.LocalDate;

public class TicketRentBook extends Ticket {
    private LocalDate returnDate;

    public TicketRentBook(String customerName, String customerPhone, String bookTitle, int quantity, LocalDate returnDate) {
        super(customerName, customerPhone, bookTitle, quantity);
        this.returnDate = returnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
