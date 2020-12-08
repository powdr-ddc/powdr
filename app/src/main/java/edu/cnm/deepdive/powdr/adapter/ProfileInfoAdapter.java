package edu.cnm.deepdive.powdr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.powdr.adapter.FavResortAdapter.OnFavResortClickListener;
import edu.cnm.deepdive.powdr.adapter.ProfileInfoAdapter.Holder;
import edu.cnm.deepdive.powdr.databinding.ItemProfileInfoBinding;
import edu.cnm.deepdive.powdr.model.dto.User;
import java.util.List;

public class ProfileInfoAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<User> users;
  private final LayoutInflater inflater;

  public ProfileInfoAdapter(Context context, List<User> users) {
    this.context = context;
    this.users = users;
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemProfileInfoBinding binding = ItemProfileInfoBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemProfileInfoBinding binding;

    /**
     * Constructs an instance of holder
     *
     * @param binding ItemPost binding
     */
    public Holder(ItemProfileInfoBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      User user = users.get(position);

      FavResortAdapter favResortAdapter = new FavResortAdapter(context, favListener);
      binding.bio.setText(user.getBio());
      binding.favResortList.setAdapter(favResortAdapter);
    }
  }


}
