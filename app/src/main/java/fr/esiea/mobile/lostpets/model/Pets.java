package fr.esiea.mobile.lostpets.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by david on 26/10/2014.
 */
public class Pets {

    private static Pets instance;
    public static Pets getInstance() {
        if (instance == null) {
            instance = new Pets();
        }
        return instance;
    }

    Map<Integer,Pet> lstPet = new HashMap<Integer,Pet>();
    private Pets(){

    }

    public ArrayList<Pet> getPets(){
        return new ArrayList(lstPet.values());
    }

    public Pet getPetById (Integer id){
        return lstPet.get(id);
    }

    public void resetPets(){
        lstPet.clear();
    }

    public void addPet (Pet pet){
        if (!lstPet.containsKey(pet.getM_petId())){
            lstPet.put(pet.getM_petId(), pet);
        }
    }

    public void updatePets(JSONArray array){
        resetPets();
        for (int i= 0; i< array.length(); i++){
            JSONObject json = array.optJSONObject(i);
            addPet(new Pet(json));
        }
    }
}
