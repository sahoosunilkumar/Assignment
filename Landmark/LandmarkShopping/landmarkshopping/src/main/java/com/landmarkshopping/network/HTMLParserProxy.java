package com.landmarkshopping.network;

import com.landmarkshopping.network.model.Request;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sunilkumarsahoo on 9/17/16.
 */

public class HTMLParserProxy implements IHTMLParserProxy {
    /**
     * Private constructor.
     */
    private static boolean isInitialized;
    private final int NTHREDS = 2;
    private BlockingQueue<Request> queue = null;
    private ExecutorService executor = null;


    private HTMLParserProxy() {
        if (isInitialized) {
            throw new IllegalStateException("Already initialized.");
        }
        queue = new LinkedBlockingQueue<>(10);
        executor = Executors.newFixedThreadPool(NTHREDS);
        isInitialized = true;
    }

    /**
     * @return Singleton instance
     */
    public static HTMLParserProxy getInstance() {
        return InnerClass.INSTANCE;
    }

    @Override
    public void execute(Request request) {
        try {
            queue.put(request);
            execute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cancel(int requestId) {
        if (queue != null) {
            Iterator<Request> requestItr = queue.iterator();
            Request request;
            while (requestItr.hasNext()) {
                request = requestItr.next();
                if (request.getRequestId() == requestId) {
                    request.setCancel(true);
                }
            }
        }
    }

    @Override
    public void cancelAll() {
        if (queue != null) {
            Iterator<Request> requestItr = queue.iterator();
            Request request;
            while (requestItr.hasNext()) {
                request = requestItr.next();
                request.setCancel(true);
            }
        }
    }

    private void execute() {
        if (executor.isShutdown() || executor.isTerminated()) {
            executor = Executors.newFixedThreadPool(NTHREDS);
        }
        while (!queue.isEmpty()) {
            Runnable worker = null;
            try {
                worker = new HTMLParserWorker(queue.take());
                executor.execute(worker);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
    }

    /**
     * Provides the lazy-loaded Singleton instance.
     */
    private static class InnerClass {
        private static final HTMLParserProxy INSTANCE = new HTMLParserProxy();
    }


}

class HTMLParserWorker implements Runnable {

    private Request request;

    public HTMLParserWorker(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        new JSoupParser().parse(request);
    }

}