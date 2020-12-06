//package edu.cnm.deepdive.powdr.controller.adapter;
//
//import android.content.Context;
//import androidx.recyclerview.widget.RecyclerView;
//import edu.cnm.deepdive.powdr.controller.adapter.PostAdapter.Holder;
//import edu.cnm.deepdive.powdr.databinding.ItemPostBinding;
//import edu.cnm.deepdive.powdr.model.dto.Post;
//
//public class PostAdapter extends RecyclerView.Adapter<Holder> {
//
//
//  class Holder extends RecyclerView.ViewHolder {
//
//    private final ItemPostBinding binding;
//
//
//    public PostAdapter(Context context)
//
//    /**
//     * Constructs an instance of holder
//     * @param binding SkiResortItem binding
//     */
//    public Holder(ItemPostBinding binding) {
//      super(binding.getRoot());
//      this.binding = binding;
//    }
//
//    private void bind(int position) {
//      Post post = posts.get(position);
//      binding.skiResortListItem.setText(skiResort.getName());
//      itemView.setOnClickListener((v) -> listener.onClick(skiResort));
//    }
//  }
//
//  /**
//   * Interface for the onClickListener when selecting an item in the RecyclerView.
//   */
//  public interface OnResortClickListener {
//    void onClick(SkiResort skiResort);
//  }
//
//}
