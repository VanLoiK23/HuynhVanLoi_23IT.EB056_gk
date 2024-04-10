package GiuaKi_jv_23IT.EB056;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import ConnectMysql.Connectmysql;

public class Main {
	 private static final String FILE_PATH = "phuongtiengiaothong.json";
	 private static final Scanner scanner = new Scanner(System.in);
	 
	 public static void main(String[] args) {
		 List<phuongtiengiaothong> pt = new ArrayList();

	        while (true) {
	            System.out.println("\n1. Create phuong tien moi");
	            System.out.println("2. Display info phuong tien");
	            System.out.println("3. Save phuong tien to file");
	            System.out.println("4. Read info from file");
             System.out.println("5. Save phuong tien to MySQL");            
	            System.out.println("6. Read info from MySQL");
	            System.out.println("7. Exit");

	            System.out.print("Enter your choice: ");
	            String choice = scanner.nextLine();

	            switch (choice) {
	                case "1":
	                    phuongtiengiaothong newpt = createPT();
	                    pt.add(newpt);
	                    break;
	                case "2":
	                    for (phuongtiengiaothong p : pt) {
	                        p.showInfo();
	                    }
	                    break;
	                case "3":
	                	savePTToFile(pt);
	                    System.out.println("phuong tien saved to file success!");
	                    break;
	                case "4":
	                	loadXeFromFile();
	                	break;
	                case "5":
	                	SaveMysql(pt);
	                	break;
	                case "6":
	                	LoadIn4Mysql();
	                	break;
	                case "7":
	                	System.exit(0);
	                default:
	                    System.out.println("Invalid choice!");
	            }
	        }
	}
	 private static phuongtiengiaothong createPT() {
	        System.out.print("Enter ID: ");
	        int ID = scanner.nextInt();
	        System.out.print("Enter hang san xuat: ");
	        scanner.nextLine();
	        String brand = scanner.nextLine();
	        scanner.nextLine();
	        System.out.print("Enter nam san xuat: ");
	        int publishYear = scanner.nextInt();
	        System.out.print("Enter price: ");
	        float price = scanner.nextFloat();
	        System.out.print("Enter color of xe: ");
	        String color = scanner.nextLine();
	        scanner.nextLine();
	        System.out.print("Enter loai phuong tien (Car/Xemay/Xetai): ");
	        String loaiphuongtien = scanner.nextLine().toLowerCase();


	        switch (loaiphuongtien) {
	            case "car":
	                System.out.print("Enter slots: ");
	                int slots = Integer.parseInt(scanner.nextLine());
	                System.out.print("Enter engineType: ");
	                String engineType = scanner.nextLine();
	                return new Car(ID, brand, publishYear, price, color, slots, engineType);
	            case "xemay":
	                System.out.print("Enter capacity: ");
	                float capacity = scanner.nextFloat();
	                return new Xemay(ID, brand, publishYear, price, color, capacity);
	            case "xetai":
	                System.out.print("Enter loadWeight: ");
	                float loadWeight = scanner.nextFloat();
	                return new Xetai(ID, brand, publishYear, price, color,loadWeight);
	            default:
	                System.out.println("Invalid !");
	                return null;
	        }
	    }
	 private static void loadXeFromFile() {
		    JSONParser parser = new JSONParser();

		    try (FileReader reader = new FileReader(FILE_PATH)) {
		        Object obj = parser.parse(reader);
		        JSONArray ptArray = (JSONArray) obj;

		        for (Object ptObj : ptArray) {
		            JSONObject ptJson = (JSONObject) ptObj;
		            String ptType = (String) ptJson.get("ptType");
		            try {
		                switch (ptType) {
		                    case "car":
		                        Car oto = new Car(
		                                ((Long) ptJson.get("id")).intValue(),
		                                (String) ptJson.get("brand"),
		                                ((Long) ptJson.get("publishYear")).intValue(),
		                                ((Double) ptJson.get("price")).floatValue(),
		                                (String) ptJson.get("color"),
		                                ((Long) ptJson.get("slots")).intValue(),
		                                (String) ptJson.get("engineType")
		                        );
		                        displayPTInfo(oto);
		                        break;
		                    case "xemay":
		                        Xemay xe_m = new Xemay(
		                                ((Long) ptJson.get("id")).intValue(),
		                                (String) ptJson.get("brand"),
		                                ((Long) ptJson.get("publishYear")).intValue(),
		                                ((Double) ptJson.get("price")).floatValue(),
		                                (String) ptJson.get("color"),
		                                ((Double) ptJson.get("capacity")).floatValue()
		                        );
		                        displayPTInfo(xe_m);
		                        break;
		                    case "xetai":
		                        Xetai xet = new Xetai(
		                                ((Long) ptJson.get("id")).intValue(),
		                                (String) ptJson.get("brand"),
		                                ((Long) ptJson.get("publishYear")).intValue(),
		                                ((Double) ptJson.get("price")).floatValue(),
		                                (String) ptJson.get("color"),
		                                ((Double) ptJson.get("loadWeight")).floatValue()
		                        );
		                        displayPTInfo(xet);
		                        break;
		                    default:
		                        System.out.println("Invalid !");
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		                System.out.println("Error processing " + ptType + " vehicle!");
		            }
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.println("Error reading from file!");
		    }
		}


	    private static void displayPTInfo(phuongtiengiaothong pt) {
	        System.out.println("ID: " + pt.id);
	        System.out.println("Brand: " + pt.brand);
	        System.out.println("publishYear: " + pt.publishYear);
	        System.out.println("price: " + pt.price);
	        System.out.println("color: " + pt.color);
	        System.out.println("PT Type: " + pt.ptType);

	        if (pt instanceof Car) {
	            Car c = (Car) pt;
	            System.out.println("Slots: " + c.slots);
	            System.out.println("engineType: " + c.engineType);
	        } else if (pt instanceof Xemay) {
	            Xemay x = (Xemay) pt;
	            System.out.println("capacity: " + x.capacity);
	        } else if (pt instanceof Xetai) {
	            Xetai t = (Xetai) pt;
	            System.out.println("loadWeight: "+t.loadWeight);
	        }
	        System.out.println("-------------------------");
	    }
	    private static void savePTToFile(List<phuongtiengiaothong> pt) {
	        JSONArray ptArray = new JSONArray();

	        for (phuongtiengiaothong t : pt) {
	            JSONObject ptJson = new JSONObject();
	            ptJson.put("id", t.id);
	            ptJson.put("brand", t.brand);
	            ptJson.put("publishYear", t.publishYear);
	            ptJson.put("price", t.price);
	            ptJson.put("color", t.color);
	            ptJson.put("ptType", t.ptType);


	            if (t instanceof Car) {
	                Car c = (Car) t;
	                ptJson.put("slots", c.slots);
	                ptJson.put("engineType", c.engineType);
	            } else if (t instanceof Xemay) {
	                Xemay x = (Xemay) t;
	                ptJson.put("capacity", x.capacity);
	                
	            } else if (t instanceof Xetai) {
	                Xetai ta = (Xetai) t;
	                ptJson.put("loadWeight", ta.loadWeight);
	                
	            }

	            ptArray.add(ptJson);
	        } 

	        try (FileWriter file = new FileWriter(FILE_PATH)) {
	            file.write(ptArray.toJSONString());
	            file.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    // table ptgiaothong
//	    id INT PRIMARY KEY,
//	    brand VARCHAR(255),
//	    publishYear INT,
//	    price FLOAT,
//	    color VARCHAR(50),
//	    ptType VARCHAR(20),
//	    slots INT,
//	    engineType VARCHAR(50),
//	    capacity FLOAT,
//	    loadWeight FLOAT
	    private static void SaveMysql(List<phuongtiengiaothong> pt) {
	        Connection connect = Connectmysql.getConnection();
	        for (phuongtiengiaothong p : pt) {
	            try {
	                String sql = "INSERT INTO ptgiaothong (id, brand, publishYear, price, color, ptType";
	                String values = " VALUES (?, ?, ?, ?, ?, ?";
	                if (p instanceof Car) {
	                    sql += ", slots, engineType)";
	                    values += ", ?, ?)";
	                } else if (p instanceof Xemay) {
	                    sql += ", capacity)";
	                    values += ", ?)";
	                } else if (p instanceof Xetai) {
	                    sql += ", loadWeight)";
	                    values += ", ?)";
	                }
	                sql += values;

	                PreparedStatement statement = connect.prepareStatement(sql);
	                statement.setInt(1, p.id);
	                statement.setString(2, p.brand);
	                statement.setInt(3, p.publishYear);
	                statement.setFloat(4, p.price);
	                statement.setString(5, p.color);
	                statement.setString(6, p.ptType);

	                if (p instanceof Car) {
	                    Car c = (Car) p;
	                    statement.setInt(7, c.slots);
	                    statement.setString(8, c.engineType);
	                } else if (p instanceof Xemay) {
	                    Xemay x = (Xemay) p;
	                    statement.setFloat(7, x.capacity);
	                } else if (p instanceof Xetai) {
	                    Xetai t = (Xetai) p;
	                    statement.setFloat(7, t.loadWeight);
	                }

	                int rowsInserted = statement.executeUpdate();
	                if (rowsInserted > 0) {
	                    System.out.println("A new record was inserted successfully!");
	                }

	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    private static final String SELECT_QUERY = "SELECT * FROM ptgiaothong";
  private static void LoadIn4Mysql() {
	  Connection connection = null;
      PreparedStatement statement = null;
      ResultSet resultSet = null;
	  try {
		  connection = Connectmysql.getConnection();
          statement = connection.prepareStatement(SELECT_QUERY);
          resultSet = statement.executeQuery();

          while (resultSet.next()) {
              int id = resultSet.getInt("id");
              String brand = resultSet.getString("brand");
              int publishYear = resultSet.getInt("publishYear");
              float price = resultSet.getFloat("price");
              String color = resultSet.getString("color");
              String ptType = resultSet.getString("ptType");

              System.out.println("ID: " + id);
              System.out.println("Brand: " + brand);
              System.out.println("Publish Year: " + publishYear);
              System.out.println("Price: " + price);
              System.out.println("Color: " + color);
              System.out.println("PT Type: " + ptType);

              if ("car".equals(ptType)) {
                  int slots = resultSet.getInt("slots");
                  String engineType = resultSet.getString("engineType");
                  System.out.println("Slots: " + slots);
                  System.out.println("Engine Type: " + engineType);
              } else if ("xemay".equals(ptType)) {
                  float capacity = resultSet.getFloat("capacity");
                  System.out.println("Capacity: " + capacity);
              } else if ("xetai".equals(ptType)) {
                  float loadWeight = resultSet.getFloat("loadWeight");
                  System.out.println("Load Weight: " + loadWeight);
              }

              System.out.println("-------------------------");
          }
      } catch (SQLException e) {
          e.printStackTrace();
      } finally {
          try {
              if (resultSet != null) {
                  resultSet.close();
              }
              if (statement != null) {
                  statement.close();
              }
              if (connection != null) {
                  connection.close();
              }
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }
  }
}
