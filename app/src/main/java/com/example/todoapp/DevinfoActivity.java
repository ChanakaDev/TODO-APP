package com.example.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DevinfoActivity extends AppCompatActivity {

    // Replace with actual user information, this is just for demonstration
    private String currentUserName;
    private String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_devinfo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get strings from resources
        currentUserName = getString(R.string.username);
        currentUserEmail = getString(R.string.email);

        ImageButton backBtn = findViewById(R.id.backIcon);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DevinfoActivity.this, TodoActivity.class);
                startActivity(intent);
            }
        });

        Button editDetailsBtn = findViewById(R.id.edit_button);
        editDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDetailsDialog();
            }
        });

        Button signOutBtn = findViewById(R.id.sign_out_button);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignOutConfirmationDialog();
            }
        });
    }

    // Function to show the dialog for editing user details
    private void showEditDetailsDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_user_details, null);

        EditText editUserName = dialogView.findViewById(R.id.edit_user_name);
        EditText editUserEmail = dialogView.findViewById(R.id.edit_user_email);

        editUserName.setText(currentUserName);
        editUserEmail.setText(currentUserEmail);

        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Edit User Details").setView(dialogView).setPositiveButton("Save", null) // We set the click listener later to avoid auto-dismiss
                .setNegativeButton("Cancel", null).create();

        dialog.show();

        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

        positiveButton.setTextColor(getResources().getColor(R.color.btn_txt_green));
        negativeButton.setTextColor(getResources().getColor(R.color.btn_txt_red));

        // Setting click listener after showing the dialog to prevent automatic dismissal
        positiveButton.setOnClickListener(v -> {
            currentUserName = editUserName.getText().toString();
            currentUserEmail = editUserEmail.getText().toString();
            dialog.dismiss();
        });
    }


    // Function to show the sign-out confirmation dialog
    private void showSignOutConfirmationDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Sign Out").setMessage("Are you sure you want to sign out?").setPositiveButton("Yes", null) // We set the click listener later to handle custom actions
                .setNegativeButton("No", null).create();

        dialog.show();

        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

        positiveButton.setTextColor(getResources().getColor(R.color.btn_txt_green));
        negativeButton.setTextColor(getResources().getColor(R.color.btn_txt_red));

        // Setting click listener after showing the dialog to handle custom actions and prevent auto-dismiss
        positiveButton.setOnClickListener(v -> {
            Intent intent = new Intent(DevinfoActivity.this, SigninActivity.class);
            startActivity(intent);
            finish();
        });
    }

}
