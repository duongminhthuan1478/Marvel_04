package com.framgia.mavel;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.framgia.mavel.MainActivity.LOG_TAG;

/**
 * Class thực hiện HTTP request và nhận phản hồi từ JSON
 */
public final class QueryUtils {

    private QueryUtils() { }

    public static ArrayList<Character> fetchCharacter(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        ArrayList<Character> listCharacter = extractFeatureFromJson(jsonResponse);
        return listCharacter;
    }

    /**
     * Trả về một list{@link Character} objects đã được phân tích từ JSON
     */
    private static ArrayList<Character> extractFeatureFromJson(String characterJSON) {
        if (TextUtils.isEmpty(characterJSON)) {
            return null;
        }
        ArrayList<Character> listUser = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(characterJSON);
            JSONObject dataCharacterObject = jsonObject.getJSONObject("data");
            JSONArray characterArray = dataCharacterObject.getJSONArray("results");

            for (int i = 0; i < characterArray.length(); i++) {
                JSONObject currentCharacter = characterArray.getJSONObject(i);
                String name = currentCharacter.getString("name");
                JSONObject thumbnail = currentCharacter.getJSONObject("thumbnail");
                String image = thumbnail.getString("path");

                Character character = new Character(name, image);
                listUser.add(character);
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return listUser;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            // Phản hồi thành công
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
