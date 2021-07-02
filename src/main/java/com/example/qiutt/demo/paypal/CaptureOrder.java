package com.example.qiutt.demo.paypal;

import java.io.IOException;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.braintreepayments.http.HttpResponse;
import com.paypal.orders.*;
import lombok.extern.slf4j.Slf4j;

/*
 * https://developer.paypal.com/docs/business/checkout/server-side-api-calls/capture-order/
 *1. Import the PayPal SDK client that was created in `Set up Server-Side SDK`.
 *This step extends the SDK client.  It's not mandatory to extend the client, you can also inject it.
 */
@Slf4j
public class CaptureOrder extends PayPalClient {

	//2. Set up your server to receive a call from the client
	/**
	 *Method to capture order after creation. Pass a valid, approved order ID
	 *an argument to this method.
	 *
	 *@param orderId Authorization ID from authorizeOrder response
	 *@param debug   true = print response data
	 *@return HttpResponse<Capture> response received from API
	 *@throws IOException Exceptions from API if any
	 */
	public String captureOrder(String orderId, boolean debug)  {
		OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
		request.requestBody(buildOrderActionRequestBody());
		//3. Call PayPal to capture an order
		HttpResponse<Order> response = null;
		try {
			response = client().execute(request);
			//HttpResponse<Order> response = live().execute(request);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("{}调用PayPal的捕获订单接口，返回结果{}",orderId, JSON.toJSONString(e));
			return e.getLocalizedMessage();
		}
		log.info("{}调用PayPal的捕获订单接口，返回结果{}",orderId, JSON.toJSONString(response));
		// 4. Save the capture ID to your database. Implement logic to save capture to your database for future reference.
		if (Objects.nonNull(response)
				&&response.statusCode() == 201
				&&response.result().status().equals("COMPLETED")) {
			for (PurchaseUnit purchaseUnit : response.result().purchaseUnits()) {
				for (Capture capture : purchaseUnit.payments().captures()) {
					return capture.id();
				}
			}
		}
//		if (debug) {
//			System.out.println("Status Code: " + response.statusCode());
//			System.out.println("Status: " + response.result().status());
//			System.out.println("Order ID: " + response.result().id());
//			System.out.println("Links: ");
//			for (LinkDescription link : response.result().links()) {
//				System.out.println("\t" + link.rel() + ": " + link.href());
//			}
//			System.out.println("Capture ids:");
//			for (PurchaseUnit purchaseUnit : response.result().purchaseUnits()) {
//				for (Capture capture : purchaseUnit.payments().captures()) {
//					System.out.println("\t" + capture.id());
//				}
//			}
//			System.out.println("Buyer: ");
//			Customer buyer = response.result().payer();
//			System.out.println("Buyer: "+ JSONObject.toJSONString(buyer));
//
//			System.out.println("\tEmail Address: " + buyer.emailAddress());
//			System.out.println("\tName: " + buyer.name().fullName());
//			System.out.println("\tPhone Number: " + buyer.phone().countryCode() + buyer.phone().nationalNumber());
		return null;
	}

	/**
	 *Creating empty body for capture request.
	 *You can set the payment source if required.
	 *
	 *@return OrderRequest request with empty body
	 */
	public OrderRequest buildRequestBody() {
		return new OrderRequest();
	}

	/**
	 *Creating empty body for capture request.
	 *You can set the payment source if required.
	 *
	 *@return OrderRequest request with empty body
	 * {
	"issuer": "VISA",
	"cardNumber": "4024007131199365",
	"expiryMonth": "05",
	"expiryYear": "2026",
	"exp": "05/2026",
	"cvv": 912,
	"name": "Terry Ratke",
	"address": "4600 Clifford Shore",
	"country": "Heard Island and McDonald Islands",
	"zipcode": "22999-8720"
	}
	 */
	public OrderActionRequest buildOrderActionRequestBody() {
		OrderActionRequest orderActionRequest=new OrderActionRequest();
		Card card =new Card()
				.number("4024007131199365")
				.expiry("2026-07")
				.cardType("credit")
				.name("test_refund")
				.securityCode("912")
//				.lastDigits("")
//				.cardType("credit")
				;

		PaymentSource paymentSource=new PaymentSource().card(card);
		orderActionRequest.paymentSource(paymentSource);
		return orderActionRequest;
	}

	/**
	 *Driver function to invoke capture payment on order.
	 *Replace the order ID with the valid, approved order ID.
	 *
	 *@param args
	 */
	public static void main(String[] args) {
		try {
			String captureId=new CaptureOrder().captureOrder("14X172839F461124J", true);
			System.out.println("captureId:"+captureId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}