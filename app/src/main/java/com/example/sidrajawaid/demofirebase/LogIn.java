package com.example.sidrajawaid.demofirebase;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogIn extends Fragment{
    EditText ed1;
    EditText ed2;
    TextView tv1;
    Button login;
    private FirebaseAuth firebaseAuth;
    public LogIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        ed1=view.findViewById(R.id.ed1);
        ed2=view.findViewById(R.id.ed2);
        login=view.findViewById(R.id.btnlogin);
        tv1=view.findViewById(R.id.text3);
        tv1.setClickable(true);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager frg_mng=getFragmentManager();
                FragmentTransaction fragmentTransaction=frg_mng.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.ltr,R.anim.rtl,R.anim.backpressenter,R.anim.backpressexit);
                SignUp fragment2=new SignUp();
                fragmentTransaction.replace(R.id.parentframelayout,fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMethod();
            }
        });
        return view;
    }
    public void loginMethod()
    {
        String username=ed1.getText().toString().trim();
        String password=ed2.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Signed In =   " + task.getResult().getAdditionalUserInfo(), Toast.LENGTH_SHORT).show();
                            Log.d("MainActivity", "Signed In =  " + task.getResult());
                        } else {
                            Toast.makeText(getContext(), "Problem Siging In =   " + task.getException(), Toast.LENGTH_SHORT).show();
                            Log.d("MainActivity", "Signed In =  " + task.getException());

                        }
                        //Exception block
                        /*
                        if(!task.isSuccessful()) {
                        try {
        throw task.getException();
    } catch(FirebaseAuthWeakPasswordException e) {
        mTxtPassword.setError(getString(R.string.error_weak_password));
        mTxtPassword.requestFocus();
    } catch(FirebaseAuthInvalidCredentialsException e) {
        mTxtEmail.setError(getString(R.string.error_invalid_email));
        mTxtEmail.requestFocus();
    } catch(FirebaseAuthUserCollisionException e) {
        mTxtEmail.setError(getString(R.string.error_user_exists));
        mTxtEmail.requestFocus();
    } catch(Exception e) {
        Log.e(TAG, e.getMessage());
    }
    }*/
                    }


                });

    }
}
