
public class Main {

    // a) список книг заданого автора
    public static void printBooksByAuthor(Book[] books, String author) {
        System.out.println("Книги автора: " + author);
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(b);
            }
        }
    }

    // b) список книг заданого видавництва
    public static void printBooksByPublisher(Book[] books, String publisher) {
        System.out.println("Книги видавництва: " + publisher);
        for (Book b : books) {
            if (b.getPublisher().equalsIgnoreCase(publisher)) {
                System.out.println(b);
            }
        }
    }

    // c) список книг після заданого року
    public static void printBooksAfterYear(Book[] books, int year) {
        System.out.println("Книги після року: " + year);
        for (Book b : books) {
            if (b.getYear() > year) {
                System.out.println(b);
            }
        }
    }

    public static void main(String[] args) {
        // створюємо масив об'єктів
        Book[] books = {
                new Book(1, "Місто", "Валер'ян Підмогильний", "Книгоспілка", 1928, 350, 250.50),
                new Book(2, "Кобзар", "Тарас Шевченко", "Веселка", 1840, 500, 300.00),
                new Book(3, "Хіба ревуть воли...", "Панас Мирний", "Освіта", 1880, 450, 280.75),
                new Book(4, "Записки українського самашедшого", "Ліна Костенко", "А-БА-БА-ГА-ЛА-МА-ГА", 2010, 400, 350.00),
                new Book(5, "Маруся Чурай", "Ліна Костенко", "Дніпро", 1979, 300, 220.40),
        };

        // Виклики методів
        printBooksByAuthor(books, "Ліна Костенко");
        System.out.println();

        printBooksByPublisher(books, "Освіта");
        System.out.println();

        printBooksAfterYear(books, 1950);
    }
}
