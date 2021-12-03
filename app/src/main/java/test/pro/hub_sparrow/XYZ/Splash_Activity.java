package test.pro.hub_sparrow.XYZ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import test.pro.hub_sparrow.Login_Signup.Login_signup_Activity;
import test.pro.hub_sparrow.MainActivity;
import test.pro.hub_sparrow.R;

public class Splash_Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    LottieAnimationView lottie;
    TextView sparrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lottie=findViewById(R.id.lotie);
        sparrow=findViewById(R.id.sparrow);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        sparrow.setAnimation(animation);

        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebaseUser!= null){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    finish();
                }else{
                    Intent i = new Intent(Splash_Activity.this, Login_signup_Activity.class);
                    startActivity(i);
                    finish();
                }


            }
        },3700);


    }



}