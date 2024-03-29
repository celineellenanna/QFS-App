package ch.hsr.qfs.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class AuthLoginFragment extends Fragment {

    private AuthService authService = AuthService.getInstance();

    public AuthLoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_auth_login, container, false);

        ((MainActivity) getActivity()).changeToolbarTitle("Anmelden");
        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        final EditText etUsername = (EditText) viewRoot.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) viewRoot.findViewById(R.id.etPassword);
        final Button btnLogin = (Button) viewRoot.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean valid = true;

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.matches("")) {
                    etUsername.setError("Bitte Benutzername eingeben");
                    valid = false;
                }
                if (password.matches("")) {
                    etPassword.setError("Bitte Passwort eingeben");
                    valid = false;
                }

                if (valid) {
                    authService.login(username, password, new ApiHttpCallback<ApiHttpResponse<User>>() {
                        @Override
                        public void onCompletion(ApiHttpResponse<User> response) {
                            if (response.getSuccess()) {
                                ((MainActivity) getActivity()).setNavigationViewValues();
                                ((MainActivity) getActivity()).changeFragment(new QuizHomeFragment());
                                ((MainActivity) getActivity()).updateNavigationList();
                            } else {
                                Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(String message) {

                        }
                    });
                }
            }
        });

        return viewRoot;
    }
}
