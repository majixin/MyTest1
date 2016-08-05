package com.bwf.landz.ui.online.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.Constants;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.LogUtils;
import com.bwf.framwork.utils.StringUtils;
import com.bwf.landz.R;
import com.bwf.landz.entity.HouseArrBean;
import com.bwf.landz.entity.HouseOneArrBean;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/20 0020.
 * Description:
 */
public class OnLineHouseAdapter extends BaseAdapter {

    private Context context;

    private List<Object> totalList;

    private int type_01 = 0;//houseArr

    private int type_02 = 1;//houseOneArr

    private ImageLoader loader;

    public OnLineHouseAdapter(Context context) {
        this.context = context;
        loader = new ImageLoader();

    }

    public void setTotalList(List<Object> totalList) {
        this.totalList = totalList;
    }

    @Override
    public int getCount() {
        return totalList == null ? 0 : totalList.size();
    }

    @Override
    public Object getItem(int position) {
        return totalList == null ? null : totalList.get(position);
    }

    @Override
    public int getItemViewType(int position) {

        if (totalList.get(position) instanceof HouseArrBean)
            return type_01;
        else
            return type_02;

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ViewHolder2 viewHolder2 = null;
        int type = getItemViewType(position);
        if (convertView == null) {

            if (type == type_01) {//houseArr
                viewHolder = new ViewHolder();
                convertView = View.inflate(context, R.layout.item_twohand_one, null);
                viewHolder.im_two_twopic = (ImageView) convertView.findViewById(R.id.im_two_twopic);
                viewHolder.tv_two_twocontent = (TextView) convertView.findViewById(R.id.tv_two_twocontent);
                viewHolder.tv_two_twoName = (TextView) convertView.findViewById(R.id.tv_two_twoName);
                viewHolder.ll_bottom_label = (LinearLayout) convertView.findViewById(R.id.ll_bottom_label);
                viewHolder.tv_detail_tese1 = (TextView) convertView.findViewById(R.id.tv_detail_tese1);
                viewHolder.tv_detail_tese2 = (TextView) convertView.findViewById(R.id.tv_detail_tese2);
                viewHolder.tv_detail_tese3 = (TextView) convertView.findViewById(R.id.tv_detail_tese3);
                viewHolder.tv_two_twoarea = (TextView) convertView.findViewById(R.id.tv_two_twoarea);
                viewHolder.tv_two_twomoney = (TextView) convertView.findViewById(R.id.tv_two_twomoney);
                convertView.setTag(viewHolder);
            } else if (type == type_02) {
                viewHolder2 = new ViewHolder2();
                convertView = View.inflate(context, R.layout.item_twohand_two, null);
                viewHolder2.im_two_onepic = (ImageView) convertView.findViewById(R.id.im_two_onepic);
                viewHolder2.tv_two_onedetail = (TextView) convertView.findViewById(R.id.tv_two_onedetail);
                viewHolder2.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
                viewHolder2.tv_one_detail = (TextView) convertView.findViewById(R.id.tv_one_detail);
                viewHolder2.tv_two_onemoney = (TextView) convertView.findViewById(R.id.tv_two_onemoney);
                convertView.setTag(viewHolder2);
            }

        } else {
            if (type == type_01) {//houseArr
                viewHolder = (ViewHolder) convertView.getTag();
            } else if (type == type_02)
                viewHolder2 = (ViewHolder2) convertView.getTag();
        }

        if (type == type_01) {//houseArr
            HouseArrBean twoModel = (HouseArrBean) totalList.get(position);
            viewHolder.tv_two_twocontent.setText(twoModel.salesTrait);
            viewHolder.tv_two_twoName.setText(twoModel.resblockName + "  "
                    + twoModel.circleTypeName);
            viewHolder.tv_two_twomoney.setText(StringUtils
                    .doubleFormat(twoModel.totalPrices) + "万");
            viewHolder.tv_two_twoarea.setText(twoModel.bedroomAmount + "室"
                    + twoModel.parlorAmount + "厅" + "  "
                    + StringUtils.doubleFormat(twoModel.buildSize) + "㎡");
            showLabel(twoModel.houseLabel, viewHolder);
            viewHolder.im_two_twopic.setImageResource(R.mipmap.defult_twopic);
            loader.displayImg(twoModel.titleImg
                            + Constants.IMG_URL_SUFFIX_ONLINE_LIST_TWO,
                    viewHolder.im_two_twopic);

        } else if (type == type_02) {//
            HouseOneArrBean oneModel = (HouseOneArrBean) totalList.get(position);
            viewHolder2.tv_two_onemoney.setText(StringUtils
                    .doubleFormat(oneModel.totalprBegin)
                    + "-"
                    + StringUtils.doubleFormat(oneModel.totalprEnd) + "万");
            viewHolder2.tv_type.setText(oneModel.resblockType);
            viewHolder2.tv_two_onedetail.setText(oneModel.resblockOneName);
            viewHolder2.tv_one_detail.setText(oneModel.bedroomAmount + "室"
                    + oneModel.parlorAmount + "厅" + "  "
                    + StringUtils.doubleFormat(oneModel.buildSize) + "㎡  "
                    + StringUtils.doubleFormat(oneModel.unitprBegin) + "-"
                    + StringUtils.doubleFormat(oneModel.unitprEnd) + "万/㎡");
            viewHolder2.im_two_onepic.setImageResource(R.mipmap.defult_onepic);
            loader.displayImg(oneModel.titlepicImg
                            + Constants.IMG_URL_SUFFIX_ONLINE_LIST_ONE,
                    viewHolder2.im_two_onepic);
        }

        return convertView;
    }

    /**
     * houseArr显示
     */
    private class ViewHolder {
        ImageView im_two_twopic;//大图
        TextView tv_two_twocontent;//
        TextView tv_two_twoName;//
        LinearLayout ll_bottom_label;//
        TextView tv_detail_tese1;//
        TextView tv_detail_tese2;//
        TextView tv_detail_tese3;//
        TextView tv_two_twoarea;//
        TextView tv_two_twomoney;//
    }

    /**
     * houseOneArr显示
     */
    private class ViewHolder2 {
        ImageView im_two_onepic;//大图
        TextView tv_two_onedetail;// 昆泰家润中心
        TextView tv_type;//  公寓
        TextView tv_one_detail;//  两室两厅
        TextView tv_two_onemoney;//  价格
    }


    // 显示标签内容
    private void showLabel(String label, ViewHolder holder) {
        if (!TextUtils.isEmpty(label)) {
            String[] arr = label.split(" ");
            if (arr.length >= 3) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
                holder.tv_detail_tese2.setText(arr[1]);
                holder.tv_detail_tese2.setVisibility(View.VISIBLE);
                holder.tv_detail_tese3.setText(arr[2]);
                holder.tv_detail_tese3.setVisibility(View.VISIBLE);
            } else if (arr.length == 2) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
                holder.tv_detail_tese2.setText(arr[1]);
                holder.tv_detail_tese2.setVisibility(View.VISIBLE);
            } else if (arr.length == 1) {
                holder.tv_detail_tese1.setText(arr[0]);
                holder.tv_detail_tese1.setVisibility(View.VISIBLE);
            }
        }
    }

}
