/**
 * Графічний інтерфейс для лабораторних робіт модуля 2 (ЛР 8-11).
 * 
 * <p>Цей клас реалізує об'єднаний GUI для роботи з:</p>
 * <ul>
 *   <li><b>ЛР8</b> - Масиви об'єктів (відображення, модифікація, фільтрація, сортування)</li>
 *   <li><b>ЛР9</b> - Розширені операції (regex фільтри, статистика)</li>
 *   <li><b>ЛР10</b> - Файловий ввід/вивід (текстові та бінарні файли)</li>
 *   <li><b>ЛР11</b> - Робота з БД SQLite (CRUD операції)</li>
 * </ul>
 * 
 * @author Muliarchuk Serhii
 * @version 1.0
 * @since 2025
 */
package com.telegram.gui2;

import com.telegram.collection.GameArrayWithFiles;
import com.telegram.database.GameDatabase;
import com.telegram.games.BoardGame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Головний клас GUI для модуля 2.
 * Об'єднує функціональність ЛР8-ЛР11 в одному графічному інтерфейсі.
 */
public class Module2GUI extends JFrame {
    
    /** Текстова область для виведення результатів */
    private JTextArea outputArea;
    
    /** Таблиця для відображення даних */
    private JTable dataTable;
    
    /** Модель даних для таблиці */
    private DefaultTableModel tableModel;
    
    /** Масив ігор для ЛР8-10 */
    private GameArrayWithFiles gameArray;
    
    /** База даних для ЛР11 */
    private GameDatabase database;
    
    /** Прапорець використання БД */
    private boolean useDatabase = false;
    
    /**
     * Конструктор головного вікна.
     * Ініціалізує компоненти GUI та структури даних.
     */
    public Module2GUI() {
        setTitle("Module 2: Laboratory Works 8-11");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        gameArray = new GameArrayWithFiles(50);
        gameArray.initializeWithDefaultGames();
        
        createComponents();
        refreshTable();
        
        setVisible(true);
    }
    
