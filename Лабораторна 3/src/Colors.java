/**
 * Клас з ANSI кодами кольорів для кольорового виводу в термінал.
 * Використовується для візуалізації дроїдів, здоров'я та повідомлень.
 * 
 * @author Пелех Денис
 * @version 1.0
 */
public class Colors {
    /** Скидання кольору до стандартного */
    public static final String RESET = "\u001B[0m";
    /** Червоний колір */
    public static final String RED = "\u001B[31m";
    /** Зелений колір */
    public static final String GREEN = "\u001B[32m";
    /** Жовтий колір */
    public static final String YELLOW = "\u001B[33m";
    /** Синій колір */
    public static final String BLUE = "\u001B[34m";
    /** Фіолетовий колір */
    public static final String PURPLE = "\u001B[35m";
    /** Блакитний колір */
    public static final String CYAN = "\u001B[36m";
    /** Білий колір */
    public static final String WHITE = "\u001B[37m";
    
    /** Яскраво-червоний колір */
    public static final String BRIGHT_RED = "\u001B[91m";
    /** Яскраво-зелений колір */
    public static final String BRIGHT_GREEN = "\u001B[92m";
    /** Яскраво-жовтий колір */
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    /** Яскраво-синій колір */
    public static final String BRIGHT_BLUE = "\u001B[94m";
    /** Яскраво-фіолетовий колір */
    public static final String BRIGHT_PURPLE = "\u001B[95m";
    /** Яскраво-блакитний колір */
    public static final String BRIGHT_CYAN = "\u001B[96m";
    
    /** Жирний текст */
    public static final String BOLD = "\u001B[1m";
    
    /** Червоний фон */
    public static final String BG_RED = "\u001B[41m";
    /** Зелений фон */
    public static final String BG_GREEN = "\u001B[42m";
    /** Жовтий фон */
    public static final String BG_YELLOW = "\u001B[43m";
}
