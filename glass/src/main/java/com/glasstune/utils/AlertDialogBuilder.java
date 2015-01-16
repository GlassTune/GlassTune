package com.glasstune.utils;

import android.content.Context;
import android.content.DialogInterface;

import com.glasstune.activities.AlertDialog;

/**
 * Created by njackson on 16/01/15.
 */
public class AlertDialogBuilder implements IAlertDialogBuilder {
    private Context _context;
    private int _textResID;
    private int _footnoteResId;
    private int _iconResId;
    private DialogInterface.OnClickListener _clickListener;

    @Override
    public IAlertDialogBuilder setContext(Context context) {
        _context = context;
        return this;
    }

    @Override
    public IAlertDialogBuilder setText(int textResId) {
        _textResID = textResId;
        return this;
    }

    @Override
    public IAlertDialogBuilder setFootnote(int footnoteResId) {
        _footnoteResId = footnoteResId;
        return this;
    }

    @Override
    public IAlertDialogBuilder setIcon(int iconResId) {
        _iconResId = iconResId;
        return this;
    }

    @Override
    public IAlertDialogBuilder setOnClickListener(DialogInterface.OnClickListener clickListener) {
        _clickListener = clickListener;
        return this;
    }

    @Override
    public AlertDialog build() {
        return new AlertDialog(_context,_iconResId,_textResID,_footnoteResId,_clickListener);
    }
}
