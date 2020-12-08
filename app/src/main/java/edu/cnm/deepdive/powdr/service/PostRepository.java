package edu.cnm.deepdive.powdr.service;

import android.content.ContentResolver;
import android.content.Context;
import edu.cnm.deepdive.powdr.model.dto.Post;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;
import okhttp3.MediaType;

public class PostRepository {


  private final Context context;
  private final ContentResolver resolver;
  private final GoogleSignInService signInService;
  private final PowdrWebService webService;
  private final MediaType multipartFormType;

  public PostRepository(Context context) {
    this.context = context;
    resolver = context.getContentResolver();
    signInService = GoogleSignInService.getInstance();
    webService = PowdrWebService.getInstance();
    multipartFormType = MediaType.parse("multipart/form-data");
  }

  public Single<List<Post>> getAll() {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getPosts);
  }

  public Single<List<Post>> getInDateRange(Date start, Date end) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getPosts(token, start, end));
  }

  public Single<List<Post>> getMostRecent(int days) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getPosts(token, days));
  }


  public Single<Post> save(Post post) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.post(token, post));
  }

//  public Single<User> add(User imagePath, String title, String description) {
//    return signInService.refreshBearerToken()
//        .observeOn(Schedulers.io())
//        .flatMap((token) -> {
//          try (
//              Cursor cursor = resolver.query(uri, null, null, null, null);
//              InputStream input = resolver.openInputStream(uri);
//          ) {
//            MediaType type = MediaType.parse(resolver.getType(uri));
//            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//            cursor.moveToFirst();
//            String filename = cursor.getString(nameIndex);
//            File outputDir = context.getCacheDir();
//            File outputFile = File.createTempFile("xfer", null, outputDir);
//            Files.copy(input, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            RequestBody fileBody = RequestBody.create(outputFile, type);
//            MultipartBody.Part filePart = MultipartBody.Part
//                .createFormData("file", filename, fileBody);
//            RequestBody titlePart = RequestBody.create(title, multipartFormType);
//            if (description != null) {
//              RequestBody descriptionPart = RequestBody.create(description, multipartFormType);
//              return webService.post(token, filePart, titlePart, descriptionPart);
//            } else {
//              return webService.post(token, filePart, titlePart);
//            }
//          }
//        });
//
//
  }
