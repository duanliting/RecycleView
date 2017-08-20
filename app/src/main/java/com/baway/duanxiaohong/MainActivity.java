package com.baway.duanxiaohong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baway.duanxiaohong.adapter.MyAdapter;
import com.baway.duanxiaohong.bean.ImageBean;
import com.baway.duanxiaohong.utils.HttpConnect;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullLoadMoreRecyclerView xrv;
    private List<ImageBean.美女Bean> list;
    private MyAdapter adapter;
    private String url = "http://c.3g.163.com/recommend/getChanListNews?channel=T1456112189138&passport=&devId=1uuFYbybIU2oqSRGyFrjCw%3D%3D&lat=" +
            "%2F%2FOm%2B%2F8ScD%2B9fX1D8bxYWg%3D%3D&lon=LY2l8sFCNzaGzqWEPPgmUw%3D%3D&version=9.0&net=wifi&ts=1464769308&sign=" +
            "bOVsnQQ6gJamli6%2BfINh6fC%2Fi9ydsM5XXPKOGRto5G948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=meizu_store2014_news&mac=" +
            "sSduRYcChdp%2BBL1a9Xa%2F9TC0ruPUyXM4Jwce4E9oM30%3D&size=";
    private int page=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        list = new ArrayList<>();


        initView();
        boolean conn = HttpConnect.isConn(this);
        //如果没有网络
        if (!conn) {
            Toast.makeText(this, "当前网络无连接", Toast.LENGTH_SHORT).show();
        } else {
            //有的话就做自己的操作
            //初始化数据
            initDate();
        }
        adapter.setListener(new MyAdapter.onItemClickListener() {
            @Override
            public void itemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "这是第"+position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initView() {
        xrv = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        adapter = new MyAdapter(list,MainActivity.this);
        xrv.setAdapter(adapter);
        xrv.setStaggeredGridLayout(2);//参数为列数
        xrv.setPullRefreshEnable(true);
        xrv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                list.clear();
                initDate();
                xrv.setPullLoadMoreCompleted();

            }

            @Override
            public void onLoadMore() {
                page++;
                initDate();
                xrv.setPullLoadMoreCompleted();
            }
        });
    }

    private void initDate() {
        RequestParams params = new RequestParams();
        params.setUri(url+page);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.i("onSuccess", "onSuccess: "+result.toString());
                Gson gson = new Gson();
                ImageBean imageBean = gson.fromJson(result, ImageBean.class);
                 list.addAll(imageBean.get美女());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }
}
