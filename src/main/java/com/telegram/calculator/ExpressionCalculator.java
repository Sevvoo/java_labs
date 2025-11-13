package com.telegram.calculator;

public class ExpressionCalculator {
    private double x;
    private double y;
    private double d;
    
    public ExpressionCalculator() {
        this.x = 1.5;
        this.y = 2.0;
        this.d = 3.0;
    }
    
    public ExpressionCalculator(double x, double y, double d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
    
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public double getD() {
        return d;
    }
    
    public void setD(double d) {
        this.d = d;
    }
    
    public double calculate() throws ArithmeticException {
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
    
    public static double calculateStatic(double x, double y, double d) throws ArithmeticException {
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