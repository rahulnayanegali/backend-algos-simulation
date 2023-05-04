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

    static int getMaxIndex(int[] arr, int start, int end) {
  int max = start;
  for (int i = start; i <= end; i++) {
    if (arr[max] < arr[i]) {
      max = i;
    }
  }
  return max;
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

    @GetMapping("/insertionSort")
    @CrossOrigin(origins = "http://localhost:3000")
    public SseEmitter streamSseMvc2(@RequestParam("arr") String arrStr) {
        int[] arr = Arrays.stream(arrStr.split(",")).mapToInt(Integer::parseInt).toArray();
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                int[] newArr = {};
                for (int i = 0; i < arr.length - 1; i++) {
                    for (int j = i + 1; j > 0; j--) {
                        if (arr[j] < arr[j - 1]) {
                            swap(arr, j, j - 1);
                            newArr = Arrays.copyOf(arr, arr.length);
                            SseEmitter.SseEventBuilder event = SseEmitter.event().data(newArr);
                            emitter.send(event);
                            Thread.sleep(1000);
                            System.out.println("Sent event: " + Arrays.toString(newArr));
                        } else {
                            break;
                        }
                    }
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
    @GetMapping("/selectionSort")
    @CrossOrigin(origins = "http://localhost:3000")
    public SseEmitter streamSseMvc3(@RequestParam("arr") String arrStr) {
        int[] arr = Arrays.stream(arrStr.split(",")).mapToInt(Integer::parseInt).toArray();
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
        try {
          int[] newArr = {};
          for (int i = 0; i < arr.length; i++) {
            //            find the maximum element in the arr and swap it
            int last = arr.length - i - 1;
            int maxIndex = getMaxIndex(arr, 0, last);
            swap(arr, maxIndex, last);
            newArr = Arrays.copyOf(arr, arr.length);
            SseEmitter.SseEventBuilder event = SseEmitter.event().data(newArr);
            emitter.send(event);
            Thread.sleep(1000);
            System.out.println("Sent event: " + Arrays.toString(newArr));
          }
          SseEmitter.SseEventBuilder completed = SseEmitter.event().data("completed");
          emitter.send(completed);
          System.out.println("Sorting completed.");
        }
        catch (Exception ex) {
          emitter.completeWithError(ex);
          System.err.println("Error occurred: " + ex.getMessage());
        }
  });
  return emitter;
}

    @GetMapping("/bubbleSort")
    @CrossOrigin(origins = "http://localhost:3000")
    public SseEmitter streamSseMvc4(@RequestParam("arr") String arrStr) {
        int[] arr = Arrays.stream(arrStr.split(",")).mapToInt(Integer::parseInt).toArray();
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                int[] newArr = {};
                boolean swapped;
                // run n-1 times
                for (int i = 0; i < arr.length; i++) {
                    // for each step, max item will come at the last respective index
                    swapped = false;
                    for (int j = 1; j< arr.length-i; j++) {
                        // swap if the current element is smaller than prev
                        if (arr[j] < arr[j-1]) {
                            // swap
//                            swap(arr, arr[j], arr[j-1]);
                            int temp = arr[j];
                            arr[j] = arr[j-1];
                            arr[j-1] = temp;
                            swapped = true;
                            newArr = Arrays.copyOf(arr, arr.length);
                            SseEmitter.SseEventBuilder event = SseEmitter.event().data(newArr);
                            emitter.send(event);
                            Thread.sleep(1000);
                            System.out.println("Sent event: " + Arrays.toString(newArr));
                        }
                    }
                    if (!swapped) {
                        break;
                    }
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