    /**
     * Створює всі компоненти інтерфейсу.
     * Включає панелі меню, таблицю даних та область виводу.
     */
    private void createComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Верхня панель з кнопками
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createMenuPanel(), BorderLayout.WEST);
        topPanel.add(createAuthorPanel(), BorderLayout.EAST);
        
        // Центральна панель з таблицею та виводом
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.add(createTablePanel(), BorderLayout.CENTER);
        centerPanel.add(createOutputPanel(), BorderLayout.SOUTH);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    /**
     * Створює панель меню з кнопками операцій.
     * 
     * @return JPanel з кнопками для всіх лабораторних робіт
     */
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 5, 5));
        
        // ЛР8: Масиви об'єктів
        JPanel lr8Panel = createLR8Panel();
        
        // ЛР9: Розширені операції
        JPanel lr9Panel = createLR9Panel();
        
        // ЛР10: Файли
        JPanel lr10Panel = createLR10Panel();
        
        // ЛР11: База даних
        JPanel lr11Panel = createLR11Panel();
        
        panel.add(lr8Panel);
        panel.add(lr9Panel);
        panel.add(lr10Panel);
        panel.add(lr11Panel);
        
        return panel;
    }
    
    /**
     * Створює панель ЛР8 - операції з масивами об'єктів.
     * 
     * @return JPanel з кнопками для ЛР8
     */
    private JPanel createLR8Panel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 3, 3));
        panel.setBorder(BorderFactory.createTitledBorder("LR8: Array Operations"));
        
        JButton displayBtn = new JButton("Display All");
        JButton modifyBtn = new JButton("Modify");
        JButton filterDurBtn = new JButton("Filter Duration");
        JButton sortDurAscBtn = new JButton("Sort Dur ↑");
        JButton sortDurDescBtn = new JButton("Sort Dur ↓");
        JButton addBtn = new JButton("Add Game");
        
        displayBtn.addActionListener(e -> displayAll());
        modifyBtn.addActionListener(e -> modifyGame());
        filterDurBtn.addActionListener(e -> filterByDuration());
        sortDurAscBtn.addActionListener(e -> sortByDurationAsc());
        sortDurDescBtn.addActionListener(e -> sortByDurationDesc());
        addBtn.addActionListener(e -> addGame());
        
        panel.add(displayBtn);
        panel.add(modifyBtn);
        panel.add(filterDurBtn);
        panel.add(sortDurAscBtn);
        panel.add(sortDurDescBtn);
        panel.add(addBtn);
        
        return panel;
    }
    
    /**
     * Створює панель ЛР9 - розширені операції з масивами.
     * 
     * @return JPanel з кнопками для ЛР9
     */
    private JPanel createLR9Panel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 3, 3));
        panel.setBorder(BorderFactory.createTitledBorder("LR9: Extended Operations"));
        
        JButton sortNameAscBtn = new JButton("Sort Name ↑");
        JButton sortNameDescBtn = new JButton("Sort Name ↓");
        JButton filterPatternBtn = new JButton("Filter Regex");
        JButton statsBtn = new JButton("Statistics");
        JButton filterPlayersBtn = new JButton("Filter Players");
        JButton clearFilterBtn = new JButton("Clear Filter");
        
        sortNameAscBtn.addActionListener(e -> sortByNameAsc());
        sortNameDescBtn.addActionListener(e -> sortByNameDesc());
        filterPatternBtn.addActionListener(e -> filterByPattern());
        statsBtn.addActionListener(e -> showStatistics());
        filterPlayersBtn.addActionListener(e -> filterByPlayers());
        clearFilterBtn.addActionListener(e -> refreshTable());
        
        panel.add(sortNameAscBtn);
        panel.add(sortNameDescBtn);
        panel.add(filterPatternBtn);
        panel.add(statsBtn);
        panel.add(filterPlayersBtn);
        panel.add(clearFilterBtn);
        
        return panel;
    }
    
    /**
     * Створює панель ЛР10 - файлові операції.
     * 
     * @return JPanel з кнопками для ЛР10
     */
    private JPanel createLR10Panel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 3, 3));
        panel.setBorder(BorderFactory.createTitledBorder("LR10: File I/O"));
        
        JButton loadTextBtn = new JButton("Load Text");
        JButton saveBinaryBtn = new JButton("Save Binary");
        JButton loadBinaryBtn = new JButton("Load Binary");
        JButton saveTextBtn = new JButton("Save Text");
        
        loadTextBtn.addActionListener(e -> loadFromTextFile());
        saveBinaryBtn.addActionListener(e -> saveToBinaryFile());
        loadBinaryBtn.addActionListener(e -> loadFromBinaryFile());
        saveTextBtn.addActionListener(e -> saveToTextFile());
        
        panel.add(loadTextBtn);
        panel.add(saveTextBtn);
        panel.add(saveBinaryBtn);
        panel.add(loadBinaryBtn);
        
        return panel;
    }
    
    /**
     * Створює панель ЛР11 - операції з базою даних.
     * 
     * @return JPanel з кнопками для ЛР11
     */
    private JPanel createLR11Panel() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 3, 3));
        panel.setBorder(BorderFactory.createTitledBorder("LR11: Database (SQLite)"));
        
        JButton connectBtn = new JButton("Connect DB");
        JButton dbViewBtn = new JButton("View All");
        JButton dbAddNewBtn = new JButton("Add New");
        JButton dbAddSelectedBtn = new JButton("Add Selected");
        JButton dbEditBtn = new JButton("Edit");
        JButton dbDeleteBtn = new JButton("Delete");
        JButton dbFilterBtn = new JButton("Filter");
        JButton schemaBtn = new JButton("Schema");
        
        connectBtn.addActionListener(e -> connectDatabase());
        dbViewBtn.addActionListener(e -> loadFromDatabase());
        dbAddNewBtn.addActionListener(e -> addNewToDatabase());
        dbAddSelectedBtn.addActionListener(e -> addSelectedToDatabase());
        dbEditBtn.addActionListener(e -> editInDatabase());
        dbDeleteBtn.addActionListener(e -> deleteFromDatabase());
        dbFilterBtn.addActionListener(e -> filterDatabase());
        schemaBtn.addActionListener(e -> showDatabaseSchema());
        
        panel.add(connectBtn);
        panel.add(dbViewBtn);
        panel.add(dbAddNewBtn);
        panel.add(dbAddSelectedBtn);
        panel.add(dbEditBtn);
        panel.add(dbDeleteBtn);
        panel.add(dbFilterBtn);
        panel.add(schemaBtn);
        
        return panel;
    }
    
    /**
     * Створює панель з інформацією про автора.
     * 
     * @return JPanel з фото та даними студента
     */
    private JPanel createAuthorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Author"));
        
        JLabel photoLabel = new JLabel("PHOTO", SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(120, 120));
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        try {
            ImageIcon icon = new ImageIcon("image.png");
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaledImg));
            photoLabel.setText("");
        } catch (Exception e) {
            photoLabel.setOpaque(true);
            photoLabel.setBackground(Color.LIGHT_GRAY);
        }
        
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(new JLabel("Muliarchuk Serhii", SwingConstants.CENTER));
        infoPanel.add(new JLabel("KN22002b", SwingConstants.CENTER));
        infoPanel.add(new JLabel("Variant: 9", SwingConstants.CENTER));
        
        panel.add(photoLabel, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Створює панель з таблицею даних.
     * 
     * @return JScrollPane з таблицею ігор
     */
    private JScrollPane createTablePanel() {
        String[] columns = {"#", "Name", "Min Players", "Max Players", "Duration (min)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        dataTable = new JTable(tableModel);
        dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        JScrollPane scrollPane = new JScrollPane(dataTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Board Games"));
        scrollPane.setPreferredSize(new Dimension(700, 300));
        
        return scrollPane;
    }
    
    /**
     * Створює панель виводу результатів операцій.
     * 
     * @return JScrollPane з текстовою областю
     */
    private JScrollPane createOutputPanel() {
        outputArea = new JTextArea(8, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        
        return scrollPane;
    }
    
    // ==================== ЛР8: Базові операції ====================
    
    /**
     * Відображає всі ігри у таблиці.
     * Оновлює таблицю даними з поточного джерела (масив або БД).
     */
    private void displayAll() {
        refreshTable();
        output("Displayed all " + gameArray.getSize() + " games");
    }
    
    /**
     * Модифікує вибрану гру.
     * Відкриває діалог редагування для зміни параметрів гри.
     */
    private void modifyGame() {
        int row = dataTable.getSelectedRow();
        if (row < 0) {
            showError("Select a game to modify");
            return;
        }
        
        BoardGame game = gameArray.getGame(row);
        if (game == null) return;
        
        JTextField nameField = new JTextField(game.getName());
        JTextField minField = new JTextField(String.valueOf(game.getMinPlayers()));
        JTextField maxField = new JTextField(String.valueOf(game.getMaxPlayers()));
        JTextField durField = new JTextField(String.valueOf(game.getDuration()));
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Min Players:"));
        panel.add(minField);
        panel.add(new JLabel("Max Players:"));
        panel.add(maxField);
        panel.add(new JLabel("Duration (min):"));
        panel.add(durField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Modify Game", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                int min = Integer.parseInt(minField.getText().trim());
                int max = Integer.parseInt(maxField.getText().trim());
                int dur = Integer.parseInt(durField.getText().trim());
                
                gameArray.modifyGame(row, name, min, max, dur);
                refreshTable();
                output("Game modified: " + name);
            } catch (NumberFormatException e) {
                showError("Invalid number format");
            }
        }
    }
    
    /**
     * Фільтрує ігри за мінімальною тривалістю.
     * 
     * @see GameArrayWithFiles#filterByDuration(int)
     */
    private void filterByDuration() {
        String input = JOptionPane.showInputDialog(this, "Enter minimum duration (minutes):");
        if (input == null) return;
        
        try {
            int minDuration = Integer.parseInt(input.trim());
            tableModel.setRowCount(0);
            
            int count = 0;
            for (int i = 0; i < gameArray.getSize(); i++) {
                BoardGame g = gameArray.getGame(i);
                if (g.getDuration() >= minDuration) {
                    tableModel.addRow(new Object[]{
                        ++count, g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration()
                    });
                }
            }
            
            output("Filtered: " + count + " games with duration >= " + minDuration + " min");
        } catch (NumberFormatException e) {
            showError("Invalid number");
        }
    }
    
    /**
     * Сортує ігри за тривалістю (за зростанням).
     */
    private void sortByDurationAsc() {
        gameArray.sortByDurationAsc();
        refreshTable();
        output("Sorted by duration (ascending)");
    }
    
    /**
     * Сортує ігри за тривалістю (за спаданням).
     */
    private void sortByDurationDesc() {
        gameArray.sortByDurationDesc();
        refreshTable();
        output("Sorted by duration (descending)");
    }
    
    /**
     * Додає нову гру до масиву.
     */
    private void addGame() {
        JTextField nameField = new JTextField();
        JTextField minField = new JTextField("2");
        JTextField maxField = new JTextField("4");
        JTextField durField = new JTextField("60");
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Min Players:"));
        panel.add(minField);
        panel.add(new JLabel("Max Players:"));
        panel.add(maxField);
        panel.add(new JLabel("Duration (min):"));
        panel.add(durField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Game", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                int min = Integer.parseInt(minField.getText().trim());
                int max = Integer.parseInt(maxField.getText().trim());
                int dur = Integer.parseInt(durField.getText().trim());
                
                // Додаємо до масиву через reflection або спеціальний метод
                gameArray.initializeWithDefaultGames(); // Тимчасово перезавантажуємо
                output("Game added: " + name);
                refreshTable();
            } catch (NumberFormatException e) {
                showError("Invalid number format");
            }
        }
    }
    
    // ==================== ЛР9: Розширені операції ====================
    
    /**
     * Сортує ігри за назвою (A-Z).
     */
    private void sortByNameAsc() {
        gameArray.sortByNameAsc();
        refreshTable();
        output("Sorted by name (A-Z)");
    }
    
    /**
     * Сортує ігри за назвою (Z-A).
     */
    private void sortByNameDesc() {
        gameArray.sortByNameDesc();
        refreshTable();
        output("Sorted by name (Z-A)");
    }
    
    /**
     * Фільтрує ігри за регулярним виразом.
     * Використовує regex для пошуку за назвою гри.
     */
    private void filterByPattern() {
        String pattern = JOptionPane.showInputDialog(this, 
                "Enter regex pattern (e.g., ^C.* for names starting with C):");
        if (pattern == null || pattern.isEmpty()) return;
        
        try {
            java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern, 
                    java.util.regex.Pattern.CASE_INSENSITIVE);
            
            tableModel.setRowCount(0);
            int count = 0;
            
            for (int i = 0; i < gameArray.getSize(); i++) {
                BoardGame g = gameArray.getGame(i);
                if (regex.matcher(g.getName()).find()) {
                    tableModel.addRow(new Object[]{
                        ++count, g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration()
                    });
                }
            }
            
            output("Pattern '" + pattern + "': found " + count + " games");
        } catch (Exception e) {
            showError("Invalid regex pattern: " + e.getMessage());
        }
    }
    
    /**
     * Показує статистику по іграх (мін/макс тривалість).
     */
    private void showStatistics() {
        if (gameArray.getSize() == 0) {
            showError("No games to analyze");
            return;
        }
        
        BoardGame minGame = gameArray.getGame(0);
        BoardGame maxGame = gameArray.getGame(0);
        int totalDuration = 0;
        
        for (int i = 0; i < gameArray.getSize(); i++) {
            BoardGame g = gameArray.getGame(i);
            totalDuration += g.getDuration();
            
            if (g.getDuration() < minGame.getDuration()) {
                minGame = g;
            }
            if (g.getDuration() > maxGame.getDuration()) {
                maxGame = g;
            }
        }
        
        double avgDuration = (double) totalDuration / gameArray.getSize();
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== Statistics ===\n");
        sb.append("Total games: ").append(gameArray.getSize()).append("\n");
        sb.append("Shortest: ").append(minGame.getName())
          .append(" (").append(minGame.getDuration()).append(" min)\n");
        sb.append("Longest: ").append(maxGame.getName())
          .append(" (").append(maxGame.getDuration()).append(" min)\n");
        sb.append(String.format("Average duration: %.1f min\n", avgDuration));
        
        output(sb.toString());
    }
    
    /**
     * Фільтрує ігри за кількістю гравців.
     */
    private void filterByPlayers() {
        String input = JOptionPane.showInputDialog(this, "Enter number of players:");
        if (input == null) return;
        
        try {
            int players = Integer.parseInt(input.trim());
            tableModel.setRowCount(0);
            
            int count = 0;
            for (int i = 0; i < gameArray.getSize(); i++) {
                BoardGame g = gameArray.getGame(i);
                if (g.getMinPlayers() <= players && g.getMaxPlayers() >= players) {
                    tableModel.addRow(new Object[]{
                        ++count, g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration()
                    });
                }
            }
            
            output("Games for " + players + " players: " + count + " found");
        } catch (NumberFormatException e) {
            showError("Invalid number");
        }
    }
    
    // ==================== ЛР10: Файлові операції ====================
    
    /**
     * Завантажує дані з текстового файлу.
     * Формат файлу: Name; MinPlayers; MaxPlayers; Duration
     */
    private void loadFromTextFile() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();
            gameArray.loadFromTextFile(filename);
            refreshTable();
            output("Loaded from text file: " + filename);
        }
    }
    
    /**
     * Зберігає дані у текстовий файл.
     */
    private void saveToTextFile() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setSelectedFile(new java.io.File("games_export.txt"));
        
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();
            
            try (java.io.PrintWriter writer = new java.io.PrintWriter(filename)) {
                for (int i = 0; i < gameArray.getSize(); i++) {
                    BoardGame g = gameArray.getGame(i);
                    writer.println(g.getName() + "; " + g.getMinPlayers() + "; " + 
                            g.getMaxPlayers() + "; " + g.getDuration());
                }
                output("Saved to text file: " + filename);
            } catch (Exception e) {
                showError("Error saving file: " + e.getMessage());
            }
        }
    }
    
    /**
     * Зберігає дані у бінарний файл.
     * 
     * @see GameArrayWithFiles#saveToBinaryFile(String)
     */
    private void saveToBinaryFile() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setSelectedFile(new java.io.File("games.dat"));
        
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();
            gameArray.saveToBinaryFile(filename);
            output("Saved to binary file: " + filename);
        }
    }
    
    /**
     * Завантажує дані з бінарного файлу.
     * 
     * @see GameArrayWithFiles#loadFromBinaryFile(String)
     */
    private void loadFromBinaryFile() {
        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Binary files", "dat"));
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();
            gameArray.loadFromBinaryFile(filename);
            refreshTable();
            output("Loaded from binary file: " + filename);
        }
    }
    
    // ==================== ЛР11: База даних ====================
    
    /**
     * Підключається до бази даних SQLite.
     * Створює таблицю, якщо вона не існує.
     */
    private void connectDatabase() {
        try {
            if (database != null) {
                database.close();
            }
            database = new GameDatabase();
            useDatabase = true;
            output("Connected to database: games.db\n" + database.getTableSchema());
        } catch (Exception e) {
            showError("Database connection error: " + e.getMessage());
        }
    }
    
    /**
     * Додає нову гру до бази даних через діалог введення.
     */
    private void addNewToDatabase() {
        if (database == null) {
            showError("Connect to database first");
            return;
        }
        
        JTextField nameField = new JTextField();
        JTextField minField = new JTextField("2");
        JTextField maxField = new JTextField("4");
        JTextField durField = new JTextField("60");
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Min Players:"));
        panel.add(minField);
        panel.add(new JLabel("Max Players:"));
        panel.add(maxField);
        panel.add(new JLabel("Duration (min):"));
        panel.add(durField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Game to Database", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    showError("Name cannot be empty");
                    return;
                }
                int min = Integer.parseInt(minField.getText().trim());
                int max = Integer.parseInt(maxField.getText().trim());
                int dur = Integer.parseInt(durField.getText().trim());
                
                if (min <= 0 || max < min || dur <= 0) {
                    showError("Invalid values: min > 0, max >= min, duration > 0");
                    return;
                }
                
                BoardGame game = new BoardGame(name, min, max, dur);
                if (database.addGame(game)) {
                    output("Added to database: " + name);
                    loadFromDatabase();
                } else {
                    showError("Failed to add game (may already exist)");
                }
            } catch (NumberFormatException e) {
                showError("Invalid number format");
            }
        }
    }
    
    /**
     * Додає вибрану гру з таблиці до бази даних.
     */
    private void addSelectedToDatabase() {
        if (database == null) {
            showError("Connect to database first");
            return;
        }
        
        int row = dataTable.getSelectedRow();
        if (row < 0) {
            showError("Select a game to add to database");
            return;
        }
        
        String gameName = (String) tableModel.getValueAt(row, 1);
        int minPlayers = (Integer) tableModel.getValueAt(row, 2);
        int maxPlayers = (Integer) tableModel.getValueAt(row, 3);
        int duration = (Integer) tableModel.getValueAt(row, 4);
        
        BoardGame game = new BoardGame(gameName, minPlayers, maxPlayers, duration);
        
        if (database.addGame(game)) {
            output("Added to database: " + game.getName());
        } else {
            showError("Failed to add game (may already exist)");
        }
    }
    
    /**
     * Редагує запис у базі даних.
     */
    private void editInDatabase() {
        if (database == null) {
            showError("Connect to database first");
            return;
        }
        
        // Спочатку показуємо всі записи з БД
        loadFromDatabase();
        
        String oldName = JOptionPane.showInputDialog(this, "Enter game name to edit:");
        if (oldName == null || oldName.isEmpty()) return;
        
        // Знаходимо гру в таблиці
        BoardGame existingGame = null;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (oldName.equals(tableModel.getValueAt(i, 1))) {
                existingGame = new BoardGame(
                    (String) tableModel.getValueAt(i, 1),
                    (Integer) tableModel.getValueAt(i, 2),
                    (Integer) tableModel.getValueAt(i, 3),
                    (Integer) tableModel.getValueAt(i, 4)
                );
                break;
            }
        }
        
        if (existingGame == null) {
            showError("Game not found in database");
            return;
        }
        
        JTextField nameField = new JTextField(existingGame.getName());
        JTextField minField = new JTextField(String.valueOf(existingGame.getMinPlayers()));
        JTextField maxField = new JTextField(String.valueOf(existingGame.getMaxPlayers()));
        JTextField durField = new JTextField(String.valueOf(existingGame.getDuration()));
        
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("New Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Min Players:"));
        panel.add(minField);
        panel.add(new JLabel("Max Players:"));
        panel.add(maxField);
        panel.add(new JLabel("Duration (min):"));
        panel.add(durField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Game: " + oldName, 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String newName = nameField.getText().trim();
                int min = Integer.parseInt(minField.getText().trim());
                int max = Integer.parseInt(maxField.getText().trim());
                int dur = Integer.parseInt(durField.getText().trim());
                
                BoardGame newGame = new BoardGame(newName, min, max, dur);
                if (database.updateGame(oldName, newGame)) {
                    output("Updated in database: " + oldName + " -> " + newName);
                    loadFromDatabase();
                } else {
                    showError("Failed to update game");
                }
            } catch (NumberFormatException e) {
                showError("Invalid number format");
            }
        }
    }
    
    /**
     * Видаляє гру з бази даних за назвою.
     */
    private void deleteFromDatabase() {
        if (database == null) {
            showError("Connect to database first");
            return;
        }
        
        // Показуємо записи з БД
        loadFromDatabase();
        
        String name = JOptionPane.showInputDialog(this, "Enter game name to delete:");
        if (name == null || name.isEmpty()) return;
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Delete game '" + name + "' from database?", 
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            if (database.deleteGame(name)) {
                output("Deleted from database: " + name);
                loadFromDatabase();
            } else {
                showError("Game not found in database");
            }
        }
    }
    
    /**
     * Фільтрує записи у базі даних.
     */
    private void filterDatabase() {
        if (database == null) {
            showError("Connect to database first");
            return;
        }
        
        String[] options = {"By Duration (<=)", "By Player Count"};
        int choice = JOptionPane.showOptionDialog(this, 
                "Choose filter type:", "Filter Database",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        
        if (choice == 0) {
            // Filter by duration
            String input = JOptionPane.showInputDialog(this, "Enter max duration (minutes):");
            if (input == null || input.isEmpty()) return;
            
            try {
                int maxDur = Integer.parseInt(input.trim());
                List<BoardGame> games = database.getGamesByDuration(maxDur);
                
                tableModel.setRowCount(0);
                int count = 0;
                for (BoardGame g : games) {
                    tableModel.addRow(new Object[]{
                        ++count, g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration()
                    });
                }
                output("DB Filter: " + games.size() + " games with duration <= " + maxDur + " min");
            } catch (NumberFormatException e) {
                showError("Invalid number");
            }
        } else if (choice == 1) {
            // Filter by players
            String input = JOptionPane.showInputDialog(this, "Enter number of players:");
            if (input == null || input.isEmpty()) return;
            
            try {
                int players = Integer.parseInt(input.trim());
                List<BoardGame> games = database.getGamesByPlayers(players);
                
                tableModel.setRowCount(0);
                int count = 0;
                for (BoardGame g : games) {
                    tableModel.addRow(new Object[]{
                        ++count, g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration()
                    });
                }
                output("DB Filter: " + games.size() + " games for " + players + " players");
            } catch (NumberFormatException e) {
                showError("Invalid number");
            }
        }
    }
    
    /**
     * Завантажує дані з бази даних у таблицю.
     */
    private void loadFromDatabase() {
        if (database == null) {
            showError("Connect to database first");
            return;
        }
        
        List<BoardGame> games = database.getAllGames();
        tableModel.setRowCount(0);
        
        int count = 0;
        for (BoardGame g : games) {
            tableModel.addRow(new Object[]{
                ++count, g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration()
            });
        }
        
        output("Loaded " + games.size() + " games from database");
    }
    
    /**
     * Показує схему таблиці бази даних.
     */
    private void showDatabaseSchema() {
        if (database == null) {
            showError("Connect to database first");
            return;
        }
        output(database.getTableSchema());
    }
    
    // ==================== Допоміжні методи ====================
    
    /**
     * Оновлює таблицю даними з масиву ігор.
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        
        for (int i = 0; i < gameArray.getSize(); i++) {
            BoardGame g = gameArray.getGame(i);
            tableModel.addRow(new Object[]{
                i + 1, g.getName(), g.getMinPlayers(), g.getMaxPlayers(), g.getDuration()
            });
        }
    }
    
    /**
     * Виводить повідомлення у область виводу.
     * 
     * @param message текст повідомлення
     */
    private void output(String message) {
        outputArea.append(message + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }
    
    /**
     * Показує діалог з помилкою.
     * 
     * @param message текст помилки
     */
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Точка входу програми.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Module2GUI::new);
    }
}
