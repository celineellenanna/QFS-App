package com.example.pewpew.qfs;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pewpew.qfs.domain.User;
import com.example.pewpew.qfs.service.ApiHttpCallback;
import com.example.pewpew.qfs.service.ApiHttpResponse;
import com.example.pewpew.qfs.service.AuthService;

import java.util.ArrayList;

public class AuthLoginFragment extends Fragment {


    public AuthLoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_auth_login, container, false);

        final EditText tfUsername = (EditText) viewRoot.findViewById(R.id.tfUsername);
        final EditText tfPassword = (EditText) viewRoot.findViewById(R.id.tfPassword);
        final Button btnLogin = (Button) viewRoot.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AuthService authService = AuthService.getInstance();

                String username = tfUsername.getText().toString();
                String password = tfPassword.getText().toString();

                if(username.length() > 0 && password.length() > 0) {
                    authService.login(tfUsername.getText().toString(), tfPassword.getText().toString(), new ApiHttpCallback<ApiHttpResponse<User>>() {
                        @Override
                        public void onCompletion(ApiHttpResponse<User> response) {
                            if (response.getSuccess()) {
                                ((MainActivity) getActivity()).changeFragment(new HomeFragment());
                                ((MainActivity) getActivity()).updateDrawerList();
                            } else {
                                Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(String message) {

                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Benutzername oder Passwort nicht eingegeben", Toast.LENGTH_LONG).show();
                }
            }
        });

        return viewRoot;
    }

}
