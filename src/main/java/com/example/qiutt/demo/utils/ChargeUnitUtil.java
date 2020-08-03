package com.example.qiutt.demo.utils;

import com.example.qiutt.demo.enums.ChargeUnitEnum;

import java.math.BigDecimal;

public class ChargeUnitUtil {
	/** 时长 */
	private BigDecimal time;

	/** 计费单位 */
	private ChargeUnitEnum chargeUnit;



	public ChargeUnitUtil() {
		super();
	}

	public ChargeUnitUtil(BigDecimal time, ChargeUnitEnum chargeUnit) {
		super();
		this.time = time;
		this.chargeUnit = chargeUnit;
	}

	public BigDecimal getTime() {
		return time;
	}

	public void setTime(BigDecimal time) {
		this.time = time;
	}

	public ChargeUnitEnum getChargeUnit() {
		return chargeUnit;
	}

	public void setChargeUnit(ChargeUnitEnum chargeUnit) {
		this.chargeUnit = chargeUnit;
	}

	@Override
	public String toString() {
//		if (ChargeUnitEnum.DAY.equals(chargeUnit) && time != null) {
//			long day = time.longValueExact();
//			long hour = time.subtract(new BigDecimal(day)).multiply(new BigDecimal(24)).longValue();
//			return new StringBuilder().append(day).append("天").append(hour).append("时").toString();
//
//		} else if (ChargeUnitEnum.HOUR.equals(chargeUnit) && time != null) {
//			long hour = time.longValueExact();
//			long minute = time.subtract(new BigDecimal(hour)).multiply(new BigDecimal(60)).longValue();
//			return new StringBuilder().append(hour).append("时").append(minute).append("分").toString();
//
//		} else if (ChargeUnitEnum.MINUTE.equals(chargeUnit) && time != null) {
//			long minute = time.longValueExact();
//			return new StringBuilder().append(minute).append("分").toString();
//
//		} else {
//			StringBuilder timeBuilder = new StringBuilder();
//			if (time != null) {
//				BigDecimal newTime = time.setScale(2, BigDecimal.ROUND_HALF_UP);
//				timeBuilder.append(newTime);
//			}
//			if (chargeUnit != null) {
//				timeBuilder.append(chargeUnit.getLabel());
//			}
//			return timeBuilder.toString();
//		}
		if (ChargeUnitEnum.DAY.equals(chargeUnit) && time != null) {
			long day = time.longValueExact();
			long hour = time.subtract(new BigDecimal(day)).multiply(new BigDecimal(24)).longValue();
			return I18nUtils.translationTime(day,ChargeUnitEnum.DAY).append(I18nUtils.translationTime(hour,ChargeUnitEnum.HOUR)).toString();

		} else if (ChargeUnitEnum.HOUR.equals(chargeUnit) && time != null) {
			long hour = time.longValueExact();
			long minute = time.subtract(new BigDecimal(hour)).multiply(new BigDecimal(60)).longValue();
			return I18nUtils.translationTime(hour,ChargeUnitEnum.HOUR).append(I18nUtils.translationTime(minute,ChargeUnitEnum.MINUTE)).toString();

		} else if (ChargeUnitEnum.MINUTE.equals(chargeUnit) && time != null) {
			long minute = time.longValueExact();
			return I18nUtils.translationTime(minute,ChargeUnitEnum.MINUTE).toString();

		} else {
			StringBuilder timeBuilder = new StringBuilder();
			if (time != null) {
				BigDecimal newTime = time.setScale(2, BigDecimal.ROUND_HALF_UP);
				timeBuilder.append(newTime);
			}
			if (chargeUnit != null) {
				timeBuilder.append(chargeUnit.getLabel());
			}
			return timeBuilder.toString();
		}
	}
}
