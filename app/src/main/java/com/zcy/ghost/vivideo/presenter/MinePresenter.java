package com.zcy.ghost.vivideo.presenter;

import android.support.annotation.NonNull;

import com.zcy.ghost.vivideo.base.RxPresenter;
import com.zcy.ghost.vivideo.model.bean.Record;
import com.zcy.ghost.vivideo.model.bean.VideoType;
import com.zcy.ghost.vivideo.model.db.RealmHelper;
import com.zcy.ghost.vivideo.presenter.contract.MineContract;
import com.zcy.ghost.vivideo.utils.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: CollectionPresenter
 * Creator: cp
 * date: 2016/9/29 12:19
 */
public class MinePresenter extends RxPresenter implements MineContract.Presenter {
    MineContract.View mView;
    public static final int maxSize = 30;

    public MinePresenter(@NonNull MineContract.View oneView) {
        mView = Preconditions.checkNotNull(oneView);
        mView.setPresenter(this);
        getHistoryData();
    }

    @Override
    public void getHistoryData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        List<VideoType> list = new ArrayList<>();
        VideoType videoType;
        int maxlinth = records.size() <= 3 ? records.size() : 3;
        for (int i = 0; i < maxlinth; i++) {
            Record record = records.get(i);
            videoType = new VideoType();
            videoType.title = record.title;
            videoType.pic = record.pic;
            videoType.dataId = record.getId();
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public void delAllHistory() {
        RealmHelper.getInstance().deleteAllRecord();
    }
}
