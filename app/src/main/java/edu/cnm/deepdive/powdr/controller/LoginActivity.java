package edu.cnm.deepdive.powdr.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.powdr.service.GoogleSignInService;
import edu.cnm.deepdive.powdr.service.UserRepository;

/**
 * Activity to handle logging in through the Google Sign In Service before switching to the
 * {@link NavigationActivity}.
 */
public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;

  private GoogleSignInService service;
  private ActivityLoginBinding binding;
  private UserRepository userRepository;

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    service = GoogleSignInService.getInstance();
//    userRepository = new UserRepository(this);
    //noinspection ResultOfMethodCallIgnored
    service.refresh()
        .subscribe(
            this::updateAndSwitch,
            (throwable) -> {
              binding = ActivityLoginBinding.inflate(getLayoutInflater());
              binding.signIn.setOnClickListener((v) ->
                  service.startSignIn(this, LOGIN_REQUEST_CODE));
              setContentView(binding.getRoot());
            }
        );
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service.completeSignIn(data)
          .addOnSuccessListener(this::updateAndSwitch)
          .addOnFailureListener((throwable) ->
              Toast.makeText(this, R.string.login_failure_message, Toast.LENGTH_LONG).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  private void updateAndSwitch(GoogleSignInAccount account) {

    Intent intent = new Intent(this, NavigationActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);

//    //noinspection ResultOfMethodCallIgnored
//    userRepository.createUser(account)
//        .subscribe(
//            (user) -> {
//              Intent intent = new Intent(this, MainActivity.class)
//                  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//              startActivity(intent);
//            },
//            (throwable) -> {
//              // TODO Remove this after development completes
//              Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
//              throw new RuntimeException(throwable);
//            }
//        );
  }
}