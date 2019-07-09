package Employee;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


class Delete
{
    static JLabel titleLbl, empIdLbl;
    static JTextField empIdTxt;
    static JButton searchBtn, deleteBtn, showDeletedRecordBtn;
    static JTable deleteEmployeeTable, deleteEmployeeAddressTable, allDeletedEmployeeTable, allDeletedEmployeeAdressTable;
    
    static JPanel delete(Connection conn)
    {
        //rightPanel is the DISPLAY_PANEL that have to be returned to the MainPage. 
            //DISPLAY_PANEL setup
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(255, 255, 153));
        rightPanel.setBounds(200, 40, 600, 460);

            //TITLE_LABEL setup
        titleLbl = new JLabel("Delete Employee Record");
        titleLbl.setBounds(170, 0,250, 30);
        titleLbl.setFont(new Font(null, Font.BOLD, 18));
        
        	//EMPLOYEE_ID_LABEL
        empIdLbl = new JLabel("Enter Employee Id");
        empIdLbl.setBounds(100, 50, 120, 20);
        
        	//EMPLOYEE_ID_TEXTFIELD
        empIdTxt = new JTextField();
        empIdTxt.setBounds(220, 50, 120, 20);
        
        	//SEARCH_BUTTON
        searchBtn = new JButton("Search");
        searchBtn.setBounds(350, 50, 80, 20);
        
        	//DELETE_BUTTON
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(500, 160, 80, 20);
        deleteBtn.setForeground(Color.red);
        
        	//SHOW_DELETED_RECORD_BUTTON
        showDeletedRecordBtn = new JButton("Show All Deleted Records");
        showDeletedRecordBtn.setBounds(180, 185, 220, 20);
        
        	//2 pairs of DISPLAY is going to be created so we need all 2 tables COLUMN_NAME.
        		//DISPLAY 1 & 3
        String[] tableColumn = {"EmpId", "FirstName", "LastName", "Father", "Gender", "Nationality", "Birth-Date", "Mobie No.", "Qualification"};
        		//DISPLAY 2 & 4
        String[] addressTableColumn = {"Department", "Join-Date", "Address-Id", "Room-No", "Area", "City", "Pincode", "State"};
        	
        	//FIRST_DISPLAY
        		//DEFAULT_TABLE_MODEL create to make a TABLE_FORMATTED_DISPLAY
        DefaultTableModel tableModel = new DefaultTableModel();
        		//all COLUMN_NAMES are set by using SET_COLUMN_IDENTIFIERS_METHOD
        tableModel.setColumnIdentifiers(tableColumn);
        		//now we INITILIZE our display with TABLE_FORMATTED_DISPLAY
        deleteEmployeeTable = new JTable(tableModel);
        		//getting the COLUMN_MODEL to make changes in column structure
        TableColumnModel columnModel = deleteEmployeeTable.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(50);
        columnModel.getColumn(3).setMaxWidth(60);
        columnModel.getColumn(4).setMaxWidth(50);
        columnModel.getColumn(6).setMaxWidth(60);
        columnModel.getColumn(7).setMaxWidth(70);
        		//we need JSCROLLPANE if data is more than the TABLE_SIZE
    				//so JSCROLLPANE is created and INTIALIZED with our DISPLAY
        JScrollPane deleteEmployee = new JScrollPane(deleteEmployeeTable);
        		//SETBOUNDS is used to set the CO-ORDINATES and SIZE of DISPLAY
        deleteEmployee.setBounds(10, 80, 570, 40);
        
        	//SECOND_DISPLAY
        		//DEFAULT_TABLE_MODEL create to make a TABLE_FORMATTED_DISPLAY
        DefaultTableModel addressTableModel = new DefaultTableModel();
        		//all COLUMN_NAMES are set by using SET_COLUMN_IDENTIFIERS_METHOD
        addressTableModel.setColumnIdentifiers(addressTableColumn);
        		//now we INITILIZE our display with TABLE_FORMATTED_DISPLAY
        deleteEmployeeAddressTable = new JTable(addressTableModel);
        		//we need JSCROLLPANE if data is more than the TABLE_SIZE
    				//so JSCROLLPANE is created and INTIALIZED with our DISPLAY
        JScrollPane deleteEmployeeAddress = new JScrollPane(deleteEmployeeAddressTable);
        		//SETBOUNDS is used to set the CO-ORDINATES and SIZE of DISPLAY
        deleteEmployeeAddress.setBounds(10, 120, 570, 40);
        
