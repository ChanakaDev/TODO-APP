package com.example.todoapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_todo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton menuIcon = findViewById(R.id.menuIcon);
        ImageButton devInfoIcon = findViewById(R.id.devInfoIcon);

        Button addTodoBtn = findViewById(R.id.add_todo_button);
        addTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the dialog layout
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_add_todo, null);

                // Create the AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(TodoActivity.this);
                builder.setTitle("Add New Todo");
                builder.setView(dialogView);

                // Get the EditText from the dialog layout
                final EditText todoInput = dialogView.findViewById(R.id.todo_input);

                // Set up the buttons
                builder.setPositiveButton("Add", null);
                builder.setNegativeButton("Cancel", null);

                // Show the dialog
                AlertDialog dialog = builder.create();
                dialog.show();

                // Set button colors after the dialog is shown
                Button addButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button cancelButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

                addButton.setTextColor(Color.parseColor("#379A61"));  // Set green for "Add" button
                cancelButton.setTextColor(Color.parseColor("#B3261E"));  // Set red for "Cancel" button

                // Handle the "Add" button click
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String todoText = todoInput.getText().toString().trim();
                        if (!todoText.isEmpty()) {
                            // Add the todo (You can store it or update your UI here)
                            Toast.makeText(TodoActivity.this, "Todo added: " + todoText, Toast.LENGTH_SHORT).show();
                            dialog.dismiss(); // Dismiss the dialog after adding
                        } else {
                            Toast.makeText(TodoActivity.this, "Please enter a valid todo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Handle the "Cancel" button click
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // Dismiss the dialog on cancel
                    }
                });
            }
        });

        devInfoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TodoActivity.this, DevinfoActivity.class);
                startActivity(intent);
            }
        });

    }
}