package Employee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

class Sort
{
    static JLabel titleLbl, sortLbl;
    static JButton displayBtn;
    static JTable firstTable, secondTable, thirdTable;
    static String[] sortList = {"...", "GENDER", "NATIONALITY", "HIGHEST_QUALIFICATION", "DEPARTMENT", "STATE"};
    static JComboBox<String> sortListComboBox;
    static JPanel sort(Connection conn)
    {
        //rightPanel is the DISPLAY_PANEL that have to be returned to the MainPage. 
            //DISPLAY_PANEL setup
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(255, 255, 153));
        rightPanel.setBounds(200, 40, 600, 460);

            //TITLE_LABEL setup
        titleLbl = new JLabel("All Employees");
        titleLbl.setBounds(200, 0,200, 30);
        titleLbl.setFont(new Font(null, Font.BOLD, 18));
        
        	//DISPLAY_ALL_BUTTON setup
        displayBtn = new JButton("Display All Record");
        displayBtn.setBounds(180, 50, 150, 20);
        
        	//SORT_LBAEL setup
        sortLbl = new JLabel("Sort By");
        sortLbl.setBounds(370, 50, 60, 20);
        
        	//SORT_DROP_DOWN MENU
        sortListComboBox = new JComboBox<>(sortList);
        sortListComboBox.setBounds(420, 50, 150, 20);
        
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
		
			//Adding ACTIONLISTENER to the DISPLAY_ALL_BUTTON
		displayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					//REMOVING_EXISTING_ROWS from all the TABLE_FORMATTED_DISPLAY
					if(firstTableModel.getRowCount() != 0)
					{
						while(firstTableModel.getRowCount() != 0)
						{
							firstTableModel.removeRow(0);
							secondTableModel.removeRow(0);
							thirdTableModel.removeRow(0);
						}
					}

					PreparedStatement displayAllStatement;
					if("...".equals(sortListComboBox.getSelectedItem()))
					{
						displayAllStatement = conn.prepareStatement("select * from employee_table, address_table where (employee_table.emp_id = address_table.emp_id)");
					}
					else
					{
						displayAllStatement = conn.prepareStatement("select * from employee_table, address_table where (employee_table.emp_id = address_table.emp_id) order by " + (String)sortListComboBox.getSelectedItem());
					}
					ResultSet rs = displayAllStatement.executeQuery();
					
					//creating 3 OBJECT_ARRAY to store the values of DATABASE so that it can be add to the 3 DISPLAYS
							//FIRST_DISPLAY_DATA
					Object[] firstTableData = new Object[6];
							//SECOND_DISPLAY_DATA
					Object[] secondTableData = new Object[5];
							//THIRD_DISPLAY_DATA
					Object[] thirdTableData = new Object[6];
					int l, i, j, k;
					while(rs.next())
					{
						l = 1;
						i = 0;
						j = 0;
						k = 0;
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
        rightPanel.add(sortLbl);
        
        rightPanel.add(displayBtn);
        
        rightPanel.add(sortListComboBox);
        
        rightPanel.add(firstShowTable);
        rightPanel.add(secondShowTable);
        rightPanel.add(thirdShowTable);
        
            //RETURNING the completed DISPLAY_PANEL
        return rightPanel;
    }
}
