package com.example.qiutt.demo.stream;


import java.io.Serializable;
import java.math.BigDecimal;

public class PayTypeDetail implements Serializable {

    private static final long serialVersionUID = -1275660447344597546L;

    private ConsumptPayType consumptPayType;

    private BigDecimal payAmount;

    public PayTypeDetail(){

    }
    public PayTypeDetail(ConsumptPayType consumptPayType, BigDecimal payAmount){
        this.consumptPayType=consumptPayType;
        this.payAmount=payAmount;

    }
    public ConsumptPayType getConsumptPayType() {
        return consumptPayType;
    }

    public void setConsumptPayType(ConsumptPayType consumptPayType) {
        this.consumptPayType = consumptPayType;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
