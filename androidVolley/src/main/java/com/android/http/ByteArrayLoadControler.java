package com.android.http;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

/**
 * ByteArrayLoadControler implements Volley Listener & ErrorListener
 * 
 * @author steven pan
 * 
 */
class ByteArrayLoadControler extends AbsLoadControler implements Listener<byte[]>, ErrorListener {
	
	private LoadListener mOnLoadListener;


	public ByteArrayLoadControler(LoadListener requestListener) {
		this.mOnLoadListener = requestListener;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		String errorMsg = null;
		if (error.getMessage() != null) {
			errorMsg = error.getMessage();
		} else {
			try {
				errorMsg = "Server Response Error (" + error.networkResponse.statusCode + ")";
			} catch (Exception e) {
				errorMsg = "Server Response Error";
			}
		}
		this.mOnLoadListener.onError(errorMsg, getOriginUrl());
	}

	@Override
	public void onResponse(byte[] response) {
		this.mOnLoadListener.onSuccess(response, getOriginUrl());
	}
}