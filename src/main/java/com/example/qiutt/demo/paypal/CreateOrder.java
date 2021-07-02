package com.example.qiutt.demo.paypal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.braintreepayments.http.HttpResponse;
import com.paypal.orders.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
 * https://developer.paypal.com/docs/business/checkout/server-side-api-calls/create-order/
 *1. Import the PayPal SDK client that was created in `Set up Server-Side SDK`.
 *This step extends the SDK client. It's not mandatory to extend the client, you can also inject it.
 * https://developer.paypal.com/docs/api/orders/v2/
 */
@Slf4j
public class CreateOrder extends PayPalClient {

	//2. Set up your server to receive a call from the client
	/**
	 *Method to create order
	 *
	 *@param debug true = print response data
	 *@return HttpResponse<Order> response received from API
	 *@throws IOException Exceptions from API if any
	 */
	public HttpResponse<Order> createOrder(boolean debug) throws IOException {
		OrdersCreateRequest request = new OrdersCreateRequest();
		request.prefer("return=representation");
		request.requestBody(buildRequestBodyOnlyRequired());
		String payHref;
		//3. Call PayPal to set up a transaction
		HttpResponse<Order> response = client().execute(request);
		log.info("调用PayPal的创建订单接口，返回结果{}", JSON.toJSONString(response));
		if (debug) {
			if (response.statusCode() == 201) {
				System.out.println("Status Code: " + response.statusCode());
				System.out.println("Status: " + response.result().status());
				System.out.println("Order ID: " + response.result().id());
				System.out.println("Intent: " + response.result().intent());
				System.out.println("Links: ");
				for (LinkDescription link : response.result().links()) {
					System.out.println("\t" + link.rel() + ": " + link.href() + "\tCall Type: " + link.method());
					if (link.rel().equals("approve")) {
						payHref = link.href();
					}
				}
				System.out.println("Total Amount: " + response.result().purchaseUnits().get(0).amount().currencyCode()
						+ " " + response.result().purchaseUnits().get(0).amount().value());
			}
		}
		return response;
	}

	/**
	 *Method to generate sample create order body with CAPTURE intent
	 *
	 *@return OrderRequest with created order request
	 */
	private OrderRequest buildRequestBodyOnlyRequired() {
		OrderRequest orderRequest = new OrderRequest();
		//The merchant intends to capture payment immediately after the customer makes a payment.
		orderRequest.intent("CAPTURE");

		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
		PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
				.amount(new AmountWithBreakdown().currencyCode("USD").value("3.00"));
		purchaseUnitRequests.add(purchaseUnitRequest);
		orderRequest.purchaseUnits(purchaseUnitRequests);

		//If you are not using the PayPal JavaScript SDK to initiate PayPal Checkout (in context) ensure that you include application_context.return_url is specified or you will get "We're sorry, Things don't appear to be working at the moment" after the payer approves the payment.
//		ApplicationContext applicationContext = new ApplicationContext().returnUrl("").userAction("PAY_NOW");
//		orderRequest.applicationContext(applicationContext);

		return orderRequest;
	}

	/**
	 *Method to generate sample create order body with CAPTURE intent
	 *
	 *@return OrderRequest with created order request
	 */
	private OrderRequest buildRequestBody() {
		OrderRequest orderRequest = new OrderRequest();
		//The merchant intends to capture payment immediately after the customer makes a payment.
		orderRequest.intent("CAPTURE");

		ApplicationContext applicationContext = new ApplicationContext().brandName("EXAMPLE INC").landingPage("BILLING")
				.shippingPreference("SET_PROVIDED_ADDRESS");
		orderRequest.applicationContext(applicationContext);

		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
		PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().referenceId("PUHF")
				.description("Sporting Goods").customId("CUST-HighFashions").softDescriptor("HighFashions")
				.amount(new AmountWithBreakdown().currencyCode("USD").value("230.00")
						.breakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("USD").value("180.00"))
								.shipping(new Money().currencyCode("USD").value("30.00"))
								.handling(new Money().currencyCode("USD").value("10.00"))
								.taxTotal(new Money().currencyCode("USD").value("20.00"))
								.shippingDiscount(new Money().currencyCode("USD").value("10.00"))))
				.items(new ArrayList<Item>() {
					private static final long serialVersionUID = -7537141953314920367L;

					{
						add(new Item().name("T-shirt").description("Green XL").sku("sku01")
								.unitAmount(new Money().currencyCode("USD").value("90.00"))
								.tax(new Money().currencyCode("USD").value("10.00")).quantity("1")
								.category("PHYSICAL_GOODS"));
						add(new Item().name("Shoes").description("Running, Size 10.5").sku("sku02")
								.unitAmount(new Money().currencyCode("USD").value("45.00"))
								.tax(new Money().currencyCode("USD").value("5.00")).quantity("2")
								.category("PHYSICAL_GOODS"));
					}
				})
				.shipping(new ShippingDetails().name(new Name().fullName("John Doe"))
						.addressPortable(new AddressPortable().addressLine1("123 Townsend St").addressLine2("Floor 6")
								.adminArea2("San Francisco").adminArea1("CA").postalCode("94107").countryCode("US")));
		purchaseUnitRequests.add(purchaseUnitRequest);
		orderRequest.purchaseUnits(purchaseUnitRequests);
		return orderRequest;
	}

	/**
	 *This driver function invokes the createOrder function to create
	 *a sample order.
	 */
	public static void main(String args[]) {
		try {
			new CreateOrder().createOrder(true);
		} catch (com.braintreepayments.http.exceptions.HttpException e) {
			System.out.println(e.getLocalizedMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

//		OkHttpClient client = new OkHttpClient();
//
//		Request request = new Request.Builder()
//				.url("https://lookup.binlist.net/"+"4032031334635827")
//				.get()
//				.build();
//
//		try {
//			Call call=client.newCall(request);
//			Response response = client.newCall(request).execute();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}


	}
}