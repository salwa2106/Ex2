package Entity;

import java.net.URLDecoder;

public class Consts {
	
	 private Consts() {
	 throw new AssertionError();
}




protected static final String DB_FILEPATH = getDBPath();

public static final String CONN_STR = "jdbc:ucanaccess://"  + DB_FILEPATH + ";COLUMNORDER=DISPLAY";


public static final String SQL_Get_Manufacturer = "SELECT * FROM TblManufacturer WHERE [TblManufacturer].ManufacturerID = ?";
public static final String SQL_show_Manufacturer = "SELECT * FROM TblManufacturer";

public static final String SQL_add_Manufacturer = "INSERT INTO TblManufacturer ( ManufacturerID, FullName, ContactPhoneNumber, Address, Email ) VALUES (?,?,?,?,?)";
public static final String SQL_delete_Manufacturer = "DELETE FROM TblManufacturer WHERE ManufacturerID = ?";
public static final String SQL_update_Manufacturer =  "UPDATE TblManufacturer SET TblManufacturer.FullName = ?, TblManufacturer.ContactPhoneNumber = ?, TblManufacturer.Address = ?, TblManufacturer.Email = ?"
		+ "WHERE (((TblManufacturer.[ManufacturerID])=?))";
		
	//	"{ call qryInsOrderDetails(?,?,?,?) }";



public static final String  SQL_get_Wine= "SELECT TblWine*"
		+ "FROM TblWine"
		+ "WHERE (((TblWine.CatalogNumber)=?) And ((TblWine.[ManufacturerId])=?));";
public static final String SQL_show_Wine = "SELECT * FROM TblWine";

public static final String SQL_add_Wine = "INSERT INTO TblWine (CatalogNumber, ManufacturerId, WineName, WineTypeSerialNumber, Description, ProductionYear, PricePerBottle, SweetnessLevel, ProductImg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

public static final String SQL_delete_Wine = "DELETE FROM TblWine WHERE CatalogNumber = ?";
		
public static final String SQL_update_Wine = "UPDATE TblWine SET TblWine.WineName = ?, TblWine.WineTypeSerialNumber = ?, TblWine.Description = ?, TblWine.ProductionYear = ?, TblWine.PricePerBottle = ?, TblWine.SweetnessLevel = ?, TblWine.ProductImg = ?, TblWine.ManufacturerId = ?"
		+ "WHERE (((TblWine.CatalogNumber)=?))";
		

private static String getDBPath() {
	try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar"))
		{
			decoded = decoded.substring(0, decoded.lastIndexOf('/'));
			System.out.println(decoded);
	
			return decoded + "/database/Database61.accdb";}
		else {
			decoded = decoded.substring(0, decoded.lastIndexOf("/bin"));
			//System.out.println(decoded);
	
			return decoded + "/src/Entity/Database61.accdb";}
			
		
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}
}