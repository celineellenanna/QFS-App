package ch.hsr.qfs.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hsr.qfs.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        final EditText etFirstname = (EditText) viewRoot.findViewById(R.id.etFirstname);
        final EditText etLastname = (EditText) viewRoot.findViewById(R.id.etLastname);
        final EditText etEmail = (EditText) viewRoot.findViewById(R.id.etEmail);
        final EditText etUsername = (EditText) viewRoot.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) viewRoot.findViewById(R.id.etPassword);
        final EditText etPasswordRepeat = (EditText) viewRoot.findViewById(R.id.etPasswordRepeat);
        final Button btnRegister = (Button) viewRoot.findViewById(R.id.btnRegister);

        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String email = etEmail.getText().toString();
                    if (!isValidEmail(email)) {
                        etEmail.setError("E-Mail ungültig");
                    }
                }
            }
        });

        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String password = etPassword.getText().toString();
                    //Log.d("QFS", "Passwort: " + password);
                    if (!isValidPassword(password)) {
                        etPassword.setError("Passwort zu kurz (mind. 6 Zeichen)");
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = etFirstname.getText().toString();
                String lastname = etLastname.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordRepeat = etPasswordRepeat.getText().toString();

                if (firstname.matches("")) {
                    etFirstname.setError("Bitte Vorname eingeben");
                }
                if (lastname.matches("")) {
                    etLastname.setError("Bitte Nachname eingeben");
                }
                if (email.matches("")) {
                    etEmail.setError("Bitte E-Mail eingeben");
                }
                if (username.matches("")) {
                    etUsername.setError("Bitte Username eingeben");
                }
                if (password.matches("")) {
                    etPassword.setError("Bitte Passwort eingeben");
                }

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

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "hsr.ch";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        } else {
            return false;
        }
    }
}
