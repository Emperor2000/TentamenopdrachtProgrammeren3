package com.example.randomuserapp.services;

import android.os.AsyncTask;
import android.util.Log;

import com.example.randomuserapp.model.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class RandomUserTask extends AsyncTask<Void, Void, String> {

    private final String TAG = RandomUserTask.class.getSimpleName();
    private String mRandomUserApi = "https://api.blindwalls.gallery/apiv2/murals";
    private RandomUserListerner listener;

    public RandomUserTask(RandomUserListerner listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG,"getRandomUser was called");

        String response = null;

        //ToDo: Make url
        try {
            URL mUrl = new URL(mRandomUserApi);
            URLConnection urlConnection = mUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("GET");

            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == 200){
                InputStream in = httpURLConnection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if(hasInput){
                    response = scanner.next();
                }
                Log.d(TAG,response);
            }else{
                Log.e(TAG,"Error: code = " + responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ToDo: Send request to server
        //ToDo: Compile response
        //ToDo: Return name of user

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        Log.d(TAG, "onPostExecute was called");
        Log.d(TAG, "Response = " + response);

        ArrayList<Profile> profileArrayList = new ArrayList<>();

        try {
            JSONArray results = new JSONArray(response);

            for(int i = 0; i < results.length();i++){
                JSONObject user = results.getJSONObject(i);
                //Get name
                String title = user.getString("author");

                //Get image
                JSONArray imgResult = user.getJSONArray("images");

                String img = null;

                for(int j = 0; j < imgResult.length();j++){
                    if (imgResult.getJSONObject(j).getString("type").equals("frontpage")){
                        img = user.getJSONArray("images").getJSONObject(j).getString("url");
                    }
                }

                Profile profile = new Profile(title).setImgUrl("https://api.blindwalls.gallery/" + img);
                profileArrayList.add(profile);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listener.onUserNameAvailable(profileArrayList);
    }

    public interface RandomUserListerner{
        public void onUserNameAvailable(ArrayList<Profile> profiles);
    }

}
