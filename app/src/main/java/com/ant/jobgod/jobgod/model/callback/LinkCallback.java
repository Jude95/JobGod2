package com.ant.jobgod.jobgod.model.callback;

import com.android.http.RequestManager;

/**
 * Created by Mr.Jude on 2015/6/13.
 */
class LinkCallback implements RequestManager.RequestListener {
    private LinkCallback link;
    public LinkCallback add(LinkCallback other){
        other.setLink(this);
        return other;
    }
    private void setLink(LinkCallback link){
        this.link = link;
    }

    @Override
    public void onRequest() {
        link.onRequest();
    }

    @Override
    public void onSuccess(String s) {
        link.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        link.onError(s);
    }
}
