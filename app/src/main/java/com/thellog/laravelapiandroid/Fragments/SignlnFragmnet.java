//package com.thellog.laravelapiandroid.Fragments;
//
//import android.app.AlertDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.android.material.textfield.TextInputLayout;
//import com.thellog.laravelapiandroid.Constant;
//import com.thellog.laravelapiandroid.Home;
//import com.thellog.laravelapiandroid.Http;
//import com.thellog.laravelapiandroid.LocalStorage;
//import com.thellog.laravelapiandroid.LoginActivity;
//import com.thellog.laravelapiandroid.R;
//import com.thellog.laravelapiandroid.RegisterActivity;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SignlnFragmnet extends Fragment {
//
//    private View view;
//    private TextInputLayout layoutEmail, layoutPassword;
//    private TextInputEditText txtEmail , txtPassword;
//    private TextView txtSignUp;
//    private Button btnSignIn;
//    private Button btnHomes;
//    public SignlnFragmnet(){}
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.layout_sign_in, container , false);
//        btnHomes = view.findViewById(R.id.btnHome);
//
//        btnHomes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity() , Home.class);
//                startActivity(intent);
//            }
//        });
//        init();
//        return view;
//    }
//
//    private void init() {
//        layoutEmail = view.findViewById(R.id.txtLayoutEmailSignIn);
//        layoutPassword = view.findViewById(R.id.txtLayoutPasslSignIn);
//        txtEmail = view.findViewById(R.id.txtEmailSignIn);
//        txtPassword = view.findViewById(R.id.txtPassSignIn);
//        txtSignUp = view.findViewById(R.id.txtsignUp);
//        btnSignIn = view.findViewById(R.id.btnsignIn);
//
//        txtSignUp.setOnClickListener(v->{
//            //change fragment
//            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameAuthContainer , new SignUpFrament()).commit();
//        });
//
//        btnSignIn.setOnClickListener(v->{
//            //validate fields first
//            if(validate()){
//                //do something
//                login();
//
//            }
//
//
//        });
//
//        txtEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (txtEmail.getText().toString().isEmpty()) {
//                    layoutEmail.setErrorEnabled(false);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        txtPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                if(txtPassword.getText().toString().length() >7){
//                    layoutPassword.setErrorEnabled(false);
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//    }
//
//    private boolean validate()
//    {
//        if (txtEmail.getText().toString().isEmpty()){
//            layoutEmail.setErrorEnabled(true);
//            layoutEmail.setError("Email is Required");
//
//            return false;
//        }
//        if (txtPassword.getText().toString().length() < 0 ){
//            layoutPassword.setErrorEnabled(true);
//            layoutPassword.setError("Required is least 0 characters");
//            return false;
//        }
//        return true;
//    }
//
//    private void login()
//    {
//        StringRequest request = new StringRequest(Request.Method.POST, Constant.LOGIN , response -> {
//            //ve get response if connection success
//            try {
//                JSONObject object = new JSONObject(response);
//
//                if(object.getBoolean("success")){
//                    JSONObject user = object.getJSONObject("Uesr");
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }, error -> {
//            error.printStackTrace();
//        })
//        {
//            //add parametes
//
//
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("email" , txtEmail.getText().toString().trim());
//                map.put("password" , txtPassword.getText().toString());
//                return map;
//            }
//        };
//        RequestQueue queue = Volley.newRequestQueue(getContext());
//        queue.add(request);
//    }