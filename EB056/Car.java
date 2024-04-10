package GiuaKi_jv_23IT.EB056;

public class Car  extends phuongtiengiaothong{
	   int slots;
	   String engineType;
		
	   public Car(int id, String brand, int publishYear, float price, String color,int slots,String engineType) {
			super(id, brand, publishYear, price, color,"car");
			this.slots=slots;
			this.engineType=engineType;
		}
       public void showInfo() {
    	   super.showInfo();
    	   System.out.println("Slots: "+slots);
    	   System.out.println("Enginetype: "+engineType);
       }
}
