package Employee;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


class Add
{
    static JLabel titleLbl, addressLbl, firstNameLbl, lastNameLbl, fatherNameLbl, genderLbl, nationalityLbl, dateOfBirthLbl, mobileNumberLbl;
    static JLabel highQualificationLbl, departmentLbl, dateOfJoiningLbl, roomNoLbl, areaLbl, cityLbl, pincodeLbl, stateLbl;
    static JTextField firstNameTxt, lastNameTxt, fatherNameTxt, mobileNumberTxt, roomNoTxt, areaTxt, cityTxt, pincodeTxt, stateTxt, dateOfBirthTxt, dateOfJoiningTxt;
    static JPanel rightPanel;
    static JButton resetBtn, addBtn;
    static String gender[] = {"Male", "Female", "Other"};
    static String department[] = {"I.T", "Electrical", "Civil", "Management", "Accounting"};
    static String nationality[] = {"INDIAN", "Other"};
    static String qualification[] = {"10th", "12th","Diploma", "Graduate", "Post-Graduate", "Ph.D"};
    static JComboBox<String> genderComboBox;
    static JComboBox<String> departmentComboBox;
    static JComboBox<String> nationalityComboBox;
    static JComboBox<String> highestQualificationComboBox;

    static JPanel add(Connection conn)
    {
        //rightPanel is the DISPLAY_PANEL that have to be returned to the MainPage. 
            //DISPLAY_PANEL setup
        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(255, 255, 153));
        rightPanel.setBounds(200, 40, 600, 460);

            //TITLE_LABEL setup
        titleLbl = new JLabel("Employee's Details");
        titleLbl.setBounds(200, 0,200, 30);
        titleLbl.setFont(new Font(null, Font.BOLD, 18));

            //ADDRESS_TITLE_LABEL setup
        addressLbl = new JLabel("---------- Address Details ----------");
        addressLbl.setBounds(200, 170, 440, 100);
        addressLbl.setFont(new Font(null, Font.BOLD, 12));

            //FIRST_NAME_LABEL setup
        firstNameLbl = new JLabel("First Name");
        firstNameLbl.setBounds(40, 50, 80, 20);
        
            //LAST_NAME_LABEL setup
        lastNameLbl = new JLabel("Last Name");
        lastNameLbl.setBounds(330, 50, 80, 20);

            //FATHER'S_NAME_LABEL setup
        fatherNameLbl = new JLabel("Father's Name");
        fatherNameLbl.setBounds(40, 80, 100, 20);

            //GENDER_LABEL setup
        genderLbl = new JLabel("Gender");
        genderLbl.setBounds(330, 80, 80, 20);

            //NATIONALITY_LABEL setup
        nationalityLbl = new JLabel("Nationality");
        nationalityLbl.setBounds(40, 110, 80, 20);

            //DATE_OF_BIRTH_LABEL setup
        dateOfBirthLbl = new JLabel("Date-Of-Birth");
        dateOfBirthLbl.setBounds(330, 110, 80, 20);

            //MOBILE_NUMBER_LABEL setup
        mobileNumberLbl = new JLabel("Mobile No.");
        mobileNumberLbl.setBounds(40, 140, 80, 20);

            //HIGHEST_QUALIFICATION_LABEL setup
        highQualificationLbl = new JLabel("Highest Qualification");
        highQualificationLbl.setBounds(330, 140, 120, 20);

            //DEPARTMENT_LABEL setup
        departmentLbl = new JLabel("Department");
        departmentLbl.setBounds(40, 170, 80, 20);

            //DATE_OF_JOINING_LABEL setup
        dateOfJoiningLbl = new JLabel("Date-Of-Joining");
        dateOfJoiningLbl.setBounds(330, 170, 100, 20);

            //ROOM_NUMBER_LABEL setup
        roomNoLbl = new JLabel("Room No.");
        roomNoLbl.setBounds(40, 240, 80, 20);

            //AREA_LABEL setup
        areaLbl = new JLabel("Area");
        areaLbl.setBounds(330, 240, 80, 20);

            //CITY_LABEL setup
        cityLbl = new JLabel("City");
        cityLbl.setBounds(40, 270, 80, 20);

            //PINCODE_LABEL setup
        pincodeLbl = new JLabel("Pincode");
        pincodeLbl.setBounds(330, 270, 80, 20);

            //STATE_LABEL setup
        stateLbl = new JLabel("State");
        stateLbl.setBounds(40, 300, 80, 20);

