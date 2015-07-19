package com.android.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * RequestManager
 * 
 * @author steven pan
 * 
 */
public class RequestManager {

	private static final String CHARSET_UTF_8 = "UTF-8";

	private int TIMEOUT_COUNT = 10 * 1000;

	private int RETRY_TIMES = 1;
	
	private boolean SHOULD_CACHE = false;

	private HashMap<String,String> HEADER;
	
	private volatile static RequestManager instance = null;

	private RequestQueue mRequestQueue = null;
	
	private NetworkImageCache mImageCache = null;
	
	private ImageLoader mImageLoader = null;
	
    private int times = 0;
	
	private boolean Debug = false;
	private String DebugTag;

	public interface RequestListener {
		void onRequest();
		void onSuccess(String response);

		void onError(String errorMsg);
	}

	private RequestManager() {

	}
	
	public void setDebugMode(boolean isDebug,String DebugTag){
		this.Debug = isDebug;
		this.DebugTag = DebugTag;
	}
	
	public void init(Context context) {
		this.mRequestQueue = Volley.newRequestQueue(context);
		mImageCache = new NetworkImageCache(context);
		mImageLoader = new ImageLoader(RequestManager.getInstance()
				.getRequestQueue(), mImageCache);
	}

	public static RequestManager getInstance() {
		if (null == instance) {
			synchronized (RequestManager.class) {
				if (null == instance) {
					instance = new RequestManager();
				}
			}
		}
		return instance;
	}

	public RequestQueue getRequestQueue() {
		return this.mRequestQueue;
	}

	public void setTimeOut(int time){
		TIMEOUT_COUNT = time;
	}
	
	public void setRetryTimes(int times){
		RETRY_TIMES = times;
	}
	
	public void setCacheEnable(boolean isCache){
		SHOULD_CACHE = isCache;
	}
	
	public void setHeader(HashMap<String,String> header){
		HEADER = header;
	}
	/**
	 * default get method
	 * 
	 * @param url
	 * @param requestListener
	 * @return
	 */
	public LoadControler get(String url, RequestListener requestListener) {
		return this.request(Method.GET, url, null , null, requestListener, SHOULD_CACHE, TIMEOUT_COUNT, RETRY_TIMES);
	}
	
	public LoadControler get(String url, HashMap<String,String> header,RequestListener requestListener, boolean shouldCache) {
		return this.request(Method.GET, url, null, header, requestListener, shouldCache, TIMEOUT_COUNT, RETRY_TIMES);
	}

	/**
	 * default post method
	 * 
	 * @param url
	 * @param data
	 *            String, Map<String, String> or RequestMap(with file)
	 * @param requestListener
	 * @return
	 */
	public LoadControler post(final String url, Object data, final RequestListener requestListener) {
		return this.post(url, data, HEADER,requestListener, SHOULD_CACHE, TIMEOUT_COUNT, RETRY_TIMES);
	}

    public LoadControler post(final String url, Object data, boolean chackeCache,final RequestListener requestListener) {
        return this.post(url, data, HEADER,requestListener, chackeCache, TIMEOUT_COUNT, RETRY_TIMES);
    }

    public void invalidate(String url, Object data){
        mRequestQueue.getCache().invalidate(url+data.toString(),true);
    }

