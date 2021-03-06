/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.chromicle.amritaResource;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
* {@link WordAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
* based on a data source, which is a list of {@link Word} objects.
*/
public class WordAdapter extends ArrayAdapter<Word> {

    private List<Word> wordList;
    private ArrayList<Word> wordArrayList;

    /**
    * Create a new {@link WordAdapter} object.
    *
    * @param context is the current context (i.e. Activity) that the adapter is being created in.
    * @param words is the list of {@link Word}s to be displayed.
    */
    public WordAdapter(Context context, List<Word> words) {
        super(context, 0, words);
        this.wordList = words;
        this.wordArrayList = new ArrayList<>();
        this.wordArrayList.addAll(wordList);
    }

    @Override
    public int getCount() {
        return wordList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        miwokTextView.setText(currentWord.getSubjectName());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        defaultTextView.setText(currentWord.getSubNotesName());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.

        if (position == 0) {
            miwokTextView.setTextSize(25);
            miwokTextView.setTextColor(Color.parseColor("#FD8E09"));
        } else {

            miwokTextView.setTextSize(20);
            miwokTextView.setTextColor(Color.parseColor("#000000"));
        }

        return listItemView;
    }

    // funtcion search like recylerview
    void search(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        wordList.clear();

        if (charText.length() == 0) {
            wordList.addAll(wordArrayList);

        } else {
            for (Word word : wordArrayList) {
                if (word.getSubjectName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    wordList.add(word);
                }
            }
        }
        notifyDataSetChanged();
    }
}
