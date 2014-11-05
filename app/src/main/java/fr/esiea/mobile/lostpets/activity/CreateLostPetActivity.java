package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.dao.UserDataSource;
import fr.esiea.mobile.lostpets.model.Pet;
import fr.esiea.mobile.lostpets.model.User;

public class CreateLostPetActivity extends Activity implements View.OnClickListener {

    private static String zipCodeRegex = "^\\d{5}(?:[-\\s]\\d{4})?$";
    private static String numberRegex = "[0-9]+";
    private static String textRegex = "[a-zA-Z]+";
    private static String sexRegex = "^[MF]{1}$";

    private EditText m_editUserFirstName;
    private EditText m_editUserLastName;
    private EditText m_editUserAddress;
    private EditText m_editUserZipCode;
    private EditText m_editUserCity;
    private EditText m_editUserPhone;

    private EditText m_editPetName;
    private EditText m_editPetColour;
    private EditText m_editPetRace;
    private EditText m_editPetSex;
    private EditText m_editPetTatoo;
    private EditText m_editPetPicture;
    private EditText m_editPetAddress;
    private EditText m_editPetZipCode;
    private EditText m_editPetCity;

    private Button m_btnSavelostPet;
    private UserDataSource m_dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lost_pet);

        m_btnSavelostPet = (Button) findViewById(R.id.btn_saveLostPet);

        m_editUserFirstName = (EditText) findViewById(R.id.edit_myFirstName);
        m_editUserLastName = (EditText) findViewById(R.id.edit_myLastName);
        m_editUserAddress = (EditText) findViewById(R.id.edit_myAddress);
        m_editUserZipCode = (EditText) findViewById(R.id.edit_myZipCode);
        m_editUserCity = (EditText) findViewById(R.id.edit_myCity);
        m_editUserPhone = (EditText) findViewById(R.id.edit_myPhone);

        m_editPetName = (EditText) findViewById(R.id.edit_petName);
        m_editPetRace = (EditText) findViewById(R.id.edit_petRace);
        m_editPetSex = (EditText) findViewById(R.id.edit_petSex);
        m_editPetColour = (EditText) findViewById(R.id.edit_petColour);
        m_editPetTatoo = (EditText) findViewById(R.id.edit_petTatoo);
        m_editPetPicture = (EditText) findViewById(R.id.edit_petPicture);
        m_editPetAddress = (EditText) findViewById(R.id.edit_petAddress);
        m_editPetZipCode = (EditText) findViewById(R.id.edit_petZipCode);
        m_editPetCity = (EditText) findViewById(R.id.edit_petCity);

        m_btnSavelostPet.setOnClickListener(this);

        if (initSQL()) {
            User values = m_dataSource.getUserById(0);

            if (values != null) {
                m_editUserLastName.setText(values.getM_userLastName());
                m_editUserFirstName.setText(values.getM_userFirstName());
                m_editUserAddress.setText(values.getM_userAddress());
                m_editUserZipCode.setText(values.getM_userZipCode());
                m_editUserCity.setText(values.getM_userCity());
                m_editUserPhone.setText(values.getM_userPhone());
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (R.id.btn_saveLostPet == view.getId()) {
            Intent nextActivity;
            //Reinitialize colour of edit texts
            setStandardTextColour();
            Pet pet = new Pet(0, m_editPetName.getText().toString(), m_editPetRace.getText().toString(),
                    m_editPetColour.getText().toString(), m_editPetSex.getText().toString(),
                    m_editPetTatoo.getText().toString(),
                    m_editPetPicture.getText().toString(), m_editPetAddress.getText().toString(),
                    m_editPetZipCode.getText().toString(), m_editPetCity.getText().toString(),
                    m_editUserFirstName.getText().toString(), m_editUserLastName.getText().toString(),
                    m_editUserAddress.getText().toString(), m_editUserZipCode.getText().toString(),
                    m_editUserCity.getText().toString(), m_editUserPhone.getText().toString());

            if (validate(pet)) {
                //Check if this user already exist
                User values = m_dataSource.getUserById(0);
                long result = 0;

                if (values != null) {
                    //Update this user in bdd
                    values.setM_userLastName(m_editUserLastName.getText().toString());
                    values.setM_userFirstName(m_editUserFirstName.getText().toString());
                    values.setM_userAddress(m_editUserAddress.getText().toString());
                    values.setM_userZipCode(m_editUserZipCode.getText().toString());
                    values.setM_userCity(m_editUserCity.getText().toString());
                    values.setM_userPhone(m_editUserPhone.getText().toString());

                    result = m_dataSource.updateUser(0, values);
                }
                else {
                    //Insert user in bdd
                    result = m_dataSource.insertUser(m_editUserLastName.getText().toString(),
                            m_editUserFirstName.getText().toString(),
                            m_editUserAddress.getText().toString(),
                            m_editUserZipCode.getText().toString(),
                            m_editUserCity.getText().toString(),
                            m_editUserPhone.getText().toString());
                }
                if (result != -1) {
                    Toast.makeText(this, R.string.msg_saveLostPet, Toast.LENGTH_LONG).show();
                    nextActivity = new Intent(this, MainActivity.class);
                    startActivity(nextActivity);
                }
                //Post information on webservice

                //If all sounds good then print message and go to main activity

            }
            else {
                Toast.makeText(this, R.string.err_lostPetWrongField, Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean initSQL() {
        boolean isGood = true;
        m_dataSource = new UserDataSource(this);
        try {
            m_dataSource.open();
        }
        catch (Exception e) {
            isGood = false;
            Toast.makeText(this, R.string.err_lostPetOpenBDD, Toast.LENGTH_LONG).show();
        }
        return isGood;
    }

    private boolean validate(Pet pet) {
        Boolean noError = true;
        if (!pet.getM_petOwnerLastName().matches(textRegex) || pet.getM_petOwnerLastName().equals("")) {
            m_editUserLastName.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petOwnerFirstName().matches(textRegex) || pet.getM_petOwnerFirstName().equals("")) {
            m_editUserFirstName.setTextColor(Color.RED);
            noError = false;
        }
        if (pet.getM_petOwnerAddress().equals("")) {
            m_editUserAddress.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petOwnerZipCode().matches(zipCodeRegex) || pet.getM_petOwnerZipCode().equals("")) {
            m_editUserZipCode.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petOwnerCity().matches(textRegex) || pet.getM_petOwnerCity().equals("")) {
            m_editUserCity.setTextColor(Color.RED);
            noError = false;
        }
        if (pet.getM_petOwnerCity().equals("")) {
            m_editUserPhone.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petName().matches(textRegex)) {
            m_editPetName.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petRace().matches(textRegex) || pet.getM_petRace().equals("")) {
            m_editPetRace.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petColour().matches(textRegex) || pet.getM_petColour().equals("")) {
            m_editPetColour.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petSex().matches(sexRegex) || pet.getM_petSex().equals("")) {
            m_editPetSex.setTextColor(Color.RED);
            noError = false;
        }
        if (pet.getM_petLostAddress().equals("")) {
            m_editPetAddress.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petLostZipcode().matches(zipCodeRegex) || pet.getM_petLostZipcode().equals("")) {
            m_editPetZipCode.setTextColor(Color.RED);
            noError = false;
        }
        if (!pet.getM_petLostCity().matches(textRegex) || pet.getM_petLostCity().equals("")) {
            m_editPetCity.setTextColor(Color.RED);
            noError = false;
        }
        return noError;
    }

    private void setStandardTextColour() {
        m_editUserFirstName.setTextColor(Color.BLACK);
        m_editUserLastName.setTextColor(Color.BLACK);
        m_editUserAddress.setTextColor(Color.BLACK);
        m_editUserZipCode.setTextColor(Color.BLACK);
        m_editUserCity.setTextColor(Color.BLACK);
        m_editUserPhone.setTextColor(Color.BLACK);

        m_editPetName.setTextColor(Color.BLACK);
        m_editPetColour.setTextColor(Color.BLACK);
        m_editPetRace.setTextColor(Color.BLACK);
        m_editPetSex.setTextColor(Color.BLACK);
        m_editPetTatoo.setTextColor(Color.BLACK);
        m_editPetPicture.setTextColor(Color.BLACK);
        m_editPetAddress.setTextColor(Color.BLACK);
        m_editPetZipCode.setTextColor(Color.BLACK);
        m_editPetCity.setTextColor(Color.BLACK);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (m_dataSource != null) {
            try {
                m_dataSource.open();
            }
            catch (Exception e) {
                Toast.makeText(this, R.string.err_lostPetOpenBDD, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (m_dataSource != null) {
            m_dataSource.close();
        }
    }

}
