package com.example.stoica_bogdan_1105.utils;

import androidx.annotation.NonNull;

import java.util.Date;

public class Vacancy {
    private String vacancyTitle;
    private int salary;
    private int noOfApplicants;
    private Date vacancyStartDate;

    public Vacancy() {}

    public Vacancy(String vacancyTitle, int salary, int noOfApplicants, Date vacancyStartDate) {
        if (vacancyTitle != null && !vacancyTitle.equals("")) {
            this.vacancyTitle = vacancyTitle;
        }
        this.salary = salary;
        if (noOfApplicants > 0) {
            this.noOfApplicants = noOfApplicants;
        }
        this.vacancyStartDate = vacancyStartDate;
    }

    public String getVacancyTitle() {
        return vacancyTitle;
    }

    public void setVacancyTitle(String vacancyTitle) {
        if (vacancyTitle != null && !vacancyTitle.equals("")) {
            this.vacancyTitle = vacancyTitle;
        }
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getNoOfApplicants() {
        return noOfApplicants;
    }

    public void setNoOfApplicants(int noOfApplicants) {
        if (noOfApplicants > 0) {
            this.noOfApplicants = noOfApplicants;
        }
    }

    public Date getVacancyStartDate() {
        return vacancyStartDate;
    }

    public void setVacancyStartDate(Date vacancyStartDate) {
        this.vacancyStartDate = vacancyStartDate;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
