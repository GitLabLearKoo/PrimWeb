package com.prim.primweb.core.webview;

import android.view.View;
import android.widget.FrameLayout;

/**
 * ================================================
 * 作    者：linksus
 * 版    本：1.0
 * 创建日期：5/14 0014
 * 描    述：
 * 修订历史：
 * ================================================
 */
public interface IWebViewManager {
    View getWebView();

    FrameLayout getWebParentView();

    IWebViewManager create();

    IAgentWebView getAgentWeb();
}
