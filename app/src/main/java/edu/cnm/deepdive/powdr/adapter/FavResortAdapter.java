package edu.cnm.deepdive.powdr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.powdr.adapter.SkiResortAdapter.Holder;
import edu.cnm.deepdive.powdr.databinding.SkiResortItemBinding;
import org.jetbrains.annotations.NotNull;

public class FavResortAdapter extends SkiResortAdapter {

  private final Context context;
  private final LayoutInflater inflater;
// private final List<SkiResort> favResorts;
  private final OnFavResortClickListener listener;

  /**
   * Constructs an instance of the SkiResortAdapter
   *
   * @param context  Application context
   * @param listener Listener for clicking on an item
   */
  public FavResortAdapter(Context context,
      OnFavResortClickListener listener) {
    super(context, listener);
    this.context = context;
//  this.favResorts = resorts;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
    holder.bind(position);
  }

  /**
   * Holder class for the RecyclerView
   */
  class Holder extends RecyclerView.ViewHolder {

    private final SkiResortItemBinding binding;

    /**
     * Constructs an instance of holder
     * @param binding SkiResortItem binding
     */
    public Holder(SkiResortItemBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
//      SkiResort skiResort = resorts.get(position);
//      binding.skiResortListItem.setText(skiResort.getName());
//      itemView.setOnClickListener((v) -> listener.onClick(skiResort));
    }
  }

  /**
   * Interface for the onClickListener when selecting an item in the RecyclerView.
   */
  public interface OnFavResortClickListener {
//    void onClick(SkiResort skiResort);
  }


}
