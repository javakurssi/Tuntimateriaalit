package tunti3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Get the discount rate from an outside source. This is to be mocked in tests.
public class ExternalDiscountService implements DiscountService {

    @Override
    public double calculateDiscount(double total) {
        try {
            // Replace the URL with the actual endpoint
            String apiUrl = "https://example.com/discount-service?total=" + total;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response and extract the discount value
            double discount = Double.parseDouble(response.body());

            return discount;
        } catch (Exception e) {
            // Handle exceptions, e.g., network issues, parsing errors
            e.printStackTrace();
            return 0;
        }
    }
}

