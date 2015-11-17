package mashedpotato.com.lalang.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import mashedpotato.com.lalang.MainActivity;
import mashedpotato.com.lalang.R;
import mashedpotato.com.lalang.listeners.OnFragmentInteractionListener;
import roboguice.RoboGuice;
import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.fragment_login)
public class Login extends RoboFragment implements View.OnClickListener {

    @InjectView(R.id.login_et_email)
    EditText etEmail;
    @InjectView(R.id.login_et_password)
    EditText etPassword;
    @InjectView(R.id.login_btn_login)
    Button btnLogin;
    @InjectView(R.id.login_btn_signup)
    Button btnSignup;
    @InjectView(R.id.login_btn_fb)
    LoginButton btnLoginFb;

    private OnFragmentInteractionListener fragmentListener;


    public static Login newInstance() {
        return new Login();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectMembersWithoutViews(this);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectMembersWithoutViews(this);
        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnLoginFb.setReadPermissions("user_friends");
        btnLoginFb.setFragment(this);

        CallbackManager callbackManager = CallbackManager.Factory.create();

        btnLoginFb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("facebook success", loginResult.);
            }

            @Override
            public void onCancel() {
                Log.e("facebook cancel", "");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("facebook error", exception.getMessage());
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        fragmentListener = (OnFragmentInteractionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn_signup:
                fragmentListener.onFragmentTransact(Signup.newInstance(), MainActivity.FRAGMENT_ADD);
                break;
            case R.id.login_btn_login:
                ParseUser.logInInBackground(etEmail.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Log.d("logInInBackground", "Hooray! The user is logged in.");
                        } else {
                            Log.d("logInInBackground", e.getMessage());
                        }
                    }
                });
                break;
        }
    }
}
