package com.srikanth.cinemareview;

import android.content.Intent;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {


    MaterialButton logout;
    TextView name,email;
    CircleImageView profileimage;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    public ProfileFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_frag_profile, container, false);



        name=(TextView)rootView.findViewById(R.id.profile_name);
        email=(TextView)rootView.findViewById(R.id.profile_email);
        profileimage=(CircleImageView) rootView.findViewById(R.id.profile_image);
        logout=(MaterialButton) rootView.findViewById(R.id.logout_btn);


         firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
        if (firebaseUser!=null)
        {
            Glide.with(getActivity())
                    .load(firebaseUser.getPhotoUrl())
                    .into(profileimage);

            name.setText(firebaseUser.getDisplayName());
            email.setText(firebaseUser.getEmail());
        }


        googleSignInClient= GoogleSignIn.getClient(getActivity(), GoogleSignInOptions.DEFAULT_SIGN_IN);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            firebaseAuth.signOut();
                            Toast.makeText(getActivity(), "Succesfully Logout", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(),Sigin.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        }

                    }
                });
            }
        });
        return rootView;
    }

    }
