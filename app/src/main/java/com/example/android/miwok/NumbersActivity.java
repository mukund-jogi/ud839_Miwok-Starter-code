package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayeronCompletion){
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = (new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }

            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {mediaPlayer.start();}

            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {releaseMediaPlayer();}
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "litti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("Two", "otiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("Three", "tolokossu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("Six", "temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("Seven" ,"kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("Eight","kjdfl",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("Nine","asdf",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("Ten","adggd",R.drawable.number_ten,R.raw.number_ten));

        ListView listView  = (ListView) findViewById(R.id.ListView1);
       WordAdapter wordArrayAdapter = new WordAdapter(this,words, R.color.category_numbers);

        listView.setAdapter(wordArrayAdapter);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int positon, long l) {
                Word word = words.get(positon);
                releaseMediaPlayer();
                int playRequest = audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (playRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), word.getMiwokAudioId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer !=null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
