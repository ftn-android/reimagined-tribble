package com.ftn.android.reimagined_tribble.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.httpclient.IBackEnd;
import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.SupposeBackground;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

import okhttp3.OkHttpClient;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private ProgressDialog progressDialog;

    @RestService
    IBackEnd serviceClient;

    @ViewById(R.id.input_email)
    EditText _emailText;
    @ViewById(R.id.input_password)
    EditText _passwordText;
    @ViewById(R.id.btn_login)
    Button _loginButton;
    @ViewById(R.id.link_signup)
    TextView _signupLink;


    @Click(R.id.btn_login)
    void clickLoginButton() {
        login();
    }

    @Click(R.id.link_signup)
    void clickSignUpLink() {
        SignupActivity_.intent(this).startForResult(REQUEST_SIGNUP);
    }

    @AfterViews
    protected void init() {
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        //TODO delete this hardcoded values
        _emailText.setText("a@a.com");
        _passwordText.setText("1234");
    }


    @Background
    void FetchUser(String email, String password) {

        if (progressDialog==null)
            return;

        //Look in local db
        int localDB = User.find(User.class, "email = ? and password =?", email, password).size();

        if (localDB != 0) {
            onLoginSuccess(email, password);
            return;
        }
        // no match? search backend for it
        try {
            User[] usersService = serviceClient.getUsersWithEmailAndPassword(email, password);
            int serviceDB = usersService.length;

            if (serviceDB != 0) {
                saveNewUserFromService(usersService[0]);
                onLoginSuccess(email, password);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        onLoginFailed();
    }

    @SupposeBackground
    void saveNewUserFromService(User userService) {
        userService.save();
    }

    public void login() {
        Log.d(TAG, "Login");

        //TODO TEMPORARY THE VALIDATION IS DISABLE, DUE TO EASIER TESTING
        if (!validate()) {
            return;
        }

        _loginButton.setEnabled(false);

        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        FetchUser(email, password);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
               // MapsActivity_.intent(this).start();
                onLoginSuccess();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        if (!loginPreferences.getString("username", "").equals("")) {
            MapsActivity_.intent(this).start();
            onLoginSuccess();
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        //TODO Get informed about this method
        moveTaskToBack(true);
    }

    @UiThread
    public void onLoginSuccess(String email, String password) {
        loginPrefsEditor.putString("username", email);
        loginPrefsEditor.putString("password", password);
        loginPrefsEditor.commit();
        progressDialog.dismiss();
        MapsActivity_.intent(this).start();
        onLoginSuccess();
    }

    public void onLoginSuccess() {
        finish();
    }

    @UiThread
    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        progressDialog.hide();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address/username");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
