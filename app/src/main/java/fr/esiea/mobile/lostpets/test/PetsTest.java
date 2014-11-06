package fr.esiea.mobile.lostpets.test;

import android.test.InstrumentationTestCase;

import junit.framework.Assert;

import org.json.JSONObject;

import fr.esiea.mobile.lostpets.model.Pets;

/**
 * Created by david on 06/11/2014.
 */
//This class allows to test data models in this application
public class PetsTest extends InstrumentationTestCase {
    private static String json = "[{\"id\":1,\"name\":\"Médor\",\"race\":\"Cocker\",\"color\":\"Noir\",\"sex\":\"M\",\"tattoo\":\"76AFR886\",\"address\":\"7 avenue des sapins\",\"zipcode\":\"94100\",\"city\":\"Saint-Maur-Des-Fosses\",\"picture\":\"http://data.photos-animaux.com/photos/495/4950/494980.jpg\",\"ownerFirstName\":\"Arthur\",\"ownerLastName\":\"Kirsz\",\"ownerAddress\":\"38 rue Albert-de-Mun\",\"ownerZipcode\":\"94100\",\"ownerCity\":\"Saint-Maur-Des-Fosse\",\"ownerPhone\":\"0623768867\",\"url\":\"https://lostpets.herokuapp.com/pets/1.json\"}]";

    public void testUpdatePets() {
        try {
            String s_json = new String(json.getBytes(), "UTF-8");
            s_json = "{\"pets\": " + s_json + "}";
            JSONObject json = new JSONObject(s_json);
            Pets.getInstance().updatePets(json.optJSONArray("pets"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(Pets.getInstance().getPets().size(), 1);
    }

    public void testGetPetById() {
        testUpdatePets();
        String waitedName = "Médor";
        String realName = Pets.getInstance().getPetById(1).getM_petName();
        Assert.assertEquals(realName, waitedName);
    }

    public void testResetPets() {
        Pets.getInstance().resetPets();
        Assert.assertTrue(Pets.getInstance().getPets().isEmpty());
    }
}
