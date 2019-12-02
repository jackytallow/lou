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

package com.lyloou.test.mxnzp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lyloou.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:    Lou
 * Version:   V1.0
 * Date:      2017.06.20 17:31
 * <p>
 * Description:
 */
class TuPianAdapter extends RecyclerView.Adapter<TuPianAdapter.TuPianViewHolder> {
    private final List<TuPian> mList;
    private OnItemTuPianClickListener mOnItemTuPianClickListener;

    public TuPianAdapter() {
        mList = new ArrayList<>();
    }

    public void addItem(TuPian tupian) {
        mList.add(tupian);
    }

    public void addItems(List<TuPian> tupians) {
        mList.addAll(tupians);
        notifyDataSetChanged();
    }

    @Override
    public TuPianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laifudao_tupian, null);

        return new TuPianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TuPianViewHolder viewHolder, int position) {
        TuPian tupian = mList.get(position);

        ImageView ivPoster = viewHolder.ivTuPian;
        Glide.with(ivPoster.getContext())
                .load(tupian.getSourceurl())
                .into(ivPoster);
        viewHolder.view.setOnClickListener(v -> {
            if (mOnItemTuPianClickListener != null) {
                mOnItemTuPianClickListener.onClick(tupian);
            }
        });
        viewHolder.view.setOnLongClickListener(v -> {
            if (mOnItemTuPianClickListener != null) {
                mOnItemTuPianClickListener.onLongClick(tupian);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setOnItemTuPianClickListener(OnItemTuPianClickListener onItemTuPianClickListener) {
        mOnItemTuPianClickListener = onItemTuPianClickListener;
    }

    interface OnItemTuPianClickListener {
        void onClick(TuPian tuPian);

        void onLongClick(TuPian tuPian);
    }

    class TuPianViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivTuPian;
        private final View view;

        public TuPianViewHolder(View view) {
            super(view);
            this.view = view;
            ivTuPian = (ImageView) view.findViewById(R.id.iv_tupian);
        }
    }
}