	/**
	 * 
	 * @param url
	 * @param data
	 *            String, Map<String, String> or RequestMap(with file)
	 * @param requestListener
	 * @param shouldCache
	 * @param timeoutCount
	 * @param retryTimes
	 * @return
	 */
	public LoadControler post(final String url, Object data,HashMap<String,String> header, final RequestListener requestListener, boolean shouldCache,
			int timeoutCount, int retryTimes) {
		return request(Method.POST, url, data,header, requestListener, shouldCache, timeoutCount, retryTimes);
	}


//    public ImageContainer img(final String url,final ImageView imageView){
//        return mImageLoader.get(url, new ImageListener() {
//            @Override
//            public void onResponse(ImageContainer response, boolean isImmediate) {
//                if (response.getBitmap()!=null){
//                    imageView.setImageBitmap(response.getBitmap());
//                }
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//    }
//
//    public ImageContainer img(final String url,final ImageView imageView, final int resError){
//        return mImageLoader.get(url, new ImageListener() {
//            @Override
//            public void onResponse(ImageContainer response, boolean isImmediate) {
//                if (response.getBitmap()!=null){
//                    imageView.setImageBitmap(response.getBitmap());
//                }
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                imageView.setImageResource(resError);
//            }
//        });
//    }
//
//	public ImageContainer img(final String url,final ImageListener imageListener){
//		return mImageLoader.get(url, imageListener);
//	}
//
//	public ImageContainer img(final String url,final ImageListener imageListener,int maxWidth,int maxHeight){
//		return mImageLoader.get(url, imageListener, maxWidth, maxHeight);
//	}
	
	/**
	 * request
	 * 
	 * @param method
	 *            mainly Method.POST and Method.GET
	 * @param url
	 *            target url
	 * @param data
	 *            request params
	 * @param headers
	 *            request headers
	 * @param requestListener
	 *            request callback
	 * @param shouldCache
	 *            useCache
	 * @param timeoutCount
	 *            reqeust timeout count
	 * @param retryTimes
	 *            reqeust retry times
	 *            request id
	 * @return
	 */
	public LoadControler request(int method, final String url, final Object data, final Map<String, String> headers,
			final RequestListener requestListener, boolean shouldCache, int timeoutCount, int retryTimes) {
        final int curIndex = this.times++;
		return this.sendRequest(method, url, data, headers, new LoadListener() {
			@Override
			public void onStart() {
				if(requestListener!=null)
				requestListener.onRequest();
                if(Debug)Log.i(DebugTag, curIndex+"次请求-参数："+url+(data==null?"":data.toString()));
			}

			@Override
			public void onSuccess(byte[] data, String url) {
				
				String parsed = null;
				try {
					parsed = new String(data, CHARSET_UTF_8);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if(Debug)Log.i(DebugTag, curIndex+"次请求-返回："+parsed);
				if(requestListener!=null)
				requestListener.onSuccess(parsed);
			}

			@Override
			public void onError(String errorMsg, String url) {
				if(Debug)Log.i(DebugTag, curIndex+"次请求-错误："+errorMsg);
				if(requestListener!=null)
				requestListener.onError(errorMsg);
			}
		}, shouldCache, timeoutCount, retryTimes);
	}

	/**
	 * @param method
	 * @param url
	 * @param data
	 * @param headers
	 * @param requestListener
	 * @param shouldCache
	 * @param timeoutCount
	 * @param retryTimes
	 * @return
	 */
	public LoadControler sendRequest(int method, final String url, Object data,final Map<String, String> headers,
			final LoadListener requestListener, boolean shouldCache, int timeoutCount, int retryTimes) {
		if (requestListener == null)
			throw new NullPointerException();

		final ByteArrayLoadControler loadControler = new ByteArrayLoadControler(requestListener);

		Request<?> request = null;
        request = new ByteArrayRequest(method, url, data, headers,loadControler, loadControler);
        request.setShouldCache(shouldCache);


		if (headers != null && !headers.isEmpty()) {// add headers if not empty
			try {
				request.getHeaders().putAll(headers);
			} catch (AuthFailureError e) {
				e.printStackTrace();
			}
		}

		RetryPolicy retryPolicy = new DefaultRetryPolicy(timeoutCount, retryTimes, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
		request.setRetryPolicy(retryPolicy);

		loadControler.bindRequest(request);

		if (this.mRequestQueue == null)
			throw new NullPointerException();
		requestListener.onStart();
		this.mRequestQueue.add(request);

		return loadControler;
	}
	
	
	

}
