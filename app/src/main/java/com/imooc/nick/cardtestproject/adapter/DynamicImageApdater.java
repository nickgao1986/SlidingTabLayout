package com.imooc.nick.cardtestproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.imooc.nick.cardtestproject.R;
import com.imooc.nick.cardtestproject.Util.DeviceUtils;
import com.imooc.nick.cardtestproject.Util.StringUtils;

import java.util.List;


/**
 * Created by gaoyoujian on 2017/4/28.
 */

public class DynamicImageApdater extends BaseAdapter {

    private static final String TAG = "DynamicImageApdater";
    private LayoutInflater mLayoutInflater;
    private List<String> imagesList;
    private int itemWH = 48;
    /*    private ImageUtil imageUtil;*/
    private Context context;
    private int catId;
    private View.OnLongClickListener onLongClickListener;

    public DynamicImageApdater(Context context, List<String> models, boolean isLocalPath, int catId) {
        super();
        this.context = context;
        this.catId = catId;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.imagesList = models;
        itemWH = (DeviceUtils.getScreenWidth(context.getApplicationContext()) - DeviceUtils.dip2px(context, 90)) / 3;  //扣掉其它控件宽度及间距90

    }

    public int getCount() {
        return imagesList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public void setEventTag() {

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.layout_dynamic_image_item, parent, false);
                viewHolder.initHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }


            String url = imagesList.get(position);


            viewHolder.ivPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (!StringUtils.isNull(url)) {
            }
            viewHolder.ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            if (null != onLongClickListener) {
                viewHolder.ivPhoto.setOnLongClickListener(onLongClickListener);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return convertView;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }


    public class ViewHolder {
        public ViewHolder() {

        }

        public SimpleDraweeView ivPhoto;
        public ImageView ivBadge;

        public void initHolder(View convertView) {
            this.ivPhoto = (SimpleDraweeView) convertView.findViewById(R.id.ivPhoto);
            resizeKuang();
        }


        private void resizeKuang() {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
            layoutParams.width = itemWH;
            layoutParams.height = itemWH;
            ivPhoto.requestLayout();
        }
    }

}
