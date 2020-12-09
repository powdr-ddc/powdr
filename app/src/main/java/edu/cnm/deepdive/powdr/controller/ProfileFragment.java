package edu.cnm.deepdive.powdr.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.databinding.FragmentProfileBinding;
import edu.cnm.deepdive.powdr.viewmodel.ProfileViewModel;
import edu.cnm.deepdive.powdr.viewmodel.WallViewModel;

public class ProfileFragment extends Fragment {

  private static final int ADD_IMAGE_REQUEST = 1023;
  private ProfileViewModel profileViewModel;
  private FragmentProfileBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == ADD_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
      profileViewModel.saveProfilePic(data.getData());
    }
  }

  public void setupViews() {
    //noinspection ConstantConditions
    binding.profilePic.setOnClickListener((view) -> {
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      startActivityForResult(
          Intent.createChooser(intent, getString(R.string.select_image_prompt)), ADD_IMAGE_REQUEST);
    });
  }


  /**
   * Method for providing the {@link ProfileViewModel} as the ViewModel used for this fragment
   */
  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    getLifecycle().addObserver(profileViewModel);
    profileViewModel.getProfile().observe(getViewLifecycleOwner(), (user) -> {
      binding.profileName.setText(user.getUsername());

    });
    profileViewModel.getImage().observe(getViewLifecycleOwner(), (image) ->
        binding.profilePic.setImageBitmap(image)
        );
    profileViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
      }
    });
  }


}