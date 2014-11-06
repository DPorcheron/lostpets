package fr.esiea.mobile.lostpets.dao;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.adapter.PetAdapter;
import fr.esiea.mobile.lostpets.model.Pet;
import fr.esiea.mobile.lostpets.model.Pets;

/**
 * Created by david on 29/10/2014.
 */
//This class is the DAO for the wrb service
public class WebServiceDAO {

    private static final String QUERY_URL = "http://lostpets.herokuapp.com/";
    private static final Integer TIMED_OUT = 60 * 1000;
    private PetAdapter m_arrayAdapter;
    private Context m_context;
    private AsyncHttpClient m_client;

    public WebServiceDAO(Context context) {
        m_arrayAdapter = new PetAdapter(context);
        m_context = context;
    }

    //Execute GET function to the webservice
    public PetAdapter getInfos() throws Exception {
        String urlPets = URLEncoder.encode("pets.json", "UTF-8");
        m_client = new AsyncHttpClient();
        m_client.setTimeout(TIMED_OUT);
        m_client.get(QUERY_URL + urlPets, new AsyncHttpResponseHandler() {
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
                } catch (Exception e) {
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

    //TODO Execute POST function to the webservice
    public void postInfos(final Pet pet) throws Exception {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    // Add data
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("[{");
                    stringBuilder.append("\"name\":" + "\"" + pet.getM_petName() + "\"" + ",");
                    stringBuilder.append("\"race\":" + "\"" + pet.getM_petRace() + "\"" + ",");
                    stringBuilder.append("\"color\":" + "\"" + pet.getM_petColour() + "\"" + ",");
                    stringBuilder.append("\"sex\":" + "\"" + pet.getM_petSex() + "\"" + ",");
                    stringBuilder.append("\"tattoo\":" + "\"" + pet.getM_petTatoo() + "\"" + ",");
                    stringBuilder.append("\"address\":" + "\"" + pet.getM_petLostAddress() + "\"" + ",");
                    stringBuilder.append("\"zipcode\":" + "\"" + pet.getM_petLostZipcode() + "\"" + ",");
                    stringBuilder.append("\"city\":" + "\"" + pet.getM_petLostCity() + "\"" + ",");
                    stringBuilder.append("\"picture\":" + "\"" + pet.getM_petPicture() + "\"" + ",");
                    stringBuilder.append("\"ownerFirstName\":" + "\"" + pet.getM_petOwnerFirstName() + "\"" + ",");
                    stringBuilder.append("\"ownerLastName\":" + "\"" + pet.getM_petOwnerLastName() + "\"" + ",");
                    stringBuilder.append("\"ownerAddress\":" + "\"" + pet.getM_petOwnerAddress() + "\"" + ",");
                    stringBuilder.append("\"ownerZipcode\":" + "\"" + pet.getM_petOwnerZipCode() + "\"" + ",");
                    stringBuilder.append("\"ownerCity\":" + "\"" + pet.getM_petOwnerCity() + "\"" + ",");
                    stringBuilder.append("\"ownerPhone\":" + "\"" + pet.getM_petOwnerPhone() + "\"");
                    stringBuilder.append("}]");

                    // Create a new HttpClient and Post Header
                    String urlPets = URLEncoder.encode("pets", "UTF-8");
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(QUERY_URL + urlPets);

                    StringEntity se = new StringEntity(stringBuilder.toString());
                    //Set some headers to inform server about the type of the content
                    httppost.setHeader("Accept", "application/json");
                    httppost.setHeader("Content-type", "application/json");

                    try {

                        httppost.setEntity(se);

                        // Execute HTTP Post Request
                        HttpResponse response = httpclient.execute(httppost);

                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //Print webservice response
    private StringBuilder inputStreamToString(InputStream is) throws Exception {
        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }

        // Return full string
        return total;
    }
}
