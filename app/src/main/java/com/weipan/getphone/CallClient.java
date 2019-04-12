package com.weipan.getphone;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ViewSwitcher;

/**
 * 作者：Created by cc on 2018/8/3 09:19.
 * 邮箱：904359289@QQ.com.
 * 类 ：
 */

public class CallClient extends WebViewClient {

    private Context mContext;
    private ViewSwitcher mViewSwitcher;

    public CallClient(Context context) {
        this.mContext = context;
    }

    public CallClient(Context context, ViewSwitcher viewSwitcher) {
        this(context);
        this.mViewSwitcher = viewSwitcher;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, final String url) {
        if (url != null && !"".equals(url)) {
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                mContext.startActivity(intent);
            } else {
                view.loadUrl(url);
            }
        }
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mViewSwitcher != null && mViewSwitcher.getDisplayedChild() == 0) {
            mViewSwitcher.showNext();
        }
    }
}