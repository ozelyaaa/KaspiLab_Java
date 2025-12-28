void main() {
    Cart cart = new Cart();

    //Инициализируем категории
    List<Category> categories = new ArrayList<>();
    categories.add(new Category("Dairy"));
    categories.add(new Category("Meat"));
    categories.add(new Category("Drinks"));
    categories.add(new Category("Alcohol"));

    //Инициализируем корзину с заказами
    int ordersAmount = 6;
    float minPrice = 1f;
    float maxPrice = 100f;
    for (int i = 1; i <= ordersAmount; i++) {
        Category randomCategory = getRandomCategory(categories);
        float randomPrice = getRandomPrice(minPrice, maxPrice);
        Order order = new Order("Order" + i, randomCategory, randomPrice);
        cart.addOrder(order);
    }

    //Тестируем наш код
    IO.println("Содержимое нашей корзины:");
    for (Order order : cart.getOrders())
        IO.println(order);
    IO.println();

    float priceToSearchAgainst = 25f;
    List<Order> ordersAbovePrice = cart.getOrdersAbovePrice(priceToSearchAgainst);
    IO.println("Заказы с ценой выше " + priceToSearchAgainst + ": " + ordersAbovePrice);
    IO.println();

    Map<Category, Integer> ordersAmountPerCategory = cart.getOrdersAmountPerCategory();
    IO.println("Список категорий и количество заказов в каждой:");
    for (Category category : categories)
        IO.println(category + " - " + ordersAmountPerCategory.getOrDefault(category, 0));
}

//Helper methods
public float getRandomPrice(float minPrice, float maxPrice) {
    Random random = new Random();
    float randomFloat = random.nextFloat() * (maxPrice - minPrice) + minPrice;

    BigDecimal bd = new BigDecimal(Float.toString(randomFloat));
    bd = bd.setScale(2, RoundingMode.HALF_UP);

    return bd.floatValue();
}

public Category getRandomCategory(List<Category> categories) {
    Random random = new Random();
    int randomIndex = random.nextInt(categories.size());
    return categories.get(randomIndex);
}
