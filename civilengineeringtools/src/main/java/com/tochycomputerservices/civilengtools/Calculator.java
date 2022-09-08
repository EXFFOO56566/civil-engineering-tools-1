package com.tochycomputerservices.civilengtools;
/*
 *  Author: Eze-Odikwa Tochukwu
 *  Last date modified: 05-03-2022
 *  (C), All rights reserved, Tochy computer services 2022
 *
 * */
class Calculator {

    public double addition(double num1, double num2){
        return num1 + num2;
    }

    public double subtraction(double num1, double num2){
        return num1 - num2;
    }

    public double multiplication(double num1, double num2){
        return num1 * num2;
    }

    public double division(double num1, double num2){
        return num1 / num2;
    }

    public double modulus(double num1, double num2){
        return num1 % num2;
    }

    public double squareRoot(double num1, double num2){
        return (num1 * Math.sqrt(num2));
    }
    
    public double power(double num1, double num2){
        return Math.pow(num1, num2);
    }
}
