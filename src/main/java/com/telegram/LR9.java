package com.telegram;

import com.telegram.collection.GameArrayExtended;
import java.util.Scanner;

public class LR9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameArrayExtended gameArray = new GameArrayExtended(20);
        
        System.out.println("=== LR9: Extended Array Operations ===\n");
        
        System.out.println("Initializing array with 10 games...");
        gameArray.initializeWithDefaultGames();
        System.out.println("Done!\n");
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Display all games");
            System.out.println("2. Modify a game");
            System.out.println("3. Filter by duration");
            System.out.println("4. Sort by duration (ascending)");
            System.out.println("5. Sort by duration (descending)");
            System.out.println("6. Sort by name (A-Z)");
            System.out.println("7. Sort by name (Z-A)");
            System.out.println("8. Filter by name pattern");
            System.out.println("9. Show duration min/max");
            System.out.println("0. Exit");
            System.out.print("\nChoice: ");
            
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input");
                continue;
            }
            
            switch (choice) {
                case 1:
                    gameArray.displayAll();
                    break;
                    
                case 2:
                    System.out.print("Enter game number (1-" + gameArray.getSize() + "): ");
                    int index = scanner.nextInt() - 1;
                    scanner.nextLine();
                    
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();
                    
                    System.out.print("Enter min players: ");
                    int minPlayers = scanner.nextInt();
                    
                    System.out.print("Enter max players: ");
                    int maxPlayers = scanner.nextInt();
                    
                    System.out.print("Enter duration (minutes): ");
                    int duration = scanner.nextInt();
                    
                    gameArray.modifyGame(index, name, minPlayers, maxPlayers, duration);
                    break;
                    
                case 3:
                    System.out.print("Enter minimum duration: ");
                    int minDuration = scanner.nextInt();
                    gameArray.filterByDuration(minDuration);
                    break;
                    
                case 4:
                    gameArray.sortByDurationAsc();
                    break;
                    
                case 5:
                    gameArray.sortByDurationDesc();
                    break;
                    
                case 6:
                    gameArray.sortByNameAsc();
                    break;
                    
                case 7:
                    gameArray.sortByNameDesc();
                    break;
                    
                case 8:
                    System.out.println("\nExamples:");
                    System.out.println("  - Games starting with 'C': ^C");
                    System.out.println("  - Games ending with 'ly': ly$");
                    System.out.println("  - Games containing 'an': an");
                    System.out.print("\nEnter pattern (regex): ");
                    String pattern = scanner.nextLine();
                    gameArray.filterByNamePattern(pattern);
                    break;
                    
                case 9:
                    gameArray.showDurationMinMax();
                    break;
                    
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice");
            }
        }
        
        scanner.close();
    }
}
