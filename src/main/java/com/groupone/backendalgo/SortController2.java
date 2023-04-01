//package com.groupone.backendalgo;
//
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.event.EventListener;
//import org.springframework.http.MediaType;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.CopyOnWriteArraySet;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//
//@RestController
//public class SortController2 {
//    private final Set<SseEmitter> clients = new CopyOnWriteArraySet<>();
//
//    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//
//    private final ApplicationEventPublisher publisher;
//
//    public SortController2(ApplicationEventPublisher publisher) {
//        this.publisher = publisher;
//    }
//
//    void cyclicSortKunal(int[] arr) {
//        int i = 0;
//        int[] newArr = {};
//        while (i < arr.length) {
//            int correct = arr[i] - 1;
//            if (arr[i] != arr[correct]) {
//                swap(arr, i, correct);
//            } else {
//                i++;
//            }
//            newArr = Arrays.copyOf(arr, arr.length);
//            publisher.publishEvent(newArr);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                // handle the exception if necessary
//            }
//        }
//    }
//
//    static void swap(int[] arr, int first, int second) {
//        int temp = arr[second];
//        arr[second] = arr[first];
//        arr[first] = temp;
//    }
//
//    @GetMapping("/test-algo")
//    public SseEmitter SortingStream() {
//        SseEmitter sseEmitter = new SseEmitter();
//        clients.add(sseEmitter);
//        sseEmitter.onCompletion(() -> clients.remove(sseEmitter));
//        sseEmitter.onError(throwable -> clients.remove(sseEmitter));
//
//        int[] arr = {3,5,2,6,7,1,4,};
//        executor.execute(() -> cyclicSortKunal(arr));
//
//        return sseEmitter;
//    }
//
//    @Async
//    @EventListener
//    public void modifiedArrayHandler(int[] arr) {
//        List<SseEmitter> errorEmitters = new ArrayList<>();
//        System.out.println("publishing");
//        System.out.println(Arrays.toString(arr));
//        for (SseEmitter emitter : clients) {
//            try {
//                emitter.send(arr, MediaType.APPLICATION_JSON);
//            } catch (Exception e) {
//                errorEmitters.add(emitter);
//            }
//        }
//
//        errorEmitters.forEach(clients::remove);
//    }
//}
