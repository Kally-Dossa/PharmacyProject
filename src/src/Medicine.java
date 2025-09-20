public class Medicine {

    private String name;
    private String code;
    private double price;
    private int stock;
    private CategoryMedicine category;

    public Medicine(String name, String code, double price, int stock, CategoryMedicine category){
        this.name = name;
        this.code = code;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }


    public double getPrice() {
        return price;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CategoryMedicine getCategory(){
        return category;
    }
}
