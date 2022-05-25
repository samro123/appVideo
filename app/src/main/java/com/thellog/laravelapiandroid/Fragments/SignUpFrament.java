package com.thellog.laravelapiandroid.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thellog.laravelapiandroid.R;

public class SignUpFrament extends Fragment {
    private View view;

    private TextInputLayout layoutEmail, layoutPassword , layoutConfirm;
    private TextInputEditText txtEmail , txtPassword, txtConfirm;
    private TextView txtSignIn;
    private Button btnSignUp;
    public SignUpFrament(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_sign_up, container , false);
        init();
        return view;

    }
    private void init() {
        layoutEmail = view.findViewById(R.id.txtLayoutEmailSignUp);
        layoutPassword = view.findViewById(R.id.txtLayoutPasslSignUp);
        layoutConfirm = view.findViewById(R.id.txtLayoutCFPasslSignUp);
        txtEmail = view.findViewById(R.id.txtEmailSignUp);
        txtPassword = view.findViewById(R.id.txtPassSignUp);
        txtConfirm = view.findViewById(R.id.txtCFPassSignUp);
        txtSignIn = view.findViewById(R.id.txtsignIn);
        btnSignUp = view.findViewById(R.id.btnsignUp);


        txtSignIn.setOnClickListener(v->{
            //change fragment
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer , new SignlnFragmnet()).commit();
        });

        btnSignUp.setOnClickListener(v->{
            //validate fields first
            if(validate()){
                //do something
            }

        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtEmail.getText().toString().isEmpty()) {
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(txtPassword.getText().toString().length() >7){
                    layoutPassword.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtConfirm.getText().toString().equals(txtPassword.getText().toString())) {
                    layoutConfirm.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private boolean validate()
    {
        if (txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");

            return false;
        }
        if (txtPassword.getText().toString().length()<0){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 0 characters");

            return false;
        }
        if (txtConfirm.getText().toString().equals(txtPassword.getText().toString())){
            layoutConfirm.setErrorEnabled(true);
            layoutConfirm.setError("Password dose not match");

            return false;
        }
        return true;
    }
}
