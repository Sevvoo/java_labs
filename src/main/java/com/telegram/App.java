package com.telegram;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter y: ");
        double y = scanner.nextDouble();
        
        double x = 1.5;
        double d = 3.0;
        
        double num = Math.pow(Math.cos(y), 3) + Math.pow(2, d);
        double denom = Math.exp(x) + Math.log(Math.pow(Math.sin(x), 3) + 7.4);
        double R = num / denom;
        
        System.out.println("\nInput:");
        System.out.println("  y = " + y);
        System.out.println("  x = " + x);
        System.out.println("  d = " + d);
        System.out.println("\nResult:");
        System.out.printf("  R = %.6f\n", R);
        
        scanner.close();
    }
}
