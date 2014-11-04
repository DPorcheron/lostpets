package fr.esiea.mobile.lostpets.dao;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.net.URLEncoder;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.adapter.PetAdapter;
import fr.esiea.mobile.lostpets.model.Pets;

/**
 * Created by david on 29/10/2014.
 */
public class WebServiceDAO {

    private static final String QUERY_URL = "http://lostpets.herokuapp.com/";
    private static final Integer TIMED_OUT = 60 * 1000;
    private PetAdapter m_arrayAdapter;
    private Context m_context;

    public WebServiceDAO(Context context) {
        m_arrayAdapter = new PetAdapter(context);
        m_context = context;
    }

    public PetAdapter getInfos() throws Exception {
        String urlPets = URLEncoder.encode("pets.json", "UTF-8");
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(TIMED_OUT);
        client.get("http://lostpets.herokuapp.com/" + urlPets, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Successfully got a response
                String s_json = "";
                try {
                    //Translate byte[] to JSONObject
                    s_json = new String(responseBody, "UTF-8");
                    s_json = "{\"pets\": " + s_json + "}";
                    JSONObject json = new JSONObject(s_json);
                    Pets.getInstance().updatePets(json.optJSONArray("pets"));
                    m_arrayAdapter.notifyDataSetChanged();
                }
                catch (Exception e) {
                    //Print error if there is a failure connection with the webService
                    Toast.makeText(m_context, R.string.err_dataWebApi, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(m_context, R.string.err_contactWebApi, Toast.LENGTH_LONG).show();
            }
        });

        return m_arrayAdapter;
    }

    public PetAdapter postInfos() throws Exception {
        String urlPets = URLEncoder.encode("pets.json", "UTF-8");
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(TIMED_OUT);
        client.get("http://lostpets.herokuapp.com/" + urlPets, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //Successfully got a response
                String s_json = "";
                try {
                    //Translate byte[] to JSONObject
                    s_json = new String(responseBody, "UTF-8");
                    s_json = "{\"pets\": " + s_json + "}";
                    JSONObject json = new JSONObject(s_json);
                    Pets.getInstance().updatePets(json.optJSONArray("pets"));
                    m_arrayAdapter.notifyDataSetChanged();
                }
                catch (Exception e) {
                    //Print error if there is a failure connection with the webService
                    Toast.makeText(m_context, R.string.err_dataWebApi, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(m_context, R.string.err_contactWebApi, Toast.LENGTH_LONG).show();
            }
        });

        return m_arrayAdapter;
    }
}
