package edu.cnm.deepdive.powdr.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.powdr.model.dto.Message;
import edu.cnm.deepdive.powdr.service.MessageRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * A class for controlling communication between the view and the model.
 */
public class MessageViewModel extends AndroidViewModel implements LifecycleObserver {

  private final MessageRepository messageRepository;
  private final MutableLiveData<Message> message;
  private final MutableLiveData<List<Message>> messages;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public MessageViewModel(@NonNull Application application) {
    super(application);
    messageRepository = new MessageRepository(application);
    message = new MutableLiveData<>();
    messages  = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    loadMessages();
  }

  public LiveData<List<Message>> getMessages() {
    return messages;
  }

  public MutableLiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void loadMessages() {
    throwable.setValue(null);
    pending.add(
        messageRepository.getAll()
            .subscribe(
                messages::postValue,
                throwable::postValue
            )
    );
  }


}