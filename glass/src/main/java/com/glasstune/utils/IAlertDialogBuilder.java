package com.glasstune.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.glasstune.activities.AlertDialog;

/**
 * Created by njackson on 16/01/15.
 */
public interface IAlertDialogBuilder {
    IAlertDialogBuilder setContext(Context context);
    IAlertDialogBuilder setText(int textResId);
    IAlertDialogBuilder setFootnote(int footnoteResId);
    IAlertDialogBuilder setIcon(int iconResId);
    IAlertDialogBuilder setOnClickListener(DialogInterface.OnClickListener clickListener);
    AlertDialog build();
}
