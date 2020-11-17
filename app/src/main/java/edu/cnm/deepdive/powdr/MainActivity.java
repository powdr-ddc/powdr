package edu.cnm.deepdive.powdr;

import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.powdr.service.UserRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

  private UserRepository userRepository; // FIXME

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    BottomNavigationView navView = findViewById(R.id.nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
        .build();
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(navView, navController);
    // FIXME This is just temporary to verify round trip.
    userRepository = new UserRepository(this);
    userRepository.getProfileFromServer()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            (user) -> Toast.makeText(this, user.getUsername(), Toast.LENGTH_LONG).show(),
            (throwable) -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show()
        );
  }

}