package tunti3;

public class TestDiscountService implements DiscountService {
    @Override
    public double calculateDiscount(double total) {
        // A simple test implementation that provides a fixed discount for testing
        return total * 0.1; // 10% discount for testing purposes
    }
}