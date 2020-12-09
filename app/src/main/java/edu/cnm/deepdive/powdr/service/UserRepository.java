package edu.cnm.deepdive.powdr.service;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.OpenableColumns;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.powdr.model.dto.User;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserRepository {

  private final Context context;
  private final PowdrWebService webService;
  private final GoogleSignInService signInService;
  private final ContentResolver resolver;
  // TODO Add fields as appropriate for access to DAO's etc.

  public UserRepository(Context context) {
    this.context = context;
    webService = PowdrWebService.getInstance();
    signInService = GoogleSignInService.getInstance();
    resolver = context.getContentResolver();
  }

  public Single<User> getProfile() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getProfile);
  }

  public Single<Bitmap> getProfilePic() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getProfilePic)
        .map((response) -> BitmapFactory.decodeStream(response.byteStream()));
  }

  public Single<User> saveProfilePic(Uri uri) {
    File[] filesCreated = new File[1];
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> {
          try (
              Cursor cursor = resolver.query(uri, null, null, null, null);
              InputStream input = resolver.openInputStream(uri)
          ) {
            MediaType type = MediaType.parse(resolver.getType(uri));
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            cursor.moveToFirst();
            String filename = cursor.getString(nameIndex);
            File outputDir = context.getCacheDir();
            File outputFile = File.createTempFile("xfer", null, outputDir);
            filesCreated[0] = outputFile;
            Files.copy(input, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            RequestBody fileBody = RequestBody.create(outputFile, type);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", filename, fileBody);
            return webService.putProfilePic(token, filePart);
          }
        })
        .doFinally(() -> {
          if (filesCreated[0] != null) {
            try {
              filesCreated[0].delete();
            } catch (Exception e) {
              // Ignored; nothing to do to recover.
            }
          }
        });
  }

//  public Single<List<User>> getFriends()

}
