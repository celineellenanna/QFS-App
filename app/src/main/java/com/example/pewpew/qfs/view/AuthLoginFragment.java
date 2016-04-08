package com.example.pewpew.qfs.view;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pewpew.qfs.R;
import com.example.pewpew.qfs.domain.User;
import com.example.pewpew.qfs.service.apiclient.ApiHttpCallback;
import com.example.pewpew.qfs.service.apiclient.ApiHttpResponse;
import com.example.pewpew.qfs.service.AuthService;

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

                authService.login(tfUsername.getText().toString(), tfPassword.getText().toString(), new ApiHttpCallback<ApiHttpResponse<User>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<User> response) {
                        if (response.getSuccess()) {
                            ((MainActivity) getActivity()).changeFragment(new QuizHomeFragment());
                            ((MainActivity) getActivity()).updateDrawerList();
                        } else {
                            Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });

        return viewRoot;
    }

}
