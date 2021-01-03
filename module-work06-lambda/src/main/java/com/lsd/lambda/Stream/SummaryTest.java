package com.lsd.lambda.Stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public class SummaryTest {


    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //TODO
        /**
         * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
         * (2) 交易员都在哪些不同的ۡ市工作过？
         * (3) 查找所有来自于ғೃ的交易员，并按ހ名排序。
         * (4) 返回所有交易员的ހ名字符串，按字母顺序排序。
         * (5) 有没有交易员是在ዚ兰工作的？
         * (6) 打印生活在ғೃ的交易员的所有交易额。
         * (7) 所有交易中，最高的交易额是多少？
         * (8) 找到交易额最小的交易。
         */
    }


    public static class Trader {
        private final String name;
        private final String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }
    }

    public static class Transaction {
        private final Trader trader;
        private final int year;
        private final int value;
        public Transaction(Trader trader, int year, int value){
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }
    }
}
