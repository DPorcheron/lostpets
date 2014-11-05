package fr.esiea.mobile.lostpets.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import fr.esiea.mobile.lostpets.R;
import fr.esiea.mobile.lostpets.util.PictureFileManager;

public class TakePictureActivity extends Activity implements View.OnClickListener {

    TextView m_txtOwnerPhone;
    ImageView m_imgLostPetPicture;
    Button m_btnSharePicture;
    Bitmap m_imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        m_btnSharePicture = (Button) findViewById(R.id.btn_sharePicture);
        m_imgLostPetPicture = (ImageView) findViewById(R.id.row_img_pet);
        m_txtOwnerPhone = (TextView) findViewById(R.id.txt_ownerPhone);

        m_btnSharePicture.setOnClickListener(this);

        setImgView();

        if (getIntent().getExtras().get("phone") != null) {
            m_txtOwnerPhone.setText((String) getIntent().getExtras().get("phone"));
        }
    }

    private void setImgView() {
        File file = new File(PictureFileManager.getM_currentPhotoPath());
        Uri uri = Uri.fromFile(file);

        try {
            m_imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        }
        catch(Exception e) {
            Toast.makeText(this, R.string.err_pictureFile + ":" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        if (m_imageBitmap != null) {
            m_imgLostPetPicture.setImageBitmap(m_imageBitmap);
        }
    }

    @Override
    public void onClick(View view) {
        if (R.id.btn_sharePicture == view.getId()) {
            final Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/jpeg");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(PictureFileManager.getM_currentPhotoPath())));
            startActivity(Intent.createChooser(shareIntent, "Partager avec..."));
        }
    }
}
