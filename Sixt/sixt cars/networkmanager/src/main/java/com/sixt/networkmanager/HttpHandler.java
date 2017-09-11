package com.sixt.networkmanager;

import android.util.Log;


import com.sixt.networkmanager.beans.NetRequest;
import com.sixt.networkmanager.utils.NetworkConstants;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * This class is used to create the http connections.
 *
 *
 */
public final class HttpHandler implements Runnable {
    private static final String TAG = HttpHandler.class.getName();
    private boolean cancel;
    private boolean connectStatus = false;
    private NetRequest netRequest = null;
    private int requestID;

    /**
     * Constructor
     *
     * @param
     */
    public HttpHandler(NetRequest netRequest) {
        this.netRequest = netRequest;
        this.requestID = netRequest.getRequestID();
    }

    /**
     * Trust every server - dont check for any certificate
     */
    private void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");// ("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to create the http connection.
     */

    public void run() {
        OutputStream out = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        StringBuffer buffer = new StringBuffer();
        String responseData = null;
        InputStream arrayInputStream = null;
        cancel = false;
        int respCode = -1;

        try {
            Log.i(TAG, "URL :::" + netRequest.getUrl());
            url = new URL(netRequest.getUrl());

            if (!cancel) {
                do {
                    trustAllHosts();
                    // respCode = connect(requestID, requestData);
                    // Create httpConnection
                    HostnameVerifier hostnameVerifier = new HostnameVerifier() {

                        @Override
                        public boolean verify(String hostname,
                                              SSLSession session) {
                            return true;
                        }
                    };
                    httpURLConnection = (HttpURLConnection) url
                            .openConnection();
                    if (httpURLConnection instanceof HttpsURLConnection) {
                        ((HttpsURLConnection) httpURLConnection)
                                .setHostnameVerifier(hostnameVerifier);
                    }

                    if (httpURLConnection != null && !cancel) {
                        httpURLConnection.setRequestMethod(netRequest
                                .getHttpOperationType());
                        httpURLConnection.setAllowUserInteraction(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setDefaultUseCaches(false);
                        if("POST".equals(httpURLConnection.getRequestMethod())){
                            httpURLConnection.setDoInput(true);
                            httpURLConnection.setDoOutput(true);
                        }
                        writeData(httpURLConnection, out);

                        if (httpURLConnection != null && !cancel) {
                            respCode = httpURLConnection.getResponseCode();
                        }
                    }
                    if (cancel) {
                        break;
                    }
                } while (respCode == NetworkConstants.EOF && !cancel);
            }

            Log.e("Response Code outside", String.valueOf(respCode));

            byte[] data = null;
            if (!cancel) {
                // Log.e("Reading input stream", "Started Cancel:" + cancel);
                arrayInputStream = httpURLConnection.getInputStream();
                if (arrayInputStream != null) {
                    // bis = new
                    // BufferedInputStream(httpURLConnection.getInputStream());

                    int length = httpURLConnection.getContentLength();
                    int readLength = 0;
                    int readBytes = 0;
                    if (length < NetworkConstants.Limits.CHUNK_SIZE && length >
                            NetworkConstants.DEFAULT_VALUE_OF_INT) {
                        data = new byte[length];
                    } else {
                        data = new byte[NetworkConstants.Limits.CHUNK_SIZE];
                    }

                    String dataRead = null;
                    while ((readBytes = arrayInputStream.read(data)) !=
							NetworkConstants.EOF
                            && !cancel) {
                        dataRead = new String(data, NetworkConstants
								.DEFAULT_VALUE_OF_INT, readBytes);

                        buffer.append(dataRead);
                        readLength += readBytes;
                        data = null;
                        if ((length > NetworkConstants.DEFAULT_VALUE_OF_INT)
                                && (length - readLength) < NetworkConstants.Limits
								.CHUNK_SIZE) {
                            data = new byte[(length - readLength)];
                        } else {
                            data = new byte[NetworkConstants.Limits.CHUNK_SIZE];
                        }
                        if (cancel) {
                            break;
                        }
                    }
                    responseData = buffer.toString();
                }

            }

        } catch (Exception exp) {
            Log.e(TAG, "Exception in sending data network status :" + exp
					.getMessage());
            responseData = exp.getMessage();
            cancel = false;
        } finally {
            if (!cancel) {
                notifyController(cancel, requestID, responseData, respCode);
            }
            buffer = null;
            responseData = null;
            cleanup(arrayInputStream, httpURLConnection, out, baos, dos, bis, url);

        }
    }

    /**
     * writes data to url
     *
     * @param httpURLConnection
     * @param out
     * @throws Exception
     */

    private void writeData(HttpURLConnection httpURLConnection, OutputStream
			out) throws Exception {
        if (httpURLConnection != null) {
            // httpURLConnection.setRequestProperty("Connection",
            // "keep-alive");
            if (httpURLConnection != null)
                httpURLConnection.setRequestProperty(
                        "Content-Type", "application/json");
            httpURLConnection.setRequestProperty(
                    "Content-Encoding", "UTF-8");
            httpURLConnection
                    .setReadTimeout(NetworkConstants.Limits.TIME_OUT);
            httpURLConnection.setRequestProperty(
                    "ConnectionTimeout", ""
                            + NetworkConstants.Limits.TIME_OUT);

            // if (httpURLConnection != null)
            // httpURLConnection.setRequestProperty("Content-Length",
            // ""+ requestData.length);

        }

        if (httpURLConnection != null && !cancel) {
            // Log.e("httpURLConnection", "connect");
            connectStatus = false;
            httpURLConnection
                    .setReadTimeout(NetworkConstants.Limits.TIME_OUT);
            httpURLConnection.connect();

            connectStatus = true;
            // Log.e("httpURLConnection", "connect completed");
            if (httpURLConnection != null && cancel) {
                httpURLConnection.disconnect();
            }
        }

        if (httpURLConnection != null && !cancel
                && (netRequest.getRequestString() != null)) {
            // Log.e("httpURLConnection", "getOutputStream");
            out = httpURLConnection.getOutputStream();
            if ((out != null)
                    && (netRequest.getRequestString() != null) && !httpURLConnection.getRequestMethod().equals("GET")) {
                out.write(netRequest.getRequestString()
                        .getBytes());
                out.flush();
            }

        }
    }


    private void cleanup(InputStream arrayInputStream, HttpURLConnection httpURLConnection, OutputStream out, ByteArrayOutputStream baos, DataOutputStream dos, BufferedInputStream bis, URL url){
        try {
            // Log.e("finally", "Started Cancel:" + cancel);
            // Close the httpURLConnection.

            if (arrayInputStream != null) {
                arrayInputStream.close();
                arrayInputStream = null;
            }
            if (httpURLConnection != null && connectStatus) {
                httpURLConnection.disconnect();
            }
            httpURLConnection = null;
            if (out != null) {
                out.close();
                out = null;
            }

            // Close the data output stream.
            if (dos != null) {
                dos.close();
            }
            dos = null;
            // Close the byte array output stream.
            if (baos != null) {
                baos.close();
            }
            baos = null;
            // Close the url
            if (url != null) {
                url = null;
            }

            // Close the byte array output stream.
            if (bis != null) {
                bis.close();
            }
            bis = null;
            cancel = false;
        } catch (Exception exp) {

        }
    }
    /**
     * notifies controller
     *
     * @param cancel
     * @param requestID
     * @param response
     * @param responseCode
     */
    public void notifyController(boolean cancel, int requestID,
                                 String response, int responseCode) {
        if (!cancel) {
            if((this.netRequest != null) && (this.netRequest.getCallback() != null))
                this.netRequest.getCallback().onHttpResponseReceived(requestID,
                    response, responseCode);
        }
    }

    public void cancel(boolean flag) {
        cancel = true;
    }

}
