package com.kisannetwork.contactsapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.kisannetwork.contactsapp.R;

public class MyProgressDialog {

    private static Dialog dialog;

    public static void show(Context context) {
        try {
            if (dialog != null) {
                if (dialog.isShowing())
                    dialog.dismiss();
                dialog = null;
            }

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.dialog_progress);
            dialog.show();
        } catch (Exception e) {

        }

    }

    public static void hide() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

}

