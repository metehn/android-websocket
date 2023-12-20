package com.metehan.android_websocket;

import android.util.Log;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketHandler extends WebSocketListener {

    private MainActivity context;

    public WebSocketHandler(MainActivity context) {
        this.context = context;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        // WebSocket bağlantısı başarıyla açıldığında yapılacak işlemler
        Log.i("Test123", "onOpen: ");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        // Gelen metin mesajını işleme
        Log.i("Test123", "onMessage: " + text);
        updateTextView("Received message: " + text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        // Gelen binary mesajını işleme
        Log.i("Test123", "onMessage: " + bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        // WebSocket bağlantısı kapatılmadan önce yapılacak işlemler
        Log.i("Test123", "onClosing: ");
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        // WebSocket bağlantısı kapatıldığında yapılacak işlemler
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        // WebSocket bağlantısı hatası
        Log.i("Test123", "onFailure: " + t.getLocalizedMessage());
    }

    public void updateTextView(String str){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.addToList(str);
                Log.i("Test123", "updateTextView: " + str);
            }
        });
    }

}
