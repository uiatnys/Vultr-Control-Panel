package com.wangzh.vultr.model.entity;

/**
 * Created by WangZH on 2017/4/24.
 */

public class AccountInfoDTO {


    /**
     * balance : -7.94  余额
     * pending_charges : 2.20  预计费用
     * last_payment_date : 2017-03-31 09:43:40   上次支付时间
     * last_payment_amount : -1.00   上次支付金额
     */

    private String balance;
    private String pending_charges;
    private String last_payment_date;
    private String last_payment_amount;

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPending_charges() {
        return pending_charges;
    }

    public void setPending_charges(String pending_charges) {
        this.pending_charges = pending_charges;
    }

    public String getLast_payment_date() {
        return last_payment_date;
    }

    public void setLast_payment_date(String last_payment_date) {
        this.last_payment_date = last_payment_date;
    }

    public String getLast_payment_amount() {
        return last_payment_amount;
    }

    public void setLast_payment_amount(String last_payment_amount) {
        this.last_payment_amount = last_payment_amount;
    }
}
