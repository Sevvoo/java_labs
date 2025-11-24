/*
 * ЛР5 - Демонстрація основних концепцій ООП у Java
 * 
 * Ця програма демонструє:
 * - Створення об'єктів класу (екземплярів)
 * - Використання конструкторів (за замовчуванням та з параметрами)
 * - Виклик методів екземпляра (instance methods)
 * - Виклик статичних методів (static methods)
 * - Використання геттерів для отримання значень атрибутів
 */

package com.telegram;

// ІМПОРТ КЛАСУ З ІНШОГО ПАКЕТУ
// Дозволяє використовувати ExpressionCalculator з пакету com.telegram.calculator
import com.telegram.calculator.ExpressionCalculator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LR5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // ДЕМОНСТРАЦІЯ 1: КОНСТРУКТОР ЗА ЗАМОВЧУВАННЯМ
        // Створюємо об'єкт calc1 класу ExpressionCalculator
        // Викликається конструктор без параметрів: ExpressionCalculator()
        // Об'єкт отримує стандартні значення: x=1.5, y=2.0, d=3.0
        System.out.println("=== Default constructor ===");
        ExpressionCalculator calc1 = new ExpressionCalculator();
        
        try {
            // ВИКЛИК НЕСТАТИЧНОГО МЕТОДУ (instance method)
            // calc1.calculate() - використовує атрибути об'єкта calc1
            double R = calc1.calculate();
            
            System.out.println("\nInput:");
            // ВИКЛИК GET-МЕТОДІВ (геттерів)
            // Отримуємо значення приватних атрибутів через публічні методи
            System.out.println("  x = " + calc1.getX());
            System.out.println("  y = " + calc1.getY());
            System.out.println("  d = " + calc1.getD());
            System.out.println("\nResult:");
            System.out.printf("  R = %.6f\n", R);
            
        } catch (ArithmeticException e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
        
        // ДЕМОНСТРАЦІЯ 2: КОНСТРУКТОР З ПАРАМЕТРАМИ
        // Створюємо об'єкт calc2 з власними значеннями
        System.out.println("\n=== Parameterized constructor ===");
        double x = 0;
        double y = 0;
        double d = 0;
        
        try {
            System.out.print("Enter x: ");
            x = scanner.nextDouble();
            System.out.print("Enter y: ");
            y = scanner.nextDouble();
            System.out.print("Enter d: ");
            d = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Invalid input.");
            scanner.close();
            return;
        }
        
        // СТВОРЕННЯ ОБ'ЄКТА calc2 З ВИКОРИСТАННЯМ КОНСТРУКТОРА З ПАРАМЕТРАМИ
        // Викликається: ExpressionCalculator(double x, double y, double d)
        // Об'єкт calc2 отримує значення, введені користувачем
        ExpressionCalculator calc2 = new ExpressionCalculator(x, y, d);
        
        try {
            // calc2.calculate() - використовує атрибути об'єкта calc2
            // calc1 і calc2 - різні об'єкти з різними значеннями атрибутів
            double R = calc2.calculate();
            
            System.out.println("\nInput:");
            System.out.println("  x = " + calc2.getX());
            System.out.println("  y = " + calc2.getY());
            System.out.println("  d = " + calc2.getD());
            System.out.println("\nResult:");
            System.out.printf("  R = %.6f\n", R);
            
        } catch (ArithmeticException e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
        
        // ДЕМОНСТРАЦІЯ 3: СТАТИЧНИЙ МЕТОД
        // Не потрібно створювати об'єкт для виклику статичного методу
        // Викликається через ім'я класу: ExpressionCalculator.calculateStatic()
        System.out.println("\n=== Static method ===");
        
        try {
            // ВИКЛИК СТАТИЧНОГО МЕТОДУ (static method)
            // calculateStatic() не використовує атрибути об'єкта
            // Працює тільки з переданими параметрами (x, y, d)
            // Різниця зі звичайним методом: не потрібен об'єкт для виклику
            double R = ExpressionCalculator.calculateStatic(x, y, d);
            
            System.out.println("\nInput:");
            System.out.println("  x = " + x);
            System.out.println("  y = " + y);
            System.out.println("  d = " + d);
            System.out.println("\nResult:");
            System.out.printf("  R = %.6f\n", R);
            
        } catch (ArithmeticException e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
        
        scanner.close();
    }
}
