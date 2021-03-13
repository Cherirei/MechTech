package com.example.mechtech;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfileFrament extends Fragment {

    private View view;
    TextView name, username, email, phoneNo, username2;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_profile_frament, container, false);

        name = view.findViewById(R.id.profile_name);
        username = view.findViewById(R.id.profile_username);
        username2 = view.findViewById(R.id.username2);
        email = view.findViewById(R.id.profile_email);
        phoneNo = view.findViewById(R.id.profile_phoneNo);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                username2.setText(documentSnapshot.getString("UserName"));
                name.setText(documentSnapshot.getString("Name"));
                username.setText(documentSnapshot.getString("UserName"));
                email.setText(documentSnapshot.getString("Email"));
                phoneNo.setText(documentSnapshot.getString("PhoneNo"));
            }
        });
        return view;
    }
}