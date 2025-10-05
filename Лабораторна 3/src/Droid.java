import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактний базовий клас для всіх типів бойових дроїдів.
 * Містить основні характеристики: здоров'я, шкоду, а також систему бафів.
 * 
 * @author Пелех Денис
 * @version 2.0
 */
public abstract class Droid {
    /** Ім'я дроїда */
    protected String name;
    /** Максимальне здоров'я дроїда */
    protected int maxHealth;
    /** Поточне здоров'я дроїда */
    protected int health;
    /** Базова шкода дроїда */
    protected int damage;
    /** Чи живий дроїд */
    protected boolean isAlive;
    /** Список активних бафів */
    protected List<Buff> activeBuffs;

    /**
     * Конструктор дроїда.
     * 
     * @param name Ім'я дроїда
     * @param health Початкове та максимальне здоров'я
     * @param damage Базова шкода дроїда
     */
    public Droid(String name, int health, int damage) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.damage = damage;
        this.isAlive = true;
        this.activeBuffs = new ArrayList<>();
    }

    /**
     * Отримати шкоду з урахуванням активних бафів.
     * 
     * @param baseDamage Базова шкода
     * @return Модифікована шкода
     */
    public int getModifiedDamage(int baseDamage) {
        int finalDamage = baseDamage;
        
        for (Buff buff : activeBuffs) {
            if (buff.getType() == BuffType.DOUBLE_DAMAGE) {
                finalDamage *= 2;
            } else if (buff.getType() == BuffType.CRITICAL_CHARGE) {
                finalDamage *= 3;
            }
        }
        
        return finalDamage;
    }

    /**
     * Отримати шкоду, враховуючи захисні бафи.
     * 
     * @param damage Вхідна шкода
     */
    public void takeDamage(int damage) {
        int finalDamage = damage;
        
        // Застосування щита
        for (Buff buff : activeBuffs) {
            if (buff.getType() == BuffType.SHIELD) {
                finalDamage = (int)(finalDamage * 0.5); // 50% зменшення
                break;
            }
        }
        
        this.health -= finalDamage;
        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false;
        }
    }

    /**
     * Відновити здоров'я.
     * 
     * @param amount Кількість здоров'я для відновлення
     */
    public void heal(int amount) {
        this.health += amount;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    /**
     * Додати баф до дроїда.
     * 
     * @param buff Баф для додавання
     * @return Повідомлення про активацію бафу
     */
    public String addBuff(Buff buff) {
        // Видалити старий баф того ж типу
        activeBuffs.removeIf(b -> b.getType() == buff.getType());
        activeBuffs.add(buff);
        
        // Застосувати моментальні ефекти
        if (buff.getType() == BuffType.FULL_HEAL) {
            heal(maxHealth);
            return String.format("%s %s отримує %s - здоров'я відновлено до %d!", 
                Colors.BRIGHT_GREEN, name, buff.toString(), health);
        }
        
        return String.format("%s %s отримує %s!", 
            Colors.BRIGHT_CYAN, name, buff.toString());
    }

    /**
     * Оновити бафи після раунду.
     */
    public void updateBuffs() {
        activeBuffs.forEach(Buff::decreaseDuration);
        activeBuffs.removeIf(buff -> !buff.isActive());
    }

    /**
     * Отримати список активних бафів.
     * 
     * @return Список бафів
     */
    public List<Buff> getActiveBuffs() {
        return activeBuffs;
    }

    /**
     * Перевірити наявність конкретного типу бафу.
     * 
     * @param type Тип бафу
     * @return true якщо баф активний
     */
    public boolean hasBuff(BuffType type) {
        return activeBuffs.stream().anyMatch(b -> b.getType() == type);
    }

    /**
     * Атакувати ціль.
     * 
     * @param target Ціль атаки
     * @return Опис результату атаки
     */
    public abstract String attack(Droid target);
    
    /**
     * Отримати тип дроїда.
     * 
     * @return Назва типу
     */
    public abstract String getType();
    
    /**
     * Отримати колір дроїда для візуалізації.
     * 
     * @return ANSI код кольору
     */
    public abstract String getColor();

    /**
     * Отримати ім'я дроїда.
     * 
     * @return Ім'я дроїда
     */
    public String getName() {
        return name;
    }

    /**
     * Отримати поточне здоров'я.
     * 
     * @return Поточне здоров'я
     */
    public int getHealth() {
        return health;
    }

    /**
     * Отримати максимальне здоров'я.
     * 
     * @return Максимальне здоров'я
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Отримати базову шкоду.
     * 
     * @return Базова шкода
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Перевірити, чи живий дроїд.
     * 
     * @return true якщо живий
     */
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s%s%s (%s) - Здоров'я: %d/%d, Шкода: %d", 
            getColor(), name, Colors.RESET, getType(), health, maxHealth, damage));
        
        if (!activeBuffs.isEmpty()) {
            result.append(Colors.CYAN).append(" [");
            for (int i = 0; i < activeBuffs.size(); i++) {
                if (i > 0) result.append(", ");
                result.append(activeBuffs.get(i).getType().getIcon());
            }
            result.append("]").append(Colors.RESET);
        }
        
        return result.toString();
    }
    
    /**
     * Створити візуальну смужку здоров'я.
     * 
     * @return Кольорова смужка здоров'я
     */
    public String getHealthBar() {
        int barLength = 20;
        double healthPercent = (double)health / maxHealth;
        int filled = (int)(barLength * healthPercent);
        
        String color;
        if (healthPercent > 0.6) {
            color = Colors.BRIGHT_GREEN;
        } else if (healthPercent > 0.3) {
            color = Colors.BRIGHT_YELLOW;
        } else {
            color = Colors.BRIGHT_RED;
        }
        
        StringBuilder bar = new StringBuilder(color + "[");
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                bar.append("█");
            } else {
                bar.append("░");
            }
        }
        bar.append("]" + Colors.RESET);
        return bar.toString();
    }
}
