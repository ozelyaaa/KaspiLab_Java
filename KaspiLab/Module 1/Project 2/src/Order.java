public class Order {
    private String name;
    private Category category;
    private float price;

    public Order(String name, Category category, float price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s; %s; %.2f", name, category, price);
    }

    //Все неиспользованные методы на будущее, если этот проект еще будет расширяться :)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
