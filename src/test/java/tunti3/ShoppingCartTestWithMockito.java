package tunti3;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartTestWithMockito {

    private ShoppingCart cart;
    private DiscountService discountService;

    @BeforeEach
    void setUp() {
        discountService = mock(DiscountService.class);
        cart = new ShoppingCart(discountService);
    }

    @AfterEach
    void tearDown() {
        cart = null;
        discountService = null;
    }

    @Test
    void testCalculateTotalWithMockedDiscountService() {
        // Arrange
        cart.addItem("Laptop", 1000.0);
        cart.addItem("Phone", 500.0);

        // Mock the behavior of the discount service
        when(discountService.calculateDiscount(anyDouble())).thenReturn(150.0);

        // Act
        double total = cart.calculateTotal();

        // Assert
        assertEquals(1350, total, 0.001);

        // Verify that the discount service was called with the correct parameters
        verify(discountService, times(1)).calculateDiscount(1500.0);
    }
}

