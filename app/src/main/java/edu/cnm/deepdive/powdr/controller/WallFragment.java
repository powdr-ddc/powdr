package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.adapter.PostAdapter;
import edu.cnm.deepdive.powdr.databinding.FragmentWallBinding;
import edu.cnm.deepdive.powdr.viewmodel.WallViewModel;
import java.util.Collections;

/**
 * Inflates the UI for the wall screen.
 */
public class WallFragment extends Fragment {

  private WallViewModel wallViewModel;
  private FragmentWallBinding binding;
  private SwipeRefreshLayout swipeRefreshLayout;
  private RecyclerView recyclerView;
  private PostAdapter adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
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
      binding.swipeRefresh.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh() {
          adapter.clear();
          wallViewModel.loadMostRecent();
          binding.swipeRefresh.setRefreshing(false);
        }
      });

    });
    wallViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
      }
    });
  }

}