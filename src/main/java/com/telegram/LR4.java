package com.telegram;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LR4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 0;
        
        try {
            System.out.print("Enter variant N: ");
            N = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Invalid input.");
            scanner.close();
            return;
        }
        
        double xStart = -10 - 2.5 * N;
        double xEnd = 5 + 1.2 * N;
        double step = 0.5 + N / 20.0;
        double y = 2.0;
        double d = 3.0;
        
        System.out.println("\nParameters:");
        System.out.println("  N = " + N);
        System.out.println("  Range: [" + xStart + "; " + xEnd + "]");
        System.out.println("  Step: " + step);
        
        System.out.println("\n=== FOR loop ===");
        calculateWithFor(xStart, xEnd, step, y, d);
        
        System.out.println("\n=== WHILE loop ===");
        calculateWithWhile(xStart, xEnd, step, y, d);
        
        scanner.close();
    }
    
    private static void calculateWithFor(double xStart, double xEnd, double step, double y, double d) {
        int count = 0;
        int errors = 0;
        
        for (double x = xStart; x <= xEnd; x += step) {
            try {
                double R = calculate(x, y, d);
                System.out.printf("x = %7.3f | R = %12.6f\n", x, R);
                count++;
            } catch (ArithmeticException e) {
                errors++;
            }
        }
        
        System.out.println("\nCalculated: " + count + ", Skipped: " + errors);
    }
    
    private static void calculateWithWhile(double xStart, double xEnd, double step, double y, double d) {
        int count = 0;
        int errors = 0;
        double x = xStart;
        
        while (x <= xEnd) {
            try {
                double R = calculate(x, y, d);
                System.out.printf("x = %7.3f | R = %12.6f\n", x, R);
                count++;
            } catch (ArithmeticException e) {
                errors++;
            }
            x += step;
        }
        
        System.out.println("\nCalculated: " + count + ", Skipped: " + errors);
    }
    
    private static double calculate(double x, double y, double d) throws ArithmeticException {
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
