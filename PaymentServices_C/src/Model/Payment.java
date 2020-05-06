package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Beans.PaymentBean;
import DB_Config.DBConnection;

public class Payment {

	public String readPayment() {
		String output = "";
		try {

			Connection con = DBConnection.connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed

			
			output = "<table border='1'><th>Card No</th><th>Paid Name</th><th>Paid Date</th><th>Paid Amount</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {

				String paymentid = Integer.toString(rs.getInt("PayID"));
				String cardno = Integer.toString(rs.getInt("cardNo"));
				String payname = rs.getString("payName");
				String paydate = rs.getString("payDate");
				String payamount = Integer.toString(rs.getInt("payAmount"));
				
				
		
				
				
				
				
				
				
				
				

				// Add into the html table
				output += "<tr><td><input id='hidPaymentIDUpdate' name='hidPaymentIDUpdate' type='hidden' value='" + paymentid + "'>"+ cardno + "</td>";
	
				output += "<td>" + payname + "</td>";
				output += "<td>" + paydate + "</td>";
				output += "<td>" + payamount + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button'value='Remove'class='btnRemove btn btn-danger' data-payid='"
						+ paymentid + "'>" + "</td></tr>";
				
				
				/*
				 * output +=
				 * "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				 * +
				 * "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
				 * + itemID + "'>" + "</td></tr>";
				 */
				
				

			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertPayment(String cardNo, String name, String date, String amount) {

		String output = "";
		try {

			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment(PayID, cardNo, payName, payDate, payAmount)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cardNo);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, date);
			preparedStmt.setDouble(5, Double.parseDouble(amount));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newPayment = readPayment();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newPayment + "\"}"; 
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the peyment.\"}"; 
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String id) {
		String output = "";
		try {

			Connection con = DBConnection.connect();

			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where PayID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));

			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newPayment = readPayment();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newPayment + "\"}"; 

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String id, String cardNo, String name, String date, String amount) {

		String output = "";

		try {

			Connection con = DBConnection.connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET cardNo = ?, payName = ?, payDate = ?, payAmount = ? WHERE PayID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, cardNo);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, date);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setInt(5, Integer.parseInt(id));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayment = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
