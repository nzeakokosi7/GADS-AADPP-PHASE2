package com.example.aadpp.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.aadpp.Models.SkillLeaderResponseModel;
import com.example.aadpp.R;

import java.util.ArrayList;
import java.util.List;

public class SkillLeaderAdapter extends RecyclerView.Adapter<SkillLeaderAdapter.MenuViewHolder> {
    public static final String TAG = SkillLeaderAdapter.class.getSimpleName();

    class MenuViewHolder extends RecyclerView.ViewHolder {

      View mView;
      private TextView mLeaderName, mLeaderStats;
      private ImageView mBadgeDisplay;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            mLeaderName = mView.findViewById(R.id.leader_name);
            mLeaderStats = mView.findViewById(R.id.leader_stats);
            mBadgeDisplay = mView.findViewById(R.id.badge_display);

        }

        public void setPropertyImage(final Context ctx, String displayUrl) {
            Glide.with(ctx)
                    .load(displayUrl)
                    .thumbnail(0.1f)
                    .apply(new RequestOptions())

                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            mProgressLoader.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            mProgressLoader.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(mBadgeDisplay);
        }
    }

    private final LayoutInflater mInflater;
    private List<SkillLeaderResponseModel> mLeaderList;
    private Context mContext;

    public SkillLeaderAdapter(Context context) {
        mLeaderList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setLeaderList(List<SkillLeaderResponseModel> SkillLeaderResponseModels) {
        this.mLeaderList.clear();
        this.mLeaderList.addAll(SkillLeaderResponseModels);

        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.skill_leader_item_view, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder viewHolder, int position) {
        Log.d("View", "onBindViewHolder: " + position);
        SkillLeaderResponseModel current = this.mLeaderList.get(position);
        viewHolder.mLeaderName.setText(current.getName());
        String stats = current.getScore() + " skill IQ score, " + current.getCountry();
        viewHolder.mLeaderStats.setText(stats);
        
        viewHolder.setPropertyImage(mContext, current.getBadgeUrl());

    }

    @Override
    public int getItemCount() {
        if (mLeaderList != null)
            return mLeaderList.size();
        else return 0;
    }

}
