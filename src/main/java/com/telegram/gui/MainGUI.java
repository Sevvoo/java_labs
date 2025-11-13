package com.telegram.gui;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {
    private JTextArea outputArea;
    private JPanel mainPanel;
    
    public MainGUI() {
        setTitle("Laboratory Works 1-7");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        createComponents();
        setVisible(true);
    }
    
    private void createComponents() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(createMenuPanel(), BorderLayout.WEST);
        topPanel.add(createAuthorPanel(), BorderLayout.EAST);
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
        JButton clearButton = new JButton("Clear Output");
        clearButton.addActionListener(e -> outputArea.setText(""));
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(clearButton, BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Laboratory Works"));
        
        JButton lr1Btn = new JButton("LR1: Basic Expression");
        JButton lr2Btn = new JButton("LR2: Validation");
        JButton lr3Btn = new JButton("LR3: Exceptions");
        JButton lr4Btn = new JButton("LR4: Loops");
        JButton lr5Btn = new JButton("LR5: Classes");
        JButton lr6Btn = new JButton("LR6: OOP");
        JButton lr7Btn = new JButton("LR7: Abstract & Interface");
        JButton aboutBtn = new JButton("About");
        
        lr1Btn.addActionListener(e -> runLR1());
        lr2Btn.addActionListener(e -> runLR2());
        lr3Btn.addActionListener(e -> runLR3());
        lr4Btn.addActionListener(e -> runLR4());
        lr5Btn.addActionListener(e -> runLR5());
        lr6Btn.addActionListener(e -> runLR6());
        lr7Btn.addActionListener(e -> runLR7());
        aboutBtn.addActionListener(e -> showAbout());
        
        panel.add(lr1Btn);
        panel.add(lr2Btn);
        panel.add(lr3Btn);
        panel.add(lr4Btn);
        panel.add(lr5Btn);
        panel.add(lr6Btn);
        panel.add(lr7Btn);
        panel.add(aboutBtn);
        
        return panel;
    }
    
    private JPanel createAuthorPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Author Info"));
        
        JLabel photoLabel = new JLabel("PHOTO", SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(150, 150));
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        try {
            ImageIcon icon = new ImageIcon("image.png");
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaledImg));
            photoLabel.setText("");
        } catch (Exception e) {
            photoLabel.setOpaque(true);
            photoLabel.setBackground(Color.LIGHT_GRAY);
        }
        
        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(new JLabel("Muliarchuk Serhii", SwingConstants.CENTER));
        infoPanel.add(new JLabel("Group: KN22002b", SwingConstants.CENTER));
        infoPanel.add(new JLabel("Variant: 9", SwingConstants.CENTER));
        
        panel.add(photoLabel, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void runLR1() {
        String input = JOptionPane.showInputDialog(this, "Enter y:");
        if (input == null) return;
        
        try {
            double y = Double.parseDouble(input);
            double x = 1.5;
            double d = 3.0;
            
            double num = Math.pow(Math.cos(y), 3) + Math.pow(2, d);
            double denom = Math.exp(x) + Math.log(Math.pow(Math.sin(x), 3) + 7.4);
            double R = num / denom;
            
            outputArea.setText("=== LR1: Basic Expression ===\n");
            outputArea.append("\nInput:\n");
            outputArea.append("  y = " + y + "\n");
            outputArea.append("  x = " + x + "\n");
            outputArea.append("  d = " + d + "\n");
            outputArea.append("\nResult:\n");
            outputArea.append(String.format("  R = %.6f\n", R));
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void runLR2() {
        String input = JOptionPane.showInputDialog(this, "Enter y:");
        if (input == null) return;
        
        try {
            double y = Double.parseDouble(input);
            double x = 1.5;
            double d = 3.0;
            
            StringBuilder output = new StringBuilder("=== LR2: Validation ===\n\n");
            boolean isValid = true;
            
            double sinCubed = Math.pow(Math.sin(x), 3);
            double logArg = sinCubed + 7.4;
            
            if (logArg <= 0) {
                output.append("ERROR: sin^3(x) + 7.4 <= 0\n");
                isValid = false;
            }
            
            double denom = Math.exp(x) + Math.log(logArg);
            
            if (Math.abs(denom) < 1e-10) {
                output.append("ERROR: denominator = 0\n");
                isValid = false;
            }
            
            if (!isValid) {
                output.append("\nCalculation impossible.\n");
            } else {
                double num = Math.pow(Math.cos(y), 3) + Math.pow(2, d);
                double R = num / denom;
                
                output.append("Input:\n");
                output.append("  y = ").append(y).append("\n");
                output.append("  x = ").append(x).append("\n");
                output.append("  d = ").append(d).append("\n");
                output.append("\nResult:\n");
                output.append(String.format("  R = %.6f\n", R));
            }
            
            outputArea.setText(output.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void runLR3() {
        String input = JOptionPane.showInputDialog(this, "Enter y:");
        if (input == null) return;
        
        try {
            double y = Double.parseDouble(input);
            double x = 1.5;
            double d = 3.0;
            
            StringBuilder output = new StringBuilder("=== LR3: Exception Handling ===\n\n");
            
            try {
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
                
                output.append("Input:\n");
                output.append("  y = ").append(y).append("\n");
                output.append("  x = ").append(x).append("\n");
                output.append("  d = ").append(d).append("\n");
                output.append("\nResult:\n");
                output.append(String.format("  R = %.6f\n", R));
                
            } catch (ArithmeticException e) {
                output.append("\nERROR: ").append(e.getMessage()).append("\n");
            }
            
            outputArea.setText(output.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void runLR4() {
        String input = JOptionPane.showInputDialog(this, "Enter variant N:");
        if (input == null) return;
        
        try {
            int N = Integer.parseInt(input);
            double xStart = -10 - 2.5 * N;
            double xEnd = 5 + 1.2 * N;
            double step = 0.5 + N / 20.0;
            double y = 2.0;
            double d = 3.0;
            
            StringBuilder output = new StringBuilder("=== LR4: Loops ===\n\n");
            output.append("Parameters:\n");
            output.append("  N = ").append(N).append("\n");
            output.append("  Range: [").append(xStart).append("; ").append(xEnd).append("]\n");
            output.append("  Step: ").append(step).append("\n\n");
            output.append("FOR loop results (first 10):\n");
            
            int count = 0;
            for (double x = xStart; x <= xEnd && count < 10; x += step) {
                try {
                    double R = calculate(x, y, d);
                    output.append(String.format("x = %7.3f | R = %12.6f\n", x, R));
                    count++;
                } catch (ArithmeticException e) {
                }
            }
            
            outputArea.setText(output.toString());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void runLR5() {
        outputArea.setText("=== LR5: Classes ===\n\n");
        outputArea.append("See LR5.java for full implementation\n");
        outputArea.append("\nDefault constructor demo:\n");
        outputArea.append("  x = 1.5, y = 2.0, d = 3.0\n");
        outputArea.append("\nFeatures:\n");
        outputArea.append("- Getters and setters\n");
        outputArea.append("- Default constructor\n");
        outputArea.append("- Parameterized constructor\n");
        outputArea.append("- Non-static method\n");
        outputArea.append("- Static method\n");
    }
    
    private void runLR6() {
        outputArea.setText("=== LR6: OOP ===\n\n");
        outputArea.append("Classes:\n");
        outputArea.append("- BoardGame (base class)\n");
        outputArea.append("- StrategyGame (inheritance)\n");
        outputArea.append("- GameCollection (composition)\n");
        outputArea.append("- Player (aggregation)\n\n");
        outputArea.append("Features:\n");
        outputArea.append("- Inheritance\n");
        outputArea.append("- Composition/Aggregation\n");
        outputArea.append("- Method overloading\n");
        outputArea.append("- Method overriding\n");
        outputArea.append("\nSee LR6_1.java and LR6_2.java for demos\n");
    }
    
    private void runLR7() {
        outputArea.setText("=== LR7: Abstract & Interface ===\n\n");
        outputArea.append("Abstract class:\n");
        outputArea.append("- Game (abstract)\n");
        outputArea.append("  - showRules() [abstract]\n");
        outputArea.append("  - calculateScore() [abstract]\n\n");
        outputArea.append("Interface:\n");
        outputArea.append("- Playable\n");
        outputArea.append("  - startGame()\n");
        outputArea.append("  - endGame()\n");
        outputArea.append("  - canPlay()\n\n");
        outputArea.append("Implementations:\n");
        outputArea.append("- CardGame extends Game implements Playable\n");
        outputArea.append("- DiceGame extends Game implements Playable\n");
        outputArea.append("\nSee LR7.java for demo\n");
    }
    
    private void showAbout() {
        JDialog dialog = new JDialog(this, "About", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(400, 350);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel photoLabel = new JLabel("PHOTO", SwingConstants.CENTER);
        photoLabel.setPreferredSize(new Dimension(150, 150));
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        
        try {
            ImageIcon icon = new ImageIcon("image.png");
            Image img = icon.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            photoLabel.setIcon(new ImageIcon(scaledImg));
            photoLabel.setText("");
        } catch (Exception e) {
            photoLabel.setOpaque(true);
            photoLabel.setBackground(Color.LIGHT_GRAY);
        }
        
        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setText("Laboratory Works 1-7\n\n" +
                    "Student: Muliarchuk Serhii\n" +
                    "Group: KN22002b\n" +
                    "Variant: 9\n\n" +
                    "Expression:\n" +
                    "R = (cos³(y) + 2^d) / (e^x + ln(sin³(x) + 7.4))\n\n" +
                    "© 2025");
        
        panel.add(photoLabel, BorderLayout.NORTH);
        panel.add(info, BorderLayout.CENTER);
        
        dialog.add(panel);
        dialog.setVisible(true);
    }
    
    private double calculate(double x, double y, double d) throws ArithmeticException {
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI());
    }
}
