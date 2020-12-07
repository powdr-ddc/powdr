package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import edu.cnm.deepdive.powdr.databinding.FragmentSkiResortBinding;
import edu.cnm.deepdive.powdr.viewmodel.SkiResortViewModel;
import org.jetbrains.annotations.NotNull;

public class SkiResortFragment extends Fragment {

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public SkiResortFragment() {
    // Required empty public constructor
  }

  private FragmentSkiResortBinding binding;
  private SkiResortViewModel viewModel;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentSkiResortBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(SkiResortViewModel.class);
    getLifecycle().addObserver(viewModel);
//    viewModel.getAllSkiResorts().observe(getViewLifecycleOwner(), (skiResorts) -> {
//      SkiResortAdapter adapter = new SkiResortAdapter(getContext(), skiResorts, (resort) ->
//          Navigation.findNavController(getView()).navigate(SkiResortFragmentDirections.showWeather(
//              (float) resort.getLatitude(), (float) resort.getLongitude())));
      //noinspection ConstantConditions
      binding.skiResortList.addItemDecoration(
          new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
//      binding.skiResortList.setAdapter(adapter);
//    });
  }
}