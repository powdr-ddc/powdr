package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.powdr.BuildConfig;
import edu.cnm.deepdive.powdr.databinding.FragmentWeatherBinding;
import edu.cnm.deepdive.powdr.viewmodel.SkiResortViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * Inflates the UI for the weather screen.
 */
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
      viewModel.requestWeather(skiResort.getLatitude(), skiResort.getLongitude());
    });
    viewModel.getWeather().observe(getViewLifecycleOwner(), (weather) -> {
      binding.currentTemp.setText(String.valueOf(weather.getTemperature().getCurrent()));
      String iconUrl = String.format(BuildConfig.ICON_URL_PATTERN, weather.getWeather().get(0).getIcon());
      Picasso.get().load(iconUrl).into(binding.weatherIcon);
      binding.currentWeather.setText(weather.getWeather().get(0).getDescription());
      binding.windSpeedValue.setText(String.valueOf(weather.getWind().getSpeed()));
//      binding.snowfallOneHourValue.setText(String.valueOf(weather.getSnow().getOneHour()));
//      binding.snowfallThreeHourValue.setText(String.valueOf(weather.getSnow().getThreeHour()));
    });
    //noinspection ConstantConditions
    WeatherFragmentArgs args = WeatherFragmentArgs.fromBundle(getArguments());
    viewModel.loadSkiResort(args.getId());
  }
}