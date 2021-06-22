package com.example.projectblazer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private EditText fName;
    private EditText lName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mRegButton;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        fName=findViewById(R.id.fname_TextField);
        lName=findViewById(R.id.lname_TextField);
        mEmail=findViewById(R.id.remail_TextField);
        mPassword=findViewById(R.id.rPassword_TextField);
        mRegButton=findViewById(R.id.register_Button);

        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName=fName.getText().toString();
                String lastName=lName.getText().toString();
                String email=mEmail.getText().toString();
                String password=mPassword.getText().toString();

                if(!firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupActivity.this,"Account Successfully Created ",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignupActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }else{
                    Toast.makeText(SignupActivity.this,"Please Fill empty field",Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}