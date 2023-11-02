package tunti3;

public class ShoppingCartApp {

    public static void main(String[] args) {
        // Create an instance of ExternalDiscountService
        DiscountService discountService = new ExternalDiscountService();

        // Create a ShoppingCart and provide the discount service
        ShoppingCart shoppingCart = new ShoppingCart(discountService);

        // Add items to the shopping cart
        shoppingCart.addItem("Laptop", 1000.0);
        shoppingCart.addItem("Phone", 500.0);

        // Calculate and print the total price
        double totalPrice = shoppingCart.calculateTotal();
        System.out.println("Total Price after Discount: $" + totalPrice);
    }
}