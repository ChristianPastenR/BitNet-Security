package com.example.bitnetsecurity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class registro_final extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int GALLERY_INTENT = 2;
    private StorageReference mStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_final);
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    public void foto(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void cargarImagen(View v) {
        Permisos p = new Permisos(getApplicationContext());
        if (p.checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            Intent openPictureIntent = new Intent(Intent.ACTION_PICK);
            openPictureIntent.setType("image/*");
            startActivityForResult(openPictureIntent, GALLERY_INTENT);
        }
    }

    public void mostrarImagen(View v){
        //Recuperar la URI desde la BDD
        ImageView imFoto = (ImageView) findViewById(R.id.ivFoto);
        Glide.with(this)
                .load("https://firebasestorage.googleapis.com/v0/b/bitnet-739b0.appspot.com/o/images%2Ffoto.jpg?alt=media&token=10730fe1-5e4d-4837-9e7a-f31a613e22a1")
                .fitCenter()
                .centerCrop()
                .into(imFoto);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE &&  resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imFoto = (ImageView) findViewById(R.id.ivFoto);
            imFoto.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,500,500,false));

            StorageReference fotoRef = mStorage.child("images/foto.jpg");
            //Generar arreglo de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Convertir bitmap al formato y calidad deseado
            imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] data1 = baos.toByteArray();
            //Subir a firebase
            UploadTask uploadTask = fotoRef.putBytes(data1);
        }else if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Foto cargada", Toast.LENGTH_SHORT).show();

                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            //Guardamos la ruta de la foto cargada en firebase
                            ImageView imFoto = (ImageView) findViewById(R.id.ivFoto);
                            Glide.with(registro_final.this)
                                    .load(uri)
                                    .fitCenter()
                                    .centerCrop()
                                    .into(imFoto);
                        }
                    });

                }
            });
        }
    }

    public void atras (View v){
        Intent i = new Intent(this,registro.class);
        startActivity(i);
    }

    public void terminosYcondiciones(View v){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Terminos y condiciones");
        builder.setMessage(Html.fromHtml("<pre>1. ACEPTACIÓN \n" +
                "En el presente documento (en adelante, el “Contrato”) se establecen los términos y condiciones de Robert Half Internacional Empresa de Servicios Transitorios Limitada, con domicilio en Avenida Isidora Goyenechea 2800 Piso 15. Torre Titanium 7550-647 Las Condes, que serán de aplicación al acceso y uso por parte del Usuario de esta página web (el  “Sitio Web”). Les rogamos lean atentamente el presente Contrato. </pre>"));




        builder.setNegativeButton("Salir",null);
        builder.create();
        builder.show();
    }


}