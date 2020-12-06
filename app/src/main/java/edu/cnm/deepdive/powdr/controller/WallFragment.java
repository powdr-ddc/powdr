package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.powdr.databinding.FragmentWallBinding;
import edu.cnm.deepdive.powdr.viewmodel.WallViewModel;

public class WallFragment extends Fragment {

  private WallViewModel wallViewModel;
  private FragmentWallBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentWallBinding.inflate(inflater);
    return binding.getRoot();
  }
}