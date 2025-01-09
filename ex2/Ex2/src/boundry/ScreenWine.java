package boundry;

import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Control.WineLogic;
import Control.XmlControl;
import Entity.Consts;
import Entity.Wine;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.awt.event.ActionEvent;

public class ScreenWine extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField catalognum, name, winetypeid, productionyear, priceperbottle, img;
    private JComboBox<String> sweetness;
    private JTextArea desc;
    private DefaultTableModel tableModel;
    private JTable wineTable;
    private JScrollPane scrollPane;
    private JTextField manufacturer;

    public ScreenWine() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1031, 638);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Wine Screen");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(367, 20, 239, 40);
        contentPane.add(lblNewLabel);

        JLabel lblCatalogNum = new JLabel("Catalog Number:");
        lblCatalogNum.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCatalogNum.setBounds(20, 80, 150, 30);
        contentPane.add(lblCatalogNum);

        catalognum = new JTextField();
        catalognum.setBounds(180, 80, 150, 30);
        contentPane.add(catalognum);

        JLabel lblManufacturer = new JLabel("Manufacturer:");
        lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblManufacturer.setBounds(20, 130, 150, 30);
        contentPane.add(lblManufacturer);
       

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(20, 180, 150, 30);
        contentPane.add(lblName);

        name = new JTextField();
        name.setBounds(180, 180, 150, 30);
        contentPane.add(name);

        JLabel lblWineType = new JLabel("WineType Serial:");
        lblWineType.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblWineType.setBounds(400, 80, 180, 30);
        contentPane.add(lblWineType);

        winetypeid = new JTextField();
        winetypeid.setBounds(600, 80, 150, 30);
        contentPane.add(winetypeid);

        JLabel lblProductionYear = new JLabel("Production Year:");
        lblProductionYear.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblProductionYear.setBounds(400, 130, 180, 30);
        contentPane.add(lblProductionYear);

        productionyear = new JTextField();
        productionyear.setBounds(600, 130, 150, 30);
        contentPane.add(productionyear);

        JLabel lblPrice = new JLabel("Price Per Bottle:");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPrice.setBounds(400, 180, 180, 30);
        contentPane.add(lblPrice);

        priceperbottle = new JTextField();
        priceperbottle.setBounds(600, 180, 150, 30);
        contentPane.add(priceperbottle);

        JLabel lblSweetness = new JLabel("Sweetness Level:");
        lblSweetness.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblSweetness.setBounds(400, 230, 180, 30);
        contentPane.add(lblSweetness);

        sweetness = new JComboBox<>();
        sweetness.setBounds(600, 230, 150, 30);
        sweetness.addItem("Dry");
        sweetness.addItem("Semi-Dry");
        sweetness.addItem("Semi-Sweet");
        sweetness.addItem("Sweet");
        contentPane.add(sweetness);

        JLabel lblImg = new JLabel("Product Image:");
        lblImg.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblImg.setBounds(20, 230, 150, 30);
        contentPane.add(lblImg);
