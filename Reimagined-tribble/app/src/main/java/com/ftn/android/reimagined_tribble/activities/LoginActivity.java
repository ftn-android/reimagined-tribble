package com.ftn.android.reimagined_tribble.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.dao.UserDao;
import com.ftn.android.reimagined_tribble.model.Location;
import com.ftn.android.reimagined_tribble.model.User;
import com.google.common.collect.Iterables;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by szberko
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;


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
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
    }


    public void login() {
        Log.d(TAG, "Login");

        //TODO TEMPORARY THE VALIDATION IS DISABLE, DUE TO EASIER TESTING
//        if (!validate()) {
//            onLoginFailed();
//            return;
//        }

        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");
        user.setEmail("admin");
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
        if(User.find(User.class, "email = ? and password =?", email, password).size()!=0){
            MapsActivity_.intent(this).start();
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
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        //TODO Get informed about this method
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
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
