package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;

public class SkiResortViewModel extends AndroidViewModel implements LifecycleObserver {

  public SkiResortViewModel(@NonNull Application application) {
    super(application);
  }
}
