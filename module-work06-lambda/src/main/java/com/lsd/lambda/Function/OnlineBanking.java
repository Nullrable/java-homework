package com.lsd.lambda.Function;

import java.util.function.Consumer;

/**
 * @Author: nhsoft.lsd
 */
public abstract class OnlineBanking {

    public void processCustomer(int id, Consumer<String> makeCustomerHappy){
        String c = "i am customer";
        makeCustomerHappy.accept(c);
    }

}
