package com.ftn.android.reimagined_tribble.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.httpclient.IBackEnd;
import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by ftn/tim
 */
@EActivity(R.layout.activity_signup)
public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    @ViewById(R.id.input_name)
    EditText _nameText;
    @ViewById(R.id.input_email)
    EditText _emailText;
    @ViewById(R.id.input_password)
    EditText _passwordText;
    @ViewById(R.id.btn_signup)
    Button _signupButton;
    @ViewById(R.id.link_login)
    TextView _loginLink;

    private ProgressDialog progressDialog;

    @RestService
    IBackEnd serviceClient;

    @Click(R.id.btn_signup)
    void signUpButton() {
        signup();
    }

    @Click(R.id.link_login)
    void loginButton() {
        finish();
    }

    @Background
    void RegisterUser(User user) {
        //Look in local db
        int localDB = User.find(User.class, "email=?", user.getEmail()).size();

        if (localDB != 0) {
            failedRegister();
            return;
        }

        try {
            Log.d(TAG, user.toString());
            serviceClient.registerUser(user);
            user.save();
        } catch (Exception e) {
            e.printStackTrace();
            failedRegister();
            return;
        }

        onSignupSuccess(user);
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        progressDialog = new ProgressDialog(SignupActivity.this, R.style.SignUpDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String userName = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        User user = new User(userName, password, 0, 0, email);
        RegisterUser(user);
    }

    @UiThread
    public void onSignupSuccess(User user) {
        _signupButton.setEnabled(true);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        loginPrefsEditor.putString("username", user.getEmail());
        loginPrefsEditor.putString("password", user.getPassword());
        loginPrefsEditor.commit();

        setResult(RESULT_OK, null);
        progressDialog.dismiss();
        MapsActivity_.intent(this).start();
        finish();
    }

    @UiThread
    void hideProgressDialog()
    {
        setResult(RESULT_FIRST_USER,null);
        progressDialog.hide();
        _signupButton.setEnabled(true);
    }

    @UiThread
    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();
        hideProgressDialog();
    }

    @UiThread
    void failedRegister() {
        Toast.makeText(getBaseContext(), "User with this email already exists!", Toast.LENGTH_LONG).show();
        hideProgressDialog();
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
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
