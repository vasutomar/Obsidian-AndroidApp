package vasu.lenovo.obsidianandroidapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class PostImageForDecoding {

    private Bitmap bitmap;
    private OkHttpClient client = new OkHttpClient();
    public String decodedMessage = "";
    public Context context;
    public TextView out;

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public PostImageForDecoding(Bitmap bitmap, Context context, TextView out) {
        this.bitmap = bitmap;
        this.context = context;
        this.out = out;
        final String imageBytes = BitMapToString(bitmap);
        send_image(imageBytes);
    }

    public void send_image(final String imageBytes) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String server_url = "http://192.168.43.22:5000/Decode_Image";
                try  {
                    OkHttpClient client = new OkHttpClient();

                    RequestBody formBody = new FormBody.Builder()
                            .add("image",imageBytes)
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
                Request request = new Request.Builder()
                        .url(server_url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            Log.e("here"," Exception");
                            throw new IOException("Unexpected code " + response);
                        } else {
                            final String responseData = response.body().string();
                            decodedMessage = responseData;
                            out.setText(decodedMessage);
                        }
                    }
                });
            }
        });
        thread.start();
    }
}
