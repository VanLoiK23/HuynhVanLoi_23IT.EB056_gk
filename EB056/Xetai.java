package GiuaKi_jv_23IT.EB056;

public class Xetai extends phuongtiengiaothong{
	float loadWeight;
	public Xetai(int id, String brand, int publishYear, float price, String color,float loadWeight) {
		super(id, brand, publishYear, price, color,"xetai");
		this.loadWeight=loadWeight;
	}
	public void showInfo() {
		   super.showInfo();
		   System.out.println("LoadWeight: "+loadWeight);
	}
}
