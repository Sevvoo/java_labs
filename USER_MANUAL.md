# Laboratory Works 1-7 - User Manual

## Author Information
- Student: Muliarchuk Serhii
- Group: KN22002b
- Variant: 9
- Date: November 2025

## Expression
R = (cos³(y) + 2^d) / (e^x + ln(sin³(x) + 7.4))

---

## How to Run

### What You Need
- Java 17 or higher installed
- Terminal or command prompt

### Compile the program
```bash
javac -d target/classes src/main/java/com/telegram/gui/MainGUI.java
```

### Run GUI Application
```bash
java -cp target/classes com.telegram.gui.MainGUI
```

### Run Individual Labs (console mode)
```bash
java -cp target/classes com.telegram.App
java -cp target/classes com.telegram.LR2
java -cp target/classes com.telegram.LR3
java -cp target/classes com.telegram.LR4
java -cp target/classes com.telegram.LR5
java -cp target/classes com.telegram.LR6_1
java -cp target/classes com.telegram.LR6_2
java -cp target/classes com.telegram.LR7
```

---

## Using the GUI

### Main Window Layout
The program has a simple interface:
- Left side - buttons for each lab work
- Top right - my info and photo
- Center - results output area
- Bottom - clear output button

### How to Use Each Lab

#### LR1: Basic Expression
Click the button and enter a value for y. The program will calculate the result using x=1.5 and d=3.0.

#### LR2: Validation
Same as LR1, but now it checks if the values are valid before calculating. If something's wrong, you'll see an error message.

#### LR3: Exceptions
This one shows how the program handles errors properly using try-catch blocks.

#### LR4: Loops
Enter your variant number N. The program will calculate the expression for a range of x values and show you the first 10 results.
- Range: [-10-2.5*N; 5+1.2*N]
- Step: 0.5+N/20

#### LR5: Classes
Shows info about the ExpressionCalculator class - how it uses constructors, getters/setters, and different types of methods.

#### LR6: OOP
Demonstrates object-oriented programming with board games:
- BoardGame as a base class
- StrategyGame that extends it
- GameCollection that contains multiple games
- Shows inheritance and composition

#### LR7: Abstract & Interface
Shows more advanced OOP with abstract classes and interfaces:
- Game abstract class
- Playable interface
- CardGame and DiceGame implementations

#### About Button
Click to see detailed info about the project and author.

---

## What Each Lab Does

### LR1: Basic Expression Calculation
Just calculates the formula. You enter y, it gives you the result.

### LR2: Input Validation
Same calculation but with checks:
- Makes sure log argument is positive
- Makes sure we're not dividing by zero
- Shows errors if something's wrong

### LR3: Exception Handling
Shows proper error handling:
- Catches invalid input (like entering text instead of numbers)
- Catches math errors (like division by zero)
- Displays clear error messages

### LR4: Loops and Ranges
Calculates the expression for many x values at once:
- Uses FOR loop
- Uses WHILE loop
- Skips values that would cause errors
- Shows how loops work in practice

### LR5: Object-Oriented Programming Basics
Created a calculator class with:
- Fields for x, y, d with getters and setters
- Two constructors (default and with parameters)
- Regular method and static method
- Put it in a separate package

### LR6: Inheritance and Composition
Made a system of classes for board games:
- BoardGame - basic game class
- StrategyGame - extends BoardGame (inheritance)
- GameCollection - contains games (composition)
- Player - used by games (aggregation)
- Shows method overloading and overriding

### LR7: Abstract Classes and Interfaces
Added more structure:
- Game - abstract base class with abstract methods
- Playable - interface that games must implement
- CardGame and DiceGame - concrete implementations
- Demonstrates polymorphism

---

## Project Structure
```
src/main/java/com/telegram/
├── App.java                    - LR1
├── LR2.java                    - LR2
├── LR3.java                    - LR3
├── LR4.java                    - LR4
├── LR5.java                    - LR5
├── LR6_1.java                  - LR6 demo 1
├── LR6_2.java                  - LR6 demo 2
├── LR7.java                    - LR7
├── calculator/
│   └── ExpressionCalculator.java
├── games/
│   ├── Player.java
│   ├── BoardGame.java
│   ├── StrategyGame.java
│   ├── GameCollection.java
│   ├── Game.java              - abstract
│   ├── Playable.java          - interface
│   ├── CardGame.java
│   └── DiceGame.java
└── gui/
    └── MainGUI.java            - main GUI
```

---

## Technical Stuff

### The Formula
- Numerator: cos³(y) + 2^d
- Denominator: e^x + ln(sin³(x) + 7.4)

### Validation Rules
1. sin³(x) + 7.4 must be > 0 (can't take log of negative number)
2. Denominator can't be zero
3. Result must be a real number (not NaN or infinity)

### Default Values
- x = 1.5
- y = 2.0 or user input
- d = 3.0

---

## If Something Goes Wrong

### Invalid input error
Enter numbers only. Use a dot for decimals, not a comma.

### Log argument error
This happens when the math doesn't work out for the given x value. It's normal for some values.

### GUI won't start
- Check your Java version (needs to be 17 or newer)
- Make sure everything is compiled
- Run from the project root folder

### Class not found
Make sure you're in the right directory and using the correct command:
```bash
java -cp target/classes com.telegram.gui.MainGUI
```

### Photo doesn't show
Make sure image.png is in the project root folder.

---

## Summary

This project shows everything I learned in Java:
- Basic math and input/output
- Checking if input is valid
- Handling errors properly
- Using loops
- Object-oriented programming
- Inheritance and polymorphism
- Abstract classes and interfaces
- Making GUIs with Swing

All labs are combined in one program with a simple GUI that's easy to use.
