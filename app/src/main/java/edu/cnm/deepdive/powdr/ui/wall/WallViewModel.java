package edu.cnm.deepdive.powdr.ui.wall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WallViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public WallViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is home fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}