package tunti3;

public class Calculator {
	
    public int add(int a, int b) {
        return a + b;
    }
    
    public String addWithCurrency(int a, int b) {
        return (a + b) + " €";
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (double) a / b;
    }
    
    public int square(int a) {
        return multiply(a, a);
    }
}
