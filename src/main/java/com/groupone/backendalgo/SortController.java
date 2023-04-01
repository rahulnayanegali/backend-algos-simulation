package com.groupone.backendalgo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RestController
public class SortController {
    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private final ApplicationEventPublisher publisher;

    private int count = 0;

    public SortController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    void cyclicSortKunal(int[] arr) {
        int i = 0;
        int[] newArr = {};
        int expectedEvents = arr.length;
        int eventsPublished = 0;
        while (i < arr.length) {
            int correct = arr[i] - 1;
            if (arr[i] != arr[correct]) {
                swap(arr, i, correct);
                newArr = Arrays.copyOf(arr, arr.length);
                publisher.publishEvent(newArr);
                eventsPublished++;
            } else {
                i++;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // handle the exception if necessary
            }
        }
//        if (eventsPublished == expectedEvents) {
//            // all events have been published, complete the SseEmitter
//            for (SseEmitter emitter : clients) {
//                emitter.complete();
//
//            }
//        }
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[second];
        arr[second] = arr[first];
        arr[first] = temp;
    }

    @GetMapping("/test-algo")
    public SseEmitter streamSseMvc() {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        int[] arr = {3,5,2,6,7,1,4,};
        sseMvcExecutor.execute(() -> {
            try {
                int i = 0;
                int[] newArr = {};
                int expectedEvents = arr.length;
                int eventsPublished = 0;
                while (i < arr.length) {
                    int correct = arr[i] - 1;
                    if (arr[i] != arr[correct]) {
                        swap(arr, i, correct);
                        newArr = Arrays.copyOf(arr, arr.length);
                        SseEmitter.SseEventBuilder event = SseEmitter.event()
                                .data(newArr);

                        emitter.send(event);
                        Thread.sleep(1000);
                    } else {
                        i++;
                    }
                }
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }
//    @GetMapping("/test-algo") //2.2
//    public SseEmitter SortingStream() {
//        SseEmitter sseEmitter = new SseEmitter();
//        clients.add(sseEmitter);
////        sseEmitter.onTimeout(() -> clients.remove(sseEmitter));
//        sseEmitter.onError(throwable -> clients.remove(sseEmitter));
//        sseEmitter.onTimeout(() -> {
//            sseEmitter.complete();
//            this.clients.remove(sseEmitter);
//        });
//        sseEmitter.onCompletion(() -> {
//            this.clients.remove(sseEmitter);
//        });
//
//        int[] arr = {3,5,2,6,7,1,4,};
////        for (int i = 0; i < arr.length; i++) {
////            publisher.publishEvent(arr);
////        }
//        cyclicSortKunal(arr);
//        return sseEmitter;
//    }

//    @Async
//    @EventListener
//    public void modifiedArrayHandler(int[] arr) { //2.3
//        List<SseEmitter> errorEmitters = new ArrayList<>();
//        count ++;
//        System.out.println("publisisng"+" "+count);
//
//        System.out.println(Arrays.toString(arr));
//
//        for (SseEmitter emitter : clients) {
//            try {
//                emitter.send(arr, MediaType.APPLICATION_JSON);
//                emitter.complete();
//            } catch (Exception e) {
//                errorEmitters.add(emitter);
//            }
//        }
//
//
//        errorEmitters.forEach(clients::remove);
//    }
}

