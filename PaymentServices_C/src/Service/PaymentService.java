package Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Beans.PaymentBean;
import Model.Payment;

@Path("/Payment")
public class PaymentService {

	Payment payment = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return payment.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(String paymentData) {

		PaymentBean paymentb = new PaymentBean(paymentData);

		String output = payment.insertPayment(paymentb);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String paymentData) {

		JsonObject PaymentObject = new JsonParser().parse(paymentData).getAsJsonObject();

		String paymentID = PaymentObject.get("paymentID").getAsString();
		String output = payment.deletePayment(paymentID);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String Payment) {

		PaymentBean doc = new PaymentBean(Payment);

		String output = payment.updatePayment(doc);
		return output;
	}

}
