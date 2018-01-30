package com.framgia.mavel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class CharactersFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private static CharacterAdapter mCharacterAdapter;
    private static List<Character> mListCharacter = new ArrayList<>();

    private static final String REQUEST_URL =
            "http://gateway.marvel.com/v1/public/characters?ts=1517371364&apikey="
                    + "165b3b5c0b9df88ded28553649f9ebea&hash=979a4ae370b787ff6c36652f55548a2d";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        displayRecyclerView(rootView);

        CharacterAsyncTask task = new CharacterAsyncTask();
        task.execute(REQUEST_URL);
        Toast.makeText(getActivity(), "Load Data Success!", Toast.LENGTH_SHORT).show();

        return rootView;
    }

    private void displayRecyclerView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mCharacterAdapter = new CharacterAdapter(getActivity(), mListCharacter);
        mRecyclerView.setAdapter(mCharacterAdapter);
    }

    private static class CharacterAsyncTask extends AsyncTask<String, Void, ArrayList<Character>> {

        @Override
        protected ArrayList<Character> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Character> arrayCharacter = QueryUtils.fetchCharacter(urls[0]);
            return arrayCharacter;
        }

        @Override
        protected void onPostExecute(ArrayList<Character> characters) {
            super.onPostExecute(characters);
            mListCharacter.clear();

            if (characters != null && !characters.isEmpty()) {
                mListCharacter.addAll(characters);
                mCharacterAdapter.notifyDataSetChanged();
            }
        }
    }
}
