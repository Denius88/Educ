/**
 * Клас Book представляє книгу з усіма необхідними даними.
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int pages;
    private double price;

    /**
     * Конструктор для створення об'єкта Book.
     *
     * @param id        Ідентифікатор книги
     * @param title     Назва книги
     * @param author    Автор книги
     * @param publisher Видавництво
     * @param year      Рік видання
     * @param pages     Кількість сторінок
     * @param price     Ціна
     */
    public Book(int id, String title, String author, String publisher, int year, int pages, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.price = price;
    }

    /** Геттери та сеттери */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    /**
     * Повертає рядок з усіма даними книги.
     * @return інформація про книгу
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", Назва='" + title + '\'' +
                ", Автор='" + author + '\'' +
                ", Видавництво='" + publisher + '\'' +
                ", Рік=" + year +
                ", Сторінки=" + pages +
                ", Ціна=" + price +
                '}';
    }
}