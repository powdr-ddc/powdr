package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.powdr.model.dto.PostResponse;

public class WallViewModel extends AndroidViewModel {

  private MutableLiveData<PostResponse> post;
  private MutableLiveData<String> mText;

  public WallViewModel(@NonNull Application application) {
    super(application);
    post= new MutableLiveData<>();
    mText = new MutableLiveData<>();
    mText.setValue("This is screen to display posts from all of the Users you've befriended");
  }

  public LiveData<PostResponse> getPosts() {
    return post;
  }

  public LiveData<String> getText() {
    return mText;
  }
}