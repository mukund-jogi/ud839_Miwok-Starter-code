package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mukund.jogi on 12/9/17.
 */

public class WordAdapter extends ArrayAdapter<Word>{


    private int color_bg;

    public WordAdapter(Activity context, ArrayList<Word> words, int category_color) {
        super(context,0,words);
        color_bg = category_color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItmeView = convertView;
        if(listItmeView==null)
        {
            listItmeView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView defaultTextView = (TextView) listItmeView.findViewById(R.id.defautlTextView);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTextView = (TextView) listItmeView.findViewById(R.id.miwokTextView);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        ImageView imageView = (ImageView) listItmeView.findViewById(R.id.image_view1);

        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImgageIdRes());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);

        View textContainer = listItmeView.findViewById(R.id.linearLayoutWithText);
        int backgroundColor = ContextCompat.getColor(getContext(),color_bg);
        textContainer.setBackgroundColor(backgroundColor);
//        return super.getView(position, convertView, parent);
        return listItmeView;
    }
}
