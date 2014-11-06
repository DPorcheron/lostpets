package fr.esiea.mobile.lostpets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.model.Pet;
import fr.esiea.mobile.lostpets.model.Pets;

/**
 * Created by david on 26/10/2014.
 */
//This class is the PetAdapter which manage pet list
public class PetAdapter extends BaseAdapter {

    Context m_context;

    private static LayoutInflater inflater;

    public PetAdapter(Context context) {
        this.m_context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    //Return the number of elements in Pets list
    public int getCount() {
        return Pets.getInstance().getPets().size();
    }

    @Override
    //Return the i-ème Pet in the Pets list
    public Object getItem(int i) {
        return Pets.getInstance().getPets().get(i);
    }

    @Override
    //Return the Pet with id i in the Pets list
    public long getItemId(int i) {
        return new Long(Pets.getInstance().getPets().get(i).getM_petId());
    }

    @Override
    //This method manage the holder view to load data faster
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        RowView holder;

        if (v == null) {
            v = inflater.inflate(R.layout.row_pet, null);
            //initialization of the ViewHolder
            holder = new RowView();
            holder.petPictureView = (ImageView) v.findViewById(R.id.row_img_pet);
            holder.petNameView = (TextView) v.findViewById(R.id.row_lbl_petName);
            holder.petRaceView = (TextView) v.findViewById(R.id.row_lbl_petRace);
            holder.petColourView = (TextView) v.findViewById(R.id.row_lbl_petColour);
            holder.petSexView = (TextView) v.findViewById(R.id.row_lbl_petSex);

            if (v.findViewById(R.id.layout_petInfos) != null) {
                holder.petTatooView = (TextView) v.findViewById(R.id.row_lbl_petTatoo);
                holder.lostAddressView = (TextView) v.findViewById(R.id.row_lbl_lostAddress);
                holder.lostZipCodeView = (TextView) v.findViewById(R.id.row_lbl_lostZipCode);
                holder.lostCityView = (TextView) v.findViewById(R.id.row_lbl_lostCity);
            }

            if (v.findViewById(R.id.layout_ownerInfos) != null) {
                holder.userFirstName = (TextView) v.findViewById(R.id.row_lbl_userFirstName);
                holder.userLastName = (TextView) v.findViewById(R.id.row_lbl_userLastName);
                holder.userAddress = (TextView) v.findViewById(R.id.row_lbl_userAddress);
                holder.userZipCode = (TextView) v.findViewById(R.id.row_lbl_userZipCode);
                holder.userCity = (TextView) v.findViewById(R.id.row_lbl_userCity);
                holder.userPhone = (TextView) v.findViewById(R.id.row_lbl_userPhoneNumber);
            }
            //save holder
            v.setTag(holder);
        }
        else {
            holder = (RowView)v.getTag();
        }
        Pet pet = (Pet) getItem(i);

        holder.petNameView.setText(pet.getM_petName());
        holder.petRaceView.setText(pet.getM_petRace());
        holder.petColourView.setText(pet.getM_petColour());
        holder.petSexView.setText(pet.getM_petSex());

        if (v.findViewById(R.id.layout_petInfos) != null) {
            holder.petTatooView.setText(pet.getM_petTatoo());
            holder.lostAddressView.setText(pet.getM_petLostAddress());
            holder.lostZipCodeView.setText(pet.getM_petLostZipcode());
            holder.lostCityView.setText(pet.getM_petLostCity());
        }

        if (v.findViewById(R.id.layout_ownerInfos) != null) {
            holder.userFirstName.setText(pet.getM_petOwnerFirstName());
            holder.userLastName.setText(pet.getM_petOwnerLastName());
            holder.userAddress.setText(pet.getM_petOwnerAddress());
            holder.userZipCode.setText(pet.getM_petOwnerZipCode());
            holder.userCity.setText(pet.getM_petOwnerCity());
            holder.userPhone.setText(pet.getM_petOwnerPhone());
        }

        //Loads picture in the holder with Picasso librairy
        if (pet.getM_petPicture() != null && !pet.getM_petPicture().equals("")){
            Picasso.with(m_context)
                    .load(pet.getM_petPicture())
                    .placeholder(R.drawable.no_available_image)
                    .error(R.drawable.no_available_image)
                    .into(holder.petPictureView);
        }
        else {
            Picasso.with(m_context)
                    .load(R.drawable.no_available_image)
                    .placeholder(R.drawable.no_available_image)
                    .error(R.drawable.no_available_image)
                    .into(holder.petPictureView);
        }

        return v;
    }

    static class RowView {
        TextView petNameView, petRaceView, petColourView, petSexView, petTatooView,
                lostAddressView, lostZipCodeView, lostCityView,
                userFirstName, userLastName, userAddress, userZipCode, userCity, userPhone;
        ImageView petPictureView;
    }
}
