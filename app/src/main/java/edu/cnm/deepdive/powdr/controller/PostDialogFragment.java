package edu.cnm.deepdive.powdr.controller;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.powdr.R;
import edu.cnm.deepdive.powdr.databinding.FragmentPostDialogBinding;
import edu.cnm.deepdive.powdr.model.dto.Post;
import edu.cnm.deepdive.powdr.viewmodel.WallViewModel;

/**
 * Fragment for the dialog of adding a post upon pressing the floating action button.
 */
public class PostDialogFragment extends DialogFragment implements TextWatcher {

  private FragmentPostDialogBinding binding;
  private AlertDialog dialog;
  private WallViewModel viewModel;
  private Post post;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    binding = FragmentPostDialogBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new Builder(getContext())
        .setIcon(R.drawable.ic_create_post)
        .setTitle(R.string.create_post)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {
        })
        .setPositiveButton(android.R.string.ok, (dlg, which) -> create())
        .create();
    dialog.setOnShowListener((dlg) -> checkSubmitConditions());
    return dialog;

  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(WallViewModel.class);
    binding.body.addTextChangedListener(this);
    post = new Post();
  }


  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }

  /**
   * Method for indicating conditions required to activate Positive submit button
   */
  private void checkSubmitConditions() {
    Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
    //noinspection ConstantConditions
    positive.setEnabled(!binding.body.getText().toString().trim().isEmpty());
  }


  /**
   * Method used to create a brand new Post upon pressing Submit button in dialog
   */
  @SuppressWarnings("ConstantConditions")
  private void create() {
    String name = binding.body.getText().toString().trim();
    post.setContent(name);
    viewModel.save(post);
  }

}
