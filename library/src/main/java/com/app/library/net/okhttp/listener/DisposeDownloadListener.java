package com.app.library.net.okhttp.listener;

/**
 * Description: 监听下载进度
 *
 */

public interface DisposeDownloadListener extends DisposeDataListener {
	void onProgress(int progrss);
}
