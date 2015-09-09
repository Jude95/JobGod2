package com.ant.jobgod.jobgod.module.main.joblist;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Region;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bingoogolapple.flowlayout.BGAFlowLayout;

/**
 * Created by Mr.Jude on 2015/7/11.
 */
@RequiresPresenter(FiltratePresenter.class)
public class FiltrateActivity extends BeamBaseActivity<FiltratePresenter> {
    @InjectView(R.id.img_trade_add)
    ImageView imgTradeAdd;
    @InjectView(R.id.img_city_add)
    ImageView imgCityAdd;
    @InjectView(R.id.tv_sort)
    TextView tvSort;
    @InjectView(R.id.grid_trade)
    BGAFlowLayout gridTrade;
    @InjectView(R.id.grid_city)
    BGAFlowLayout gridCity;

    private FiltrateAdapter mTradeAdapter;
    private FiltrateAdapter mCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_filtrate);
        ButterKnife.inject(this);
        mTradeAdapter = new FiltrateAdapter(position -> getPresenter().deleteTrade(position),gridTrade);
        mCityAdapter = new FiltrateAdapter(position -> getPresenter().deleteCity(position),gridCity);
        imgTradeAdd.setOnClickListener(v -> getPresenter().beginAddTrade());
        imgCityAdd.setOnClickListener(v -> getPresenter().beginAddCity());
        tvSort.setOnClickListener(v -> getPresenter().beginSetSort());
    }

    public void setTrade(Trade[] trades) {
        String[] texts = new String[trades.length];
        for (int i = 0; i < trades.length; i++) {
            texts[i] = trades[i].getName();
        }
        mTradeAdapter.setTexts(texts);
    }

    public void setCity(Region[] regions) {
        String[] texts = new String[regions.length];
        for (int i = 0; i < regions.length; i++) {
            texts[i] = regions[i].getName();
        }
        mCityAdapter.setTexts(texts);
    }

    public void setSort(String sortName) {
        tvSort.setText(sortName);
    }

    public void showSortDialog(String[] sorts) {
        new MaterialDialog.Builder(this)
                .title("选择排序方式")
                .items(sorts)
                .positiveText(R.string.ok)
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
                        getPresenter().finishSetSort(i);
                        return true;
                    }
                })
                .show();
    }

    public void showTradeDialog(final Trade[] trades) {
        String[] texts = new String[trades.length];
        for (int i = 0; i < trades.length; i++) {
            texts[i] = trades[i].getName();
        }
        new MaterialDialog.Builder(this)
                .title("选择感兴趣的行业")
                .items(texts)
                .positiveText(R.string.ok)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog materialDialog, Integer[] integers, CharSequence[] charSequences) {
                        Trade[] add = new Trade[integers.length];
                        for (int i = 0; i < integers.length; i++) {
                            add[i] = trades[integers[i]];
                        }
                        getPresenter().finishAddTrade(add);
                        return false;
                    }
                })
                .show();
    }

    private MaterialDialog dialog;

    public void showCityDialog(int laseRegionCode) {
        RegionView view = new RegionView(this, region -> {
            getPresenter().finishAddCity(region);
            dialog.dismiss();
        }, laseRegionCode);
        dialog = new MaterialDialog.Builder(this)
                .title("选择感兴趣的地区")
                .customView(view, false)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filtrate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filtrate) {
            getPresenter().saveAndExit();
        }
        return super.onOptionsItemSelected(item);
    }
}
