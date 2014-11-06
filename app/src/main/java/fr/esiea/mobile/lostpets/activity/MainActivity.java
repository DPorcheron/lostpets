package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.esiea.mobile.lostpets.R;

//This class is the MainActivity linked to activity_main.xml
public class MainActivity extends Activity implements View.OnClickListener {

    Button m_btnInfos;
    Button m_btnLostPet;
    Button m_btnFoundPet;
    Button m_btnLostPetAroundMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_btnLostPet = (Button) findViewById(R.id.btn_lostMyPet);
        m_btnLostPet.setOnClickListener(this);
        m_btnFoundPet = (Button) findViewById(R.id.btn_findPet);
        m_btnFoundPet.setOnClickListener(this);
        m_btnLostPetAroundMe = (Button) findViewById(R.id.btn_lostPetsAroundMe);
        m_btnLostPetAroundMe.setOnClickListener(this);
        m_btnInfos = (Button) findViewById(R.id.btn_infos);
        m_btnInfos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent nextActivity;
        if (R.id.btn_infos == view.getId()){
            nextActivity = new Intent(this, InfosActivity.class);
            startActivity(nextActivity);
        }
        else if (R.id.btn_findPet == view.getId()){
            nextActivity = new Intent(this, PetActivity.class);
            startActivity(nextActivity);
        }
        else if (R.id.btn_lostMyPet == view.getId()) {
            nextActivity = new Intent(this, CreateLostPetActivity.class);
            startActivity(nextActivity);
        }
        else if (R.id.btn_lostPetsAroundMe == view.getId()) {
            nextActivity = new Intent(this, MapsActivity.class);
            startActivity(nextActivity);
        }
    }
}
