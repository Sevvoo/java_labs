package com.telegram;

import com.telegram.calculator.ExpressionCalculator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LR5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Default constructor ===");
        ExpressionCalculator calc1 = new ExpressionCalculator();
        
        try {
            double R = calc1.calculate();
            
            System.out.println("\nInput:");
            System.out.println("  x = " + calc1.getX());
            System.out.println("  y = " + calc1.getY());
            System.out.println("  d = " + calc1.getD());
            System.out.println("\nResult:");
            System.out.printf("  R = %.6f\n", R);
            
        } catch (ArithmeticException e) {
            System.out.println("\nERROR: " + e.getMessage());
        }
        
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
        
        ExpressionCalculator calc2 = new ExpressionCalculator(x, y, d);
        
        try {
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
        
        System.out.println("\n=== Static method ===");
        
        try {
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
