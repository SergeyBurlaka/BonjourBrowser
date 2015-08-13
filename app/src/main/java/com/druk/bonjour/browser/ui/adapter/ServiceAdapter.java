/*
 * Copyright (C) 2015 Andriy Druk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.druk.bonjour.browser.ui.adapter;

import com.druk.bonjour.browser.R;
import com.druk.bonjour.browser.dnssd.BonjourService;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private final int mBackground;
    private final ArrayList<BonjourService> services = new ArrayList<>();

    public ServiceAdapter(Context context) {
        TypedValue mTypedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.two_text_item, viewGroup, false);
        view.setBackgroundResource(mBackground);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public BonjourService getItem(int position) {
        return services.get(position);
    }

    public void clear() {
        this.services.clear();
    }

    public void add(BonjourService service) {
        this.services.remove(service);
        this.services.add(service);
        Collections.sort(services, (lhs, rhs) -> lhs.serviceName.compareTo(rhs.serviceName));
    }

    public void remove(BonjourService bonjourService) {
        if (this.services.remove(bonjourService)) {
            Collections.sort(services, (lhs, rhs) -> lhs.serviceName.compareTo(rhs.serviceName));
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView domain;
        public TextView serviceCount;

        public ViewHolder(View itemView) {
            super(itemView);
            domain = (TextView) itemView.findViewById(R.id.text1);
            serviceCount = (TextView) itemView.findViewById(R.id.text2);
        }
    }
}
