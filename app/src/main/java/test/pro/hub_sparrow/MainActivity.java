package test.pro.hub_sparrow;

import static com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import test.pro.hub_sparrow.Accademic.Routine_Activiy;
import test.pro.hub_sparrow.Contacts.Faculty_Members;
import test.pro.hub_sparrow.Contacts.Official_Members;
import test.pro.hub_sparrow.ExamController.Reasult_Activity;
import test.pro.hub_sparrow.Login_Signup.Login_signup_Activity;
import test.pro.hub_sparrow.Transport.Transport_Activity;
import test.pro.hub_sparrow.University_sites.UniversityWebsiteActivity;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FirebaseAuth firebaseAuth;
    BarChart barChart;




    @Override  //To set the toolbar,drawer / anything Mainactivity.xml
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        barChart=findViewById(R.id.barchart);
        navigationView = findViewById(R.id.nav_drawer);
        drawerLayout = findViewById(R.id.navigationview_id);
        barChart=findViewById(R.id.barchart);

        ArrayList<BarEntry> Visitors=new ArrayList<>();
        Visitors.add(new BarEntry(1,2));
        Visitors.add(new BarEntry(2,4));
        Visitors.add(new BarEntry(3,3));
        Visitors.add(new BarEntry(4,2));
        Visitors.add(new BarEntry(5,3));
        Visitors.add(new BarEntry(6,4));
        Visitors.add(new BarEntry(7,2));
        Visitors.add(new BarEntry(8,3));

        BarDataSet barDataSet=new BarDataSet(Visitors,"Visitors");
        barDataSet.setColors(MATERIAL_COLORS);
        barDataSet.setValueTextColor(android.R.color.black);
        barDataSet.setValueTextSize(16f);
        BarData barData=new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.animateY(2000);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.routine_id:
                        Intent intent = new Intent(MainActivity.this, Routine_Activiy.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Opening Routine", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.laibrary_id:
                        Intent intent1 = new Intent(MainActivity.this,Routine_Activiy.class);
                        startActivity(intent1);
                        Toast.makeText(MainActivity.this, "Launching Laibrary", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.facultymembers_id:
                        Intent intent2 =new Intent(MainActivity.this, Faculty_Members.class);
                        startActivity(intent2);
                        Toast.makeText(MainActivity.this,"Launching Faculty Members Information",Toast.LENGTH_SHORT).show();;
                        return true;


                    case R.id.transport_id:
                        Intent intent3 = new Intent(MainActivity.this, Transport_Activity.class);
                        startActivity(intent3);
                        Toast.makeText(MainActivity.this, "Launching Transport schedule", Toast.LENGTH_SHORT).show();
                        return true;



                    case R.id.officialcontact_id:
                        Intent intent4 = new Intent(MainActivity.this, Official_Members.class);
                        startActivity(intent4);
                        Toast.makeText(MainActivity.this, "Opening Official Contacts", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.universitywebsite:
                        Intent intent5 = new Intent(MainActivity.this, UniversityWebsiteActivity.class);
                        startActivity(intent5);
                        Toast.makeText(MainActivity.this, "Launching University Website", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.result_id:
                        Intent intent6 = new Intent(MainActivity.this, Reasult_Activity.class);
                        startActivity(intent6);
                        Toast.makeText(MainActivity.this, "Launching Office_Activity", Toast.LENGTH_SHORT).show();
                        return true;


                    case R.id.university_facebookpage:
                        Intent intent7 = new Intent(MainActivity.this, UniversityFacebook.class);
                        startActivity(intent7);
                        Toast.makeText(MainActivity.this, "Opening University Facebook Page", Toast.LENGTH_SHORT).show();
                        return true;




                }
                return false;
            }


        });

    }

    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.side_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.alumni_id:
                Intent intent=new Intent(MainActivity.this,Routine_Activiy.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Alumni option clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout_id:
                Toast.makeText(MainActivity.this, "Logout Successful", Toast.LENGTH_SHORT).show();
                Logout();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void Logout() {
        firebaseAuth.signOut();
        startActivity(new Intent(getApplicationContext(),Login_signup_Activity.class));
        finish();
    }


}



