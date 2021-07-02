package com.example.qiutt.demo.paypal;

import java.io.IOException;

import com.paypal.payments.*;
import org.json.JSONObject;

import com.braintreepayments.http.HttpResponse;
import com.braintreepayments.http.serializer.Json;

/*
 * https://developer.paypal.com/docs/checkout/integration-features/refunds/
 *1. Import the PayPal SDK client that was created in `Set up Server-Side SDK`.
 *This step extends the SDK client. It's not mandatory to extend the client, you also can inject it.
 */
public class CaptureRefundOrder extends PayPalClient {

	//2. Set up your server to receive a call from the client
	// Method to refund the capture. Pass a valid capture ID.
	//
	// @param captureId Capture ID from authorizeOrder response
	// @param debug     true = print response data
	// @return HttpResponse<Capture> response received from API
	// @throws IOException Exceptions from API if any

	public HttpResponse<Refund> refundOrder(String captureId, boolean debug) throws IOException {
		CapturesRefundRequest request = new CapturesRefundRequest(captureId);
		request.prefer("return=representation");
		request.requestBody(buildRequestBody());
    	//3. Call PayPal to refund an capture
		HttpResponse<Refund> response = client().execute(request);
		if (debug) {
			System.out.println("Status Code: " + response.statusCode());
			System.out.println("Status: " + response.result().status());
			System.out.println("Refund Id: " + response.result().id());
			System.out.println("Links: ");
			for (LinkDescription link : response.result().links()) {
				System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
			}
			System.out.println("Full response body:");
			System.out.println(new JSONObject(new Json()
					.serialize(response.result())).toString(4));
		}
		return response;
	}

	// Creating a body for partial refund request.
	// For full refund, pass the empty body.
	//
	// @return OrderRequest request with empty body

	public RefundRequest buildRequestBody() {
		RefundRequest refundRequest = new RefundRequest();
//		Money money = new Money();
//		money.currencyCode("USD");
//		money.value("3.00");
//		refundRequest.amount(money);

		return refundRequest;
	}

	// This function initiates capture refund.
	// Replace Capture ID with a valid capture ID.
	//
	// @param args

	public static void main(String[] args) {
		try {
			new CaptureRefundOrder().refundOrder("28A56167MR518952X", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}