package mashedpotato.com.lalang;

import android.content.Context;

import com.parse.Parse;

/**
 * Created by fatima.g.m.carillo on 10/20/2015.
 */
public class MashedParse {

    public static final String OBJ_USER = "User";

    public static void initialize(Context context) {
        Parse.enableLocalDatastore(context);
        Parse.initialize(context, context.getString(R.string.parse_application_id), context.getString(R.string.parse_client_key));
    }

}
