package com.clinkk.base.permDialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.clinkk.R;
import com.clinkk.base.BaseDialog;


/**
 * This is custom dialog for showing the permission Requirement.
 * */
public class ReqPermissionDialog extends BaseDialog implements View.OnClickListener {

    TextView tvTitle, tvDesc;
    private int vTitle, vDesc;
    private Listener listener;

    public ReqPermissionDialog(@NonNull Context context, @StringRes int tvTitle, @StringRes int tvDesc, Listener listener) {
        super(context);
        this.vDesc = tvDesc;
        this.vTitle = tvTitle;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_permission);
        setDimBlur(getWindow());
        tvTitle = findViewById(R.id.tvTitle);
        tvDesc = findViewById(R.id.tvDescription);

        try {
            tvDesc.setText(vDesc);
            tvTitle.setText(vTitle);
        } catch (Exception e) {
        }

        findViewById(R.id.tvOk).setOnClickListener(this);
        findViewById(R.id.tvCancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvOk:
                listener.onPositive();
                break;
            case R.id.tvCancel:
                listener.onNegative();
                break;
        }
        ReqPermissionDialog.this.dismiss();
    }

    public interface Listener {
        void onPositive();

        void onNegative();
    }
}
