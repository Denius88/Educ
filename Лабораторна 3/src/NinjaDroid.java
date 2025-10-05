public class NinjaDroid extends Droid {
    private static final int NINJA_HEALTH = 90;
    private static final int NINJA_DAMAGE = 22;
    private int dodgeChance = 40; // 40% шанс ухилитися
    private int splashDamage = 10; // Додаткова шкода від сплешу

    public NinjaDroid(String name) {
        super(name, NINJA_HEALTH, NINJA_DAMAGE);
    }

    @Override
    public void takeDamage(int damage) {
        // Шанс ухилитися від атаки
        boolean dodged = Math.random() * 100 < dodgeChance;
        if (dodged) {
            System.out.println("  ⚡ " + name + " майстерно ухиляється від удару!");
            return;
        }
        super.takeDamage(damage);
    }

    @Override
    public String attack(Droid target) {
        // Звичайна атака
        target.takeDamage(damage);
        String result = String.format("%s швидко атакує %s на %d шкоди", 
            name, target.getName(), damage);

        // 30% шанс сплеш-атаки (додаткова шкода)
        if (Math.random() * 100 < 30) {
            target.takeDamage(splashDamage);
            result += String.format(" + СПЛЕШ на %d шкоди! 💥", splashDamage);
        }

        return result;
    }

    @Override
    public String getType() {
        return "Ніндзя";
    }

    @Override
    public String getColor() {
        return Colors.PURPLE;
    }
}
