package MyProject.bookStoreService;

import java.util.ArrayList;
import java.util.Scanner;

public interface IActionService<T> {
    void insert(ArrayList<T> objects);
    T inputInfo(Scanner scanner);
    void update(ArrayList<T> objects);
    void delete(ArrayList<T> objects);
}
