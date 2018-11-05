package vasu.lenovo.obsidianandroidapp;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
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
    public ImageView Img;
    public String choice;

    PostMessage(String message,ImageView Img,String choice) {
        this.message = message;
        this.Img = Img;
        this.choice = choice;
        send_message();
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public void send_message() {
        Img.buildDrawingCache();
        Bitmap bitmap = Img.getDrawingCache();
        final String imageBytes = BitMapToString(bitmap);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    OkHttpClient client = new OkHttpClient();
                    String server_url = "http://192.168.43.22:5000/post_message";

                    RequestBody formBody = new FormBody.Builder()
                            .add("message", message)
                            .add("image",imageBytes)
                            .add("choice",choice)
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
