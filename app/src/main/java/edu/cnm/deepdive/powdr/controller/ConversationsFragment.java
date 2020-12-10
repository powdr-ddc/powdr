package edu.cnm.deepdive.powdr.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.databinding.FragmentConversationBinding;
import edu.cnm.deepdive.powdr.viewmodel.MessageViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * Fragment class to inflate the UI and display the list of various conversations a user may
 * have with other users.
 */
public class ConversationsFragment extends Fragment {

  private MessageViewModel messageViewModel;
  private FragmentConversationBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentConversationBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  private void setupViewModel() {
    messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
    getLifecycle().addObserver(messageViewModel);
    binding.messageUserName1.setText(R.string.message_sender);
    binding.messageUserName2.setText(R.string.message_sender);
    binding.messageIcon1.setImageResource(R.drawable.jonah_profile);
    binding.messageIcon2.setImageResource(R.drawable.jonah_profile);
    binding.convoTimestamp1.setText(R.string.sent_message_timestamp);
    binding.convoTimestamp2.setText(R.string.sent_convo_timestamp);
    binding.messageUserName1.setOnClickListener((v) ->
        Navigation.findNavController(getView())
            .navigate(ConversationsFragmentDirections.showChat()));
  }


}