package edu.cnm.deepdive.powdr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.powdr.adapter.SkiResortAdapter.Holder;
import edu.cnm.deepdive.powdr.databinding.ItemSkiResortBinding;
import org.jetbrains.annotations.NotNull;

public class SkiResortAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
//  private final List<SkiResort> resorts;
  private final OnResortClickListener listener;

  /**
   * Constructs an instance of the SkiResortAdapter
   * @param context Application context
   * @param listener Listener for clicking on an item
   */
  public SkiResortAdapter(Context context, //List<SkiResort> resorts,
      OnResortClickListener listener) {
    this.context = context;
//    this.resorts = resorts;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemSkiResortBinding binding = ItemSkiResortBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return 0;
//    return resorts.size();
  }

  /**
   * Holder class for the RecyclerView
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemSkiResortBinding binding;

    /**
     * Constructs an instance of holder
     * @param binding ItemSkiResort binding
     */
    public Holder(ItemSkiResortBinding binding) {
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
  public interface OnResortClickListener {
//    void onClick(SkiResort skiResort);
  }

}
