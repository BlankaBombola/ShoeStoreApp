package model;

public class Shoe {
    private int id;
    private String name, brand, description, imagePath;
    private double price;
    private int size;

    public Shoe(int id, String name, String brand, String description, double price, int size, String imagePath) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.size = size;
        this.imagePath = imagePath;
    }

    // Gettery i settery
    public int getId() { return id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getSize() { return size; }
    public String getImagePath() { return imagePath; }

    public void setName(String name) { this.name = name; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setSize(int size) { this.size = size; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}