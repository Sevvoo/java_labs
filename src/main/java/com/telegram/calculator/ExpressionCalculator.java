/*
 * ЛР5 - Теоретичні питання
 * 
 * 1. ПОНЯТТЯ КЛАСУ І ОБ'ЄКТУ:
 * Клас - це шаблон або "креслення" для створення об'єктів.
 * Клас описує властивості (атрибути) та поведінку (методи) об'єктів.
 * 
 * Об'єкт - це конкретний примірник (екземпляр) класу.
 * Наприклад: ExpressionCalculator - це клас, а calc1, calc2 - це об'єкти цього класу.
 * Один клас може мати багато об'єктів з різними значеннями атрибутів.
 * 
 * 2. АТРИБУТИ І МЕТОДИ КЛАСУ. ВИКЛИК МЕТОДІВ:
 * Атрибути (поля) - це змінні, які зберігають стан об'єкта.
 * У цьому класі: x, y, d - це атрибути.
 * 
 * Методи - це функції, які описують поведінку об'єкта.
 * У цьому класі: getX(), setX(), calculate() - це методи.
 * 
 * Виклик методів:
 * - Для нестатичних методів: objectName.methodName()
 *   Приклад: calc1.calculate()
 * - Для статичних методів: ClassName.methodName()
 *   Приклад: ExpressionCalculator.calculateStatic(x, y, d)
 * 
 * 3. МЕТОДИ GET І SET. ТИПІЗОВАНІ І БЕЗТИПОВІ (VOID) МЕТОДИ:
 * 
 * Get-методи (геттери) - повертають значення приватного атрибута.
 * Приклад: public double getX() { return x; }
 * 
 * Set-методи (сеттери) - встановлюють нове значення приватного атрибута.
 * Приклад: public void setX(double x) { this.x = x; }
 * 
 * Типізовані методи - повертають конкретний тип даних (int, double, String тощо).
 * Приклад: public double calculate() - повертає double
 * 
 * Безтипові (void) методи - не повертають жодного значення.
 * Приклад: public void setX(double x) - нічого не повертає, тільки змінює стан
 * 
 * 4. КОНСТРУКТОРИ КЛАСУ:
 * Конструктор - це спеціальний метод для ініціалізації об'єктів.
 * Має таке ж ім'я, як і клас, не має типу повернення.
 * 
 * У цьому класі є 2 конструктори:
 * - ExpressionCalculator() - конструктор за замовчуванням (без параметрів)
 * - ExpressionCalculator(double x, double y, double d) - конструктор з параметрами
 * 
 * 5. ПЕРЕВАНТАЖЕННЯ МЕТОДІВ:
 * Перевантаження - це можливість мати кілька методів з однаковою назвою,
 * але різними параметрами (різна кількість або різні типи).
 * 
 * Приклади в цьому класі:
 * - Два конструктори ExpressionCalculator() - перевантаження конструктора
 * - calculate() і calculateStatic() - це не перевантаження (різні назви)
 * 
 * 6. ПОНЯТТЯ СТАТИЧНОГО АТРИБУТУ І МЕТОДУ КЛАСУ:
 * 
 * Статичні атрибути/методи належать класу, а не конкретному об'єкту.
 * Вони існують в єдиному екземплярі для всіх об'єктів класу.
 * 
 * Статичний метод:
 * - Викликається через ім'я класу: ClassName.methodName()
 * - Не має доступу до нестатичних атрибутів
 * - Приклад: calculateStatic() - статичний метод
 * 
 * Нестатичний метод:
 * - Викликається через об'єкт: object.methodName()
 * - Має доступ до всіх атрибутів та методів класу
 * - Приклад: calculate() - нестатичний метод
 * 
 * 7. ПОНЯТТЯ ПАКЕТІВ У JAVA:
 * Пакет - це механізм групування пов'язаних класів.
 * 
 * package com.telegram.calculator; - цей клас знаходиться в пакеті calculator
 * 
 * Переваги пакетів:
 * - Організація коду (структурування проекту)
 * - Уникнення конфліктів імен (два класи з однаковою назвою в різних пакетах)
 * - Контроль доступу (модифікатори доступу працюють на рівні пакетів)
 * - Повторне використання коду
 * 
 * Імпорт пакетів:
 * import com.telegram.calculator.ExpressionCalculator;
 * Дозволяє використовувати класи з інших пакетів.
 */

