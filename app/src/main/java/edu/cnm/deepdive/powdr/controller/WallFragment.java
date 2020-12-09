package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import edu.cnm.deepdive.powdr.adapter.PostAdapter;
import edu.cnm.deepdive.powdr.databinding.FragmentWallBinding;
import edu.cnm.deepdive.powdr.viewmodel.WallViewModel;
import java.util.Collections;

public class WallFragment extends Fragment {

  private WallViewModel wallViewModel;
  private FragmentWallBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentWallBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
    setupViews();
  }

  public void setupViews() {
    //noinspection ConstantConditions
    binding.createPostFab.setOnClickListener((view) -> Navigation.findNavController(getView())
    .navigate(WallFragmentDirections.openDialog()));
  }

  /**
   * Method for providing the {@link WallViewModel} as the ViewModel used for this fragment
   */
  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    wallViewModel = new ViewModelProvider(this).get(WallViewModel.class);
    getLifecycle().addObserver(wallViewModel);
    wallViewModel.getPosts().observe(getViewLifecycleOwner(), (posts) -> {
      Collections.reverse(posts);
      PostAdapter adapter = new PostAdapter(
          activity, posts);
      binding.postList.setAdapter(adapter);
    });
    wallViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
      }
    });
  }


}