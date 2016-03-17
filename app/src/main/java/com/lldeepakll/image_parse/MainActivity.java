package com.lldeepakll.image_parse;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ImageAdapter mAdapter;
    private ArrayList<Model> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView mListView = (ListView)findViewById(R.id.imageListView);

        model = new ArrayList<>();

        new ImageTask().execute("http://www.mocky.io/v2/56ea55dc10000007036c18f2");
        mAdapter = new ImageAdapter(getApplicationContext(),R.layout.item_image_layout,model);
        mListView.setAdapter(mAdapter);

    }

    class ImageTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Please wait,\nConnecting server.....");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... url) {
            try {
                HttpGet get = new HttpGet(url[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(get);

                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    Log.d(TAG, "" + data.length() + " length");

                    JSONObject object = new JSONObject(data);
                    JSONArray array = object.getJSONArray("images");

                    for (int i = 0; i < array.length(); i++)
                    {
                        JSONObject object2 = array.getJSONObject(i);
                        Model img = new Model();
                        img.setImageUrl(object2.getString("url"));
                        model.add(img);
                    }
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            mAdapter.notifyDataSetChanged();
            if(!result)
                Toast.makeText(getApplicationContext(), "Unable to fetch data.", Toast.LENGTH_LONG).show();
        }
    }
}
