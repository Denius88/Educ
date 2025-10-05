import java.io.*;
import java.util.List;

public class BattleManager {
    
    public static void saveBattleToFile(Battle battle, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            List<String> log = battle.getBattleLog();
            for (String line : log) {
                writer.println(line);
            }
            System.out.println("Бій збережено в " + filename);
        } catch (IOException e) {
            System.out.println("Помилка збереження бою: " + e.getMessage());
        }
    }
    
    public static void replayBattleFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("\n=== ВІДТВОРЕННЯ БОЮ З ФАЙЛУ ===\n");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                try {
                    Thread.sleep(100); // Small delay for better readability
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("\n=== КІНЕЦЬ ВІДТВОРЕННЯ ===\n");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка читання бою: " + e.getMessage());
        }
    }
}
