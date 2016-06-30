package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.httpclient.BackEnd;
import com.ftn.android.reimagined_tribble.httpclient.model.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;


    @ViewById(R.id.input_email) EditText _emailText;
    @ViewById(R.id.input_password) EditText _passwordText;
    @ViewById(R.id.btn_login) Button _loginButton;
    @ViewById(R.id.link_signup) TextView _signupLink;


    @Click(R.id.btn_login)
    void clickLoginButton(){
        login();
    }

    @Click(R.id.link_signup)
    void clickSignUpLink(){
        SignupActivity_.intent(this).startForResult(REQUEST_SIGNUP);
    }

    @AfterViews
    protected void init(){
        Stetho.initializeWithDefaults(this);

        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        _emailText.setText("admin@admin.com");
        _passwordText.setText("admin");
    }


    public void login() {
        Log.d(TAG, "Login");

        //TODO TEMPORARY THE VALIDATION IS DISABLE, DUE TO EASIER TESTING
        if (!validate()) {
            return;
        }

        com.ftn.android.reimagined_tribble.model.User user = new com.ftn.android.reimagined_tribble.model.User();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setEmail("admin@admin.com");
        user.save();



        _loginButton.setEnabled(false);

//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
//        List<User> users = userDatabase.getAllUsers();
//
        BackEnd backEnd = new BackEnd();
        Call<List<User>> users = backEnd.listUsers();
        try {
            Response<List<User>> userList = users.execute();
            List<User> userListBody = userList.body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(com.ftn.android.reimagined_tribble.model.User.find(com.ftn.android.reimagined_tribble.model.User.class, "email = ? and password =?", email, password).size()!=0){
            loginPrefsEditor.putString("username", email);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
            MapsActivity_.intent(this).start();
            onLoginSuccess();
        }
        else {
            onLoginFailed();
        }







//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);

    //    MapsActivity_.intent(this).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful activity_signup logic here
                // By default we just finish the Activity and log them in automatically
                MapsActivity_.intent(this).start();
                onLoginSuccess();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        if(!loginPreferences.getString("username", "").equals(""))
        {
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

    public void onLoginSuccess() {
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

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
