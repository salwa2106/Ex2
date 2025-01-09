package Control;

import java.io.File;
import java.net.URLDecoder;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.swing.JOptionPane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Entity.Consts;
import Entity.Wine;

public class XmlControl {
    private static XmlControl _instance;
    public static final String XML_FILEPATH = getxmlPath();
    private XmlControl() {
    }

    public static XmlControl getInstance() {
        if (_instance == null) {
            _instance = new XmlControl();
        }
        return _instance;
    }

    public void importManufacturersFromXML(String path) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();

            NodeList nl = doc.getElementsByTagName("Manufacturer");
            int errors = 0;

            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl.item(i);
                    try {
                        String manufacturerId = el.getElementsByTagName("ID").item(0).getTextContent();
                        String fullName = el.getElementsByTagName("Name").item(0).getTextContent();
                        String phone = el.getElementsByTagName("Phone").item(0).getTextContent();
                        String address = el.getElementsByTagName("Address").item(0).getTextContent();
                        String email = el.getElementsByTagName("Email").item(0).getTextContent();
                         
                        if(Manufacturerlogic.getInstance().getManufacturerById(manufacturerId)!=null) {
                        	throw new IllegalArgumentException("Manufacturer ID already exists");
                        }
                        
                        
                        if (!Manufacturerlogic.getInstance().addManufacturer(manufacturerId, fullName, phone, address, email)) {
                            errors++;
                            System.out.println("Failed to add Manufacturer: " + manufacturerId);
                        } else {
                            System.out.println("Manufacturer added successfully: " + manufacturerId);
                        }
                    } catch (Exception ex) {
                        errors++;
                        System.out.println("Error processing manufacturer: " + ex.getMessage());
                    }
                }
            }


            System.out.println((errors == 0) ? "Manufacturers imported successfully!" :
                    String.format("Manufacturers imported with %d errors.", errors));

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate Data Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void importWinesFromXML(String path) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();

            NodeList nl = doc.getElementsByTagName("Wine");
            int errors = 0;

            for (int i = 0; i < nl.getLength(); i++) {
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl.item(i);
                    try {
                        String catalogNumber = el.getElementsByTagName("CatalogNumber").item(0).getTextContent();
                        String manufacturerId = el.getElementsByTagName("ManufacturerID").item(0).getTextContent();
                        String wineTypeSerialNum = el.getElementsByTagName("WineTypeSerialNum").item(0).getTextContent();
                        String name = el.getElementsByTagName("Name").item(0).getTextContent();
                        String description = el.getElementsByTagName("Description").item(0).getTextContent();
                        String productionYear = el.getElementsByTagName("ProductionYear").item(0).getTextContent();
                        String pricePerBottle = el.getElementsByTagName("PricePerBottle").item(0).getTextContent();
                        String sweetnessLevel = el.getElementsByTagName("SweetnessLevel").item(0).getTextContent();
                        String productImg = el.getElementsByTagName("ProductImg").item(0).getTextContent();

                        if (WineLogic.getInstance().getWineByCatalogNumber(catalogNumber) != null) {
                            throw new IllegalArgumentException("Wine with catalog number already exists");
                        }

                        Wine wine = new Wine(catalogNumber, manufacturerId, wineTypeSerialNum, name, description, productionYear, pricePerBottle, sweetnessLevel, productImg);
                        if (!WineLogic.getInstance().addWine(
                        	    wine.getCatalogNumber(),
                        	    wine.getManufacturerId(),
                        	    wine.getName(),
                        	    wine.getWineTypeSerialNum(),
                        	    wine.getDescription(),
                        	    wine.getProductionYear(),
                        	    wine.getPricePerBottle(),
                        	    wine.getSweetnessLevel(),
                        	    wine.getProductImg()
                        	)) {
                            errors++;
                            System.out.println("Failed to add Wine: " + catalogNumber);
                        } else {
                            System.out.println("Wine added successfully: " + catalogNumber);
                        }
                    } catch (Exception ex) {
                        errors++;
                        System.out.println("Error processing wine: " + ex.getMessage());
                    }
                }
            }

            System.out.println((errors == 0) ? "Wines imported successfully!" :
                    String.format("Wines imported with %d errors.", errors));

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "Duplicate Data Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    private static String getxmlPath() {
    	try {
    		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    		String decoded = URLDecoder.decode(path, "UTF-8");
    		if (decoded.contains(".jar"))
    		{
    			decoded = decoded.substring(0, decoded.lastIndexOf('/'));
    			System.out.println(decoded);
    	
    			return decoded + "/database/xml.xml";}
    		else {
    			decoded = decoded.substring(0, decoded.lastIndexOf("/bin"));
    			//System.out.println(decoded);
    	
    			return decoded + "/src/Entity/xml.xml";}
    			
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
