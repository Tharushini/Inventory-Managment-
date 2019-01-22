package views;

public class Product {
    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] picture;
    
    public Product(int id, String name, float price, String addDate, byte[] picture){
        this.id = id;
        this.name = name;
        this.price = price;
        this.addDate = addDate;
        this.picture = picture;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public float getPrice(){
        return price;
    }
    
    public String getAddDate(){
        return addDate;
    }
    
    public byte[] getPicture(){
        return picture;
    }
}
