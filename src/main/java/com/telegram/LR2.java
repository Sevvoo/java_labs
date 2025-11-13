package com.telegram;

import java.util.Scanner;

public class LR2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter y: ");
        double y = scanner.nextDouble();
        
        double x = 1.5;
        double d = 3.0;
        boolean isValid = true;
        
        double sinCubed = Math.pow(Math.sin(x), 3);
        double logArg = sinCubed + 7.4;
        
        if (logArg <= 0) {
            System.out.println("ERROR: sin^3(x) + 7.4 <= 0");
            isValid = false;
        }
        
        double denom = Math.exp(x) + Math.log(logArg);
        
        if (Math.abs(denom) < 1e-10) {
            System.out.println("ERROR: denominator = 0");
            isValid = false;
        }
        
        if (!isValid) {
            System.out.println("\nCalculation impossible.");
        } else {
            double num = Math.pow(Math.cos(y), 3) + Math.pow(2, d);
            double R = num / denom;
            
            System.out.println("\nInput:");
            System.out.println("  y = " + y);
            System.out.println("  x = " + x);
            System.out.println("  d = " + d);
            System.out.println("\nResult:");
            System.out.printf("  R = %.6f\n", R);
        }
        
        scanner.close();
    }
}
