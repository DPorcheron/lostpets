package fr.esiea.mobile.lostpets.dao;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
                    // Create a new HttpClient and Post Header
                    String urlPets = URLEncoder.encode("pets", "UTF-8");
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost(QUERY_URL + urlPets);

                    try {
                        // Add data
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("name", pet.getM_petName()));
                        nameValuePairs.add(new BasicNameValuePair("race", pet.getM_petRace()));
                        nameValuePairs.add(new BasicNameValuePair("color", pet.getM_petColour()));
                        nameValuePairs.add(new BasicNameValuePair("sex", pet.getM_petSex()));
                        nameValuePairs.add(new BasicNameValuePair("tattoo", pet.getM_petTatoo()));
                        nameValuePairs.add(new BasicNameValuePair("address", pet.getM_petLostAddress()));
                        nameValuePairs.add(new BasicNameValuePair("zipcode", pet.getM_petLostZipcode()));
                        nameValuePairs.add(new BasicNameValuePair("city", pet.getM_petLostCity()));
                        nameValuePairs.add(new BasicNameValuePair("picture", pet.getM_petPicture()));
                        nameValuePairs.add(new BasicNameValuePair("ownerFirstName", pet.getM_petOwnerFirstName()));
                        nameValuePairs.add(new BasicNameValuePair("ownerLastName", pet.getM_petOwnerLastName()));
                        nameValuePairs.add(new BasicNameValuePair("ownerAddress", pet.getM_petOwnerAddress()));
                        nameValuePairs.add(new BasicNameValuePair("ownerZipcode", pet.getM_petOwnerZipCode()));
                        nameValuePairs.add(new BasicNameValuePair("ownerCity", pet.getM_petOwnerCity()));
                        nameValuePairs.add(new BasicNameValuePair("ownerPhone", pet.getM_petOwnerPhone()));
                        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                        // Execute HTTP Post Request
                        HttpResponse response = httpclient.execute(httppost);
                        System.out.println(inputStreamToString(response.getEntity().getContent()));

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
