package com.zaotao.daylucky.view.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.isuu.base.widget.title.AppTitleBarLayout;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.base.BaseActivity;
import com.zaotao.daylucky.presenter.AboutUsPresenter;

import butterknife.BindView;

public class AboutUsActivity extends BaseActivity<AboutUsPresenter> {

    @BindView(R.id.app_bar_about_us)
    AppTitleBarLayout appBarAboutUs;
    @BindView(R.id.about_us_item0)
    RelativeLayout aboutUsItem0;
    @BindView(R.id.about_us_item1)
    RelativeLayout aboutUsItem1;
    @BindView(R.id.about_us_item2)
    RelativeLayout aboutUsItem2;
    @BindView(R.id.about_us_company_text)
    TextView aboutUsCompanyText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        appBarAboutUs.setStartImageResource(R.drawable.ic_back);
        appBarAboutUs.setTitleText(getString(R.string.about_us));
        aboutUsCompanyText.setText(R.string.about_us_copyright_text);
    }

    @Override
    protected void initListener() {
        aboutUsItem0.setOnClickListener(v -> AppWebViewActivity.startAppWebViewActivity(mContext, getSupportPresenter().htmlUserProtocol(), getString(R.string.title_bar_text_agreement)));
        aboutUsItem1.setOnClickListener(v -> AppWebViewActivity.startAppWebViewActivity(mContext, getSupportPresenter().htmlPrivatePrivacy(), getString(R.string.title_bar_text_privacy)));
        aboutUsItem2.setOnClickListener(v -> AppWebViewActivity.startAppWebViewActivity(mContext, getSupportPresenter().htmlContactsUs(), getString(R.string.title_bar_text_contacts_us)));
    }

    @Override
    protected AboutUsPresenter initPresenter() {
        return new AboutUsPresenter();
    }

}