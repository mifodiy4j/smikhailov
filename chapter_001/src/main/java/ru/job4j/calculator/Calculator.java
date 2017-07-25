package ru.job4j.calculator;

/**
    * Calculate.
    *
    *@author smikhailov
    *@since 25.07.2017
    *@version 1
    */
public class Calculator {
    private double result;
    
    /**
        * Add.
        *@param
        *@return
        *@throws
        */
    public void add(double first, double second) {
        this.result = first + second;
    }   

    /**
        * Subtract.
        *@param
        *@return
        *@throws
        */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
        * Div.
        *@param
        *@return
        *@throws
        */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
        * Multiple.
        *@param
        *@return
        *@throws
        */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
        * GetResult.
        *@param
        *@return
        *@throws
        */
    public double getResult() {
        return this.result;
    }
}