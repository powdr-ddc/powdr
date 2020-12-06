package edu.cnm.deepdive.powdr.ui.wall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.powdr.R;

public class WallFragment extends Fragment {

  private WallViewModel wallViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    wallViewModel =
        new ViewModelProvider(this).get(WallViewModel.class);
    View root = inflater.inflate(R.layout.fragment_wall, container, false);
    final TextView textView = root.findViewById(R.id.text_home);
    wallViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
}