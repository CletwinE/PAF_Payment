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

			output = "<table border=\"1\">" + "<tr>" + "<th>Card No</th>" + "<th>Paid Name</th>"
					+ "<th>Paid Date</th>" + "<th>Paid Amount</th>";

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
				output += "<tr>" + "<td>" + cardno + "</td>";
				output += "<td>" + payname + "</td>";
				output += "<td>" + paydate + "</td>";
				output += "<td>" + payamount + "</td>";

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

	public String insertPayment(PaymentBean doc) {

		String output = "";
		try {

			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "insert into payment" + "(`PayID`,`cardNo`,`payName`,`payDate`,`payAmount`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, doc.getCardNo());
			preparedStmt.setString(3, doc.getPayName());
			preparedStmt.setString(4, doc.getPayDate());
			preparedStmt.setDouble(5, doc.getAmount());

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted Payment successfully";
		} catch (Exception e) {
			output = "Error while inserting Payment Info";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String paymentID) {
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
			preparedStmt.setInt(1, Integer.parseInt(paymentID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted Payment successfully";

		} catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(PaymentBean doc) {

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
			preparedStmt.setInt(1, doc.getCardNo());
			preparedStmt.setString(2, doc.getPayName());
			preparedStmt.setString(3, doc.getPayDate());
			preparedStmt.setDouble(4, doc.getAmount());
			preparedStmt.setInt(5, doc.getPaymentID());

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