        	//THIRD_DISPLAY
        		//DEFAULT_TABLE_MODEL create to make a TABLE_FORMATTED_DISPLAY
        DefaultTableModel allTableModel = new DefaultTableModel();
        		//all COLUMN_NAMES are set by using SET_COLUMN_IDENTIFIERS_METHOD
        allTableModel.setColumnIdentifiers(tableColumn);
        		//now we INITILIZE our display with TABLE_FORMATTED_DISPLAY
        allDeletedEmployeeTable = new JTable(allTableModel);
        		//getting the COLUMN_MODEL to make changes in column structure
        TableColumnModel columnModelAll = allDeletedEmployeeTable.getColumnModel();
        columnModelAll.getColumn(0).setMaxWidth(50);
        columnModelAll.getColumn(3).setMaxWidth(60);
        columnModelAll.getColumn(4).setMaxWidth(50);
        columnModelAll.getColumn(6).setMaxWidth(60);
        columnModelAll.getColumn(7).setMaxWidth(70);
        		//we need JSCROLLPANE if data is more than the TABLE_SIZE
    				//so JSCROLLPANE is created and INTIALIZED with our DISPLAY
        JScrollPane allDeletedEmployee = new JScrollPane(allDeletedEmployeeTable);
        		//SETBOUNDS is used to set the CO-ORDINATES and SIZE of DISPLAY
        allDeletedEmployee.setBounds(10, 215, 570, 100);
        
        	//FOURTH_DISPLAY
        		//DEFAULT_TABLE_MODEL create to make a TABLE_FORMATTED_DISPLAY
        DefaultTableModel allAddressTableModel = new DefaultTableModel();
        		//all COLUMN_NAMES are set by using SET_COLUMN_IDENTIFIERS_METHOD
        allAddressTableModel.setColumnIdentifiers(addressTableColumn);
        		//now we INITILIZE our display with TABLE_FORMATTED_DISPLAY
        allDeletedEmployeeAdressTable = new JTable(allAddressTableModel);
        		//we need JSCROLLPANE if data is more than the TABLE_SIZE
    				//so JSCROLLPANE is created and INTIALIZED with our DISPLAY
        JScrollPane allDeletedEmployeeAddress = new JScrollPane(allDeletedEmployeeAdressTable);
        		//SETBOUNDS is used to set the CO-ORDINATES and SIZE of DISPLAY
        allDeletedEmployeeAddress.setBounds(10, 320, 570, 100);
        
