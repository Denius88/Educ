public class BerserkerDroid extends Droid {
    private static final int BERSERKER_HEALTH = 140;
    private static final int BERSERKER_DAMAGE = 18;
    private double rageMultiplier = 1.0;

    public BerserkerDroid(String name) {
        super(name, BERSERKER_HEALTH, BERSERKER_DAMAGE);
    }

    @Override
    public String attack(Droid target) {
        // Лютість збільшується коли здоров'я низьке
        double healthPercent = (double)health / maxHealth;
        if (healthPercent < 0.3) {
            rageMultiplier = 2.0; // Подвійна шкода при <30% здоров'я
        } else if (healthPercent < 0.5) {
            rageMultiplier = 1.5; // 1.5x шкода при <50% здоров'я
        } else {
            rageMultiplier = 1.0;
        }

        int actualDamage = (int)(damage * rageMultiplier);
        target.takeDamage(actualDamage);

        if (rageMultiplier > 1.5) {
            return String.format("%s в ШАЛЕНІЙ ЛЮТОСТІ розриває %s на %d шкоди! 💢", 
                name, target.getName(), actualDamage);
        } else if (rageMultiplier > 1.0) {
            return String.format("%s в лютості б'є %s на %d шкоди! 😤", 
                name, target.getName(), actualDamage);
        } else {
            return String.format("%s атакує %s на %d шкоди", 
                name, target.getName(), actualDamage);
        }
    }

    @Override
    public String getType() {
        return "Берсерк";
    }

    @Override
    public String getColor() {
        return Colors.RED;
    }
}
