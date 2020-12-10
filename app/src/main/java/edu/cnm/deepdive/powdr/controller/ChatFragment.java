package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.databinding.FragmentChatBinding;
import edu.cnm.deepdive.powdr.viewmodel.MessageViewModel;

/**
 * Fragment class to inflate the UI for the messaging that takes place between two users.
 */
public class ChatFragment extends Fragment {

  private FragmentChatBinding binding;
  private MessageViewModel messageViewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentChatBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
   setupViewModel();
  }

  private void setupViewModel() {
    messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
    getLifecycle().addObserver(messageViewModel);
    binding.receivedBody.setText(R.string.received_message_body);
    binding.receivedBody2.setText(R.string.received_body2);
    binding.senderPic.setImageResource(R.drawable.jonah_profile);
    binding.senderPic2.setImageResource(R.drawable.jonah_profile);
    binding.sentBody.setText(R.string.sent_message_body);
  }
}
