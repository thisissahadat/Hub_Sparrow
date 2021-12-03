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

import test.pro.hub_sparrow.MainActivity;
import test.pro.hub_sparrow.R;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText useremail, userpassword;
    AppCompatButton loginbtn;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        dialog=new ProgressDialog(LoginActivity.this);
        dialog.setTitle("Please wait");
        dialog.setMessage("Checking your information");

        useremail=findViewById(R.id.login_email_edtext);
        userpassword=findViewById(R.id.login_pass_edtext);
        loginbtn=findViewById(R.id.login_loginbtn);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=useremail.getText().toString().trim();
                String password=userpassword.getText().toString().trim();

                if (email.isEmpty()){
                    showAlart("Email can't be empty... Please enter a registered email address.");
                }else if (password.isEmpty()){
                    showAlart("Please enter the correct Password..");
                }else {
                    dialog.show();
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                            }else {
                                dialog.dismiss();
                                String error=task.getException().getLocalizedMessage();
                                Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }



            }
        });


    }

    private void showAlart(String s) {
        AlertDialog.Builder builder= new AlertDialog.Builder(LoginActivity.this);
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