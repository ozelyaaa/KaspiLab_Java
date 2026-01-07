package kz.lab.module1.advanced;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record Order(long id, double price, LocalDateTime orderDate, Set<String> items) {
    public Order {
        if (price <= 0)
            throw new IllegalArgumentException("Price should be a positive number!");
        if (!orderDate.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Order date should be in the past!");
        items = Set.copyOf(items);
    }
}
