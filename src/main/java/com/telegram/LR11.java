package com.telegram;

import com.telegram.database.GameDatabase;
import com.telegram.games.BoardGame;
import java.util.List;
import java.util.Scanner;

public class LR11 {
    private static GameDatabase db;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        db = new GameDatabase();
        scanner = new Scanner(System.in);
        
        System.out.println("=== LR11: Database Client ===");
        System.out.println("Database: SQLite (games.db)");
        System.out.println("Table: board_games\n");
        
        boolean running = true;
        
        while (running) {
            showMenu();
            int choice = readInt("Choice: ");
            
            switch (choice) {
                case 1 -> viewAll();
                case 2 -> addRecord();
                case 3 -> filterByDuration();
                case 4 -> filterByPlayers();
                case 5 -> deleteRecord();
                case 6 -> editRecord();
                case 7 -> showSchema();
                case 8 -> recreateTable();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
            System.out.println();
        }
        
        db.close();
        scanner.close();
        System.out.println("Goodbye!");
    }
    
    private static void showMenu() {
        System.out.println("--- Menu ---");
        System.out.println("1. View all records");
        System.out.println("2. Add new record");
        System.out.println("3. Filter by duration (<=)");
        System.out.println("4. Filter by player count");
        System.out.println("5. Delete record");
        System.out.println("6. Edit record");
        System.out.println("7. Show table schema");
        System.out.println("8. Recreate table (BONUS)");
        System.out.println("0. Exit");
    }
    
    private static void viewAll() {
        System.out.println("\n=== All Games ===");
        List<BoardGame> games = db.getAllGames();
        
        if (games.isEmpty()) {
            System.out.println("Database is empty.");
            return;
        }
        
        printHeader();
        for (BoardGame g : games) {
            printGame(g);
        }
        System.out.println("Total: " + games.size() + " records");
    }
    
    private static void addRecord() {
        System.out.println("\n=== Add New Game ===");
        
        System.out.print("Name: ");
        String name = scanner.nextLine();
        
        int minP = readInt("Min players: ");
        int maxP = readInt("Max players: ");
        int dur = readInt("Duration (min): ");
        
        BoardGame game = new BoardGame(name, minP, maxP, dur);
        
        if (db.addGame(game)) {
            System.out.println("Game added successfully!");
        } else {
            System.out.println("Failed to add game.");
        }
    }
    
    private static void filterByDuration() {
        System.out.println("\n=== Filter by Duration ===");
        int maxDur = readInt("Max duration (min): ");
        
        List<BoardGame> games = db.getGamesByDuration(maxDur);
        
        if (games.isEmpty()) {
            System.out.println("No games found with duration <= " + maxDur + " min.");
            return;
        }
        
        System.out.println("Games with duration <= " + maxDur + " min:");
        printHeader();
        for (BoardGame g : games) {
            printGame(g);
        }
        System.out.println("Found: " + games.size() + " records");
    }
    
    private static void filterByPlayers() {
        System.out.println("\n=== Filter by Player Count ===");
        int players = readInt("Number of players: ");
        
        List<BoardGame> games = db.getGamesByPlayers(players);
        
        if (games.isEmpty()) {
            System.out.println("No games found for " + players + " players.");
            return;
        }
        
        System.out.println("Games for " + players + " players:");
        printHeader();
        for (BoardGame g : games) {
            printGame(g);
        }
        System.out.println("Found: " + games.size() + " records");
    }
    
    private static void deleteRecord() {
        System.out.println("\n=== Delete Game ===");
        viewAll();
        
        System.out.print("Enter game name to delete: ");
        String name = scanner.nextLine();
        
        if (db.deleteGame(name)) {
            System.out.println("Game deleted successfully!");
        } else {
            System.out.println("Game not found or delete failed.");
        }
    }
    
    private static void editRecord() {
        System.out.println("\n=== Edit Game ===");
        viewAll();
        
        System.out.print("Enter game name to edit: ");
        String oldName = scanner.nextLine();
        
        System.out.println("Enter new values:");
        System.out.print("New name: ");
        String newName = scanner.nextLine();
        
        int minP = readInt("New min players: ");
        int maxP = readInt("New max players: ");
        int dur = readInt("New duration (min): ");
        
        BoardGame newGame = new BoardGame(newName, minP, maxP, dur);
        
        if (db.updateGame(oldName, newGame)) {
            System.out.println("Game updated successfully!");
        } else {
            System.out.println("Game not found or update failed.");
        }
    }
    
    private static void showSchema() {
        System.out.println("\n=== Table Schema ===");
        System.out.println(db.getTableSchema());
    }
    
    private static void recreateTable() {
        System.out.println("\n=== Recreate Table (BONUS) ===");
        System.out.print("This will delete all data! Continue? (y/n): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("y")) {
            db.dropTable();
            db.createTable();
            System.out.println("Table recreated successfully!");
            System.out.println(db.getTableSchema());
        } else {
            System.out.println("Cancelled.");
        }
    }
    
    private static void printHeader() {
        System.out.println("-".repeat(60));
        System.out.printf("%-25s %8s %8s %10s%n", "Name", "Min", "Max", "Duration");
        System.out.println("-".repeat(60));
    }
    
    private static void printGame(BoardGame g) {
        System.out.printf("%-25s %8d %8d %10d%n", 
            g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration());
    }
    
    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(scanner.nextLine());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}
