package GiuaKi_jv_23IT.EB056;

public class phuongtiengiaothong implements ICar {
	protected int id;
    protected String brand;
    protected int publishYear;
    protected float price;
    protected String color;
	protected String ptType;

	public phuongtiengiaothong(int id, String brand, int publishYear, float price, String color,String ptType) {
		super();
		this.id = id;
		this.brand = brand;
		this.publishYear = publishYear;
		this.price = price;
		this.color = color;
		this.ptType=ptType;
	}
	@Override
	public void showInfo() {
		System.out.println("ID: " + id);
	    System.out.println("Brand : " + brand);
	    System.out.println("PublishYear: " + publishYear);
	    System.out.println("Price : " + price);
	    System.out.println("Color: " + color);
		
	}
    
}
