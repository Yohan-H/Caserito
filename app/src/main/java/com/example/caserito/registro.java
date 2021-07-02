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

public class registro extends AppCompatActivity {

   // private EditText nombre;
    //private EditText celular;
    private EditText correo;
    private EditText contrasena;
    private EditText confiContrasena;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Inicializando contraseñas
        //nombre = findViewById(R.id.txtNombre);
        //celular = findViewById(R.id.editTextPhone2);
        correo = findViewById(R.id.editTextTextEmailAddress);
        contrasena = findViewById(R.id.editTextTextPassword2);
        confiContrasena = findViewById(R.id.editTextTextPassword3);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // reload();
        }}
        public void registroUsuario(View view){
            if ( contrasena.getText().toString().equals(confiContrasena.getText().toString())){
                mAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Log.d(TAG, "createUserWithEmail:success");
                                    Toast.makeText(getApplicationContext(), "Ingresando usuario creado", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                    Intent i =new Intent (getApplicationContext(), menu.class);
                                    startActivity(i);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Datos ingresados incorrectos",Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });
            }


            else{
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }

        }}