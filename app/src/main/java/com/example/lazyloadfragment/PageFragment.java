package com.example.lazyloadfragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import java.util.ArrayList;

public class PageFragment extends LazyloadFragment implements XRecyclerView.LoadingListener {
    private CommonAdapter<String> adapter;
    private ArrayList<String> datas = new ArrayList<>();
    private XRecyclerView recyclerView;
    private Handler handler = new Handler();

    @Override
    public int setContentView() {
        return R.layout.fragment_page;
    }


    @Override
    public void init() {
        recyclerView = rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommonAdapter<String>(getActivity(),R.layout.item,datas) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        recyclerView.setAdapter(adapter);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(this);

    }

    @Override
    public void lazyLoad() {
        recyclerView.refresh();
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.refreshComplete();
                for(int i=0;i<10;i++){
                    datas.add("");
                }
                adapter.notifyDataSetChanged();
            }
        },500);
    }

    @Override
    public void onLoadMore() {

    }
}
