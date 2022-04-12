package com.example.billboardchart;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class DetailFragment extends Fragment {

    private int workId;
    //method to get and set workID
    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        String cool = "cool";
        View view = getView();  //give you access to the view XML template
        TextView textView = (TextView) view.findViewById(R.id.hotTextTitle);
        TextView textView1 = (TextView) view.findViewById(R.id.hotTextTitle1);
        TextView textView2 = (TextView) view.findViewById(R.id.hotTextTitle2);
        TextView title = (TextView) view.findViewById(R.id.txt_title);

//        TextView test = (TextView) view.findViewById(R.id.hotTextTitle);
//        String string = "cool";


//        test.setText(Categories.categories[workId].getDescription());
        String Link = null;
        if(Categories.categories[workId].getName() == "Hot 100") {
            Link = "https://billboard-api2.p.rapidapi.com/hot-100?range=1-5&date=2022-03-01";

                title.setText(Categories.categories[0].getName());

                ImageView img1 = (ImageView) view.findViewById(R.id.img1);
                img1.setImageResource(R.drawable.encanto);


                ImageView img2 = (ImageView) view.findViewById(R.id.img2);
                img2.setImageResource(R.drawable.heat);


                ImageView img3= (ImageView) view.findViewById(R.id.img3);
                img3.setImageResource(R.drawable.gayle);


        } else if (Categories.categories[workId].getName() == "Billboard 200") {
            Link = "https://billboard-api2.p.rapidapi.com/billboard-200?date=2022-03-01&range=1-5";

            title.setText(Categories.categories[1].getName());

                ImageView img4 = (ImageView) view.findViewById(R.id.img1);
                img4.setImageResource(R.drawable.encanto);


                ImageView img5 = (ImageView) view.findViewById(R.id.img2);
                img5.setImageResource(R.drawable.gunna2);


                ImageView img6= (ImageView) view.findViewById(R.id.img3);
                img6.setImageResource(R.drawable.morgan);


        }else if (Categories.categories[workId].getName() == "Artist 100") {
            Link = "https://billboard-api2.p.rapidapi.com/artist-100?range=1-5&date=2022-03-01";

                title.setText(Categories.categories[2].getName());

                ImageView img7 = (ImageView) view.findViewById(R.id.img1);
                img7.setImageResource(R.drawable.ed);


                ImageView img8 = (ImageView) view.findViewById(R.id.img2);
                img8.setImageResource(R.drawable.weekend);


                ImageView img9= (ImageView) view.findViewById(R.id.img3);
                img9.setImageResource(R.drawable.adele);

        }
        //API connection
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Link)
                .get()
                .addHeader("X-RapidAPI-Host", "billboard-api2.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "85f1fcaac5mshc7489665becae38p113b5djsn7ed9d609fc7a")
                .build();


        client.newCall(request).enqueue(new Callback() {

            //Parsing of the JSON file
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                JSONObject obj1 = new JSONObject(myResponse);

                                JSONObject songsObject = obj1.getJSONObject("content");
                                JSONArray songsArray = songsObject.toJSONArray(songsObject.names());


                                textView.setText(Categories.categories[0].setDescription(
                                        "Artist: " + songsArray.getJSONObject(0).getString("artist")
                                        + ", Rank: " + songsArray.getJSONObject(0).getString("rank")
                                        + ", Peak Position: " + songsArray.getJSONObject(0).getString("peak position")
                                        + ", Weeks On Chart: " + songsArray.getJSONObject(0).getString("weeks on chart")

                                ));


                                textView1.setText(Categories.categories[1].setDescription(
                                        "Artist: " + songsArray.getJSONObject(1).getString("artist")
                                                + ", Rank: " + songsArray.getJSONObject(1).getString("rank")
                                                + ", Peak Position: " + songsArray.getJSONObject(1).getString("peak position")
                                                + ", Weeks On Chart: " + songsArray.getJSONObject(1).getString("weeks on chart")

                                ));

                                textView2.setText(Categories.categories[2].setDescription(
                                        "Artist: " + songsArray.getJSONObject(2).getString("artist")
                                                + ", Rank: " + songsArray.getJSONObject(2).getString("rank")
                                                + ", Peak Position: " + songsArray.getJSONObject(2).getString("peak position")
                                                + ", Weeks On Chart: " + songsArray.getJSONObject(2).getString("weeks on chart")

                                ));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });

    }
}


