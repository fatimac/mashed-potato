package mashedpotato.com.lalang;

import android.os.Bundle;

import mashedpotato.com.lalang.fragments.Login;
import mashedpotato.com.lalang.listeners.OnFragmentInteractionListener;
import roboguice.activity.RoboFragmentActivity;
import roboguice.fragment.RoboFragment;

public class MainActivity extends RoboFragmentActivity implements OnFragmentInteractionListener {

    public static final int FRAGMENT_ADD = 0;
    public static final int FRAGMENT_REPLACE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MashedParse.initialize(this);

        //getSupportFragmentManager().beginTransaction().add(R.id.main_content, Login.newInstance()).commit();
        onFragmentTransact(Login.newInstance(), FRAGMENT_ADD);
    }

    @Override
    public void onFragmentTransact(RoboFragment fragment, int transaction) {
        if (transaction == FRAGMENT_REPLACE)
            getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment).commit();
        else
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, fragment).addToBackStack(null).commit();
    }
}
