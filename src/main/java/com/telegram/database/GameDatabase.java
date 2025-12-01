/**
 * Пакет database містить класи для роботи з базами даних.
 */
package com.telegram.database;

import com.telegram.games.BoardGame;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з базою даних SQLite (ЛР11).
 * 
 * <p>Реалізує CRUD операції для таблиці board_games:</p>
 * <ul>
 *   <li><b>Create</b> - додавання нових ігор ({@link #addGame(BoardGame)})</li>
 *   <li><b>Read</b> - читання даних ({@link #getAllGames()}, {@link #getGamesByDuration(int)})</li>
 *   <li><b>Update</b> - оновлення записів ({@link #updateGame(String, BoardGame)})</li>
 *   <li><b>Delete</b> - видалення записів ({@link #deleteGame(String)})</li>
 * </ul>
 * 
 * <p>Схема таблиці board_games:</p>
 * <pre>
 * CREATE TABLE board_games (
 *     id INTEGER PRIMARY KEY AUTOINCREMENT,
 *     name TEXT NOT NULL UNIQUE,
 *     min_players INTEGER NOT NULL CHECK(min_players &gt; 0),
 *     max_players INTEGER NOT NULL CHECK(max_players &gt;= min_players),
 *     duration INTEGER NOT NULL CHECK(duration &gt; 0)
 * )
 * </pre>
 * 
 * @author Muliarchuk Serhii
 * @version 1.0
 * @see Connection
 * @see PreparedStatement
 */
public class GameDatabase {
    
    /** URL підключення до бази даних SQLite */
    private static final String DB_URL = "jdbc:sqlite:games.db";
    
    /** З'єднання з базою даних */
    /** З'єднання з базою даних */
    private Connection connection;
    
    /**
     * Конструктор. Завантажує драйвер SQLite, підключається до БД та створює таблицю.
     */
    public GameDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite driver not found: " + e.getMessage());
        }
        connect();
        createTable();
    }
    
    /**
     * Встановлює з'єднання з базою даних.
     * Якщо файл бази даних не існує, він буде створений автоматично.
     */
    private void connect() {
        try {
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
    
    /**
     * Створює таблицю board_games, якщо вона не існує.
     * Визначає ключові поля та обмеження (constraints).
     */
    public void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS board_games (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                min_players INTEGER NOT NULL CHECK(min_players > 0),
                max_players INTEGER NOT NULL CHECK(max_players >= min_players),
                duration INTEGER NOT NULL CHECK(duration > 0)
            )
            """;
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
    
    /**
     * Додає нову гру до бази даних.
     * 
     * @param game об'єкт BoardGame для додавання
     * @return true, якщо гру успішно додано; false, якщо сталася помилка
     *         (наприклад, гра з такою назвою вже існує)
     */
    public boolean addGame(BoardGame game) {
        String sql = "INSERT INTO board_games (name, min_players, max_players, duration) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, game.getName());
            pstmt.setInt(2, game.getMinPlayers());
            pstmt.setInt(3, game.getMaxPlayers());
            pstmt.setInt(4, game.getDuration());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Повертає список усіх ігор з бази даних.
     * 
     * @return List з об'єктами BoardGame, відсортований за id
     */
    public List<BoardGame> getAllGames() {
        List<BoardGame> games = new ArrayList<>();
        String sql = "SELECT * FROM board_games ORDER BY id";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                BoardGame game = new BoardGame(
                    rs.getString("name"),
                    rs.getInt("min_players"),
                    rs.getInt("max_players"),
                    rs.getInt("duration")
                );
                games.add(game);
            }
        } catch (SQLException e) {
            System.out.println("Select error: " + e.getMessage());
        }
        
        return games;
    }
    
    /**
     * Повертає ігри з тривалістю, меншою або рівною заданій.
     * 
     * @param maxDuration максимальна тривалість гри в хвилинах
     * @return List з відфільтрованими BoardGame об'єктами
     */
    public List<BoardGame> getGamesByDuration(int maxDuration) {
        List<BoardGame> games = new ArrayList<>();
        String sql = "SELECT * FROM board_games WHERE duration <= ? ORDER BY duration";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, maxDuration);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BoardGame game = new BoardGame(
                    rs.getString("name"),
                    rs.getInt("min_players"),
                    rs.getInt("max_players"),
                    rs.getInt("duration")
                );
                games.add(game);
            }
        } catch (SQLException e) {
            System.out.println("Filter error: " + e.getMessage());
        }
        
        return games;
    }
    
    /**
     * Повертає ігри, в які можна грати заданою кількістю гравців.
     * Фільтрує за умовою: minPlayers &lt;= playerCount &lt;= maxPlayers
     * 
     * @param playerCount кількість гравців
     * @return List з відповідними BoardGame об'єктами
     */
    public List<BoardGame> getGamesByPlayers(int playerCount) {
        List<BoardGame> games = new ArrayList<>();
        String sql = "SELECT * FROM board_games WHERE min_players <= ? AND max_players >= ? ORDER BY name";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, playerCount);
            pstmt.setInt(2, playerCount);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                BoardGame game = new BoardGame(
                    rs.getString("name"),
                    rs.getInt("min_players"),
                    rs.getInt("max_players"),
                    rs.getInt("duration")
                );
                games.add(game);
            }
        } catch (SQLException e) {
            System.out.println("Filter error: " + e.getMessage());
        }
        
        return games;
    }
    
    /**
     * Оновлює дані гри в базі даних.
     * 
     * @param oldName стара назва гри (для пошуку запису)
     * @param newGame об'єкт BoardGame з новими даними
     * @return true, якщо запис успішно оновлено; false, якщо гру не знайдено
     */
    public boolean updateGame(String oldName, BoardGame newGame) {
        String sql = "UPDATE board_games SET name = ?, min_players = ?, max_players = ?, duration = ? WHERE name = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newGame.getName());
            pstmt.setInt(2, newGame.getMinPlayers());
            pstmt.setInt(3, newGame.getMaxPlayers());
            pstmt.setInt(4, newGame.getDuration());
            pstmt.setString(5, oldName);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Видаляє гру з бази даних за назвою.
     * 
     * @param name назва гри для видалення
     * @return true, якщо запис успішно видалено; false, якщо гру не знайдено
     */
    public boolean deleteGame(String name) {
        String sql = "DELETE FROM board_games WHERE name = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Закриває з'єднання з базою даних.
     * Рекомендується викликати при завершенні роботи з БД.
     */
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Close error: " + e.getMessage());
        }
    }
    
    /**
     * Видаляє таблицю board_games з бази даних.
     * УВАГА: всі дані будуть втрачені!
     */
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS board_games";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Drop error: " + e.getMessage());
        }
    }
    
    /**
     * Повертає опис схеми таблиці бази даних.
     * Включає інформацію про всі поля та їх обмеження.
     * 
     * @return текстовий опис схеми таблиці
     */
    public String getTableSchema() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: board_games\n");
        sb.append("Columns:\n");
        sb.append("  id          - INTEGER PRIMARY KEY AUTOINCREMENT\n");
        sb.append("  name        - TEXT NOT NULL UNIQUE\n");
        sb.append("  min_players - INTEGER NOT NULL CHECK(min_players > 0)\n");
        sb.append("  max_players - INTEGER NOT NULL CHECK(max_players >= min_players)\n");
        sb.append("  duration    - INTEGER NOT NULL CHECK(duration > 0)\n");
        return sb.toString();
    }
}
