package com.telegram;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LR3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double y = 0;
        double x = 1.5;
        double d = 3.0;
        
        try {
            System.out.print("Enter y: ");
            y = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Invalid input.");
            scanner.close();
            return;
        }
        
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
            
            System.out.println("\nInput:");
            System.out.println("  y = " + y);
            System.out.println("  x = " + x);
            System.out.println("  d = " + d);
            System.out.println("\nResult:");
            System.out.printf("  R = %.6f\n", R);
            
        } catch (ArithmeticException e) {
            System.out.println("\nERROR: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