            //FIRST_NAME_TEXTFIELD setup
        firstNameTxt = new JTextField();
        firstNameTxt.setBounds(140, 52, 150, 20);
        
            //LAST_NAME_TEXTFIELD setup
        lastNameTxt = new JTextField();
        lastNameTxt.setBounds(400, 52, 150, 20);

            //FATHER_NAME_TEXTFIELD setup
        fatherNameTxt = new JTextField();
        fatherNameTxt.setBounds(140, 82, 150, 20);

            //MOBILE_NUMBER_TEXTFIELD setup
        mobileNumberTxt = new JTextField();
        mobileNumberTxt.setBounds(140, 142, 150, 20);

            //ROOM_NUMBER_TEXTFIELD setup
        roomNoTxt = new JTextField();
        roomNoTxt.setBounds(140, 242, 150, 20);
    
            //AREA_TEXTFIELD setup
        areaTxt = new JTextField();
        areaTxt.setBounds(400, 242, 150, 20);
    
        	//CITY_TEXTFIELD setup
        cityTxt = new JTextField();
        cityTxt.setBounds(140, 272, 150, 20);
    
        	//PINCODE_TEXTFIELD setup
        pincodeTxt = new JTextField();
        pincodeTxt.setBounds(400, 272, 150, 20);
    
        	//STATE_TEXTFIELD setup
        stateTxt = new JTextField();
        stateTxt.setBounds(140, 302, 150, 20);
        
        	//DATE-0F-BIRTH_TEXTFIELD setup
        dateOfBirthTxt = new JTextField();
        dateOfBirthTxt.setBounds(440, 112, 110, 20);
        
        	//DATE-OF-JOINING_TEXTFIELD setup
        dateOfJoiningTxt = new JTextField();
        dateOfJoiningTxt.setBounds(440, 172, 110, 20);
        
            //RESET_BUTTON setup
        resetBtn = new JButton("RESET");
        resetBtn.setBounds(180, 350, 100, 30);

            //ADD_BUTTON setup
        addBtn = new JButton("ADD");
        addBtn.setBounds(330, 350, 100, 30);

            //GENDER_DROPDOWN_MENU setup
        genderComboBox = new JComboBox<>(gender);
        genderComboBox.setBounds(400, 82, 150, 20);

            //DEPARTMENT_DROPDOWN_MENU setup
        departmentComboBox = new JComboBox<>(department);
        departmentComboBox.setBounds(140, 172, 150, 20);

            //NATIONALITY_DROPSDOWN_MENU setup
        nationalityComboBox = new JComboBox<>(nationality);
        nationalityComboBox.setBounds(140, 112, 150, 20);

            //HIGHEST_QUALIFICATION_DROPDOWN_MENU setup
        highestQualificationComboBox = new JComboBox<>(qualification);
        highestQualificationComboBox.setBounds(460, 142, 90, 20);
        
