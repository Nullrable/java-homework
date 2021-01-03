package com.lsd.lambda.Function;

/**
 * @Author: nhsoft.lsd
 */
public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean validate(String msg) {
        return strategy.execute(msg);
    }
}
