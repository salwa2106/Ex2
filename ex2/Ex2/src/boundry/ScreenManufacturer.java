package boundry;

import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Control.Manufacturerlogic;
import Control.WineLogic;
import Control.XmlControl;
import Entity.Consts;
import Entity.Manufacturer;

import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ScreenManufacturer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField id, fullname, phonenum, address, email;
    private JTable manufacturerTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public ScreenManufacturer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1031, 638);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Add Manufacturer");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(367, 20, 239, 40);
        contentPane.add(lblNewLabel);

        JLabel lblManufacturerId = new JLabel("Manufacturer Id:");
        lblManufacturerId.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblManufacturerId.setBounds(20, 80, 180, 30);
        contentPane.add(lblManufacturerId);

        id = new JTextField();
        id.setBounds(200, 80, 150, 30);
        contentPane.add(id);

        JLabel lblFullName = new JLabel("Full Name:");
        lblFullName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFullName.setBounds(400, 80, 150, 30);
        contentPane.add(lblFullName);

        fullname = new JTextField();
        fullname.setBounds(600, 80, 150, 30);
        contentPane.add(fullname);

        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPhoneNumber.setBounds(20, 130, 180, 30);
        contentPane.add(lblPhoneNumber);

        phonenum = new JTextField();
        phonenum.setBounds(200, 130, 150, 30);
        contentPane.add(phonenum);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAddress.setBounds(400, 130, 150, 30);
        contentPane.add(lblAddress);

        address = new JTextField();
        address.setBounds(600, 130, 150, 30);
        contentPane.add(address);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmail.setBounds(20, 180, 180, 30);
        contentPane.add(lblEmail);

        email = new JTextField();
        email.setBounds(200, 180, 150, 30);
        contentPane.add(email);

        JButton addbtn = new JButton("Add");
        addbtn.setBounds(50, 250, 100, 30);
        addbtn.addActionListener(e -> addManufacturer());
        contentPane.add(addbtn);
        addbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
             addManufacturer();			
			}
		});

        JButton resetbtn = new JButton("View");
        resetbtn.setBounds(180, 250, 100, 30);
        resetbtn.addActionListener(e -> refreshManufacturerTable());
        contentPane.add(resetbtn);

        createTable();
        refreshManufacturerTable();
    
    JButton importButton = new JButton("Import");
    importButton.setBounds(577, 254, 89, 23);
    contentPane.add(importButton);
    importButton.addActionListener(e -> importManufacturers());
}

    private void createTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Manufacturer Id");
        tableModel.addColumn("Full Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Address");
        tableModel.addColumn("Email");

        manufacturerTable = new JTable(tableModel);
        scrollPane = new JScrollPane(manufacturerTable);
        scrollPane.setBounds(50, 300, 900, 200);
        contentPane.add(scrollPane);
        
        JButton updateButton = new JButton("Update");
        updateButton.setBounds(309, 254, 89, 23);
        contentPane.add(updateButton);
        updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateManufacturer();
				
			}
		});
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(443, 254, 89, 23);
        contentPane.add(deleteButton);
        
        JButton btnNewButton = new JButton("Main");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Main m = new Main();
        		m.setVisible(true);
        		setVisible(false);
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.setBounds(20, 11, 89, 23);
        contentPane.add(btnNewButton);
        deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeManufacturer();
				
			}
		});
    }

    private void refreshManufacturerTable() {
        tableModel.setRowCount(0); // Clear existing data
        List<Manufacturer> manufacturers = Manufacturerlogic.getInstance().getAllManufacturers();
        System.out.println("Manufacturers: " + manufacturers);
        for (Manufacturer manufacturer : manufacturers) {
            tableModel.addRow(new Object[]{
                manufacturer.getId(),
                manufacturer.getFullName(),
                manufacturer.getPhoneNumber(),
                manufacturer.getAddress(),
                manufacturer.getEmail()
            });
        }
    }
    private void removeManufacturer() {
        int selectedRow = manufacturerTable.getSelectedRow();
        System.out.println("Selected row: " + selectedRow); // Debugging line

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a Manufacturer to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String ManufacturerId = (String) tableModel.getValueAt(selectedRow, 0);
        if (Manufacturerlogic.getInstance().removeManufacturer(ManufacturerId)) {
            JOptionPane.showMessageDialog(this, "Manufacturer removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshManufacturerTable();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove Manufacturer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

  
    private void updateManufacturer() {
    	 String manufacturerId = id.getText().trim();
         String fullName = fullname.getText().trim();
         String phone = phonenum.getText().trim();
         String addr = address.getText().trim();
         String emailStr = email.getText().trim();
         if (manufacturerId.isEmpty() || fullName.isEmpty() || phone.isEmpty() || addr.isEmpty() || emailStr.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
         }

        try {
           

            if (Manufacturerlogic.getInstance().updateManufacturer( manufacturerId,  fullName, phone, addr,  emailStr)) {
                JOptionPane.showMessageDialog(this, "Manufacturer updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                refreshManufacturerTable();
                resetFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update manufacturer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid Manufacturer ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addManufacturer() {
        String manufacturerId = id.getText().trim();
        String fullName = fullname.getText().trim();
        String phone = phonenum.getText().trim();
        String addr = address.getText().trim();
        String emailStr = email.getText().trim();

        if (manufacturerId.isEmpty() || fullName.isEmpty() || phone.isEmpty() || addr.isEmpty() || emailStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isAdded = Manufacturerlogic.getInstance().addManufacturer( manufacturerId,  fullName,  phone,  addr,  emailStr);

        if (isAdded) {
            JOptionPane.showMessageDialog(this, "Manufacturer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshManufacturerTable(); // Refresh table after adding
            resetFields(); // Clear input fields
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add manufacturer. Manufacturer ID may already exist.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void importManufacturers() {
        try {
            // Attempt to import manufacturers using XMLControl class
            XmlControl.getInstance().importManufacturersFromXML(XmlControl.XML_FILEPATH);

            // Refresh table after import
            refreshManufacturerTable();

            // Show success message
            JOptionPane.showMessageDialog(this, "Manufacturers data imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            // Handle duplicate data exception
            JOptionPane.showMessageDialog(this, e.getMessage(), "Duplicate Data Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Handle other exceptions
            JOptionPane.showMessageDialog(this, "Failed to import manufacturers data. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetFields() {
        id.setText("");
        fullname.setText("");
        phonenum.setText("");
        address.setText("");
        email.setText("");
    }
    
    

   
}
