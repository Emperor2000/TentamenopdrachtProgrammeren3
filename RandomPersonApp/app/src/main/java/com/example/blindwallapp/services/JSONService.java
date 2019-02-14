package com.example.blindwallapp.services;

import android.os.AsyncTask;
import android.util.Log;
import com.example.blindwallapp.model.Profile;
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

    //Assert private variables
    private final String TAG = JSONService.class.getSimpleName();
    private String mBlindWallAPI = "https://api.blindwalls.gallery/apiv2/murals";
    private JSONServiceListener listener;

    public JSONService(JSONServiceListener listener){
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d(TAG,"DoInBackground was called");

        String response = null;

        //Try to get valid API url.
        try {
            URL mUrl = new URL(mBlindWallAPI);
            //Try to open a connection with Url.
            URLConnection urlConnection = mUrl.openConnection();
            //Open hyper text transfer protocol connection.
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            //Request GET
            httpURLConnection.setRequestMethod("GET");
            //Finish connection
            httpURLConnection.connect();
            //Response code from API
            int responseCode = httpURLConnection.getResponseCode();
            //If response code is 200
            if(responseCode == 200){
                InputStream in = httpURLConnection.getInputStream();
                //Confirm input stream.
                //Create scanner object.
                //Scanner NextLine
                Scanner scanner = new Scanner(in);
                //Define seperate data streams.
                scanner.useDelimiter("\\A");
                //Define hasInput.
                boolean hasInput = scanner.hasNext();
                //If boolean is true: (if scanner has next)
                if(hasInput){
                    //Log response.
                    response = scanner.next();
                }
                Log.d(TAG,response);
            }else{
                //Log error code, responseCode was not 200.
                Log.e(TAG,"Error: code = " + responseCode);
            }
            //Catch possible exceptions.
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

        //Log onPostExecute called with response
        Log.d(TAG, "onPostExecute was called");
        Log.d(TAG, "Response = " + response);
        //Create an ArrayList in which all of our profiles will be stored.
        ArrayList<Profile> profileArrayList = new ArrayList<>();                                    //Assign ArrayList for the profiles

        //Try
        try {
            //Create JSONArray results, with response.
            JSONArray results = new JSONArray(response);

            //Check language for JSON checks
            String langu = Locale.getDefault().getLanguage();                                       //Get system language
            String lang = "en";                                                                     //Set default language
            if (langu.contains("nl")){                                                              //Check system language to contain 'nl' for languages similar to dutch
                lang = "nl";                                                                        //Set language to 'nl'
            }
            //Log show language
            Log.d(TAG, "Language = " + langu);

            //Log entering for-loop
            Log.d(TAG, "Entered for-loop to create profiles");
            for(int i = 0; i < results.length();i++){
                JSONObject user = results.getJSONObject(i);                                         //Create JSONObject for easier access (requires new id(i) for every new profile)
                //Get id from JSON object
                int id = user.getInt("id");                                                   //Get ID
                //Get title, is called author in JSON file
                String title = user.getString("author");                                      //Get author name
                String desc = user.getJSONObject("description").getString(lang);                    //Get description
                String mate = user.getJSONObject("material").getString(lang);                       //Get material
                String photographer = user.getString("photographer");                         //Get photographer
                String address = user.getString("address");                                   //Get address
                double latitude = user.getDouble("latitude");                                 //Get latitude
                double longitude = user.getDouble("longitude");                               //Get longitude
                JSONArray imgResult = user.getJSONArray("images");                            //Get image array

                String img = null;                                                                  //Set img variable with String
                ArrayList<String> imgWall = new ArrayList<>();                                      //Set imgWall variable with ArrayList

                //Check if img is a frontpage image or not
                for(int j = 0; j < imgResult.length();j++){


                    //Puts front page images in string img
                    if (imgResult.getJSONObject(j).getString("type").equals("frontpage")){

                        img = user.getJSONArray("images").getJSONObject(j).getString("url");
                    }
                    //Puts non front page images in array imgWall
                    else if(imgResult.getJSONObject(j).getString("type").equals("wall")){

                        imgWall.add(user.getJSONArray("images").getJSONObject(j).getString("url"));
                    }
                }
                //Profile constructor.
                Profile profile = new Profile(id, title, mate, address, photographer, desc, latitude, longitude).setmImgUrl("https://api.blindwalls.gallery/" + img).setWallImgUrl(imgWall);
                profileArrayList.add(profile);


                //Log put profile in arrayList
                Log.d(TAG, "Put '" + profile.getmAuthor() + "' inserted into list");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listener.onWallProfileAvailable(profileArrayList);
    }

    public interface JSONServiceListener {
        public void onWallProfileAvailable(ArrayList<Profile> profiles);
    }

}
