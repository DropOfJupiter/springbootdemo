package com.example.qiutt.demo.javadoc;

/**
 * 错误消息
 * 
 * @author admin
 * 
 */
public interface ErrorCodes {

	/**
	 * 前缀
	 */
	String PREFIX = "kpy_finance_";

	/**
	 * 分页数错误
	 */
	String INVALID_PAGE_NUMBER_ERROR = PREFIX + "InvalidPageNumber.Error";
	/**
	 * 每页数量错误
	 */
	String INVALID_PAGE_SIZE_ERROR = PREFIX + "InvalidPageSize.Error";

	/**
	 * 无效的时间段
	 */
	String INVALID_TIME_SEGMENT = PREFIX + "InvalidTimeSegment.Error";

	/**
	 * 更新数量错误
	 */
	String INVALID_UPDATE_COUNT = PREFIX + "InvalidUpdateCount.Error";
	
	/**
	 * 账单金额错误
	 */
	String INVALID_BILL_PRICE_ERROR =PREFIX + "InvalidBillPrice.Error";
	/**
	 * 后付费账单的产品配置不能为空
	 */
	String INVALID_PRODUCT_CONFIG_INFO_IS_NULL =PREFIX + "InvalidPostPaidProductConfigInfo.isNull";
	/**
	 * 后付费账单的产品配置错误
	 */
	String INVALID_PRODUCT_CONFIG_INFO_ERROR =PREFIX + "InvalidPostPaidProductConfigInfo.Error";
	
	String INVALID_POSTPAID_FLOW_BILL_EXIST_ERROR = PREFIX + "InvalidPostPaidFlowBillExist.Error";
	/**
	 * 订单UUID不能为空
	 */
	String INVALID_ORDER_UUID_IS_NULL = PREFIX + "InvalidOrderUUID.isNull";
	/**
	 * 支付时间不能为空
	 */
	String INVALID_PAY_TIME_IS_NULL =PREFIX + "InvalidPayTime.isNull";
	/**
	 * 付费类型不能为空
	 */
	String INVALID_CONSUMPT_TRAN_TYPE_IS_NULL =PREFIX + "InvalidConsumptTranType.isNull";
	/**
	 * 付费类型不正确
	 */
	String INVALID_CONSUMPT_TRAN_TYPE_ERROR = PREFIX + "InvalidConsumptTranType.Error";
	/**
	 * 账单时间不能为空
	 */
	String INVALID_BILL_TIME_IS_NULL =PREFIX + "InvalidBillTime.isNull";
	/**
	 * 账单信息为空
	 */
	String INVALID_BILL_INFO_IS_NULL = PREFIX + "InvalidBillInfo.isNull";
	/**
	 * 外部账单已存在
	 */
	String INVALID_EXTERNAL_BILL_IS_EXIST= PREFIX + "InvalidExternalBill.isExist";
	
	/**账单为空*/
	String INVALID_BILL_IS_NULL = PREFIX + "InvalidBill.IsNull";
	
	/**代金券不存在*/
	String INVALID_COUPON_ID_NOT_EXIST = PREFIX + "InvalidCouponId.NotExist";
	
	/**请求支付的价格不正确*/
	String INVALID_BILL_PAYPRICE_REQUEST_ERROR = PREFIX + "BillPayPriceRequest.Error";
	
	/**支付宝支付金额错误 */
	String INVALID_ALIPAY_AMOUNT_ERROR = PREFIX + "InvalidAlipayAmount.Error";
	
	/**支付宝结果回调页面为空 */
	String INVALID_CONSUMPT_RESULT_CALLBACK_IS_NULL = PREFIX + "InvalidConsumptResultCallback.IsNull";
	
	/**支付宝支付方式错误 */
	String INVALID_ALIPAY_METHOD_ERROR = PREFIX + "InvalidAlipayMethod.Error";
	
	/**当前用户余额不足以支付账单错误 */
	String INVALID_BALANCE_INSUFFICIENT = PREFIX + "BalanceInsufficient.Error";
	
	/**账单已支付，无需支付**/
	String INVALID_BILL_DONOT_NEED_PAID_ERROR = PREFIX + "BillDonotNeedPaid.Error";
	
	/**账单支付失败 */
	String INVALID_BILL_PAY_ERROR = PREFIX + "BillPay.Failed";
	
	/**账单补偿记录不存在 */
	String INVALID_FINANCE_BILL_COMPENSATE_LOG_NOT_EXIST = PREFIX + "InvalidFinanceBillCompensateLog.NotExist";
	
	/**账单不存在*/
	String INVALID_BILL_NOT_EXIST = PREFIX + "InvalidBill.NotExist";
	
	/**账单状态不正确**/
	String INVALID_BILL_STATUS_ERROR = PREFIX + "InvalidBillStatus.Error";
	
