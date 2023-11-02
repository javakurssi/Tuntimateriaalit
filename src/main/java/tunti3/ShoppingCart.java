package tunti3;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<String, Double> items;
    private DiscountService discountService;

    public ShoppingCart(DiscountService discountService) {
        this.items = new HashMap<>();
        this.discountService = discountService;
    }

    public void addItem(String itemName, double price) {
        items.put(itemName, price);
    }

    public double calculateTotal() {
        double totalBeforeDiscount = items.values().stream().mapToDouble(Double::doubleValue).sum();
        double discount = discountService.calculateDiscount(totalBeforeDiscount);
        return totalBeforeDiscount - discount;
    }

    //You can also inject the DiscountService with setter
    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }
}
