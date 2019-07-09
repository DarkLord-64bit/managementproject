package Employee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	//import DEFAULT_TABLE_MODEL so that we can make TABLE_FORMATED_DISPLAY
import javax.swing.table.DefaultTableModel;

import java.sql.*;

class Search
{
    static JLabel titleLbl, empIdLbl;
    static JTextField empIdTxt;
    static JButton searchBtn;
    static JTable firstTable, secondTable, thirdTable;
    
    static JPanel search(Connection conn)
    {
        //rightPanel is the DISPLAY_PANEL that have to be returned to the MainPage. 
            //DISPLAY_PANEL setup
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(255, 255, 153));
        rightPanel.setBounds(200, 40, 600, 460);
        
            //TITLE_LABEL setup
        titleLbl = new JLabel("Find Employee");
        titleLbl.setBounds(200, 0,200, 30);
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
        
        	//3 DISPLAY is going to be created so we need all 3 tables COLUMN_NAME.
        		//FIRST_DISPLAY_COLUMN_NAME
        String[] first = {"EmpId", "FirstName", "LastName", "Father", "Gender", "Nationality"};
        		//SECOND_DISPLAY_COLUMN_NAME
        String[] second = {"Birth-Date", "Mobie No.", "Qualification", "Department", "Join-Date"};
        		//THIRD_DISPLAY_COLUMN_NAME
        String[] third = {"Address-Id", "Room-No", "Area", "City", "Pincode", "State"};
        
        			//FIRST_DISPLAY
        	//DEFAULT_TABLE_MODEL create to make a TABLE_FORMATTED_DISPLAY
        DefaultTableModel firstTableModel = new DefaultTableModel();
        	//all COLUMN_NAMES are set by using SET_COLUMN_IDENTIFIERS_METHOD
        firstTableModel.setColumnIdentifiers(first);
        	//now we INITILIZE our display with TABLE_FORMATTED_DISPLAY
        firstTable = new JTable(firstTableModel);
        	//we need JSCROLLPANE if data is more than the TABLE_SIZE
        	//so JSCROLLPANE is created and INTIALIZED with our DISPLAY
        JScrollPane firstShowTable = new JScrollPane(firstTable);
        	//SETBOUNDS is used to set the CO-ORDINATES and SIZE of DISPLAY
        firstShowTable.setBounds(10, 90, 570, 100);
        
        			//SECOND_DISPLAY
        	//DEFAULT_TABLE_MODEL create to make a TABLE_FORMATTED_DISPLAY
        DefaultTableModel secondTableModel = new DefaultTableModel();
        	//all COLUMN_NAMES are set by using SET_COLUMN_IDENTIFIERS_METHOD
        secondTableModel.setColumnIdentifiers(second);
        	//now we INITILIZE our display with TABLE_FORMATTED_DISPLAY
        secondTable = new JTable(secondTableModel);
        	//we need JSCROLLPANE if data is more than the TABLE_SIZE
    		//so JSCROLLPANE is created and INTIALIZED with our DISPLAY
        JScrollPane secondShowTable = new JScrollPane(secondTable);
        	//SETBOUNDS is used to set the CO-ORDINATES and SIZE of DISPLAY
        secondShowTable.setBounds(10, 200, 570, 100);
        
        			//THIRD_DISPLAY
        	//DEFAULT_TABLE_MODEL create to make a TABLE_FORMATTED_DISPLAY
        DefaultTableModel thirdTableModel = new DefaultTableModel();
        	//all COLUMN_NAMES are set by using SET_COLUMN_IDENTIFIERS_METHOD
        thirdTableModel.setColumnIdentifiers(third);
        	//now we INITILIZE our display with TABLE_FORMATTED_DISPLAY
        thirdTable = new JTable(thirdTableModel);
        	//we need JSCROLLPANE if data is more than the TABLE_SIZE
			//so JSCROLLPANE is created and INTIALIZED with our DISPLAY
        JScrollPane thirdShowTable = new JScrollPane(thirdTable);
        	//SETBOUNDS is used to set the CO-ORDINATES and SIZE of DISPLAY
        thirdShowTable.setBounds(10, 310, 570, 100);
        
