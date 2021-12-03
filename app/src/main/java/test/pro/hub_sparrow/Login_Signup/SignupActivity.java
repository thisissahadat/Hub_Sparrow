package test.pro.hub_sparrow.Login_Signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import test.pro.hub_sparrow.R;
import test.pro.hub_sparrow.User;

public class SignupActivity extends AppCompatActivity {
    TextInputEditText username, useremail, userpassword,studentid;
    AppCompatButton register;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;
    DatabaseReference databaseReference;

    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        firebaseAuth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(SignupActivity.this);
        dialog.setTitle("Please Wait....");
        dialog.setMessage("Checking your informations..");


        username=findViewById(R.id.signin_name_edtext);
        useremail=findViewById(R.id.signin_email_edtext);
        userpassword=findViewById(R.id.signin_pass_edtext);
        register=findViewById(R.id.signin_registerbtn);




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=username.getText().toString().trim();
                String email=useremail.getText().toString().trim();
                String password=userpassword.getText().toString().trim();



                if (name.isEmpty()){
                    showAlart("Name can't be empty");
                }else if (email.isEmpty()){
                    showAlart("Email can't be empty");

                }else if (password.length()<8){
                    showAlart("Password must be 8 character/more");

                }else if (password.matches(name)){
                    showAlart("You can't use your name as password");
                }else {
                    dialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {



                            if(task.isSuccessful()){

                                firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                                if (firebaseUser!=null){
                                    String currentUserid=firebaseAuth.getUid();
                                    User user = new User(name,email,password,currentUserid);
                                    databaseReference.child(currentUserid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            dialog.dismiss();
                                            Toast.makeText(SignupActivity.this,"Account created Successfully",Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }



                            }else {
                                dialog.dismiss();
                                String error=task.getException().getLocalizedMessage();
                                showAlart(error);
                                Toast.makeText(SignupActivity.this,"Error",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }










            }
        });

    }

    private void showAlart(String s) {
        AlertDialog.Builder builder= new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Error");
        builder.setMessage(s);
        builder.setIcon(R.drawable.ic_error);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}