        	//Adding ActionListener on the ADD_BUTTON
        addBtn.addActionListener(new ActionListener()
        		{
        			public void actionPerformed(ActionEvent e)
        			{
        				try
        				{
        						//TOTAL_EMPLOYEE_COUNT till date.
        					int employeeCount;
        						//PreparedStatement is used to EXECUTE the SQL_QUERY.
        					PreparedStatement countPst, employeePst, addressPst;
        						
        						//initializing the pst is necessary although it does not mean anything but to use RESULT_SET we need to intialize it. 
        							//SQL STATEMENT to COUNT the total NUMBER_OF_EMPLOYEE till date.
        					countPst = conn.prepareStatement("select count(*) from employee_table");
        						//RESULT_SET use to store the result of the excuted query.
        					ResultSet rs = countPst.executeQuery();
        						//rs is set to 0 then next() is used to move the rs to next COLOUMN and switches to NEXT ROW.
        					rs.next();
        						//rs gets the FIRST COLOUMN and set to the employeeCount.
        					employeeCount = rs.getInt(1);
        					
        						//To check MOBILE_NUMBER is valid or not.
        					boolean checkMobileNumberTrue = checkMobileNumber(mobileNumberTxt.getText());
        					
        					if("".equals(firstNameTxt.getText()) || "".equals(lastNameTxt.getText()) || "".equals(fatherNameTxt.getText()) || "".equals(mobileNumberTxt.getText()) || "".equals(dateOfBirthTxt.getText()) || "".equals(dateOfJoiningTxt.getText()) || "".equals(roomNoTxt.getText()) || "".equals(areaTxt.getText()) || "".equals(cityTxt.getText()) || "".equals(pincodeTxt.getText()) || "".equals(stateTxt.getText()))
	        				{
	        					JOptionPane.showMessageDialog(rightPanel, "Please Fill All The Entries");
	        				}
        					else if(checkMobileNumberTrue)
        					{
        						//getSelectedItem is used to get the text of JComboBox and returns the Object.
        						String departmentSelected = (String)departmentComboBox.getSelectedItem();
        						String tempEmpId = empIdGenerator(departmentSelected, employeeCount);
        						
	        						//EMPLOYEE_TABLE_INSERT_SQL_QUERY
		        				String employeeTableQuery = "insert into employee_table values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		        				employeePst = conn.prepareStatement(employeeTableQuery);
		        					//setting all the QUESTION_MARK OF THE EMPLOYEE_TABLE_INSERT_SQL_QUERY
		        						//EMPLOYEE_ID
		        				employeePst.setString(1, tempEmpId);
		        						//FIRST_NAME
		        				employeePst.setString(2, firstNameTxt.getText());
		        						//LAST_NAME
		        				employeePst.setString(3, lastNameTxt.getText());
		        						//FATHER_NAME
		        				employeePst.setString(4, fatherNameTxt.getText());
		        						//GENDER
		        				employeePst.setString(5, (String)genderComboBox.getSelectedItem());
		        						//NATIONALITY
		        				employeePst.setString(6, (String)nationalityComboBox.getSelectedItem());
		        						//DATE_OF_BIRTH
		        				employeePst.setString(7, dateOfBirthTxt.getText());
		        						//MOBILE_NUMBER
		        				employeePst.setLong(8, Long.parseLong(mobileNumberTxt.getText()));
		        						//HIGHEST_QUALIFICATION
		        				employeePst.setString(9, (String)highestQualificationComboBox.getSelectedItem());
		        						//DEPARTMENT
		        				employeePst.setString(10, departmentSelected);
		        						//DATE_OF_JOINING
		        				employeePst.setString(11, dateOfJoiningTxt.getText());
		        				
		        					//ADDRESS_ID generation
		        				String tempAddressId = new String(tempEmpId.concat("Add"));
		        					//ADDRESS_TABLE_INSERT_SQL_QUERY
		        				String addressTableQuery = "insert into address_table values(?, ?, ?, ?, ?, ?, ?)";
		        				addressPst = conn.prepareStatement(addressTableQuery);
		        					//setting all the QUESTION_MARK OF THE EMPLOYEE_TABLE_INSERT_SQL_QUERY
		        						//ADDRESS_ID
		        				addressPst.setString(1, tempAddressId);
		        						//ROOM_NUMBER
		        				addressPst.setString(2, roomNoTxt.getText());
		        						//AREA
		        				addressPst.setString(3, areaTxt.getText());
		        						//CITY
		        				addressPst.setString(4, cityTxt.getText());
		        						//PINCODE
		        				addressPst.setString(5, pincodeTxt.getText());
		        						//STATE
		        				addressPst.setString(6, stateTxt.getText());
		        						//EMPLYEE_ID as FORIEGN_KEY
		        				addressPst.setString(7, tempEmpId);
		        				
		        				//RUNNING the EMPLOYEE_TABLE_INSERT_SQL_QUERY
		        				employeePst.executeUpdate();
		        				//RUNNING the ADDRESS_TABLE_INSERT_SQL_QUERY
		        				addressPst.executeUpdate();
		        				//set all the TEXTFIELD to empty for more entries
		        				firstNameTxt.setText("");
		        				lastNameTxt.setText("");
		        				fatherNameTxt.setText("");
		        				genderComboBox.setSelectedItem(gender[0]);
		        				nationalityComboBox.setSelectedItem(nationality[0]);
		        				dateOfBirthTxt.setText("");
		        				mobileNumberTxt.setText("");
		        				highestQualificationComboBox.setSelectedItem(qualification[0]);
		        				departmentComboBox.setSelectedItem(department[0]);
		        				dateOfJoiningTxt.setText("");
		        				roomNoTxt.setText("");
		        				areaTxt.setText("");
		        				cityTxt.setText("");
		        				pincodeTxt.setText("");
		        				stateTxt.setText("");
		        				
		        				JOptionPane.showMessageDialog(rightPanel, "Saved Successfully");
        					}
        					else
        					{
        						JOptionPane.showMessageDialog(rightPanel, "Invalid Mobile Number");
        					}
        				}
        					//it catches all the SQL_EXCEPTION
        				catch(SQLException SQLe)
        				{
        					JOptionPane.showMessageDialog(rightPanel, "Invalid Mobile Number");
        				}
        			}
        		});
        		//Adding ActionListener on the RESET_BUTTON
        resetBtn.addActionListener(new ActionListener()
        		{
        			public void actionPerformed(ActionEvent e)
        			{
        				//set all the TEXTFIELD to empty 
        				firstNameTxt.setText("");
        				lastNameTxt.setText("");
        				fatherNameTxt.setText("");
        				genderComboBox.setSelectedItem(gender[0]);
        				nationalityComboBox.setSelectedItem(nationality[0]);
        				dateOfBirthTxt.setText("");
        				mobileNumberTxt.setText("");
        				highestQualificationComboBox.setSelectedItem(qualification[0]);
        				departmentComboBox.setSelectedItem(department[0]);
        				dateOfJoiningTxt.setText("");
        				roomNoTxt.setText("");
        				areaTxt.setText("");
        				cityTxt.setText("");
        				pincodeTxt.setText("");
        				stateTxt.setText("");
        				
        				JOptionPane.showMessageDialog(rightPanel, "Reset Completed");
        			}	
        		});
            //Adding COMPONENTS to the DISPLAY_PANEL
        rightPanel.add(titleLbl);
        rightPanel.add(addressLbl);

