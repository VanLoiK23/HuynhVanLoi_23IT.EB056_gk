package GiuaKi_jv_23IT.EB056;

public class Xemay extends phuongtiengiaothong {
  

float capacity;
public Xemay(int id, String brand, int publishYear, float price, String color,float capacity) {
	super(id, brand, publishYear, price, color,"xemay");
	this.capacity=capacity;
}
public void showInfo() {
	   super.showInfo();
	   System.out.println("Capacity: "+capacity);
}
}
