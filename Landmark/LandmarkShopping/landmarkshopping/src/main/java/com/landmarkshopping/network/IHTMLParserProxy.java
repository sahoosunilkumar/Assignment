package com.landmarkshopping.network;

import com.landmarkshopping.network.model.Request;

/**
 * Responsibility : uses as proxy to give another layer of redirection so that in future we can replace the parser
 */
public interface IHTMLParserProxy {
    /**
     * This method will immediately dispatch the request.
     *
     * @param request This is the Rest request to be dispatched to jsoup
     */
    void execute(Request request);

    /**
     * This method will cancel all request(s) with particular Tag.
     *
     * @param requestId This is the Rest request's Tag
     */
    void cancel(int requestId);

    /**
     * This method will cancel all request(s).
     */
    void cancelAll();
}