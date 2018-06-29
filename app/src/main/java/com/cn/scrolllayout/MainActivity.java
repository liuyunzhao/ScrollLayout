package com.cn.scrolllayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.scrolllayout.view.ScrollLayout;
import com.cn.scrolllayout.view.ScrollRecyclerView;
import com.cn.scrolllayout.view.ScrollTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ScrollLayout mScrollLayout;
    private ScrollRecyclerView mScrollRecyclerView;
    private ScrollTextView mScrollTextView;
    private ImageView mMap;
    private RelativeLayout mTitle;
    private Button mNews;
    private Button mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTitle = findViewById(R.id.title);
        mMap = findViewById(R.id.map);
        mScrollLayout = findViewById(R.id.scroll_layout);
        mScrollRecyclerView = findViewById(R.id.scroll_list);
        mScrollTextView = findViewById(R.id.scroll_bottom);
        mNews = findViewById(R.id.news);
        mVideo = findViewById(R.id.video);

        initRecyclerViewData();
        initClick();
        mTitle.setBackgroundColor(Color.argb(0, 63, 81, 181));
        mScrollLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

    private void initRecyclerViewData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("我是：" + i);
        }
        mScrollRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(this, list);
        mScrollRecyclerView.setAdapter(adapter);
    }

    private void initClick() {
        mScrollLayout.setOnScrollChangedListener(new ScrollLayout.OnScrollChangedListener() {
            @Override
            public void onScrollChange(int status) {
                scrollLayouChange(status);
            }

            @Override
            public void onScrollProgress(int progress) {
                if (progress > 0) {
                    mTitle.setVisibility(View.VISIBLE);
                } else {
                    mTitle.setVisibility(View.INVISIBLE);
                }
                mTitle.setBackgroundColor(Color.argb(progress, 63, 81, 181));
                mScrollLayout.setBackgroundColor(Color.argb(progress, 0, 0, 0));
            }
        });


        mScrollTextView.setOnTextViewListener(new ScrollTextView.OnTextViewListener() {
            @Override
            public void onClick(View v) {
                mScrollLayout.toggle(ScrollLayout.STATUS_DEFAULT);
            }
        });

        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "地图", Toast.LENGTH_SHORT).show();
            }
        });

        mNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "新闻", Toast.LENGTH_SHORT).show();
            }
        });
        mVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "视频", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void scrollLayouChange(int status) {
        mScrollTextView.setVisibility(status == ScrollLayout.STATUS_CLOSE
                ? View.VISIBLE : View.GONE);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private Context mContext;
        private ArrayList<String> mList;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, ArrayList<String> list) {
            this.mContext = context;
            this.mList = list;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_recyclerview, parent, false);//可以
            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
            holder.mTv.setText(mList.get(position));
            holder.mTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "this is " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView mTv;

            public MyViewHolder(View itemView) {
                super(itemView);
                mTv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
