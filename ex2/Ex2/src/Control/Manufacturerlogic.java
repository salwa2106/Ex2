package Control;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Manufacturerlogic {
    private static Manufacturerlogic _instance;

    private Manufacturerlogic() {
    }

    public static Manufacturerlogic getInstance() {
        if (_instance == null) {
            _instance = new Manufacturerlogic();
        }
        return _instance;
    }

    public List<Entity.Manufacturer> getAllManufacturers() {
        List<Entity.Manufacturer> manufacturers = new ArrayList<>();
        try {
        	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Entity.Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Entity.Consts.SQL_show_Manufacturer);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    manufacturers.add(new Entity.Manufacturer(
                            rs.getString("ManufacturerID"),
                            rs.getString("FullName"),
                            rs.getString("ContactPhoneNumber"),
                            rs.getString("Address"),
                            rs.getString("Email")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return manufacturers;
    }

    public boolean addManufacturer(String manufacturerId, String fullName, String phoneNumber, String address, String email) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Entity.Consts.CONN_STR);
                 CallableStatement stmt = conn.prepareCall(Entity.Consts.SQL_add_Manufacturer)) {

                stmt.setString(1, manufacturerId);       // Parameter 1
                stmt.setString(2, fullName);         // Parameter 2
                stmt.setString(3, phoneNumber);        // Parameter 3
                stmt.setString(4, address);          // Parameter 4
                stmt.setString(5, email);            // Parameter 5

                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    


    public boolean removeManufacturer(String ManufacturerID) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Entity.Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Entity.Consts.SQL_delete_Manufacturer)) {

                stmt.setString(1, ManufacturerID); // Set the ManufacturerID parameter
                stmt.executeUpdate();
                return true; // Deletion was successful
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Deletion failed
    }


    public Entity.Manufacturer getManufacturerById(String id) {
        try {
        	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Entity.Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Entity.Consts.SQL_Get_Manufacturer)) {

                stmt.setString(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Entity.Manufacturer(
                                rs.getString("manufacturerID"),
                                rs.getString("fullName"),
                                rs.getString("phoneNumber"),
                                rs.getString("address"),
                                rs.getString("email"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateManufacturer(String ManufacturerID, String FullName, String ContactPhoneNumber, String Address, String Email) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Entity.Consts.CONN_STR);
                 PreparedStatement stmt = conn.prepareStatement(Entity.Consts.SQL_update_Manufacturer)) {

            	stmt.setString(1, FullName);
                stmt.setString(2, ContactPhoneNumber);
                stmt.setString(3, Address);
                stmt.setString(4, Email);
                stmt.setString(5, ManufacturerID);

                stmt.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

