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
public class PetAdapter extends BaseAdapter {

    Context context;

    private static LayoutInflater inflater;

    public PetAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Pets.getInstance().getPets().size();
    }

    @Override
    public Object getItem(int i) {
        return Pets.getInstance().getPets().get(i);
    }

    @Override
    public long getItemId(int i) {
        return new Long(Pets.getInstance().getPets().get(i).getM_petId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // on garde une valeur de la vue
        View v = view;
        // on garde une valeur du holder
        RowView holder;
        // on regarde si on peut recycler une vue
        if (v == null) {
            //on récupère le désérialiseur de layout
            v = inflater.inflate(R.layout.row_pet, null);
            //initialisation du ViewHolder
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
            //on sauvegarde notre holder
            v.setTag(holder);
        }
        else {
            holder = (RowView)v.getTag();
        }
        Pet pet = (Pet) getItem(i);

        // On asign directement les valeurs à notre holder
        holder.petPictureView.setImageResource(R.drawable.no_available_image);
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

        if (pet.getM_petPicture() != null){
            Picasso.with(context)
                    .load(pet.getM_petPicture())
                    .placeholder(R.drawable.no_available_image)
                    .error(R.drawable.no_available_image)
                    .into(holder.petPictureView);
        }
        else {
            holder.petPictureView.setImageResource(R.drawable.no_available_image);
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
