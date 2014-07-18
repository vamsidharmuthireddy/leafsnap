package edu.maryland.leafsnap.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import edu.maryland.leafsnap.R;
import edu.maryland.leafsnap.util.SessionManager;

public class OptionsAccountActivity extends ActionBarActivity {

    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_account);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setCurrentUsername();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCurrentUsername();
    }

    private void setCurrentUsername() {
        TextView username = (TextView) findViewById(R.id.username);
        if (getSessionManager().isLoggedIn()) {
            username.setText(getSessionManager().getCurrentUser().get(SessionManager.KEY_USERNAME));
        } else {
            username.setText(getResources().getText(R.string.not_logged_in));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onNewAccountButtonClick(View view) {
        Intent intent = new Intent(this, AccountActionActivity.class);
        intent.putExtra(AccountActionActivity.ACTION_ARG, AccountActionActivity.AccountAction.CREATE);
        this.startActivity(intent);
    }

    public void onChangeUsernameButtonClick(View view) {
        Intent intent = new Intent(this, AccountActionActivity.class);
        intent.putExtra(AccountActionActivity.ACTION_ARG, AccountActionActivity.AccountAction.UPDATE);
        this.startActivity(intent);
    }

    public void onDifferentLoginButtonClick(View view) {
        Intent intent = new Intent(this, AccountActionActivity.class);
        intent.putExtra(AccountActionActivity.ACTION_ARG, AccountActionActivity.AccountAction.VERIFY);
        this.startActivity(intent);
    }

    public SessionManager getSessionManager() {
        if (mSessionManager == null) {
            mSessionManager = new SessionManager(this);
        }
        return mSessionManager;
    }
}