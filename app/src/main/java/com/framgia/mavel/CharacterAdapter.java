package com.framgia.mavel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 29-Jan-18.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {

    private Context mContext;
    private List<Character> mListCharacter = new ArrayList<>();

    public CharacterAdapter(Context context, List<Character> listCharacter) {
        mContext = context;
        mListCharacter = listCharacter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mListCharacter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextNameCharacter;
        private ImageView mImageCharacter;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextNameCharacter = itemView.findViewById(R.id.text_name_character);
            mImageCharacter = itemView.findViewById(R.id.image_character);
        }

        public void setData(int position) {
            Character character = mListCharacter.get(position);
            mTextNameCharacter.setText(character.getName());
            mImageCharacter.setImageResource(character.getImage());
        }
    }
}
