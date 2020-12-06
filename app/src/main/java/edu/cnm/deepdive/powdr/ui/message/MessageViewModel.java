package edu.cnm.deepdive.powdr.ui.message;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MessageViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public MessageViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is dashboard fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}