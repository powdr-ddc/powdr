package edu.cnm.deepdive.powdr.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.adapter.MessageAdapter.Holder;
import edu.cnm.deepdive.powdr.adapter.SkiResortAdapter.OnResortClickListener;
import edu.cnm.deepdive.powdr.databinding.ItemConversationBinding;
import edu.cnm.deepdive.powdr.databinding.ItemSkiResortBinding;
import edu.cnm.deepdive.powdr.model.dto.Message;
import edu.cnm.deepdive.powdr.model.dto.SkiResort;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MessageAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<Message> messages;


  /**
   * Constructs an instance of the MessageAdapter
   *
   * @param context Application context
   */
  public MessageAdapter(Context context, List<Message> messages) {
    this.context = context;
    this.messages = messages;
    inflater = LayoutInflater.from(context);
  }


  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemConversationBinding binding = ItemConversationBinding.inflate(
        inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return messages.size();
  }

  /**
   * Holder class for the RecyclerView
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemConversationBinding binding;

    /**
     * Constructs an instance of holder
     *
     * @param binding ItemSkiResort binding
     */
    public Holder(ItemConversationBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      binding.messageUserName.setText(R.string.message_sender);
      binding.convoTimestamp.setText(R.string.sent_message_timestamp);
      binding.messageIcon.setImageResource(R.drawable.jonah_profile);
    }
  }

}
