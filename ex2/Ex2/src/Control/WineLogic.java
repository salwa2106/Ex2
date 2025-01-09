package Control;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Entity.Consts;
import Entity.Wine;

public class WineLogic {
	 private static WineLogic _instance;

	    private WineLogic() {
	    }

	    public static WineLogic getInstance() {
	        if (_instance == null) {
	            _instance = new WineLogic();
	        }
	        return _instance;
	    }

	    public List<Entity.Wine> getAllWines() {
	        List<Entity.Wine> wines = new ArrayList<>();
	        try {
	        	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_show_Wine);
	                 ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                	wines.add(new Wine(
	                		    rs.getString("CatalogNumber"),
	                		    rs.getString("ManufacturerId"),
	                		    rs.getString("WineName"),
	                		    rs.getString("WineTypeSerialNumber"),
	                		    rs.getString("Description"),
	                		    rs.getString("ProductionYear"),
	                		    rs.getString("PricePerBottle"),
	                		    rs.getString("SweetnessLevel"),
	                		    rs.getString("ProductImg")
	                		));

	            }}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return wines;
	    }

	    public boolean addWine(String catalogNumber, String manufacturerId, String name,String wineTypeSerialNum, String description, String productionYear,
				String pricePerBottle, String sweetnessLevel, String productImg) {
try {
 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
 try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
      PreparedStatement stmt = conn.prepareStatement(Consts.SQL_add_Wine)) {
	 stmt.setString(1, catalogNumber);
	  stmt.setString(2, manufacturerId);
	  stmt.setString(3, name);
	  stmt.setString(4, wineTypeSerialNum);
	  stmt.setString(5, description);
	  stmt.setString(6, productionYear);  
	  stmt.setString(7, pricePerBottle);
	  stmt.setString(8, sweetnessLevel);
	  stmt.setString(9, productImg);

     stmt.executeUpdate();
     return true;
 }
} catch (Exception e) {
 e.printStackTrace();
}
return false;
}



	    public boolean removeWine(String CatalogNumber) {
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_delete_Wine)) {

	                stmt.setString(1, CatalogNumber);
	                stmt.executeUpdate();
	                return true;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }


	    public Wine getWineByCatalogNumber(String catalogNumber) {
	        try {
	        	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                 PreparedStatement stmt = conn.prepareStatement(Consts.SQL_get_Wine)) {

	                stmt.setString(1, catalogNumber);
	                try (ResultSet rs = stmt.executeQuery()) {
	                    if (rs.next()) {
	                        return new Wine(
	                        		 rs.getString("catalogNumber"),
	 	                		    rs.getString("manufacturerID"),
	 	                		    rs.getString("name"),
	 	                		    rs.getString("winetypeserialNum"),
	 	                		    rs.getString("description"),
	 	                		    rs.getString("productionYear"),
	 	                		    rs.getString("pricePerbottle"),
	 	                		    rs.getString("sweetnesslevel"),
	 	                		    rs.getString("productImg"));
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	        
	        
	    }
	    public boolean updateWine(String catalogNumber, String name, String wineTypeSerialNum, String description,
                String productionYear, String pricePerBottle, String sweetnessLevel, String productImg,
                String manufacturerId) {
try {
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
   PreparedStatement stmt = conn.prepareStatement(Consts.SQL_update_Wine)) {

  stmt.setString(1, name);
  stmt.setString(2, wineTypeSerialNum);
  stmt.setString(3, description);
  stmt.setString(4, productionYear);
  stmt.setString(5, pricePerBottle);
  stmt.setString(6, sweetnessLevel);
  stmt.setString(7, productImg);
  stmt.setString(8, manufacturerId);
  stmt.setString(9, catalogNumber);

  int affectedRows = stmt.executeUpdate();
  return affectedRows > 0;
}
} catch (Exception e) {
e.printStackTrace();
}
return false;
}


	}