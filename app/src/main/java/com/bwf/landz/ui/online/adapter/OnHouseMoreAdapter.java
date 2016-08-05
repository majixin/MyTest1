package com.bwf.landz.ui.online.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.framwork.Constants;
import com.bwf.framwork.image.ImageLoader;
import com.bwf.framwork.utils.StringUtils;
import com.bwf.landz.R;
import com.bwf.landz.entity.HouseArrBean;
import com.bwf.landz.entity.HouseOneArrBean;

import java.util.List;

/**
 * Created by Lizhangfeng on 2016/7/20 0020.
 * Description: 更多房源推荐
 */
public class OnHouseMoreAdapter extends BaseAdapter {

    private Context context;

    private List<HouseOneArrBean> totalList;

    private ImageLoader loader;

    public OnHouseMoreAdapter(Context context) {
        this.context = context;
        loader = new ImageLoader();

    }

    public void setTotalList(List<HouseOneArrBean> totalList) {
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder2 viewHolder2 = null;
        if (convertView == null) {

            viewHolder2 = new ViewHolder2();
            convertView = View.inflate(context, R.layout.item_twohand_two, null);
            viewHolder2.im_two_onepic = (ImageView) convertView.findViewById(R.id.im_two_onepic);
            viewHolder2.tv_two_onedetail = (TextView) convertView.findViewById(R.id.tv_two_onedetail);
            viewHolder2.tv_type = (TextView) convertView.findViewById(R.id.tv_type);
            viewHolder2.tv_one_detail = (TextView) convertView.findViewById(R.id.tv_one_detail);
            viewHolder2.tv_two_onemoney = (TextView) convertView.findViewById(R.id.tv_two_onemoney);
            convertView.setTag(viewHolder2);
        } else
        {
            viewHolder2 = (ViewHolder2) convertView.getTag();
        }


        HouseOneArrBean oneModel = totalList.get(position);
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

        return convertView;
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

}
