package ch.hsr.qfs.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hsr.qfs.R;
import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;
import ch.hsr.qfs.service.AuthService;

public class AuthRegisterFragment extends Fragment {


    public AuthRegisterFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_auth_register, container, false);

        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        final EditText tfFirstname = (EditText) viewRoot.findViewById(R.id.tfFirstname);
        final EditText tfLastname = (EditText) viewRoot.findViewById(R.id.tfLastname);
        final EditText tfEmail = (EditText) viewRoot.findViewById(R.id.tfEmail);
        final EditText tfUsername = (EditText) viewRoot.findViewById(R.id.tfUsername);
        final EditText tfPassword = (EditText) viewRoot.findViewById(R.id.tfPassword);
        final EditText tfPasswordRepeat = (EditText) viewRoot.findViewById(R.id.tfPasswordRepeat);
        final Button btnRegister = (Button) viewRoot.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = tfFirstname.getText().toString();
                String lastname = tfLastname.getText().toString();
                String email = tfEmail.getText().toString();
                String username = tfUsername.getText().toString();
                String password = tfPassword.getText().toString();
                String passwordRepeat = tfPasswordRepeat.getText().toString();

                if (password.equals(passwordRepeat)) {
                    AuthService authService = AuthService.getInstance();
                    authService.register(firstname, lastname, email, username, password, new ApiHttpCallback<ApiHttpResponse<User>>() {
                        @Override
                        public void onCompletion(ApiHttpResponse<User> response) {
                            if (response.getSuccess()) {
                                Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                                ((MainActivity) getActivity()).changeFragment(new AuthLoginFragment());
                            } else {
                                Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(String message) {
                            Log.d("QFS - Error", message);
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Passwort stimmt nicht überein", Toast.LENGTH_LONG).show();
                }
            }
        });

        return viewRoot;
    }

}
