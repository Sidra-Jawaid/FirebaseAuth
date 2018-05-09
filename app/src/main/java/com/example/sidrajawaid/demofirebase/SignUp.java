package com.example.sidrajawaid.demofirebase;


import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends Fragment {
    EditText ed_username, ed_password, ed_retypepassword;
    Button btn;
    FirebaseAuth firebaseAuth;
    public SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        ed_username = v.findViewById(R.id.edittext1);
        ed_password = v.findViewById(R.id.edittext2);
        ed_retypepassword = v.findViewById(R.id.edittext3);
        btn = v.findViewById(R.id.btnSignup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticationMethod();
            }
        });
        return v;
    }
    public void authenticationMethod() {
       // validation for email address
        String username=ed_username.getText().toString().trim();
        String password=ed_password.getText().toString().trim();
        String repassword=ed_retypepassword.getText().toString().trim();
        if(username.contains("gmail"))
        {
            ed_username.setError("Not a valid domain");
        }
        if(TextUtils.isEmpty(username))
        {
            ed_username.setError("Required");
            return;
        }
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches())
        {
            ed_username.setError("Not a valid email address");
            return;
        }
        //validation for password
        if(TextUtils.isEmpty(password))
        {
            ed_password.setError("Required");
            return;
        }
        if(password.length()<6)
        {
            ed_password.setError("Atleast 6 characters");
            return;
        }
        //validation for retype password
        if(TextUtils.isEmpty(repassword))
        {
            ed_retypepassword.setError("Required");
            return;
        }
        if(!(password.equals(repassword)))
        {
            ed_retypepassword.setError("Password doesnot match");
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("MainActivity", "Signed Up");
                            Toast.makeText(getContext(), "Signed Up", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("MainActivity", " error signing up = " + task.getException());
                            Toast.makeText(getContext(), "error signing up"+ task.getException() ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}




