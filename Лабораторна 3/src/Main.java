import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Droid> droids = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Battle lastBattle = null;

    public static void main(String[] args) {
        boolean running = true;
        
        while (running) {
            printMenu();
            int choice = getIntInput("Ваш вибір: ");
            
            switch (choice) {
                case 1:
                    createDroid();
                    break;
                case 2:
                    showDroids();
                    break;
                case 3:
                    startOneOnOneBattle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    saveBattle();
                    break;
                case 6:
                    replayBattle();
                    break;
                case 7:
                    running = false;
                    System.out.println("Вихід з програми. До побачення!");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    private static void printMenu() {
        System.out.println("====== АРЕНА БИТВИ ДРОЇДІВ ======");
        System.out.println("1. Створити дроїда");
        System.out.println("2. Показати список дроїдів");
        System.out.println("3. Почати бій 1 на 1");
        System.out.println("4. Почати командний бій");
        System.out.println("5. Зберегти останній бій у файл");
        System.out.println("6. Відтворити бій з файлу");
        System.out.println("7. Вийти");
        System.out.println("==================================");
    }
    
    private static void createDroid() {
        System.out.println("\n" + Colors.BOLD + "Оберіть тип дроїда:" + Colors.RESET);
        System.out.println(Colors.BRIGHT_YELLOW + "1. Штурмовик" + Colors.RESET + " - Збалансований, Критичні удари");
        System.out.println(Colors.BRIGHT_BLUE + "2. Танк" + Colors.RESET + " - Висока міцність, Броня");
        System.out.println(Colors.BRIGHT_CYAN + "3. Медик" + Colors.RESET + " - Самолікування");
        System.out.println(Colors.BRIGHT_RED + "4. Снайпер" + Colors.RESET + " - Висока шкода, Точність");
        System.out.println(Colors.RED + "5. Берсерк" + Colors.RESET + " - Лютість при низькому здоров'ї (до 2x шкоди)");
        System.out.println(Colors.PURPLE + "6. Ніндзя" + Colors.RESET + " - Ухилення + Сплеш-атака");
        System.out.println(Colors.GREEN + "7. Регенератор" + Colors.RESET + " - Регенерація щоходу");
        
        int type = getIntInput("Введіть тип: ");
        System.out.print("Введіть ім'я дроїда: ");
        String name = scanner.nextLine();
        
        Droid droid = null;
        switch (type) {
            case 1:
                droid = new AssaultDroid(name);
                break;
            case 2:
                droid = new TankDroid(name);
                break;
            case 3:
                droid = new MedicDroid(name);
                break;
            case 4:
                droid = new SniperDroid(name);
                break;
            case 5:
                droid = new BerserkerDroid(name);
                break;
            case 6:
                droid = new NinjaDroid(name);
                break;
            case 7:
                droid = new RegeneratorDroid(name);
                break;
            default:
                System.out.println("Невірний тип. Створюємо Штурмовика за замовчуванням.");
                droid = new AssaultDroid(name);
        }
        
        droids.add(droid);
        System.out.println("\n" + Colors.BRIGHT_GREEN + "✓ Дроїда створено!" + Colors.RESET);
        System.out.println(droid.toString());
    }
    
    private static void showDroids() {
        if (droids.isEmpty()) {
            System.out.println("\n" + Colors.YELLOW + "Ще не створено жодного дроїда." + Colors.RESET);
            return;
        }
        
        System.out.println("\n" + Colors.BOLD + Colors.CYAN + "=== СПИСОК ДРОЇДІВ ===" + Colors.RESET);
        for (int i = 0; i < droids.size(); i++) {
            Droid d = droids.get(i);
            System.out.println("\n" + Colors.BOLD + (i + 1) + "." + Colors.RESET + " " + d.toString());
            System.out.println("   " + d.getHealthBar());
        }
    }
    
    private static void startOneOnOneBattle() {
        if (droids.size() < 2) {
            System.out.println("\nДля початку бою потрібно мінімум 2 дроїди.");
            return;
        }
        
        showDroids();
        
        int index1 = getIntInput("\nОберіть першого дроїда (номер): ") - 1;
        int index2 = getIntInput("Оберіть другого дроїда (номер): ") - 1;
        
        if (index1 < 0 || index1 >= droids.size() || index2 < 0 || index2 >= droids.size()) {
            System.out.println("Невірний вибір дроїда.");
            return;
        }
        
        if (index1 == index2) {
            System.out.println("Не можна битися з самим собою.");
            return;
        }
        
        // Create copies to preserve original droids
        Droid droid1Copy = copyDroid(droids.get(index1));
        Droid droid2Copy = copyDroid(droids.get(index2));
        
        lastBattle = new OneOnOneBattle(droid1Copy, droid2Copy);
        lastBattle.start();
    }
    
    private static void startTeamBattle() {
        if (droids.size() < 2) {
            System.out.println("\nДля командного бою потрібно мінімум 2 дроїди.");
            return;
        }
        
        showDroids();
        
        System.out.println("\n--- Формування Команди 1 ---");
        List<Droid> team1 = selectTeam();
        
        System.out.println("\n--- Формування Команди 2 ---");
        List<Droid> team2 = selectTeam();
        
        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Обидві команди повинні мати хоча б одного дроїда.");
            return;
        }
        
        lastBattle = new TeamBattle(team1, team2);
        lastBattle.start();
    }
    
    private static List<Droid> selectTeam() {
        List<Droid> team = new ArrayList<>();
        int teamSize = getIntInput("Скільки дроїдів у команді? ");
        
        for (int i = 0; i < teamSize; i++) {
            int index = getIntInput("Оберіть дроїда " + (i + 1) + " (номер): ") - 1;
            if (index >= 0 && index < droids.size()) {
                team.add(copyDroid(droids.get(index)));
            } else {
                System.out.println("Невірний вибір, пропускаємо.");
                i--;
            }
        }
        
        return team;
    }
    
    private static void saveBattle() {
        if (lastBattle == null) {
            System.out.println("\nНемає бою для збереження. Спочатку проведіть бій.");
            return;
        }
        
        System.out.print("Введіть назву файлу для збереження (наприклад, battle.txt): ");
        String filename = scanner.nextLine();
        
        BattleManager.saveBattleToFile(lastBattle, filename);
    }
    
    private static void replayBattle() {
        System.out.print("Введіть назву файлу для відтворення (наприклад, battle.txt): ");
        String filename = scanner.nextLine();
        
        BattleManager.replayBattleFromFile(filename);
    }
    
    private static Droid copyDroid(Droid original) {
        // Create a new instance with the same properties
        Droid copy = null;
        if (original instanceof AssaultDroid) {
            copy = new AssaultDroid(original.getName());
        } else if (original instanceof TankDroid) {
            copy = new TankDroid(original.getName());
        } else if (original instanceof MedicDroid) {
            copy = new MedicDroid(original.getName());
        } else if (original instanceof SniperDroid) {
            copy = new SniperDroid(original.getName());
        } else if (original instanceof BerserkerDroid) {
            copy = new BerserkerDroid(original.getName());
        } else if (original instanceof NinjaDroid) {
            copy = new NinjaDroid(original.getName());
        } else if (original instanceof RegeneratorDroid) {
            copy = new RegeneratorDroid(original.getName());
        }
        return copy;
    }
    
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Невірне введення. " + prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
}
