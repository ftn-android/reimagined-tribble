package com.ftn.android.reimagined_tribble.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.User;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by szberko
 */

@EActivity(R.layout.activity_signup)
public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @ViewById(R.id.input_name) EditText _nameText;
    @ViewById(R.id.input_email) EditText _emailText;
    @ViewById(R.id.input_password) EditText _passwordText;
    @ViewById(R.id.btn_signup) Button _signupButton;
    @ViewById(R.id.link_login) TextView _loginLink;


    @Click(R.id.btn_signup)
    void signUpButton(){
        signup();
    }

    @Click(R.id.link_login)
    void loginButton(){
        finish();
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.SignUpDialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own activity_signup logic here.
        if(User.find(User.class, "email=?",email).size()!=0){
            Toast.makeText(getBaseContext(), "User with this email already exists!", Toast.LENGTH_LONG).show();
            _signupButton.setEnabled(true);
            progressDialog.hide();
            return;
        }

        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.save();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Sign up failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
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
