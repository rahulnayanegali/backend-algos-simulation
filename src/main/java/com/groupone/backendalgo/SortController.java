package com.groupone.backendalgo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Arrays;
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

    public SortController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[second];
        arr[second] = arr[first];
        arr[first] = temp;
    }

    @GetMapping("/cycleSort")
    @CrossOrigin(origins = "http://localhost:3000")
    public SseEmitter streamSseMvc(@RequestParam("arr") String arrStr) {
        int[] arr = Arrays.stream(arrStr.split(",")).mapToInt(Integer::parseInt).toArray();
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                int i = 0;
                int[] newArr = {};
                while (i < arr.length) {
                    int correct = arr[i] - 1;
                    if (arr[i] != arr[correct]) {
                        swap(arr, i, correct);
                        newArr = Arrays.copyOf(arr, arr.length);
                        SseEmitter.SseEventBuilder event = SseEmitter.event().data(newArr);
                        emitter.send(event);
                        Thread.sleep(1000);
                        System.out.println("Sent event: " + Arrays.toString(newArr));
                    } else {
                        i++;
                    }
                    System.out.println("Current iteration: " + i);
                }
                SseEmitter.SseEventBuilder completed = SseEmitter.event().data("completed");
                emitter.send(completed);
                System.out.println("Sorting completed.");
            } catch (Exception ex) {
                emitter.completeWithError(ex);
                System.err.println("Error occurred: " + ex.getMessage());
            }
        });
        return emitter;
    }

}
