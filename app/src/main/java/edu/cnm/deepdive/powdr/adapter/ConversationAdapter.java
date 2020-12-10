package edu.cnm.deepdive.powdr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.adapter.ConversationAdapter.Holder;
import edu.cnm.deepdive.powdr.databinding.ItemConversationBinding;
import edu.cnm.deepdive.powdr.model.dto.Message;
import java.util.List;

/**
 * Adapter class to convert message objects from the database to be displayed by the
 * {@link edu.cnm.deepdive.powdr.controller.ConversationsFragment}.
 */
public class ConversationAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<Message> convos;
  private final OnConvoClickListener listener;

  public ConversationAdapter(Context context, List<Message> convos,
      OnConvoClickListener listener) {
    this.context = context;
    this.convos = convos;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemConversationBinding binding = ItemConversationBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  /**
   * Holder class for the RecyclerView
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemConversationBinding binding;

    /**
     * Constructs an instance of holder
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

  /**
   * Interface for the onClickListener when selecting an item in the RecyclerView.
   */
  public interface OnConvoClickListener {
    void onClick(Message message);
  }



}
