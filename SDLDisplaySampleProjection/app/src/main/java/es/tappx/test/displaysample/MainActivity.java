package es.tappx.test.displaysample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //If we are connected to a module we want to start our SdlService
        if(BuildConfig.TRANSPORT.equals("MULTI") || BuildConfig.TRANSPORT.equals("MULTI_HB")) {
            SdlReceiver.queryForConnectedService(this);
        }else if(BuildConfig.TRANSPORT.equals("TCP")) {
            Intent proxyIntent = new Intent(this, SdlService.class);
            startService(proxyIntent);
        }

        VideoView videoView = (VideoView) findViewById(R.id.videoView_video);

        Uri path = Uri.parse("android.resource://es.tappx.test.displaysample/"
                + R.raw.play);

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        videoView.setVideoURI(path);
        videoView.start();


    }
}
