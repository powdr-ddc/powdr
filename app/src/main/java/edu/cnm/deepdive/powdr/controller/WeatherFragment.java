package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.powdr.databinding.FragmentWeatherBinding;
import edu.cnm.deepdive.powdr.viewmodel.SkiResortViewModel;
import org.jetbrains.annotations.NotNull;

public class WeatherFragment extends Fragment {

  private SkiResortViewModel viewModel;
  private FragmentWeatherBinding binding;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentWeatherBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(SkiResortViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getSkiResort().observe(getViewLifecycleOwner(), (skiResort) -> {
      binding.skiResortName.setText(skiResort.getName());
    });
    viewModel.getWeather().observe(getViewLifecycleOwner(), (weather) -> {
      binding.currentTemp.setText(String.valueOf(weather.getTemperature().getCurrent()));
    });

  }
}