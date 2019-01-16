package com.prim.web.activity;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.prim.primweb.core.webview.PrimScrollView;
import com.prim.primweb.core.webview.X5AgentWebView;
import com.prim.web.R;
import com.prim.web.adapter.DetailAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prim
 * @version 1.0.0
 * @desc
 * @time 2019/1/11 - 4:04 PM
 */
public class WebDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerView_test;

    private List<NewBodyBean> newBodyBeanList;
    private List<NewBodyBean> testBeanList;

    DetailAdapter adapter;
    DetailAdapter testAdapter;

    private X5AgentWebView mWebView;

    private PrimScrollView scrollView;

    private TextView tv_comment, tv_comment_position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_detail);
        recyclerView = findViewById(R.id.recyclerView);
        mWebView = findViewById(R.id.weView);
        scrollView = findViewById(R.id.scrollView);
        recyclerView_test = findViewById(R.id.recyclerView_test);
        tv_comment = findViewById(R.id.tv_comment);
        tv_comment_position = findViewById(R.id.tv_comment_position);
        //支持javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true);
        //自适应屏幕
//        mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
        mWebView.loadUrl("https://www.toutiao.com/a6645933655317283335/");
        //android:descendantFocusability="blocksDescendants"

        recyclerView_test.setLayoutManager(new LinearLayoutManager(this));

        newBodyBeanList = new ArrayList<>();
        loadData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DetailAdapter(this, newBodyBeanList);
        testAdapter = new DetailAdapter(this, testBeanList);
        recyclerView.setAdapter(adapter);
        recyclerView_test.setAdapter(testAdapter);
        scrollView.setOnScrollWebToCommentListener(new PrimScrollView.OnScrollWebToCommentListener() {
            @Override
            public void onComment(boolean isComment) {
                if (!isComment) {
                    tv_comment.setText("评论");
                } else {
                    tv_comment.setText("正文");
                }
            }
        });
    }

    /**
     * 所有的数据都在一个接口中
     */
    private void loadData() {
        testBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewBodyBean bodyBean = new NewBodyBean(0, "推荐阅读::" + i, "", "", null);
            testBeanList.add(bodyBean);
        }

        //模拟评论盖楼
//        for (int i = 0; i < 20; i++) {
//            comments.add("comment:" + i);
//        }
        //模拟详情页接口
        for (int i = 0; i < 200; i++) {
            List<String> comments = new ArrayList<>();
            comments.add("comment::" + i);
            NewBodyBean newBodyBean = null;
//            if (i == 0) {
////                newBodyBean = new NewBodyBean(0, "test", "", "", null);
//            } else {
            newBodyBean = new NewBodyBean(2, "test" + i, "data" + i, "url" + i, comments);
//            }
            newBodyBeanList.add(newBodyBean);
        }
    }

    public void comment(View view) {
        boolean b = scrollView.toggleScrollToListView();
        if (b) {
            tv_comment.setText("评论");
        } else {
            tv_comment.setText("正文");
        }
    }

    public void commentPosition(View view) {
        scrollView.scrollToCommentListView(5);
    }

    public void zanClick(View view) {
        Toast.makeText(this, "赞了哦", Toast.LENGTH_SHORT).show();
    }

    public void wxClick(View view) {
        Toast.makeText(this, "分享到微信了哦", Toast.LENGTH_SHORT).show();
    }

    public void wxCClick(View view) {
        Toast.makeText(this, "分享到朋友圈了哦", Toast.LENGTH_SHORT).show();
    }

    public static class NewBodyBean {
        private int tyoe;
        private String title;
        private String data;
        private List<String> comments;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public NewBodyBean(int tyoe, String title, String data, String url, List<String> comments) {
            this.tyoe = tyoe;
            this.title = title;
            this.data = data;
            this.comments = comments;
            this.url = url;
        }

        public List<String> getComments() {
            return comments;
        }

        public void setComments(List<String> comments) {
            this.comments = comments;
        }

        public int getTyoe() {
            return tyoe;
        }

        public void setTyoe(int tyoe) {
            this.tyoe = tyoe;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
