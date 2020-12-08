package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.adapter.PostAdapter;
import edu.cnm.deepdive.powdr.databinding.FragmentProfileBinding;
import edu.cnm.deepdive.powdr.databinding.FragmentWallBinding;
import edu.cnm.deepdive.powdr.viewmodel.ProfileViewModel;
import edu.cnm.deepdive.powdr.viewmodel.WallViewModel;

public class ProfileFragment extends Fragment {

  private ProfileViewModel profileViewModel;
  private FragmentProfileBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater);
    return binding.getRoot();
  }

  /**
   * Method for providing the {@link WallViewModel} as the ViewModel used for this fragment
   */
  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
    getLifecycle().addObserver(profileViewModel);
    profileViewModel.getProfile().observe(getViewLifecycleOwner(), (profile) -> {
      // TODO Finish setting up ViewModel
          });
    profileViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
      }
    });
  }


}