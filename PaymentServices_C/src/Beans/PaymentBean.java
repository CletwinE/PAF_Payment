package Beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement
public class PaymentBean {

	// create variables
	int paymentID;
	private int cardNo;
	private String payName;
	private String payDate;
	private double amount;

	public PaymentBean() {
		// TODO Auto-generated constructor stub
	}

	public PaymentBean(String doc) {

		JsonObject paymentObject = new JsonParser().parse(doc).getAsJsonObject();

		if (paymentObject.get("PayID") != null) {
			this.paymentID = paymentObject.get("PayID").getAsInt();
		}

		this.cardNo = paymentObject.get("cardNo").getAsInt();
		this.payName = paymentObject.get("payName").getAsString();
		this.payDate = paymentObject.get("payDate").getAsString();
		this.amount = paymentObject.get("payAmount").getAsInt();

	}

	public PaymentBean(int paymentID, int cardNo, String nameOnCard, String expDate, int cvc) {
		super();
		this.paymentID = paymentID;
		this.cardNo = cardNo;
		this.payName = nameOnCard;
		this.payDate = expDate;
		this.amount = cvc;
	}

	public PaymentBean(int cardNo, String nameOnCard, String expDate, int cvc) {
		super();
		this.cardNo = cardNo;
		this.payName = nameOnCard;
		this.payDate = expDate;
		this.amount = cvc;
	}

	
	
	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}



}
