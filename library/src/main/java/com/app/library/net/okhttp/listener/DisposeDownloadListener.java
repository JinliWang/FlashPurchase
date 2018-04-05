package com.app.library.net.okhttp.listener;

/**
 * Description: 监听下载进度
 * Create By: MLS Co,Ltd
 */

public interface DisposeDownloadListener extends DisposeDataListener {
	void onProgress(int progrss);
}
