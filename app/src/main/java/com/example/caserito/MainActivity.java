package com.example.caserito;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText correo;
    private EditText contrasena;
    //private EditText confiContrasena;



    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        correo = findViewById(R.id.editTextTextEmailAddress2);
        contrasena = findViewById(R.id.editTextTextPassword);
        //confiContrasena = findViewById(R.id.editTextTextPassword3);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    //cambios de interfaz
    public void registrar(View view){
        Intent i =new Intent (this, registro.class);
        startActivity(i);
    }
    public void Tiendas(View view){
        Intent i =new Intent (this, registro.class);
        startActivity(i);
    }
    public void ingresar(View view){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent i =new Intent (getApplicationContext(), menu.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Correo o contraseña incorrecto",Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
        //    reload();
        }
    }




}