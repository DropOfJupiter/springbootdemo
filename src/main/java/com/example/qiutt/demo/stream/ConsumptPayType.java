package com.example.qiutt.demo.stream;


/**
 * 消费交易支付方式
 * 
 * @author aiya
 * 
 */
public enum ConsumptPayType  {
	BALANCE_CONSUMPT("BalanceConsumpt", "账户余额支付",false),
    COUPON_CONSUMPT("CouponConsumpt", "代金券支付",false),
    DEBT_CONSUMPT("DebtConsumpt", "欠费支付",false),
	ALIPAY_CONSUMPT("AlipayConsumpt", "支付宝支付",true),
	TENPAY_CONSUMPT("TenpayConsumpt", "财付通支付",true),
    PAYPAL("PayPal", "PayPal支付",true),
	ALIPAY_OVERSEAS("AlipayOverseas", "支付宝国际版",true),
	WXPAY("WxPay", "微信支付",true),
	UNIONPAY("UnionPay", "银联支付",true)
	;
	
	private String code;
	private String label;
	/*
	* 是否是第三方支付
	*/
    private Boolean thirdParty;
	private ConsumptPayType(String code, String label, Boolean thirdParty ) {
		this.code = code;
		this.label = label;
		this.thirdParty=thirdParty;
	}

    public void setCode(String code) {
        this.code = code;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(Boolean thirdParty) {
        this.thirdParty = thirdParty;
    }

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

}
