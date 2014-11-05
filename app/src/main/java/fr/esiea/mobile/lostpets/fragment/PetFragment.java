package fr.esiea.mobile.lostpets.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.activity.TakePictureActivity;
import fr.esiea.mobile.lostpets.model.Pet;
import fr.esiea.mobile.lostpets.model.Pets;
import fr.esiea.mobile.lostpets.util.PictureFileManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PetFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PetFragment extends Fragment implements View.OnClickListener {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PET_ID = "petId";
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private Pet m_pet;

    private OnFragmentInteractionListener m_listener;
    private Integer m_petId;
    private RelativeLayout m_layoutMain;

    private ImageView m_imgPet;
    private TextView m_lblPetName;
    private TextView m_lblPetRace;
    private TextView m_lblPetColour;
    private TextView m_lblPetSex;
    private TextView m_lblPetTatoo;
    private TextView m_lblLostAddress;
    private TextView m_lblLostZipCode;
    private TextView m_lblLostCity;

    private TextView m_lblUserFirstName;
    private TextView m_lblUserLastName;
    private TextView m_lblUserAddress;
    private TextView m_lblUserZipCode;
    private TextView m_lblUserCity;
    private TextView m_lblUserPhone;

    private Button m_btnTakePicture;

    public PetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param petId Id of the pet to display.

     * @return A new instance of fragment PetFragment.
     */
    public static PetFragment newInstance(Integer petId) {
        PetFragment fragment = new PetFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PET_ID, petId);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            m_petId = getArguments().getInt(ARG_PET_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fgt_pet, container, false);

        m_layoutMain = (RelativeLayout)rootView.findViewById(R.id.layout_main);

        m_imgPet = (ImageView)rootView.findViewById(R.id.row_img_pet);
        m_lblPetName = (TextView)rootView.findViewById(R.id.row_lbl_petName);
        m_lblPetRace = (TextView)rootView.findViewById(R.id.row_lbl_petRace);
        m_lblPetColour = (TextView)rootView.findViewById(R.id.row_lbl_petColour);
        m_lblPetSex = (TextView)rootView.findViewById(R.id.row_lbl_petSex);
        m_lblPetTatoo = (TextView)rootView.findViewById(R.id.row_lbl_petTatoo);
        m_lblLostAddress = (TextView)rootView.findViewById(R.id.row_lbl_lostAddress);
        m_lblLostZipCode = (TextView)rootView.findViewById(R.id.row_lbl_lostZipCode);
        m_lblLostCity = (TextView)rootView.findViewById(R.id.row_lbl_lostCity);

        m_lblUserFirstName = (TextView)rootView.findViewById(R.id.row_lbl_userFirstName);
        m_lblUserLastName = (TextView)rootView.findViewById(R.id.row_lbl_userLastName);
        m_lblUserAddress = (TextView)rootView.findViewById(R.id.row_lbl_userAddress);
        m_lblUserZipCode = (TextView)rootView.findViewById(R.id.row_lbl_userZipCode);
        m_lblUserCity = (TextView)rootView.findViewById(R.id.row_lbl_userCity);
        m_lblUserPhone = (TextView)rootView.findViewById(R.id.row_lbl_userPhoneNumber);

        m_btnTakePicture = (Button) rootView.findViewById(R.id.btn_takePicture);
        m_btnTakePicture.setOnClickListener(this);

        refresh(m_petId);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            m_listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        m_listener = null;
    }

    public void refresh(Integer petId) {

        m_pet = Pets.getInstance().getPetById(petId);

        if (m_pet != null) {
            m_layoutMain.setVisibility(View.VISIBLE);

            if (m_pet.getM_petPicture() != null){
                Picasso.with(this.getActivity())
                        .load(m_pet.getM_petPicture())
                        .placeholder(R.drawable.no_available_image)
                        .error(R.drawable.no_available_image)
                        .into(m_imgPet);
            }
            else {
                m_imgPet.setImageResource(R.drawable.no_available_image);
            }

            m_lblPetName.setText(m_pet.getM_petName());
            m_lblPetRace.setText(m_pet.getM_petRace());
            m_lblPetColour.setText(m_pet.getM_petColour());
            m_lblPetSex.setText(m_pet.getM_petSex());
            m_lblPetTatoo.setText(m_pet.getM_petTatoo());
            m_lblLostAddress.setText(m_pet.getM_petLostAddress());
            m_lblLostZipCode.setText(m_pet.getM_petLostZipcode());
            m_lblLostCity.setText(m_pet.getM_petLostCity());
            m_lblUserFirstName.setText(m_pet.getM_petOwnerFirstName());
            m_lblUserLastName.setText(m_pet.getM_petOwnerLastName());
            m_lblUserAddress.setText(m_pet.getM_petOwnerAddress());
            m_lblUserZipCode.setText(m_pet.getM_petOwnerZipCode());
            m_lblUserCity.setText(m_pet.getM_petOwnerCity());
            m_lblUserPhone.setText(m_pet.getM_petOwnerPhone());
        }
        else {
            //Don't show the pet infos in pet fragment
            m_layoutMain.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (R.id.btn_takePicture == view.getId()){
            takePicture();
        }
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                String fileName = PictureFileManager.createFileName(m_pet.getM_petName());
                photoFile = PictureFileManager.createImageFile(fileName);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(getActivity(), R.string.err_pictureFile + ":" + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            try {
                PictureFileManager.galleryAddPic(getActivity());

                Intent nextActivity = new Intent(this.getActivity(), TakePictureActivity.class);
                if (m_pet != null) {
                    nextActivity.putExtra("phone", m_pet.getM_petOwnerPhone());
                }
                startActivity(nextActivity);
            }
            catch (Exception e) {
                Toast.makeText(getActivity(), R.string.err_pictureFile + ":" + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
