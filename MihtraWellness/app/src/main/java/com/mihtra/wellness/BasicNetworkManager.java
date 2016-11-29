package com.mihtra.wellness;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MIHTRA WELLNESS PVT on 22-10-2016.
 */

public class BasicNetworkManager {
    private static final String TAG = "BasicNetworkManager";

    private static final String URL = "http://mihtra.com/restservice/api/users";

    private static final String HEADER_KEY="MIHTRA-SERVICE-KEY";
    private static final String HEADER_VALUE="1a2c9383dc806ac5867add0d86891c1f3fd28bd8";

    public static void executeRequest(final HttpConnectionListener listener, final int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                // Only display the first 500 characters of the retrieved
                // web page content.
                int len = 500;

                try {
                    URL url = new URL(URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput (true);
                    conn.setUseCaches (false);
                    conn.setRequestProperty(HEADER_KEY, HEADER_VALUE);
                    conn.setRequestProperty("Content-Type","application/json");
                    conn.setRequestProperty("page",""+page);
                    // Starts the query
                    conn.connect();
                    int response = conn.getResponseCode();
                    Log.d(TAG, "The response is: " + response);
                    is = conn.getInputStream();

                    // Convert the InputStream into a string
                    UserListResponse userList = parse(is);
                    listener.onSuccess(userList);
                    // Makes sure that the InputStream is closed after the app is
                    // finished using it.
                } catch (Exception ex){
                    listener.onFailure(ex.getMessage());
                }

                finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

    private static UserListResponse parse(InputStream stream) throws IOException, UnsupportedEncodingException {

        Reader reader = new InputStreamReader(stream, "UTF-8");
        return new Gson().fromJson(reader, UserListResponse.class);

    }
}