package com.telegram.calculator;

public class ExpressionCalculator {
    // АТРИБУТИ КЛАСУ (приватні поля)
    // Інкапсуляція - приховуємо дані, доступ через get/set методи
    private double x;
    private double y;
    private double d;
    
    // КОНСТРУКТОР ЗА ЗАМОВЧУВАННЯМ (без параметрів)
    // Ініціалізує об'єкт зі стандартними значеннями
    public ExpressionCalculator() {
        this.x = 1.5;
        this.y = 2.0;
        this.d = 3.0;
    }
    
    // КОНСТРУКТОР З ПАРАМЕТРАМИ
    // Ініціалізує об'єкт з заданими значеннями
    // this.x - атрибут об'єкта, x - параметр конструктора
    public ExpressionCalculator(double x, double y, double d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
    
    // GET-МЕТОДИ (ГЕТТЕРИ) - ТИПІЗОВАНІ МЕТОДИ
    // Повертають значення приватних атрибутів
    // Тип повернення: double
    public double getX() {
        return x;
    }
    
    // SET-МЕТОДИ (СЕТТЕРИ) - БЕЗТИПОВІ (VOID) МЕТОДИ
    // Встановлюють нові значення приватних атрибутів
    // Тип повернення: void (нічого не повертає)
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getD() {
        return d;
    }
    
    public void setD(double d) {
        this.d = d;
    }
    
    // НЕСТАТИЧНИЙ МЕТОД (метод екземпляра)
    // Працює з атрибутами конкретного об'єкта (this.x, this.y, this.d)
    // Викликається: objectName.calculate()
    // Типізований метод - повертає double
    public double calculate() throws ArithmeticException {
        double sinCubed = Math.pow(Math.sin(x), 3);
        double logArg = sinCubed + 7.4;
        
        if (logArg <= 0) {
            throw new ArithmeticException("Log argument <= 0");
        }
        
        double denom = Math.exp(x) + Math.log(logArg);
        
        if (Math.abs(denom) < 1e-10) {
            throw new ArithmeticException("Division by zero");
        }
        
        double num = Math.pow(Math.cos(y), 3) + Math.pow(2, d);
        double R = num / denom;
        
        if (Double.isNaN(R) || Double.isInfinite(R)) {
            throw new ArithmeticException("Invalid result");
        }
        
        return R;
    }
    
    // СТАТИЧНИЙ МЕТОД (метод класу)
    // Не працює з атрибутами об'єкта, використовує тільки параметри
    // Викликається: ClassName.calculateStatic(x, y, d)
    // Не потрібно створювати об'єкт для виклику
    // static - ключове слово, що робить метод статичним
    public static double calculateStatic(double x, double y, double d) throws ArithmeticException {
        double sinCubed = Math.pow(Math.sin(x), 3);
        double logArg = sinCubed + 7.4;
        
        if (logArg <= 0) {
            throw new ArithmeticException("Log argument <= 0");
        }
        
        double denom = Math.exp(x) + Math.log(logArg);
        
        if (Math.abs(denom) < 1e-10) {
            throw new ArithmeticException("Division by zero");
        }
        
        double num = Math.pow(Math.cos(y), 3) + Math.pow(2, d);
        double R = num / denom;
        
        if (Double.isNaN(R) || Double.isInfinite(R)) {
            throw new ArithmeticException("Invalid result");
        }
        
        return R;
    }
}