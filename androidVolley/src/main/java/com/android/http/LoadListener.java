package com.android.http;

/**
 * LoadListener special for ByteArrayLoadControler
 * 
 * @author steven-pan
 * 
 */
public interface LoadListener {
	
	void onStart();

	void onSuccess(byte[] data, String url);

	void onError(String errorMsg, String url);
}
