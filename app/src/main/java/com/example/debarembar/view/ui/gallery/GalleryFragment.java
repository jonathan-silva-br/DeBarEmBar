package com.example.debarembar.view.ui.gallery;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.example.debarembar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class GalleryFragment extends Fragment {
    private static final int PERMISSAO_REQUEST = 2222;
    private Button buttonGaleria, buttonCamera;
    private ImageView imgPerfil;
    private static final int GALERIA_IMAGENS = 111;
    private static final int CAPTURAR_IMAGEM = 2222;
    private Uri uri;
    private ImageView mImage;
    private Uri mImageUri;
    private String nomeimagem;
    private View rootView;
    private Context context;
    private FirebaseUser user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),

                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSAO_REQUEST);
            }
            final List<PackageInfo> packs = getActivity().getPackageManager().getInstalledPackages(0);
            buttonCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent takePictureIntent = new
                            Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity((PackageManager) packs) != null) {
                        startActivityForResult(takePictureIntent, CAPTURAR_IMAGEM);

                    }
                }
            });
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        try{
            mImageUri = user.getPhotoUrl();

        }catch(Exception e){
            Log.e("ERROZAO",e.getMessage());
        }


        Log.e("FirebaseUser",String.valueOf(user.getPhoneNumber()));

        buttonGaleria = root.findViewById(R.id.buttonGaleria);
        buttonCamera = root.findViewById(R.id.buttonCamera);
        imgPerfil = root.findViewById(R.id.imgPerfil);

        if(mImageUri!= null){
            Bitmap thumbnail;
            String path = null;
            try {
                thumbnail = getBitmapFromUri(root.getContext(),mImageUri);
                thumbnail = rotationBitMap(thumbnail);
                imgPerfil.setImageBitmap(thumbnail);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        buttonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_IMAGENS);


            }
        });

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 0);

        imgPerfil = root.findViewById(R.id.imgPerfil);
        root.findViewById(R.id.buttonCamera).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tirarFoto();

            }


            @SuppressLint("RestrictedApi")
            public void tirarFoto() {
                File diretorio = Environment
                        .getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES);
                nomeimagem = diretorio.getPath() + "/" +
                        System.currentTimeMillis() +
                        ".jpg";
                //uri = Uri.fromFile(new File(nomeImagem));
                uri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getApplicationContext().getPackageName() + ".provider", new File(nomeimagem));
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, CAPTURAR_IMAGEM);


            }
        });
        rootView = root;
        return root;
    }


    //Declara um atributo para guardar o context.
    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALERIA_IMAGENS) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = context.getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                thumbnail = rotationBitMap(thumbnail);
                imgPerfil.setImageBitmap(thumbnail);

                mImageUri = Uri.fromFile(new File(picturePath));

                Log.e("Uri imagem", String.valueOf(mImageUri));

                UserProfileChangeRequest.Builder novaFoto =  new UserProfileChangeRequest.Builder();
                novaFoto.setPhotoUri(mImageUri);

                user.updateProfile(novaFoto.build());

                Log.e("fotoUri",String.valueOf(user.getPhotoUrl()));
            }
        }
        if (requestCode == CAPTURAR_IMAGEM) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = context.getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(nomeimagem));
                thumbnail = rotationBitMap(thumbnail);
                imgPerfil.setImageBitmap(thumbnail);
                mostrarMensagem("Imagem capturada!");
                adicionarNaGaleria();
            } else {
                mostrarMensagem("Imagem não capturada!");
            }
        }

    }

    private void adicionarNaGaleria() {
    }

    private void mostrarMensagem(String s) {
    }

    public static Bitmap rotationBitMap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setRotate(90);
        Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap.recycle();
        return bmRotated;
    }

    private void tirarFoto() {
        File diretorio = Environment
                .getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
        nomeimagem = diretorio.getPath() + "/" +
                System.currentTimeMillis() +
                ".jpg";
        uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(nomeimagem));


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, CAPTURAR_IMAGEM);
    }

    private Bitmap getBitmapFromUri(Context context, Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                context.getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
}




