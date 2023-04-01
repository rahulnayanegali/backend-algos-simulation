package com.groupone.backendalgo;

import java.math.BigDecimal;

public class Stock {
    private final BigDecimal price;

    Stock(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

