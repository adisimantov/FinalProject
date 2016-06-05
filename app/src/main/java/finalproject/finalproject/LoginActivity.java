package finalproject.finalproject;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import finalproject.finalproject.Model.Model;

public class LoginActivity extends FragmentActivity {
    private static final String TAGGED_PLACES_PERMISSION = "user_tagged_places";
    private LoginButton loginButton;
    private AccessTokenTracker accessTokenTracker;
    private CallbackManager callbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void getHash() {

        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "finalproject.finalproject",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);

        if(AccessToken.getCurrentAccessToken() != null) {
            updateWithToken(AccessToken.getCurrentAccessToken());
        }
        else{

            callbackManager = CallbackManager.Factory.create();

            //getHash();

            Model.getInstance().init(this.getApplicationContext());
            Model.getInstance().setLastSyncTime(null);

            loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList(TAGGED_PLACES_PERMISSION));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {

                }

                @Override
                public void onCancel() {
                    //TODO: toast
                }

                @Override
                public void onError(FacebookException error) {
                    //TODO: toast
                }
            });


            accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    updateWithToken(currentAccessToken);
                }
            };
        }
    }

    private void updateWithToken(AccessToken currentAccessToken) {
        if (currentAccessToken != null) {
            //go to main.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else {
            //go to login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }
    }



      /*  info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("user_tagged_places"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Model.getInstance().getCheckinsFromFacebook();

                //AlarmReceiver.getInstance().init(getApplicationContext());
                new ServerConnection().prepareDataToServer(Calendar.getInstance(), 0,0);
*//*
                Model.getInstance().getAllLocalCheckinsAsync(new Model.GetCheckinsListener() {
                    @Override
                    public void onResult(List<Checkin> checkins) {
                        Log.d("MAIN", "total - " + checkins.size());
                        for (Checkin checkin :
                                checkins) {
                            Log.d("MAIN", checkin.getType() + " :: " + checkin.getCount() + " :: " + checkin.getTime().name());

                        }
                    }
                });*//*
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");
            }
        });*/
}

