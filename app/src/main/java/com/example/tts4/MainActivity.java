package com.example.tts4;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends Activity implements OnInitListener {
    EditText ee;
    Button b1;
    Button b2;
    Button b3;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ee=(EditText)findViewById(R.id.editText1);
        b1=(Button)findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=ee.getText().toString();
                tts.speak(str,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        Intent i = new Intent();
        i.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(i, 1);
            //---------------------------------------------
        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak("Hello, how are you?",TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        //
        b3=(Button)findViewById(R.id.button3);
        b3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak("I'm good, thank you.",TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                tts= new TextToSpeech(this,this);
            }
            else
            {
                Intent i=new Intent();
                i.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(i);
            }
        }
    }

    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS)
        {
            Toast.makeText(getApplicationContext(), "engine installed",1000).show();
        }
        if(status==TextToSpeech.ERROR)
        {
            Toast.makeText(getApplicationContext(), "engine not installed", 1000).show();
        }
    }
}