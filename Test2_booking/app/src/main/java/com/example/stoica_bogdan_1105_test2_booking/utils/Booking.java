package com.example.stoica_bogdan_1105_test2_booking.utils;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "booking")
public class Booking {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long customerCode;
    private Date startDate;
    private String payingMethod;
    private float payedSum;

    @Ignore
    public Booking() {
    }

    @Ignore
    public Booking(long customerCode, Date startDate, String payingMethod, float payedSum) {
        if (String.valueOf(customerCode).length() == 10 && payedSum > 0) {
            this.customerCode = customerCode;
            this.startDate = startDate;
            this.payingMethod = payingMethod;
            this.payedSum = payedSum;
        }
    }

    public Booking(int id, long customerCode, Date startDate, String payingMethod, float payedSum) {
        this.id = id;
        this.customerCode = customerCode;
        this.startDate = startDate;
        this.payingMethod = payingMethod;
        this.payedSum = payedSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(long customerCode) {
        if (String.valueOf(customerCode).length() == 10) {
            this.customerCode = customerCode;
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getPayingMethod() {
        return payingMethod;
    }

    public void setPayingMethod(String payingMethod) {
        this.payingMethod = payingMethod;
    }

    public float getPayedSum() {
        return payedSum;
    }

    public void setPayedSum(float payedSum) {
        if (payedSum > 0) {
            this.payedSum = payedSum;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