        	//ADDING ACTION_LISTENER to the BUTTONS
        searchBtn.addActionListener(new ActionListener()
        		{
        			public void actionPerformed(ActionEvent e)
        			{
        				try
        				{
        					if(tableModel.getRowCount() != 0)
        					{
        						tableModel.removeRow(0);
        						addressTableModel.removeRow(0);
        					}
        						
        					PreparedStatement st = conn.prepareStatement("SELECT EMPLOYEE_TABLE.EMP_ID, EMPLOYEE_TABLE.FIRST_NAME, EMPLOYEE_TABLE.LAST_NAME, EMPLOYEE_TABLE.FATHER_NAME, EMPLOYEE_TABLE.GENDER, EMPLOYEE_TABLE.NATIONALITY, EMPLOYEE_TABLE.DATE_OF_BIRTH, EMPLOYEE_TABLE.MOBILE_NUMBER, EMPLOYEE_TABLE.HIGHEST_QUALIFICATION, EMPLOYEE_TABLE.DEPARTMENT, EMPLOYEE_TABLE.DATE_OF_JOINING, ADDRESS_TABLE.ADD_ID, ADDRESS_TABLE.ROOM_NUMBER, ADDRESS_TABLE.AREA, ADDRESS_TABLE.CITY, ADDRESS_TABLE.PINCODE, ADDRESS_TABLE.STATE FROM EMPLOYEE_TABLE, ADDRESS_TABLE WHERE (EMPLOYEE_TABLE.EMP_ID = ? AND EMPLOYEE_TABLE.EMP_ID = ADDRESS_TABLE.EMP_ID)");
        					st.setString(1, empIdTxt.getText());
        					
        					ResultSet rs = st.executeQuery();
        					
        					Object[] employeeTableData = new Object[9];
        					Object[] employeeAddressTableData = new Object[9];
        					int k = 1;
        					int i = 0;
        					int j = 0;
        					rs.next();
        					while(k <= 17)
        					{
        						if(i < 9)
        						{
        							employeeTableData[i] = rs.getString(k);
        						}
        						else if(j < 8)
        						{
        							employeeAddressTableData[j] = rs.getString(k);
        							j++;	
        						}
        						i++;
        						k++;
        					}
        					
        					tableModel.addRow(employeeTableData);
        					addressTableModel.addRow(employeeAddressTableData);
        					JOptionPane.showMessageDialog(rightPanel, "Record Found");
        				}
        				catch(SQLException sqle)
        				{
        					JOptionPane.showMessageDialog(rightPanel, "No Record Found");
        				}
        			}
        		});
        deleteBtn.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e)
		        	{
		        		try
		        		{
		        			String emp_id = empIdTxt.getText();
		        			if(tableModel.getRowCount() != 0)
        					{
        						tableModel.removeRow(0);
        						addressTableModel.removeRow(0);
        					}
		        			if("".equals(emp_id))
		        			{
		        				JOptionPane.showMessageDialog(rightPanel, "Invalid Employee Id");
		        			}
		        			else
		        			{
		        				PreparedStatement checkSqlQuery = conn.prepareStatement("Select count(*) from employee_table where emp_id = ?");
		        				checkSqlQuery.setString(1, emp_id);
		        				ResultSet rs = checkSqlQuery.executeQuery();
		        				rs.next();
		        				if("0".equals(rs.getString(1)))
		        				{
		        					JOptionPane.showMessageDialog(rightPanel, "No Record Found To Be Deleted");
		        				}
		        				else
		        				{
					        		PreparedStatement copyDataSqlQuery = conn.prepareStatement("begin insert into deleted_employee_table select * from employee_table where emp_id = ?; insert into deleted_address_table select * from address_table where emp_id = ?; end;");
					        			
					        		copyDataSqlQuery.setString(1, emp_id);
					        		copyDataSqlQuery.setString(2, emp_id);
					        			
					        		PreparedStatement deleteDataSqlQuery = conn.prepareStatement("begin delete from employee_table where emp_id = ?; delete from address_table where emp_id = ?; end;");
					        			
					        		deleteDataSqlQuery.setString(1, emp_id);
					        		deleteDataSqlQuery.setString(2, emp_id);
					        			
					        		
					        		copyDataSqlQuery.executeUpdate();
					        		deleteDataSqlQuery.executeUpdate();
					        		empIdTxt.setText("");
					        		JOptionPane.showMessageDialog(rightPanel, "Record Deleted");
		        				}
			        		}
		        		}
		        		catch(SQLException sqle)
		        		{
		        			JOptionPane.showMessageDialog(rightPanel, "No Record Found");
		        			System.out.println(sqle);
		        		}
		        	}
        });
        showDeletedRecordBtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e)
	        	{
	        		try
	        		{
	        			//REMOVING_EXISTING_ROWS from all the TABLE_FORMATTED_DISPLAY
						if(allTableModel.getRowCount() != 0)
						{
							while(allTableModel.getRowCount() != 0)
							{
								allTableModel.removeRow(0);
								allAddressTableModel.removeRow(0);
							}
						}
	        			PreparedStatement showDeletedRecord = conn.prepareStatement("select * from deleted_employee_table, deleted_address_table where (deleted_employee_table.emp_id = deleted_address_table.emp_id)");
	        			ResultSet deletedRecordResult = showDeletedRecord.executeQuery();
	        			
	        			Object[] employeeTableData = new Object[9];
    					Object[] employeeAddressTableData = new Object[9];
    					int k, i, j;
    					while(deletedRecordResult.next())
    					{
    						k = 1;
    						i = 0;
    						j = 0;
    						while(k <= 17)
    							{
		    						if(i < 9)
		    						{
		    							employeeTableData[i] = deletedRecordResult.getString(k);
		    						}
		    						else if(j < 8)
		    						{
		    							employeeAddressTableData[j] = deletedRecordResult.getString(k);
		    							j++;	
		    						}
		    						i++;
		    						k++;
    							}
    						allTableModel.addRow(employeeTableData);
    						allAddressTableModel.addRow(employeeAddressTableData);
    					}
	        		}
	        		catch(SQLException sqle)
	        		{
	        			System.out.println(sqle);
	        		}
	        	}
        });
        
            //Adding COMPONENTS to the DISPLAY_PANEL
        rightPanel.add(titleLbl);
        rightPanel.add(empIdLbl);
        
        rightPanel.add(empIdTxt);
        
        rightPanel.add(deleteEmployee);
        rightPanel.add(deleteEmployeeAddress);
        
        rightPanel.add(searchBtn);
        rightPanel.add(deleteBtn);
        rightPanel.add(showDeletedRecordBtn);
        rightPanel.add(allDeletedEmployee);
        rightPanel.add(allDeletedEmployeeAddress);
        
            //RETURNING the completed DISPLAY_PANEL
        return rightPanel;
    }
}





