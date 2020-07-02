package com.h2;

import java.text.DecimalFormat;

/**
 * MortgageCalculator
 *
 */

public class MortgageCalculator {
    private long loanAmount;
    private int termInYears;
    private float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate) {
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    public static void main( String[] args) {
        long loanAmount = Long.parseLong(args[0]);
        int termInYears = Integer.parseInt(args[1]);
        float annualRate = Float.parseFloat(args[2]);

        MortgageCalculator calculator = new MortgageCalculator(loanAmount, termInYears, annualRate);
        calculator.calculateMonthlyPayment();

        System.out.println(calculator.toString());
    }

    /**
     * 
     * This is the formula:
    * M = P(r(1+r)^n/(((1+r)^n)-1)
    * M is the calculated monthly mortgage payment,  
    * P is the principal amount, represented by loanAmount in our class,  
    * r is the monthly interest rate, which you can find by calling getMonthlyInterestRate().  
    * n is the total number of payments which you can find by calling getNumberOfPayments().
     */
    public void calculateMonthlyPayment() {
        long P = this.loanAmount;
        float r = this.getMonthlyInterestRate();
        int n = this.getNumberOfPayments();

        double M = P * (((r * Math.pow((1 + r), n))) / ((Math.pow((1 + r), n)) - 1));
        this.monthlyPayment = M;
    }

    public String toString() {
        /**
         *  
         * if a digit is 0 in place where # occurs, do not show 0. When the digit is 0
         * in place where 0 occurs, show 0, don't hide it.
         * 
         * */
        DecimalFormat df = new DecimalFormat("####0.00");
        return "monthlyPayment: " + df.format(monthlyPayment);
    }

    private int getNumberOfPayments() {
        return this.termInYears * 12;
    }

    private float getMonthlyInterestRate() {
        //float interestRate = (this.annualRate / 100) / 12;
        return (this.annualRate / 100) / 12;
    }

}