	/**账单补偿金额不正确**/
	String INVALID_BILL_COMPENSATE_PRICE_ERROR = PREFIX + "InvalidBillCompensatePrice.Error";
	
	/**补偿账单记录的补偿状态不正确**/
	String INVALID_COMPENSATE_LOG_STATUS_ERROR = PREFIX + "InvalidCompensateLogStatus.Error";
	
	/**待补偿的账单支付中，不能进行补偿**/
	String INVALID_BILL_IS_PAYING = PREFIX + "InvalidBillIsPaying.Error";
	/**
	 * 待导出的数据为空为空
	 */
	String INVALID_EXPORT_BILL_LIST_IS_EXIST= PREFIX + "InvalidExternalBill.isExist";
	/**数据导出中，请勿重复请求**/
	String INVALID_EXPORT_STATUS_EXECUTING = PREFIX+"ExportStatus.executing";
	/**待导出的账单数据为空**/
	String INVALID_EXPORT_BILL_DATA_IS_NULL = PREFIX+"ExportBillData.isNull";
	/**待导出的账单数据错误**/
	String INVALID_EXPORT_BILL_DATA_ERROR = PREFIX+"ExportBillData.error";
	/**待导出的账单对应用户数据为空**/
	String INVALID_EXPORT_BILL_USER_DATA_IS_NULL = PREFIX+"ExportBillUserData.isNull";
	/**待导出的账单对应资源数据为空**/
	String INVALID_EXPORT_BILL_RESOURCE_DATA_IS_NULL = PREFIX+"ExportBillResourceData.isNull";
	/**数据导出中，请勿重复请求**/
	String INVALID_EXPORT_ERROR = PREFIX+"Export.error";
	/**账期异常**/
	String INVALID_EXPORT_BILLDATE_ERROR = PREFIX+"BillDate.Error";
	/**用户不存在**/
	String INVALID_EXPORT_USER_NOT_EXIST = PREFIX+"User.NotExist";
	/**用户ID为空**/
	String INVALID_EXPORT_USERID_IS_NULL = PREFIX+"UserId.isNull";
	/**
	 * 该资源存在未结清的账单
	 */
	String THE_RESOURCE_HAS_UNCLEAR_BILL = PREFIX+"ResourceHasUnClearBill.Error";
	/**
	 * 订单或账单UUID不能为空
	 */
	String INVALID_ORDER_OR_BILL_UUID_IS_NULL = PREFIX + "InvalidOrderOrBillUUID.isNull";
	/**
	 * 没有可作废的账单
	 */
	String INVALID_NOT_MARKABLE_BILL_ERROR = PREFIX + "InvalidNotMarkableBill.error";
	/**
	 * 错误的账单退款原因
	 */
	String INVALID_BILL_REFUND_REASON_ERROR = PREFIX+"InvalidBillRefundReason.Error";
	/**
	 * 没有可退款账单
	 */
	String INVALID_NOT_REFUNDABLE_BILL_ERROR = PREFIX+"InvalidNotRefundableBill.Error";
	/**
	 * 调账金额不等
	 */
	String INVALID_ADJUST_AMOUNT_ERROR = PREFIX+"InvalidAdjustAmount.Error";
	/**
	 * 存在减免记录
	 */
	String INVALID_HAS_ADJUST_BILL_ERROR = PREFIX+"InvalidHasAdjustBill.Error";
	/**
	 * idc账单金额错误
	 */
	String INVALID_IDC_AMOUNT_ERROR = PREFIX+"InvalidIdcAmount.Error";
	/**
	 * idc账单无需追加
	 */
	String INVALID_IDC_Bill_ERROR = PREFIX+"InvalidIdcBill.Error";
	/**
	 * 资源层账单总量错误
	 */
	String INVALID_BILL_CREATE_TOTAL_ERROR = PREFIX+"InvalidBillCreateTotal.Error";
	/**
	 * 调账退款失败
	 */
	String INVALID_ADJUSTMENT_REFUND_ERROR=PREFIX+"InvalidAdjustmentRefund.Error";

	/**用户收支明细不存在**/
	String INVALID_USER_FLOW_LOG_NOT_EXIST = PREFIX+"UserFlowLog.NotExist";

	/**账单已确认**/
	String INVALID_BILL_CONFIRMED_ERROR = PREFIX+"InvalidBillConfirmed.Error";

	/**未知的枚举**/
	String INVALID_UNKNOWN_ENUM_ERROR = PREFIX+"UnknownEnum.Error";

	/**未知的产品字典**/
	String INVALID_UNKNOWN_PRODUCT_DICTIONARY_ERROR = PREFIX+"UnknownProductDictionary.Error";

	/**线下账单确认错误**/
	String INVALID_OFFLINE_BILL_CONFIRM_ERROR = PREFIX+"InvalidOfflineBillConfirm.Error";
}
