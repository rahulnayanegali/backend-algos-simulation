package com.groupone.backendalgo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class StockPriceChanger {
    private final ApplicationEventPublisher publisher; //1

    private final Random random = new Random(); //2

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(); //3

    public StockPriceChanger(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
//        this.executor.schedule(this::changePrice, 10000, MILLISECONDS); //4
    }

    private void changePrice() {
//        BigDecimal price = BigDecimal.valueOf(random.nextGaussian()); //5
        int[] arr = {1,2,3,4};
        for (int i = 0; i < 6; i++) {
        publisher.publishEvent(arr); //6
            System.out.println("Change Price");
        }
//        executor.schedule(this::changePrice, random.nextInt(1000), MILLISECONDS); //7
    }
}
