package com.metehan.android_websocket;

/*

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
//import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;


public class MainActivity extends AppCompatActivity {

    private WebSocketExample webSocketExample;
    private EditText usernameEditText, messageEditText;
    private Button connectButton, sendButton, disconnectButton;

    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //webSocketExample = new WebSocketExample();
        client = new OkHttpClient();

        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        WebSocket ws = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                webSocket.send("Hello, World!");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                System.out.println("Received Message: " + text);
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                System.out.println("Closed Connection: " + code + " " + reason);
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                t.printStackTrace();
            }
        });


        usernameEditText = findViewById(R.id.editTextUsername);
        messageEditText = findViewById(R.id.editTextMessage);

        connectButton = findViewById(R.id.buttonConnect);
        sendButton = findViewById(R.id.buttonSend);
        disconnectButton = findViewById(R.id.buttonDisconnect);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectWebSocket();
                //connectWebSocket2();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnectWebSocket();
            }
        });
    }

    private void connectWebSocket() {
        webSocketExample = new WebSocketExample();
    }
    private void connectWebSocket2(){

        Log.i("Test123", "connectWebSocket2");

        Request request = new Request.Builder()
                .url("ws://10.0.2.2:8080/chat")
                .build();

        PieSocketListener listener = new PieSocketListener();
        okhttp3.WebSocket ws = client.newWebSocket(request, listener);
    }

    private void connectWebSocket3() {
        Log.i("Test123", "connectWebSocket3");

        try {
            com.neovisionaries.ws.client.WebSocket ws = new WebSocketFactory()
                    .createSocket("wss://10.0.2.2:8080/chat")
                    .addListener(new SimpleWebSocketListener())
                    .connect();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Test123", e.getLocalizedMessage());
        }
    }

    private void sendMessage() {
        String username = usernameEditText.getText().toString().trim();
        String message = messageEditText.getText().toString().trim();

        if (!username.isEmpty() && !message.isEmpty()) {
            String fullMessage = username + ": " + message;
            webSocketExample.sendMessage(fullMessage);
        }
    }

    private void disconnectWebSocket() {
        webSocketExample.closeWebSocket();
    }
}

*/

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;


public class MainActivity extends AppCompatActivity {

    private EditText messageEditText;
    private Button sendButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> data = new ArrayList<>();

    private WebSocket webSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        listView = findViewById(R.id.listView);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, data);
        listView.setAdapter(adapter);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        connectWebSocket();
    }

    private void connectWebSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://10.0.2.2:8080/websocket-demo").build();
        Log.i("Test123", "connectWebSocket");
        WebSocketHandler socketListener = new WebSocketHandler(this);
        webSocket = client.newWebSocket(request, socketListener);
    }

    private void sendMessage() {
        Log.i("Test123", "sendMessage");
        String message = messageEditText.getText().toString();
        webSocket.send(message);
    }

    public void addToList(String str){
        data.add(str);
        adapter.notifyDataSetChanged();
    }
}