        	//adding ACTIONLISTENER to the SEARCH_BUTTON
        searchBtn.addActionListener(new ActionListener()
        		{
        			public void actionPerformed(ActionEvent e)
        			{
        				try
        				{
        						//REMOVING_EXISTING_ROWS from all the TABLE_FORMATTED_DISPLAY
        					if(firstTableModel.getRowCount() != 0)
        					{
        						firstTableModel.removeRow(0);
        						secondTableModel.removeRow(0);
        						thirdTableModel.removeRow(0);
        					}
        						
        						//creating a PREPARED_STATEMENT with SQL_QUERY in it
        							//PREPARED_STATEMENT is used so that we can enter values in SQL_QUERY at RUNTIME
        					PreparedStatement st = conn.prepareStatement("SELECT EMPLOYEE_TABLE.EMP_ID, EMPLOYEE_TABLE.FIRST_NAME, EMPLOYEE_TABLE.LAST_NAME, EMPLOYEE_TABLE.FATHER_NAME, EMPLOYEE_TABLE.GENDER, EMPLOYEE_TABLE.NATIONALITY, EMPLOYEE_TABLE.DATE_OF_BIRTH, EMPLOYEE_TABLE.MOBILE_NUMBER, EMPLOYEE_TABLE.HIGHEST_QUALIFICATION, EMPLOYEE_TABLE.DEPARTMENT, EMPLOYEE_TABLE.DATE_OF_JOINING, ADDRESS_TABLE.ADD_ID, ADDRESS_TABLE.ROOM_NUMBER, ADDRESS_TABLE.AREA, ADDRESS_TABLE.CITY, ADDRESS_TABLE.PINCODE, ADDRESS_TABLE.STATE FROM EMPLOYEE_TABLE, ADDRESS_TABLE WHERE (EMPLOYEE_TABLE.EMP_ID = ? AND EMPLOYEE_TABLE.EMP_ID = ADDRESS_TABLE.EMP_ID)");
        							
        						//setting up the EMPLOYEE_ID in SQL_QUERY
        					st.setString(1, empIdTxt.getText());
        					
        						//creating a RESULT_SET for retrieving the RESULT of SQL_QUERY
        							//EXECUTE_QUERY method automatically executes the SQL_QUERY because it is initialized with the PREPARED_STATEMENT OBJECT
        					ResultSet rs = st.executeQuery();
        					
        						//creating 3 OBJECT_ARRAY to store the values of DATABASE so that it can be add to the 3 DISPLAYS
        							//FIRST_DISPLAY_DATA
        					Object[] firstTableData = new Object[6];
        							//SECOND_DISPLAY_DATA
        					Object[] secondTableData = new Object[5];
        							//THIRD_DISPLAY_DATA
        					Object[] thirdTableData = new Object[6];
        					int l = 1;
        					int i = 0;
        					int j = 0;
        					int k = 0;
        						//RETRIEVING the DATA from the DATABASE using already created RESULT_SET and storing it in OBJECT_ARRAY
        					rs.next();
        					while(l <= 17)
        					{
        						//RETREIVING 6 records from the RESULT_SET
        							//because FIRST_DISPLAY contains 6 COLUMNS
        						if(i < 6)
        						{
        							firstTableData[i] = rs.getString(l);
        							i++;
        						}
        						//RETREIVING 5 records from the RESULT_SET
    								//because SECOND_DISPLAY contains 5 COLUMNS
        						else if(j < 5)
        						{
        							secondTableData[j] = rs.getString(l);
        							j++;	
        						}
        						//RETREIVING 6 records from the RESULT_SET
    								//because THIRD_DISPLAY contains 6 COLUMNS
        						else if(k < 6)
        						{
        							thirdTableData[k] = rs.getString(l);
        							k++;
        						}
        						//INCREASING the RECORD_COUNT
        						l++;
        					}
        					
        						//ADDING DATA to the rows on DISPLAY
        							//FIRST_DISPLAY
        					firstTableModel.addRow(firstTableData);
        							//SECOND_DISPLAY
        					secondTableModel.addRow(secondTableData);
        							//THIRD_DISPLAY
        					thirdTableModel.addRow(thirdTableData);
        					
        						//SHOWING a DIALOG_BOX on completion
        					JOptionPane.showMessageDialog(rightPanel, "Search Completed");
        				}
        				catch(SQLException sqle)
        				{
        					//SHOWING a DIALOG_BOX if EMPLOYEE_ID_NOT_FOUND
        					JOptionPane.showMessageDialog(rightPanel, "No Data Found");
        				}
        			}
        		});
        
            //Adding COMPONENTS to the DISPLAY_PANEL
        	//all LABEL
        rightPanel.add(titleLbl);
        rightPanel.add(empIdLbl);
        	//all TEXTFIELD
        rightPanel.add(empIdTxt);
        	//all BUTTON
        rightPanel.add(searchBtn);
        	//all DISPLAY_TABLE
        rightPanel.add(firstShowTable);
        rightPanel.add(secondShowTable);
        rightPanel.add(thirdShowTable);

            //RETURNING the completed DISPLAY_PANEL
        return rightPanel;
    }
}
