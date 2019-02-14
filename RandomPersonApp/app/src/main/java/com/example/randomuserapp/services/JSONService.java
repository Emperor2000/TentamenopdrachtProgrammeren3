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
import java.util.Locale;
import java.util.Scanner;

public class JSONService extends AsyncTask<Void, Void, String> {

    private final String TAG = JSONService.class.getSimpleName();
    private String mBlindWallAPI = "https://api.blindwalls.gallery/apiv2/murals";
    private JSONServiceListener listener;

    public JSONService(JSONServiceListener listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG,"getRandomUser was called");

        String response = null;

        try {
            URL mUrl = new URL(mBlindWallAPI);
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
                //CHANGE GETSTRING 'EN' TO VARIABLE
                String langu = Locale.getDefault().getLanguage();
                String lang = "en";
                if (langu.contains("nl")){
                    lang = "nl";
                }
                int id = user.getInt("id");
                String desc = user.getJSONObject("description").getString(lang);
                String mate = user.getJSONObject("material").getString(lang);
                String photographer = user.getString("photographer");
                String address = user.getString("address");

                //Get image
                JSONArray imgResult = user.getJSONArray("images");

                String img = null;
                ArrayList<String> imgWall = new ArrayList<>();

                for(int j = 0; j < imgResult.length();j++){
                    if (imgResult.getJSONObject(j).getString("type").equals("frontpage")){
                        img = user.getJSONArray("images").getJSONObject(j).getString("url");
                    }
                    else if(imgResult.getJSONObject(j).getString("type").equals("wall")){
                        imgWall.add(user.getJSONArray("images").getJSONObject(j).getString("url"));
                    }
                }

                Profile profile = new Profile(id, title, mate, address, photographer, desc).setImgUrl("https://api.blindwalls.gallery/" + img).setWallImgUrl(imgWall);
                profileArrayList.add(profile);
                //Check if wall images are added correctly
                /*for (String check : imgWall){
                    Log.d(TAG, check);
                }*/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listener.onUserNameAvailable(profileArrayList);
    }

    public interface JSONServiceListener {
        public void onUserNameAvailable(ArrayList<Profile> profiles);
    }

}
