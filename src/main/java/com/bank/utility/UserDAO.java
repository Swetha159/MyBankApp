package com.bank.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.bank.registration.User;

public class UserDAO {

	private static ServletContext context;

	public UserDAO(ServletContext ctx) {
		context = ctx;
	}

	public int Add(User user) {
		int rowsAffected = 0;
		String insertQuery = "INSERT INTO user_accounts (name, gender, email, dob, phone_number, occupation, account_type, debit_card,  address, city, state, zip, nationality, aadhaar, pan, income, marital_status, nominee, relationship) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection(context);
				PreparedStatement statement = connection.prepareStatement(insertQuery)) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getGender());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getDob());
			statement.setString(5, user.getPhoneNumber());
			statement.setString(6, user.getOccupation());
			statement.setString(7, user.getAccountType());
			statement.setString(8, user.getDebitCard());
			statement.setString(9, user.getAddress());
			statement.setString(10, user.getCity());
			statement.setString(11, user.getState());
			statement.setString(12, user.getZip());
			statement.setString(13, user.getNationality());
			statement.setString(14, user.getAadhaar());
			statement.setString(15, user.getPan());
			statement.setString(16, user.getIncome());
			statement.setString(17, user.getMaritalStatus());
			statement.setString(18, user.getNominee());
			statement.setString(19, user.getRelationship());

			rowsAffected = statement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rowsAffected;

	}

	public int Update(User user) {
		
		int rowsUpdated = 0;
		String updateQuery = "UPDATE user_accounts SET name=?, gender=?,email=?, dob=?, phone_number=?, occupation=?, account_type=?, debit_card=?,  address=?, city=?, state=?, zip=?, nationality=?, aadhaar=?, pan=?, income=?, marital_status=?, nominee=?, relationship=? WHERE id=?";

		try (Connection connection = DatabaseConnection.getConnection(context);
				PreparedStatement statement = connection.prepareStatement(updateQuery)) {

			statement.setString(1, user.getName());
			statement.setString(2, user.getGender());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getDob());
			statement.setString(5, user.getPhoneNumber());
			statement.setString(6, user.getOccupation());
			statement.setString(7, user.getAccountType());
			statement.setString(8, user.getDebitCard());
			statement.setString(9, user.getAddress());
			statement.setString(10, user.getCity());
			statement.setString(11, user.getState());
			statement.setString(12, user.getZip());
			statement.setString(13, user.getNationality());
			statement.setString(14, user.getAadhaar());
			statement.setString(15, user.getPan());
			statement.setString(16, user.getIncome());
			statement.setString(17, user.getMaritalStatus());
			statement.setString(18, user.getNominee());
			statement.setString(19, user.getRelationship());
			statement.setInt(20, user.getId());

			rowsUpdated = statement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		rowsUpdated =0;
		return rowsUpdated;

	}
	
	public List<User> displayAll()
	{
		List<User> userList = new ArrayList<>();
		try (Connection connection = DatabaseConnection.getConnection(context);
				Statement statement = connection.createStatement(); 
				ResultSet rs = statement.executeQuery("SELECT * FROM user_accounts"))
		{

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setEmail(rs.getString("email"));
				user.setDob(rs.getString("dob"));
				user.setPhoneNumber(rs.getString("phone_number"));
				user.setOccupation(rs.getString("occupation"));
				user.setAccountType(rs.getString("account_type"));
				user.setDebitCard(rs.getString("debit_card"));
				user.setAddress(rs.getString("address"));
				user.setCity(rs.getString("city"));
				user.setState(rs.getString("state"));
				user.setZip(rs.getString("zip"));
				user.setNationality(rs.getString("nationality"));
				user.setAadhaar(rs.getString("aadhaar"));
				user.setPan(rs.getString("pan"));
				user.setIncome(rs.getString("income"));
				user.setMaritalStatus(rs.getString("marital_status"));
				user.setNominee(rs.getString("nominee"));
				user.setRelationship(rs.getString("relationship"));

				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;

	}
	
	public User displayUser(int id)
	{
	
				  String query = "SELECT * FROM user_accounts WHERE id=?";
				  User user = new User();
				 
				 try (Connection connection = DatabaseConnection.getConnection(context);
						 PreparedStatement statement = connection.prepareStatement(query);){ 

					 statement.setInt(1, id);
						ResultSet rs = statement.executeQuery() ;
			
						while (rs.next()) {
							
							user.setId(rs.getInt("id"));
							user.setName(rs.getString("name"));
							user.setGender(rs.getString("gender"));
							user.setEmail(rs.getString("email"));
							user.setDob(rs.getString("dob"));
							user.setPhoneNumber(rs.getString("phone_number"));
							user.setOccupation(rs.getString("occupation"));
							user.setAccountType(rs.getString("account_type"));
							user.setDebitCard(rs.getString("debit_card"));
							user.setAddress(rs.getString("address"));
							user.setCity(rs.getString("city"));
							user.setState(rs.getString("state"));
							user.setZip(rs.getString("zip"));
							user.setNationality(rs.getString("nationality"));
							user.setAadhaar(rs.getString("aadhaar"));
							user.setPan(rs.getString("pan"));
							user.setIncome(rs.getString("income"));
							user.setMaritalStatus(rs.getString("marital_status"));
							user.setNominee(rs.getString("nominee"));
							user.setRelationship(rs.getString("relationship"));
							

						}
					
				 
					} catch (SQLException |ClassNotFoundException e) {
						e.printStackTrace();
					}
				 return user;
					}
	public int delete(int id)
	{
		String deleteQuery = "DELETE FROM user_accounts WHERE id=?";
		 int rowsDeleted=0;
		
		try (Connection connection = DatabaseConnection.getConnection(context);
				 PreparedStatement statement = connection.prepareStatement(deleteQuery);) {
			statement.setInt(1, id);
			 rowsDeleted = statement.executeUpdate();
			
		}catch (SQLException |ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
		
	}

}