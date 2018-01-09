package com.example.shree.wlug;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoLibrary extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    public static final int RECOVERY_REQUEST=1;
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_library);
        youTubePlayerView= (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        youTubePlayerView.initialize(Config.Api_Key,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        if(!b)
        {
            youTubePlayer.cueVideo("7azhlH_CNdk");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(this,RECOVERY_REQUEST).show();
            String error = String.format(getString(R.string.player_error,youTubeInitializationResult.toString()));
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
        }

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RECOVERY_REQUEST)
        {
            getYouTubePlayerProvider().initialize(Config.Api_Key,this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {

        return youTubePlayerView;
    }
}
