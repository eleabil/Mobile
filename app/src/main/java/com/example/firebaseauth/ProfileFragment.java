package com.example.firebaseauth;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;
    private TextInputLayout newUsernameLayout;
    private TextInputEditText newUsernameField;
    private TextInputLayout newEmailLayout;
    private TextInputEditText newEmailField;
    private TextView newUsernameTv;
    private Button logoutBtn;
    private Button usernameSubmitBtn;
    private Button emailSubmitBtn;
    private Button pictureBtn;
    private TextView username;
    private TextView email;
    private ImageView profilePicture;
    private FirebaseUser user;
    private StorageReference Folder;
    private StorageReference ImageName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews(root);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        user = auth.getCurrentUser();
        if (user != null) {
            username.setText(user.getDisplayName());
            email.setText(user.getEmail());
            ImageName = Folder.child(user.getUid() + ".jpg");
            placeImage();
        } else {
            Toast.makeText(getActivity(),getString(R.string.error),Toast.LENGTH_SHORT).show();
        }

        usernameSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUsername = Objects.requireNonNull(newUsernameField.getText()).toString().trim();
                if (isUsernameValid(newUsername)){
                    username.setText(newUsername);
                    updateUsername(user, newUsername);
                }
            }
        });

        emailSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEmail = Objects.requireNonNull(newEmailField.getText()).toString().trim();
                if (isEmailValid(newEmail)){
                  //  newUsernameTv.setText(newEmail);
                    updateEmail(user, newEmail);
                }
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                Objects.requireNonNull(getActivity()).overridePendingTransition(0, 0);
            }
        });

        pictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProfilePicture();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                Uri ImageData = Objects.requireNonNull(data).getData();
                ImageName.putFile(Objects.requireNonNull(ImageData)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.i("My", "uploaded");
                        Toast.makeText(getActivity(),"Uploaded",Toast.LENGTH_SHORT).show();
                    }
                });

                placeImage();

            }
        }
    }

    private void initViews(View root){
        newUsernameLayout = root.findViewById(R.id.fragment_profile_layout_new_username);
        newUsernameField = root.findViewById(R.id.fragment_profile_new_username);
        newEmailLayout = root.findViewById(R.id.fragment_profile_layout_new_email);
        newEmailField = root.findViewById(R.id.fragment_profile_new_email);
        username = root.findViewById(R.id.fragment_profile_name);
        email = root.findViewById(R.id.fragment_profile_email);
        logoutBtn = root.findViewById(R.id.logout_btn);
        usernameSubmitBtn = root.findViewById(R.id.name_submit_btn);
        emailSubmitBtn = root.findViewById(R.id.email_submit_btn);
        pictureBtn = root.findViewById(R.id.upload_pic);
        profilePicture = root.findViewById(R.id.profile_picture_image_view);
        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
    }

    private void uploadProfilePicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void placeImage(){
        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri.toString()).into(profilePicture);                    }
        });
    }

    private void updateUsername(final FirebaseUser user, final String newName){
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(newName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(),"username updated: " + newName, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateEmail(FirebaseUser user, final String newEmail){
        user.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(),"email updated: " + newEmail,Toast.LENGTH_SHORT).show();
                Log.i("My_TAG", "new email " + newEmail);
            }
        });

    }

    private boolean isUsernameValid(final String username) {
        if (username.isEmpty()) {
            newUsernameLayout.setError(getString(R.string.enter_username));
            newUsernameLayout.requestFocus();
            return false;
        } else {
            newUsernameLayout.setError(null);
            return true;
        }
    }

    private boolean isEmailValid(final String email) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            newEmailLayout.setError(getString(R.string.enter_valid_email));
            newEmailLayout.requestFocus();
            return false;
        } else {
            newEmailLayout.setError(null);
            return true;
        }
    }
}
