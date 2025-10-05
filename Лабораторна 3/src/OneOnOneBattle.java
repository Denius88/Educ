import java.util.Random;

/**
 * Клас бою один на один між двома дроїдами.
 * Включає систему випадкових бафів, що можуть з'явитися під час раундів.
 * 
 * @author Пелех Денис
 * @version 2.0
 */
public class OneOnOneBattle extends Battle {
    private Droid droid1;
    private Droid droid2;
    private int roundNumber;
    private Random random;
    private static final int BUFF_CHANCE = 20; // 20% шанс бафу кожен раунд

    /**
     * Конструктор бою 1 на 1.
     * 
     * @param droid1 Перший дроїд
     * @param droid2 Другий дроїд
     */
    public OneOnOneBattle(Droid droid1, Droid droid2) {
        super();
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.roundNumber = 0;
        this.random = new Random();
    }

    /**
     * Випадково надати баф одному з дроїдів.
     */
    private void tryApplyRandomBuff() {
        if (random.nextInt(100) < BUFF_CHANCE) {
            Droid target = random.nextBoolean() ? droid1 : droid2;
            BuffType[] types = BuffType.values();
            BuffType randomType = types[random.nextInt(types.length)];
            
            int duration = (randomType == BuffType.FULL_HEAL) ? 0 : (random.nextInt(2) + 1); // 1-2 раунди
            Buff buff = new Buff(randomType, duration);
            
            String message = target.addBuff(buff);
            log(message + Colors.RESET);
        }
    }

    @Override
    public void start() {
        clearLog();
        log(Colors.BOLD + Colors.CYAN + "\n⚔️  === БІЙ 1 НА 1 === ⚔️" + Colors.RESET);
        log("\n" + droid1.toString());
        log("   " + droid1.getHealthBar());
        log("\n" + Colors.BOLD + Colors.YELLOW + "      ⚡ ПРОТИ ⚡" + Colors.RESET);
        log("\n" + droid2.toString());
        log("   " + droid2.getHealthBar());
        log("\n" + Colors.CYAN + "===================" + Colors.RESET + "\n");

        while (droid1.isAlive() && droid2.isAlive()) {
            roundNumber++;
            log(Colors.BOLD + Colors.PURPLE + "\n➤ Раунд " + roundNumber + Colors.RESET);
            log(Colors.PURPLE + "──────────" + Colors.RESET);
            
            // Спроба застосувати випадковий баф
            tryApplyRandomBuff();
            
            // Droid 1 attacks
            if (droid1.isAlive()) {
                String attackResult = droid1.attack(droid2);
                log(attackResult);
                log("   " + droid2.getName() + " " + droid2.getHealthBar() + " " + droid2.getHealth() + "/" + droid2.getMaxHealth());
            }
            
            // Check if droid2 is defeated
            if (!droid2.isAlive()) {
                log("\n" + Colors.RED + "☠️  " + droid2.getName() + " переможений!" + Colors.RESET);
                log(Colors.BOLD + Colors.GREEN + "\n🏆 ПЕРЕМОЖЕЦЬ: " + droid1.getName() + "! 🏆" + Colors.RESET);
                break;
            }
            
            // Droid 2 attacks
            if (droid2.isAlive()) {
                String attackResult = droid2.attack(droid1);
                log(attackResult);
                log("   " + droid1.getName() + " " + droid1.getHealthBar() + " " + droid1.getHealth() + "/" + droid1.getMaxHealth());
            }
            
            // Check if droid1 is defeated
            if (!droid1.isAlive()) {
                log("\n" + Colors.RED + "☠️  " + droid1.getName() + " переможений!" + Colors.RESET);
                log(Colors.BOLD + Colors.GREEN + "\n🏆 ПЕРЕМОЖЕЦЬ: " + droid2.getName() + "! 🏆" + Colors.RESET);
                break;
            }
            
            // Оновити бафи
            droid1.updateBuffs();
            droid2.updateBuffs();
            
            log("");
        }
    }

    /**
     * Отримати першого дроїда.
     * 
     * @return Перший дроїд
     */
    public Droid getDroid1() {
        return droid1;
    }

    /**
     * Отримати другого дроїда.
     * 
     * @return Другий дроїд
     */
    public Droid getDroid2() {
        return droid2;
    }
}
