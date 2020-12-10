package edu.cnm.deepdive.powdr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.powdr.adapter.PostAdapter.Holder;
import edu.cnm.deepdive.powdr.databinding.ItemPostBinding;
import edu.cnm.deepdive.powdr.model.dto.Post;
import java.util.Date;
import java.util.List;
import org.ocpsoft.prettytime.PrettyTime;

/**
 * Adapter to attach the list of {@link Post} to the RecyclerView in the
 * {@link edu.cnm.deepdive.powdr.controller.WallFragment}.
 */
public class PostAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Post> posts;
  private final LayoutInflater inflater;

  /**
   * Constructs an instance of the PostAdapter
   * @param context Application context
   * @param posts List of posts
   */
  public PostAdapter(Context context, List<Post> posts) {
    this.context = context;
    this.posts = posts;
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemPostBinding binding = ItemPostBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }


  @Override
  public int getItemCount() {
    return posts.size();
  }

  /**
   * Clears the recyclerView.
   */
  public void clear() {
    posts.clear();
    notifyDataSetChanged();
  }

  public void addAll(List<Post> posts) {
    posts.addAll(posts);
    notifyDataSetChanged();
  }
  class Holder extends RecyclerView.ViewHolder {

    private final ItemPostBinding binding;

    /**
     * Constructs an instance of holder
     *
     * @param binding ItemPost binding
     */
    public Holder(ItemPostBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      PrettyTime prettyTime = new PrettyTime();
      Post post = posts.get(position);
      binding.postUserName.setText(post.getUser().getUsername());
      binding.postTimestamp.setText(prettyTime.format(post.getTimestamp()));
      binding.postBody.setText(post.getContent());
      // TODO implement Image Pathing when we understand WTF that is
    }
  }

}
