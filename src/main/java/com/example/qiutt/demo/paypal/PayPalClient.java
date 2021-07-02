package com.example.qiutt.demo.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

/**
 * @author qiutt
 * @description:no description
 * @create 2021-06-07 17:18
 */
public class PayPalClient {

	private static final String SANDBOX_CLIENT_ID="AQbIawTIDBarD8Txa-9zDtSzRRPktWbZ041zPGtoAgZqv9-uMZiVeU0tn803vxCHBMKAAZMVpqwY9cAS";
	private static final String SANDBOX_CLIENT_SECRET="EBRg7YpSKYbyrWHZuSJkrWumilv1UN2kSHuFCia_lRohGP87Bdaqjq2sI9uPeFGC_ozt7UVsko0EuoIC";
	/**
	 *Set up the PayPal Java SDK environment with PayPal access credentials.
	 *This sample uses SandboxEnvironment. In production, use LiveEnvironment.
	 */
	private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			SANDBOX_CLIENT_ID,
			SANDBOX_CLIENT_SECRET);

	/**
	 *PayPal HTTP client instance with environment that has access
	 *credentials context. Use to invoke PayPal APIs.
	 */
	PayPalHttpClient client = new PayPalHttpClient(environment);

	/**
	 *Method to get client object
	 *
	 *@return PayPalHttpClient client
	 */
	public PayPalHttpClient client() {
		return this.client;
	}
}
