package com.example.cis183_hw01_rgb;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ColorAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Colors> colorsList;
    private LayoutInflater inflater;

    public ColorAdapter(Context context, ArrayList<Colors> colorsList)
    {
        this.context = context;
        this.colorsList = colorsList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return colorsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return colorsList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    //This is for the view box for the cells to show up in the ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.color_cell, parent, false);
        }

        //Name all of the cell objects within the adapter class
        TextView tv_j_row_redValue = convertView.findViewById(R.id.tv_v_row_redValue);
        TextView tv_j_row_greenValue = convertView.findViewById(R.id.tv_v_row_greenValue);
        TextView tv_j_row_blueValue = convertView.findViewById(R.id.tv_v_row_blueValue);
        TextView tv_j_row_hexValue = convertView.findViewById(R.id.tv_v_row_hexValue);
        TextView tv_j_row_Red = convertView.findViewById(R.id.tv_v_row_Red);
        TextView tv_j_row_Green = convertView.findViewById(R.id.tv_v_row_Green);
        TextView tv_j_row_Blue = convertView.findViewById(R.id.tv_v_row_Blue);
        TextView tv_j_row_hexRepresent = convertView.findViewById(R.id.tv_v_row_hexRepresent);
        View v_j_row_colorBox = convertView.findViewById(R.id.v_v_row_colorBox);

        //Access the getters and setters for the colors class
        Colors color = colorsList.get(position);

        //Update the text views within the cell for when the user saves the color
        tv_j_row_redValue.setText(String.valueOf(color.getR()));
        tv_j_row_greenValue.setText(String.valueOf(color.getG()));
        tv_j_row_blueValue.setText(String.valueOf(color.getB()));
        tv_j_row_hexValue.setText(String.valueOf(color.getHex()));
        v_j_row_colorBox.setBackgroundColor(Color.rgb(color.getR(), color.getG(), color.getB()));

        //Using the same function I used in MainActivity, we can change the color of the textview
        //depending on the brightness
        double brightness = (0.299 * color.getR()) + (0.587 * color.getG()) + (0.114 * color.getB());
        if(brightness < 128)
        {
            //Dark background = White text
            tv_j_row_redValue.setTextColor(Color.WHITE);
            tv_j_row_greenValue.setTextColor(Color.WHITE);
            tv_j_row_blueValue.setTextColor(Color.WHITE);
            tv_j_row_hexValue.setTextColor(Color.WHITE);
            tv_j_row_Red.setTextColor(Color.WHITE);
            tv_j_row_Green.setTextColor(Color.WHITE);
            tv_j_row_Blue.setTextColor(Color.WHITE);
            tv_j_row_hexRepresent.setTextColor(Color.WHITE);

        } else
        {
            //Bright background = Black text
            tv_j_row_redValue.setTextColor(Color.BLACK);
            tv_j_row_greenValue.setTextColor(Color.BLACK);
            tv_j_row_blueValue.setTextColor(Color.BLACK);
            tv_j_row_hexValue.setTextColor(Color.BLACK);
            tv_j_row_Red.setTextColor(Color.BLACK);
            tv_j_row_Green.setTextColor(Color.BLACK);
            tv_j_row_Blue.setTextColor(Color.BLACK);
            tv_j_row_hexRepresent.setTextColor(Color.BLACK);
        }

        return convertView;

    }
}
