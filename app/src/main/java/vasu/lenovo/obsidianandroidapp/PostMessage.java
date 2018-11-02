package vasu.lenovo.obsidianandroidapp;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostMessage {

    public String message;

    PostMessage(String message) {
        this.message = message;
        send();
    }

    public void send() {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    OkHttpClient client = new OkHttpClient();
                    String server_url = "http://192.168.43.22:5000/post_message";

                    RequestBody formBody = new FormBody.Builder()
                            .add("message", message)
                            .build();

                    Request request = new Request.Builder()
                            .url(server_url)
                            .post(formBody)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
