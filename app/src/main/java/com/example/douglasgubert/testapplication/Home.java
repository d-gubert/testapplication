package com.example.douglasgubert.testapplication;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Build;
import android.widget.EditText;


public class Home extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.douglasgubert.testapplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view) {
        EditText editMessage = (EditText) findViewById(R.id.edit_message);
        String message = editMessage.getText().toString();

        if (message.isEmpty()) {
            DisplayMessageDialog dialog = new DisplayMessageDialog();
            dialog.setMessage("Text editing field can't be blank.");
            dialog.show(getFragmentManager(), "message");
        }
        else {
            DisplayMessageDialog dialog = new DisplayMessageDialog();
            dialog.setMessage(message);
            dialog.show(getFragmentManager(), "message");
            resetMessage(view);
        }
    }

    public void resetMessage(View view) {
        EditText editMessage = (EditText) findViewById(R.id.edit_message);
        editMessage.setText("");
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }
    }

    public static class DisplayMessageDialog extends DialogFragment {
        private String message;

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage(message)
                   .setPositiveButton(R.string.display_message_positive, new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {

                       }
                   });

            return builder.create();
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}