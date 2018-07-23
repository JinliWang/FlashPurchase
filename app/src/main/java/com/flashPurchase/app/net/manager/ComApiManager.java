package com.flashPurchase.app.net.manager;

import com.flashPurchase.app.net.api.Api;

/**
 * Description:
 *
 */

public class ComApiManager extends ApiManager {

    public static Api getComApi() {
        return retrofit.create(Api.class);
    }

//    public static void upload(String path) {
//        ComApi service = retrofit.create(ComApi.class);
//        File file = new File(path);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getName(), requestFile);
//
//        String descriptionString = "This is a description";
//        RequestBody description =
//                RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
//
//        Call<CommonResponse<FileEntity>> call = service.upload(description, body);
//        call.enqueue(new Callback<CommonResponse<FileEntity>>() {
//            @Override
//            public void onResponse(Call<CommonResponse<FileEntity>> call, Response<CommonResponse<FileEntity>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<CommonResponse<FileEntity>> call, Throwable t) {
//
//            }
//        });
//    }

    //后台暂不支持
//    public static Single<CommonResponse<List<FileEntity>>> upload(String... files) {
//        try {
//            Api service = retrofit.create(Api.class);
//            List<MultipartBody.Part> filelist = new ArrayList<>();
//            for (int i = 0; i < files.length; i++) {
//                File file = new File(files[i]);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
//                RequestBody requestBody =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//                MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
//                filelist.add(body);
//            }
//            return service.upload(filelist);
//        } catch (Exception e) {
//            ToastUtil.show("上传失败");
//            return null;
//        }
//    }

//    public static void download(final String id, final String path, final ProcessListener listener) {
//        Api service = retrofit.create(Api.class);
//        FinFeeListRequest RecommendMoreResponse = new FinFeeListRequest();
//        RecommendMoreResponse.setEntid(SpManager.getEntId());
//        RecommendMoreResponse.setUserName(SpManager.getUserName());
//        RecommendMoreResponse.setId(id.toString());
//        service.download(RecommendMoreResponse.getAttach())
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Observer<ResponseBody>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//                        LogUtil.d("server contacted and has file");
//                        boolean writtenToDisk = writeResponseBodyToDisk(responseBody, path, listener);
//                        if (writtenToDisk) {
//                            listener.success();
//                        }
//                        LogUtil.d("file download was a success? " + writtenToDisk);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        LogUtil.d("server contact failed");
//                        listener.onFail();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                });
//    }
//
//    public static void downloadBuUrl(final String url, final String path, final ProcessListener listener) {
//        Api service = retrofit.create(Api.class);
//        service.download(url)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Observer<ResponseBody>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//                        LogUtil.d("server contacted and has file");
//                        boolean writtenToDisk = writeResponseBodyToDisk(responseBody, path, listener);
//                        if (writtenToDisk) {
//                            listener.success();
//                        }
//                        LogUtil.d("file download was a success? " + writtenToDisk);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        LogUtil.d("server contact failed");
//                        listener.onFail();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                });
//    }
//
//
//    public static void downloadBuUrl(final String url, final String path, final String cookie, final ProcessListener listener) {
//        Api service = retrofit.create(Api.class);
//        service.download(url, cookie)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Observer<ResponseBody>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResponseBody responseBody) {
//                        LogUtil.d("server contacted and has file");
//                        boolean writtenToDisk = writeResponseBodyToDisk(responseBody, path, listener);
//                        if (writtenToDisk) {
//                            listener.success();
//                        }
//                        LogUtil.d("file download was a success? " + writtenToDisk);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        LogUtil.d("server contact failed");
//                        listener.onFail();
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                });
//    }
//
//    private static boolean writeResponseBodyToDisk(ResponseBody body, String path, ProcessListener listener) {
//
//        try {
//            // todo change the file location/name according to your needs
//            File futureStudioIconFile = new File(path);
//
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//
//            try {
//                byte[] fileReader = new byte[4096];
//
////                LogUtil.d(body.bytes().length+"");
//                long fileSize = body.contentLength();
//                long fileSizeDownloaded = 0;
//
//                inputStream = body.byteStream();
//                outputStream = new FileOutputStream(futureStudioIconFile);
//
//                while (true) {
//                    int read = inputStream.read(fileReader);
//
//                    if (read == -1) {
//                        break;
//                    }
//
//                    outputStream.write(fileReader, 0, read);
//
//                    fileSizeDownloaded += read;
//                    listener.process(fileSizeDownloaded, fileSize);
//                }
//
//                outputStream.flush();
//
//                return true;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            } finally {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
