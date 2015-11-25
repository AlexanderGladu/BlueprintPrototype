package csci4620.blueprint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 100481892 on 11/25/2015.
 */

public class LoginActivity extends Activity {

    User tempUser;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent resultIntent) {
        super.onActivityResult(requestCode, responseCode, resultIntent);

        if (responseCode == RESULT_OK) {
            tempUser = (User) resultIntent.getSerializableExtra("NewUser");
            loginNew(tempUser);
        }
    }

    public void login(View view) {
        loginExist();
    }

    public void loginExist() {
        EditText usernameText = (EditText) findViewById(R.id.username_input);
        username = usernameText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.password_input);
        password = passwordText.getText().toString();

        if (username.equals("me@email.ca") && password.equals("Hello")) {
            User user = new User(username, password);
            Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
            loginIntent.putExtra("User", user);
            startActivity(loginIntent);
        } else {
            usernameText.setHint(getResources().getString(R.string.failed_hint));
            passwordText.setText(getResources().getString(R.string.password_hint));
        }
    }

    public void loginNew(User user) {
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        loginIntent.putExtra("User", user);
        startActivity(loginIntent);
    }

    public void register(View view) {
        Intent registerIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivityForResult(registerIntent, 100001);
    }

}
