package test.pro.hub_sparrow.Login_Signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import test.pro.hub_sparrow.R;

public class Login_signup_Activity extends AppCompatActivity {

    AppCompatButton login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        login=findViewById(R.id.loginbtn);
        signup=findViewById(R.id.signupbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_signup_Activity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_signup_Activity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


    }
}