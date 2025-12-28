import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersAbovePrice(float price) {
        List<Order> ordersAbovePrice = new ArrayList<>();
        for (Order order : orders)
            if (order.getPrice() > price) ordersAbovePrice.add(order);

        return ordersAbovePrice;
    }


    public Map<Category, Integer> getOrdersAmountPerCategory() {
        Map<Category, Integer> ordersAmountPerCategory = new HashMap<>();
        for (Order order : orders) {
            ordersAmountPerCategory.put(order.getCategory(),
                    ordersAmountPerCategory.getOrDefault(order.getCategory(), 0) + 1);
        }
        return ordersAmountPerCategory;
    }
}
