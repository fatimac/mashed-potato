package mashedpotato.com.lalang.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.SignUpCallback;

import mashedpotato.com.lalang.R;
import mashedpotato.com.lalang.models.User;
import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.fragment_signup)
public class Signup extends RoboFragment implements View.OnClickListener {

    @InjectView(R.id.signup_et_firstname)
    EditText etFirstName;
    @InjectView(R.id.signup_et_lastname)
    EditText etLastName;
    @InjectView(R.id.signup_et_username)
    EditText etUsername;
    @InjectView(R.id.signup_et_email)
    EditText etEmail;
    @InjectView(R.id.signup_et_password)
    EditText etPassword;
    @InjectView(R.id.signup_et_confirmpassword)
    EditText etConfirmPass;
    @InjectView(R.id.signup_btn_signup)
    Button btnSignup;

    public static Signup newInstance() {
        return new Signup();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_btn_signup:
                if (isAllFieldsValid()) {
                    User user = new User();
                    user.setFirstname(etFirstName.getText().toString());
                    user.setLastname(etLastName.getText().toString());
                    user.setUsername(etUsername.getText().toString());
                    user.setEmail(etEmail.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.addUserToParse(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                Log.d("signUpInBackground", "Hooray! Let them use the app now.");
                                getActivity().getSupportFragmentManager().popBackStack();
                            } else {
                                Log.d("signUpInBackground", e.getMessage());
                                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
                break;
        }
    }

    public boolean isAllFieldsValid() {
        String password = etPassword.getText().toString();
        String conPassword = etConfirmPass.getText().toString();

        if (etFirstName.getText().toString().isEmpty() || etLastName.getText().toString().isEmpty() || etUsername.getText().toString().isEmpty() || etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty() || etConfirmPass.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Fields must not be empty", Toast.LENGTH_LONG).show();
            return false;
        } else if (!password.contentEquals(conPassword)) {
            Toast.makeText(getActivity(), "Password did not much", Toast.LENGTH_LONG).show();
            return false;
        } else return true;
    }
}
