package com.example.tung.Lesson4_Permision_Load_Image.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.ImageVideoModelLoader;
import com.example.tung.Lesson4_Permision_Load_Image.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 5/14/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<String> mPathList;
    public String mLoadImageBy;

    public ImageAdapter(List<String> pathList, String loadImageBy) {
        mPathList = pathList;
        mLoadImageBy = loadImageBy;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mPathList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPathList == null ? 0 : mPathList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.image_load);
        }

        public void bindData(String imagePath) {
            if (imagePath != null) {
                switch (mLoadImageBy) {
                    case "Glide": // Load image by Glide
                        Glide.with(itemView.getContext())
                                .load(imagePath)
                                .centerCrop()
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher_round)
                                .into(mImage);
                        break;
                    case "Picasso": // Load image by Picasso
                        Picasso.with(itemView.getContext())
                                .load(imagePath)
//                                .centerCrop()
                                .placeholder(R.mipmap.ic_launcher)
                                .error(R.mipmap.ic_launcher_round)
                                .into(mImage);
                        break;
                    default: // Load image by Universal Image Loader
                        ImageLoader imageLoader = ImageLoader.getInstance();
                        imageLoader.init(ImageLoaderConfiguration.createDefault(itemView.getContext()));
                        imageLoader.displayImage(imagePath, mImage);
                }
            }
        }
    }
}
