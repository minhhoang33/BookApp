package MyProject.bookStoreService;

import MyProject.bookStore.entities.Book;

import java.util.ArrayList;

public class BookService {
    public Book findBookById(int id,ArrayList<Book> books) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
}
}