System.out.println("sdsd");
        img = new JTextField();
        img.setBounds(180, 230, 150, 30);
        contentPane.add(img);

        JLabel lblDesc = new JLabel("Description:");
        lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDesc.setBounds(20, 280, 150, 30);
        contentPane.add(lblDesc);

        desc = new JTextArea();
        desc.setBounds(180, 280, 570, 60);
        contentPane.add(desc);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(50, 360, 100, 30);
        btnAdd.addActionListener(e -> addWine());
        contentPane.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addWine();
				
			}
		});

        JButton btnView = new JButton("View");
        btnView.setBounds(180, 360, 100, 30);
        btnView.addActionListener(e -> refreshWineTable());
        contentPane.add(btnView);

        createTable();
        refreshWineTable();
    }

    private void createTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Catalog Number");
        tableModel.addColumn("Name");
        tableModel.addColumn("Description");
        tableModel.addColumn("Price");
        tableModel.addColumn("Production Year");
        tableModel.addColumn("Manufacturer ID");
        tableModel.addColumn("Sweetness Level");
        tableModel.addColumn("Image");

        wineTable = new JTable(tableModel);
        scrollPane = new JScrollPane(wineTable);
        scrollPane.setBounds(50, 410, 900, 180);
        contentPane.add(scrollPane);
        
        JButton updatebtn = new JButton("Update");
        updatebtn.setBounds(317, 364, 89, 23);
        contentPane.add(updatebtn);
        updatebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateWine();		
				
			}
		});
        
        JButton deletebtn = new JButton("Delete");
        deletebtn.setBounds(449, 364, 89, 23);
        contentPane.add(deletebtn);
        deletebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  String catalogNumber = catalognum.getText().trim();
				  System.out.println("Deleting wine with catalog number: " + catalogNumber);
			        // Call removeWine with the CatalogNumber
			       removeWine();
			}
		});
        JButton btnImportXML = new JButton("Import Wines from XML");
        btnImportXML.setBounds(732, 357, 200, 30);
        contentPane.add(btnImportXML);

        // ActionListener for Import Wines button
        btnImportXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use the XML_FILEPATH from XmlControl to get the correct file path
                String path = XmlControl.XML_FILEPATH;
                
                if (path != null) {
                    // Call the method to import wines from the XML file
                    XmlControl.getInstance().importWinesFromXML(path);
                    refreshWineTable(); // Refresh the table after import (if necessary)
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Invalid XML file path.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
      
        manufacturer = new JTextField();
        manufacturer.setBounds(169, 139, 161, 20);
        contentPane.add(manufacturer);
        manufacturer.setColumns(10);
        
        JButton btnNewButton = new JButton("Main");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Main m = new Main();
        		m.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(0, 11, 89, 23);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Add Manufacturer");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ScreenManufacturer s =new ScreenManufacturer();
        		s.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton_1.setBounds(20, 157, 130, 23);
        contentPane.add(btnNewButton_1);
      
    }

    private void refreshWineTable() {
        tableModel.setRowCount(0);
        List<Wine> wines = WineLogic.getInstance().getAllWines();
        System.out.println("wines:" + wines);
        for (Wine wine : wines) {
            tableModel.addRow(new Object[]{
                wine.getCatalogNumber(),
                wine.getName(),
                wine.getDescription(),
                wine.getPricePerBottle(),
                wine.getProductionYear(),
                wine.getManufacturerId(),
                wine.getSweetnessLevel(),
                wine.getProductImg()
            });
        }
    }

    private void addWine() {
        String catalogNumber = catalognum.getText().trim();
        String Name = name.getText().trim();
        String description = desc.getText().trim();
        String manufacturerid = manufacturer.getText().trim();
        String WineType = winetypeid.getText().trim();
        String priceText = priceperbottle.getText().trim();
        String productionYearText = productionyear.getText().trim();
        String sweetnessLevel = (String) sweetness.getSelectedItem();
        String image = img.getText().trim();

        if (catalogNumber.isEmpty() || Name.isEmpty() || description.isEmpty() ||
            priceText.isEmpty() || productionYearText.isEmpty()  || sweetnessLevel == null || image.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (WineLogic.getInstance().addWine(catalogNumber, manufacturerid, Name, WineType, description, productionYearText, priceText, sweetnessLevel, image)) {
            JOptionPane.showMessageDialog(this, "Wine added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshWineTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add wine. Catalog number may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        catalognum.setText("");
        name.setText("");
        winetypeid.setText("");
        productionyear.setText("");
        priceperbottle.setText("");
        img.setText("");
        manufacturer.setText("");;
        sweetness.setSelectedIndex(-1);
        desc.setText("");
    }
    private void removeWine() {
        int selectedRow = wineTable.getSelectedRow();
        System.out.println("Selected row: " + selectedRow); // Debugging line

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a wine to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String catalogNumber = (String) tableModel.getValueAt(selectedRow, 0);
        if (WineLogic.getInstance().removeWine(catalogNumber)) {
            JOptionPane.showMessageDialog(this, "Wine removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshWineTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove wine.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add Update Wine
    private void updateWine() {
        String catalogNumber = catalognum.getText().trim();
        String Name = name.getText().trim();
        String description = desc.getText().trim();
        String WineType = winetypeid.getText().trim();
        String priceText = priceperbottle.getText().trim();
        String productionYearText = productionyear.getText().trim();
        String manufacturerIdText = manufacturer.getText().trim();
        String sweetnessLevel = (String) sweetness.getSelectedItem();
        String image = img.getText().trim();

        if (catalogNumber.isEmpty() || Name.isEmpty() || description.isEmpty() ||
            priceText.isEmpty() || productionYearText.isEmpty() || sweetnessLevel == null || image.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (WineLogic.getInstance().updateWine(catalogNumber, Name, WineType, description, productionYearText, priceText, sweetnessLevel, image,manufacturerIdText)) {
            JOptionPane.showMessageDialog(this, "Wine updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshWineTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update wine. Catalog number not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   
}
