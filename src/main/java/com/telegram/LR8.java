/*
 * ЛР8 - Робота з масивами об'єктів
 * 
 * МЕТА РОБОТИ:
 * Освоїти роботу з масивами об'єктів класу BoardGame, реалізувати базові операції:
 * - Ініціалізація масиву об'єктів
 * - Виведення всіх елементів масиву
 * - Модифікація елементів масиву
 * - Фільтрація елементів за критерієм (тривалість гри)
 * - Сортування масиву за різними полями (тривалість, кількість гравців)
 * - Сортування у зростаючому та спадному порядку
 * 
 * КЛЮЧОВІ КОНЦЕПЦІЇ:
 * 1. Масив об'єктів - масив посилань на об'єкти класу BoardGame
 * 2. Сортування бульбашкою (Bubble Sort) - простий алгоритм сортування
 * 3. Фільтрація - відбір елементів за заданим критерієм
 * 4. Модифікація елементів - зміна полів об'єкта за індексом
 * 
 * СТРУКТУРА ДАНИХ:
 * - GameArray - клас-обгортка для масиву BoardGame[]
 * - BoardGame - клас настільної гри з полями: name, minPlayers, maxPlayers, duration
 * - Scanner - для введення даних користувача
 */
package com.telegram;

import com.telegram.collection.GameArray;
import java.util.Scanner;

public class LR8 {
    public static void main(String[] args) {
        // Створення сканера для зчитування вводу користувача
        Scanner scanner = new Scanner(System.in);
        
        // Створення масиву на 20 елементів типу BoardGame
        // GameArray інкапсулює логіку роботи з масивом об'єктів
        GameArray gameArray = new GameArray(20);
        
        System.out.println("=== LR8: Array of Objects ===\n");
        
        // Ініціалізація масиву 10 заздалегідь визначеними іграми
        // Метод initializeWithDefaultGames() створює об'єкти BoardGame та додає їх у масив
        System.out.println("Initializing array with 10 games...");
        gameArray.initializeWithDefaultGames();
        System.out.println("Done!\n");
        
        // Прапорець для контролю циклу меню
        boolean running = true;
        
        // Головний цикл програми - виконується доки користувач не вибере вихід
        while (running) {
            // Виведення меню з доступними операціями
            System.out.println("\n--- Menu ---");
            System.out.println("1. Display all games");           // Виведення всіх ігор з масиву
            System.out.println("2. Modify a game");               // Модифікація елемента за індексом
            System.out.println("3. Filter by duration");          // Фільтрація за мінімальною тривалістю
            System.out.println("4. Sort by duration (ascending)"); // Сортування за зростанням тривалості
            System.out.println("5. Sort by duration (descending)");// Сортування за спаданням тривалості
            System.out.println("6. Sort by min players (ascending)"); // Сортування за зростанням min гравців
            System.out.println("7. Sort by min players (descending)");// Сортування за спаданням min гравців
            System.out.println("0. Exit");                        // Вихід з програми
            System.out.print("\nChoice: ");
            
            // Зчитування вибору користувача з обробкою помилок
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Очищення буфера після nextInt()
            } catch (Exception e) {
                scanner.nextLine(); // Очищення буфера при помилці вводу
                System.out.println("Invalid input");
                continue; // Повернення до початку циклу
            }
            
            // Обробка вибору користувача через switch-case
            switch (choice) {
                case 1:
                    // ОПЕРАЦІЯ 1: Виведення всіх елементів масиву
                    // Викликає метод displayAll() класу GameArray
                    // Проходить по всіх елементах масиву та виводить інформацію про кожну гру
                    gameArray.displayAll();
                    break;
                    
                case 2:
                    // ОПЕРАЦІЯ 2: Модифікація елемента масиву
                    // Дозволяє змінити поля об'єкта BoardGame за вказаним індексом
                    System.out.print("Enter game number (1-" + gameArray.getSize() + "): ");
                    int index = scanner.nextInt() - 1; // Конвертація з 1-based у 0-based індексацію
                    scanner.nextLine(); // Очищення буфера
                    
                    // Зчитування нових значень полів
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter min players: ");
                    int minPlayers = scanner.nextInt();
                    
                    System.out.print("Enter max players: ");
                    int maxPlayers = scanner.nextInt();
                    
                    System.out.print("Enter duration (minutes): ");
                    int duration = scanner.nextInt();
                    
                    // Виклик методу модифікації з новими значеннями
                    // Метод знаходить елемент за індексом та змінює його поля через сетери
                    gameArray.modifyGame(index, name, minPlayers, maxPlayers, duration);
                    break;
                    
                case 3:
                    // ОПЕРАЦІЯ 3: Фільтрація за тривалістю
                    // Виводить тільки ті ігри, тривалість яких >= заданого значення
                    System.out.print("Enter minimum duration: ");
                    int minDuration = scanner.nextInt();
                    gameArray.filterByDuration(minDuration);
                    break;
                    
                case 4:
                    // ОПЕРАЦІЯ 4: Сортування за зростанням тривалості
                    // Використовує алгоритм бульбашкового сортування (Bubble Sort)
                    // Порівнює елементи попарно та міняє місцями якщо duration[i] > duration[i+1]
                    gameArray.sortByDurationAsc();
                    break;
                    
                case 5:
                    // ОПЕРАЦІЯ 5: Сортування за спаданням тривалості
                    // Аналогічно до case 4, але умова порівняння протилежна
                    // Міняє місцями якщо duration[i] < duration[i+1]
                    gameArray.sortByDurationDesc();
                    break;
                    
                case 6:
                    // ОПЕРАЦІЯ 6: Сортування за зростанням мінімальної кількості гравців
                    // Сортує масив порівнюючи поле minPlayers
                    gameArray.sortByMinPlayersAsc();
                    break;
                    
                case 7:
                    // ОПЕРАЦІЯ 7: Сортування за спаданням мінімальної кількості гравців
                    // Протилежне сортування до case 6
                    gameArray.sortByMinPlayersDesc();
                    break;
                    
                case 0:
                    // ОПЕРАЦІЯ 0: Вихід з програми
                    // Встановлює прапорець running = false для завершення циклу while
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                    
                default:
                    // Обробка некоректного вибору
                    // Виконується якщо choice не відповідає жодному case
                    System.out.println("Invalid choice");
            }
        }
        
        // Закриття сканера для звільнення ресурсів
        // Важливо закривати Scanner після завершення роботи з ним
        scanner.close();
    }
}
