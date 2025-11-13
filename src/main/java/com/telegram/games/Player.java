package com.telegram.games;

public class Player {
    private String name;
    private int age;
    
    public Player() {
        this.name = "Unknown";
        this.age = 0;
    }
    
    public Player(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void showInfo() {
        System.out.println("Player: " + name + ", Age: " + age);
    }
    
    public void showInfo(String prefix) {
        System.out.println(prefix + " - Player: " + name + ", Age: " + age);
    }
}
