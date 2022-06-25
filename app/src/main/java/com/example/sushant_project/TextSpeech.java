package com.example.sushant_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.*;

import java.util.Locale;


public class TextSpeech extends AppCompatActivity {
    /** Called when the activity is first created. */
    private TextToSpeech tts;
    private Button buttonSpeak;
    private EditText editText;
    private SeekBar sb1,sb2;
    private float speechRate=1.0f;//normal
    private float pitchRate=1.0f;
    TextView textView,textView2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_speech);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        buttonSpeak = (Button) findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.editText1);
        sb1 = findViewById(R.id.sb1);
        sb2 = findViewById(R.id.sb2);
        textView = findViewById(R.id.textView3);
        textView2 = findViewById(R.id.textView5);
        tts = new TextToSpeech(getApplicationContext(), new
                TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result ==
                                TextToSpeech.LANG_NOT_SUPPORTED) {
                            Toast.makeText(TextSpeech.this, "This Language is not supported",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TextSpeech.this, "Just add a  '/' for breaks between words in speech", Toast.LENGTH_LONG).show();
            }
        });
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
               @Override
               public void onProgressChanged(SeekBar seekBar, int
                       i, boolean b) {
                   switch(i){
                       case 1: speechRate=0.1f;
                           textView.setText("Very Slow");break;
                       case 2: speechRate=0.5f;
                           textView.setText("Slow"); break;
                       case 3: speechRate=1.0f;
                           textView.setText("Normal");break;
                       case 4: speechRate=1.5f;
                           textView.setText("Fast");break;
                       case 5: speechRate=2.0f;
                           textView.setText("Very Fast"); break;
                   }
               }
               @Override
               public void onStartTrackingTouch(SeekBar seekBar) {
               }
               @Override
               public void onStopTrackingTouch(SeekBar seekBar) {
               }
           });
        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int
                    i, boolean b) {
                switch(i){
                    case 1: pitchRate=0.1f;
                        textView2.setText("Very Low");break;
                    case 2: pitchRate=0.5f;
                        textView2.setText("Low"); break;
                    case 3: pitchRate=1.0f;
                        textView2.setText("Normal");break;
                    case 4: pitchRate=1.5f;
                        textView2.setText("High");break;
                    case 5: pitchRate=2.0f;
                        textView2.setText("Very High"); break;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    @Override
    public void onDestroy() {
// Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    public void start(View view) {
        tts.setSpeechRate(speechRate);
        tts.setPitch(pitchRate);
        String text = editText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    public void st1(View view) {
        tts.setSpeechRate(speechRate);
        tts.setPitch(pitchRate);
        String text = "This is an / awesome / powerful app / made by Sushant";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(TextSpeech.this, "Loading please wait a second ...", Toast.LENGTH_SHORT).show();
    }

    public void st2(View view) {
        tts.setSpeechRate(speechRate);
        tts.setPitch(pitchRate);
        String text = "This app is made with a purpose / to help people in various small ways";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(TextSpeech.this, "Loading please wait a second ...", Toast.LENGTH_SHORT).show();
    }

    public void st3(View view) {
        tts.setSpeechRate(speechRate);
        tts.setPitch(pitchRate);
        String text = "Have a Good Day / my Bro";
//        String text = "Kya kar / raha hai / kuch kaam dhanda / padhai / likhai/ kar le/ bhosadikyay";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(TextSpeech.this, "Loading please wait a second ...", Toast.LENGTH_SHORT).show();
    }

    public void st4(View view) {
        tts.setSpeechRate(speechRate);
        tts.setPitch(pitchRate);
        String text = "Testing the audio";
//        String text = "Teri maa ki chut bhosadikyay / maa dar chod /  tattoo ke sauda gar / gandu / bhikhari / ";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(TextSpeech.this, "Loading please wait a second ...", Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        Intent i = new Intent(TextSpeech.this,MainActivity.class);
//        i.putExtra("msg","Hope you enjoyed our Text to Speech Functionality");
        startActivity(i);
    }
}