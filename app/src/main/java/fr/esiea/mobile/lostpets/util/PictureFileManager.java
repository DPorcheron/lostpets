package fr.esiea.mobile.lostpets.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.Normalizer;

/**
 * Created by david on 01/11/2014.
 */
public class PictureFileManager {

    private static String m_currentPhotoPath;

    public PictureFileManager() {
    }

    public static String createFileName (String param) {
        String petName = Normalizer.normalize(param, Normalizer.Form.NFD);
        petName = petName.replaceAll("[^\\p{ASCII}]", "");
        petName = petName + ".jpg";
        return petName;
    }

    public static File createImageFile(String fileName) throws IOException {
        // Create an image file name
        File picture = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName);

        // Save a file: path for use with ACTION_VIEW intents
        m_currentPhotoPath = picture.getAbsolutePath();
        return picture;
    }

    public static void galleryAddPic(Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(m_currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    public static String getM_currentPhotoPath() {
        return m_currentPhotoPath;
    }
}
