/**
 * Клас, що представляє активний баф (посилення) на дроїді.
 * Баф має тип, тривалість та застосовується автоматично під час бою.
 * 
 * @author Пелех Денис
 * @version 1.0
 */
public class Buff {
    private BuffType type;
    private int duration;
    private int initialDuration;
    
    /**
     * Конструктор бафу.
     * 
     * @param type Тип бафу
     * @param duration Тривалість бафу в раундах
     */
    public Buff(BuffType type, int duration) {
        this.type = type;
        this.duration = duration;
        this.initialDuration = duration;
    }
    
    /**
     * Зменшити тривалість бафу на 1 раунд.
     */
    public void decreaseDuration() {
        if (duration > 0) {
            duration--;
        }
    }
    
    /**
     * Перевірити, чи баф все ще активний.
     * 
     * @return true якщо баф активний, false якщо закінчився
     */
    public boolean isActive() {
        return duration > 0;
    }
    
    /**
     * Отримати тип бафу.
     * 
     * @return Тип бафу
     */
    public BuffType getType() {
        return type;
    }
    
    /**
     * Отримати залишкову тривалість бафу.
     * 
     * @return Кількість раундів до завершення
     */
    public int getDuration() {
        return duration;
    }
    
    /**
     * Отримати початкову тривалість бафу.
     * 
     * @return Початкова тривалість
     */
    public int getInitialDuration() {
        return initialDuration;
    }
    
    @Override
    public String toString() {
        return String.format("%s %s (%d %s)", 
            type.getIcon(), 
            type.getName(), 
            duration,
            duration == 1 ? "раунд" : "раунди");
    }
}
