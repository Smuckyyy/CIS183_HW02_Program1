//Name: Marcus Rollins
//Date: 09/28/25
//Desc: An app that allows a user to manipulate SeekBars to choose colors. They can then
//      save the colors to an arraylist and re-view the colors by selecting them from a
//      ListView. They also all show their hex values when a color is selected.

package com.example.cis183_hw01_rgb;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //---------------------
    //List of Objects
    //---------------------
    TextView tv_j_redValue;
    TextView tv_j_greenValue;
    TextView tv_j_blueValue;

    TextView tv_j_redTV;
    TextView tv_j_greenTV;
    TextView tv_j_blueTV;

    TextView tv_j_hexNumber;
    TextView tv_j_hexRepresent;
    Button btn_j_saveColor;

    SeekBar sb_j_Red;
    SeekBar sb_j_Green;
    SeekBar sb_j_Blue;

    ListView lv_j_listOfColors;
    ConstraintLayout mainLayout;

    //This is for an array for all the TextView that need to change color based on the background
    TextView[] textViews;
    ArrayList<Colors> listOfColors = new ArrayList<>();
    ColorAdapter colorAdapter;

    private int r = 0;
    private int g = 0;
    private int b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //--------------------
        //Definitions
        //--------------------
        mainLayout = findViewById(R.id.main);

        //All of the Text
        TextView t0 = tv_j_redValue = findViewById(R.id.tv_v_redValue);
        TextView t1 = tv_j_greenValue = findViewById(R.id.tv_v_greenValue);
        TextView t2 = tv_j_blueValue = findViewById(R.id.tv_v_blueValue);
        TextView t3 = tv_j_redTV = findViewById(R.id.tv_v_redTV);
        TextView t4 = tv_j_greenTV = findViewById(R.id.tv_v_greenTV);
        TextView t5 = tv_j_blueTV = findViewById(R.id.tv_v_blueTV);

        TextView t6 = tv_j_hexNumber = findViewById(R.id.tv_v_hexNumber);
        TextView t7 = tv_j_hexRepresent = findViewById(R.id.tv_v_hexRepresent);

        //TextView Array
        textViews = new TextView[]{t0, t1, t2, t3, t4, t5, t6, t7};

        //Button
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);

        //Seekbars
        sb_j_Red = findViewById(R.id.sb_v_Red);
        sb_j_Green = findViewById(R.id.sb_v_Green);
        sb_j_Blue = findViewById(R.id.sb_v_Blue);

        //These make the seekBars max out when sliding over
        sb_j_Red.setMax(255);
        sb_j_Green.setMax(255);
        sb_j_Blue.setMax(255);

        //ListView
        lv_j_listOfColors = findViewById(R.id.lv_v_listOfColors);

        //This allows the ListView to show the data saved by the user
        colorAdapter = new ColorAdapter(this, listOfColors);
        lv_j_listOfColors.setAdapter(colorAdapter);


        //Button Call
        btn_j_saveColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String hex = tv_j_hexNumber.getText().toString();
                Colors newColor = new Colors(r, g, b, hex);
                listOfColors.add(newColor);
                colorAdapter.notifyDataSetChanged(); // refresh ListView
            }
        });

        //This is for the ListView so that when it is clicked, it changes the background color
        lv_j_listOfColors.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Colors colorSelected = listOfColors.get(position);
                applyColorToMainScreen(colorSelected);
            }
        });

        //--------------------
        //Function Calls
        //--------------------
        sb_j_Red.setOnSeekBarChangeListener(makeSeekBarListener(tv_j_redValue));
        sb_j_Green.setOnSeekBarChangeListener(makeSeekBarListener(tv_j_greenValue));
        sb_j_Blue.setOnSeekBarChangeListener(makeSeekBarListener(tv_j_blueValue));



    }

    //This passes data from the ListView to the MainActivity
    private void applyColorToMainScreen(Colors color)
    {
        r = color.getR();
        g = color.getG();
        b = color.getB();

        sb_j_Red.setProgress(r);
        sb_j_Green.setProgress(g);
        sb_j_Blue.setProgress(b);

        mainLayout.setBackgroundColor(Color.rgb(r, g, b));
        tv_j_hexNumber.setText(color.getHex());
    }

    //SeekBars passing Information to TextView and changing background color
    private SeekBar.OnSeekBarChangeListener makeSeekBarListener(TextView textView)
    {
        return new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                textView.setText(String.valueOf(progress));

                //These checks if the seekBars move, and depending on which one,
                //it updates that specific color depending on the helper below
                if(seekBar == sb_j_Red)
                {
                    r = progress;
                }
                if(seekBar == sb_j_Green)
                {
                    g = progress;
                }
                if(seekBar == sb_j_Blue)
                {
                    b = progress;
                }

                //Update the background color using this helper
                mainLayout.setBackgroundColor(Color.rgb(r, g, b));

                //Update the hex display text view
                String hex = String.format("#%02X%02X%02X", r, g, b);
                tv_j_hexNumber.setText(hex);

                //Change the text color depending on the background using this formula
                //and the for loop below
                double brightness = (0.299 * r) + (0.587 * g) + (0.114 * b);

                //Goes through each textView in the array that I placed in OnCreate
                for(int i = 0; i < textViews.length; i++)
                {
                    //Using the formula above if the brightness is less than 128 (dark color)
                    if(brightness < 128)
                    {
                        //For each textview, ([i])
                        //Changes the color to white since it's a dark background
                        textViews[i].setTextColor(Color.WHITE);
                    }
                    else
                    {
                        //Any other color will be bright, so it turns black
                        textViews[i].setTextColor(Color.BLACK);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        };
    }
}