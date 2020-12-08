package edu.cnm.deepdive.powdr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.databinding.FragmentProfileBinding;
import edu.cnm.deepdive.powdr.model.dto.User;

public class UserAdapter extends Adapter {

  private final LayoutInflater inflater;


  public UserAdapter(@NonNull Context context, int resource) {
    super(context, R.layout.fragment_profile);
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    FragmentProfileBinding binding = (convertView != null)
        ? FragmentProfileBinding.bind(convertView)
        : FragmentProfileBinding.inflate(inflater, parent, false);
    User user = getItem(position)
    binding.profileName.setText(user.getUsername());
    binding.profileInfo.setAdapter(SkiResortAdapter);
  }
}
