package com.example.qiutt.demo.common;

import java.math.RoundingMode;

/**
 * @author qiutt
 * @description:no description
 * @create 2020-07-09 17:29
 */
public interface CalculateConstant {
	int NORMAL_SCALE = 6;
	int DEFAULT_SCALE = 2;
	int CHANGE_PRODUCT__SCALE = 2;
	int UNSUBSCRIBE_PRODUCT_SCALE = 2;
	int SCALE_1 = 1;
	int DEFAULT_PRICE_DISPLAY_SCALE = 2;
	RoundingMode DEFAULT_PRICE_DISPLAY_ROUDING_MODE = RoundingMode.HALF_UP;
	RoundingMode NORMAL_ROUDING_MODE = RoundingMode.HALF_UP;
	RoundingMode REFUND_ROUDING_MODE = RoundingMode.DOWN;
	RoundingMode DAY_CONSUME_ROUDING_MODE = RoundingMode.DOWN;
	RoundingMode BILL_SERVICE_TIMS_ROUDING_MODE = RoundingMode.HALF_UP;
	int DEFAULT_BALANCE_DISPLAY_SCALE = 2;
	RoundingMode DEFAULT_BALANCE_DISPLAY_ROUDING_MODE = RoundingMode.DOWN;
}
