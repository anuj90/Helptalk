package anuj.livoxtest;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    GridView grid;
    TextToSpeech textToSpeech;

    ImageView imgview;

    String[] text = {                   // get all the commands
            "Leave me Alone",
            "Breath",
            "Get Away",
            "Read Braille",
            "Play with blocks",
            "Breath",

    } ;
    int[] imageId = {                   // get all the images for the grid
            R.drawable.bother,
            R.drawable.blow,
            R.drawable.boo,
            R.drawable.braille_read,
            R.drawable.build_blocks,
            R.drawable.blow,


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TouchClass touch = new TouchClass(MainActivity.this);       // to parse the (x,y) coordinates
        float t1 = touch.X;
        float t2 = touch.Y;
        final CustomGrid adapter = new CustomGrid(MainActivity.this, text, imageId);        //adapter to connect the grid
        textToSpeech= new TextToSpeech(MainActivity.this,MainActivity.this);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String text = MainActivity.this.text[+position];
                if(!textToSpeech.isSpeaking()){
                    HashMap<String,String> stringStringHashMap = new HashMap<String, String>();
                    stringStringHashMap.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, MainActivity.this.text[+position]);
                    textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, stringStringHashMap);

                    int newposition = -1;
                    imgview = (ImageView) grid.getChildAt(newposition);

                    if(newposition != -1){                      // Highlight the selected option
                        imgview.setSelected(false);
                        imgview.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

                    }
                    newposition = position;

                }else{
                    textToSpeech.stop();
                }
            }
        });

    }

    @Override
    public void onInit(int status) {

    }
}