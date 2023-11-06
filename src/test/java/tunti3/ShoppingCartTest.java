package tunti3;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    private ShoppingCart cart;
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
    	//Mock implementation of DiscountService
        discountService = new TestDiscountService();
        cart = new ShoppingCart(discountService);
    }

    @AfterEach
    void tearDown() {
        cart = null;
        discountService = null;
    }

    @Test
    void testCalculateTotalWithManualTestImplementation() {
    	
        cart.addItem("Laptop", 1000.0);
        cart.addItem("Phone", 500.0);

        double total = cart.calculateTotal();

        // Assert cart total x 10% discount is correct 
        // The third param 0.001 is the precision required in equality check.
        assertEquals(1350, total, 0.001);
    }
}

