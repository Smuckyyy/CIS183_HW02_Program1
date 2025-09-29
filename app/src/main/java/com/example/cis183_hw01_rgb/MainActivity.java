package com.example.cis183_hw01_rgb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    //---------------------
    //List of Objects
    //---------------------
    TextView tv_j_redValue;
    TextView tv_j_greenValue;
    TextView tv_j_blueValue;

    TextView tv_j_hexNumber;
    Button btn_j_saveColor;

    SeekBar sb_j_Red;
    SeekBar sb_j_Green;
    SeekBar sb_j_Blue;

    ListView lv_j_listOfColors;

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
        tv_j_redValue = findViewById(R.id.tv_v_redValue);
        tv_j_greenValue = findViewById(R.id.tv_v_greenValue);
        tv_j_blueValue = findViewById(R.id.tv_v_blueValue);

        tv_j_hexNumber = findViewById(R.id.tv_v_hexNumber);
        btn_j_saveColor = findViewById(R.id.btn_v_saveColor);

        sb_j_Red = findViewById(R.id.sb_v_Red);
        sb_j_Green = findViewById(R.id.sb_v_Green);
        sb_j_Blue = findViewById(R.id.sb_v_Blue);

        lv_j_listOfColors = findViewById(R.id.lv_v_listOfColors);


    }
}