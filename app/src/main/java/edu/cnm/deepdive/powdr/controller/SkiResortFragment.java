package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import edu.cnm.deepdive.powdr.adapter.SkiResortAdapter;
import edu.cnm.deepdive.powdr.databinding.FragmentSkiResortBinding;
import edu.cnm.deepdive.powdr.viewmodel.SkiResortViewModel;
import org.jetbrains.annotations.NotNull;

public class SkiResortFragment extends Fragment {

  private FragmentSkiResortBinding binding;
  private SkiResortViewModel viewModel;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSkiResortBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(SkiResortViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getSkiResorts().observe(getViewLifecycleOwner(), (skiResorts) -> {
      SkiResortAdapter adapter = new SkiResortAdapter(getContext(), skiResorts, (resort) ->
          Navigation.findNavController(getView()).navigate(SkiResortFragmentDirections.showWeather(
              (float) resort.getLatitude(), (float) resort.getLongitude())));
      binding.skiResortList.addItemDecoration(
          new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
      binding.skiResortList.setAdapter(adapter);
    });
    viewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
      }
    });
  }
}