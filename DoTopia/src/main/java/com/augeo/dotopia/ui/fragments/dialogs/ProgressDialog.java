package com.augeo.dotopia.ui.fragments.dialogs;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.augeo.dotopia.R;

/**
 * Created by Krasimir on 5/13/2014.
 */
public class ProgressDialog extends BaseDialog {

    public static final String TAG = ProgressDialog.class.getSimpleName();

    public static BaseDialog getInstance(Bundle args) {
        BaseDialog dialog = new ProgressDialog();
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setCancelable(false);
    }

    @Override
    protected void initUI(View view) {
        final TextView tvMessage = (TextView)view.findViewById(R.id.tv_message);
        tvMessage.setText(getArguments().getString(BaseDialog.MESSAGE_ARG_KEY));
    }

    @Override
    protected void setupDialog(AlertDialog.Builder dialogBuilder) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_progress_dialog;
    }

    @Override
    protected boolean getIsCustomDialog() {
        return true;
    }

    @Override
    protected String getDialogTag() {
        return TAG;
    }
}
