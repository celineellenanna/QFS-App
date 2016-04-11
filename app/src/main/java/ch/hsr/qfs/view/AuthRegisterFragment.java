package ch.hsr.qfs.view;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hsr.pewpew.qfs.R;
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
                if (tfPassword.getText().toString().equals(tfPasswordRepeat.getText().toString())) {
                    AuthService authService = AuthService.getInstance();
                    authService.register(tfFirstname.getText().toString(), tfLastname.getText().toString(), tfEmail.getText().toString(), tfUsername.getText().toString(), tfPassword.getText().toString(), new ApiHttpCallback<ApiHttpResponse<User>>() {
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
