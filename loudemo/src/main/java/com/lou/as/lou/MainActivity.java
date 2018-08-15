/*
 * Copyright  (c) 2017 Lyloou
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lou.as.lou;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lyloou.lou.util.Uactivity;
import com.lyloou.lou.util.Uscreen;

import java.util.Map;

/**
 * Author:    Lou
 * Version:   V1.0
 * Date:      2017.06.28 11:57
 * <p>
 * Description:
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        Map<String, Class> stringClassMap = Uactivity.getActivitiesMapFromManifest(this, this.getPackageName());

        recyclerView.setAdapter(new MainAdapter(stringClassMap));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new ItemOffsetDecoration(Uscreen.dp2Px(this, 16)));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "本页既是了", Toast.LENGTH_SHORT).show();
    }

    static class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
        final Map<String, Class> stringClassMap;
        Object[] labels;

        MainAdapter(Map<String, Class> stringClassMap) {
            this.stringClassMap = stringClassMap;
            labels = stringClassMap.keySet().toArray();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final String label = (String) labels[position];
            holder.tvTitle.setText(label);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uactivity.start(v.getContext(), stringClassMap.get(label));
                }
            });
        }

        @Override
        public int getItemCount() {
            return labels.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;
            View view;

            ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            }
        }
    }

    static class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
        private int offset;

        ItemOffsetDecoration(int offset) {
            this.offset = offset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildLayoutPosition(view);
            if (position == 0) {
                outRect.top = offset * 2;
            }

            outRect.bottom = offset;
            outRect.left = offset;
            outRect.right = offset;

        }
    }

}