        rightPanel.add(firstNameLbl);
        rightPanel.add(lastNameLbl);
        rightPanel.add(fatherNameLbl);
        rightPanel.add(genderLbl);
        rightPanel.add(nationalityLbl);
        rightPanel.add(dateOfBirthLbl);
        rightPanel.add(mobileNumberLbl);
        rightPanel.add(highQualificationLbl);
        rightPanel.add(departmentLbl);
        rightPanel.add(dateOfJoiningLbl);
        rightPanel.add(roomNoLbl);
        rightPanel.add(areaLbl);
        rightPanel.add(cityLbl);
        rightPanel.add(pincodeLbl);
        rightPanel.add(stateLbl);

        rightPanel.add(firstNameTxt);
        rightPanel.add(lastNameTxt);
        rightPanel.add(fatherNameTxt);
        rightPanel.add(mobileNumberTxt);
        rightPanel.add(roomNoTxt);
        rightPanel.add(areaTxt);
        rightPanel.add(cityTxt);
        rightPanel.add(pincodeTxt);
        rightPanel.add(stateTxt);
        rightPanel.add(dateOfBirthTxt);
        rightPanel.add(dateOfJoiningTxt);

        rightPanel.add(resetBtn);
        rightPanel.add(addBtn);

        rightPanel.add(genderComboBox);
        rightPanel.add(departmentComboBox);
        rightPanel.add(nationalityComboBox);
        rightPanel.add(highestQualificationComboBox);

            //RETURNING the completed DISPLAY_PANEL
        return rightPanel;
    }
    
    
    	//empIdGenerator() RETURNS the EMPLOYEE_ID by getting the parameter DEPARTMENT and EMPLOYEE_COUNT
    static String empIdGenerator(String department, int count)
    {
    	//empCode is generated by concatenating the DEPARTMENT_CODE and TOTAL_NUMBER_OF_EMPLOYEES + 1
    	String empCode = "E0";
    	
    	//Every department have its own DEPARTMENT_CODE
    		//I.T 		 - 	01
    		//Electrical -  02
    		//Civil 	 - 	03
    		//Management - 	04
    		//Accounting - 	05
    	if(department == "I.T")
    	{
    		empCode = empCode + 1;
    	}
    	else if(department == "Electrical")
    	{
    		empCode = empCode + 2;
    	}
    	else if(department == "Civil")
    	{
    		empCode = empCode + 3;
    	}
    	else if(department == "Management")
    	{
    		empCode = empCode + 4;
    	}
    	else if(department == "Accounting")
    	{
    		empCode = empCode + 5;
    	}
    	
    	empCode.concat(Integer.toString(count+1));
    	empCode = empCode + (count + 1);
    	
    	return empCode;
    }
    
    	//checkMobileNumber method RETURNS TRUE if mobileNumber is of 10-DIGIT and NUMERIC else FALSE
    static boolean checkMobileNumber(String mobileNumber)
    {
    	try
    	{
    		Long.parseLong(mobileNumber);
    		if(mobileNumber.length() == 10)
        	{
        		return true;
        	}
    		else
    		{
    			return false;
    		}
    	}
    	catch(NumberFormatException e)
    	{
    		return false;
    	}
    }
}

