package com.pc.edittext;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Pietro Caselani
 */
public class MaskedWatcher implements TextWatcher {
    private String mMask;
    private EditText mEditText;
    private char mCharRepresentation;
    private boolean mIsUpdating, mAcceptOnlyNumbers;

    public MaskedWatcher(String mask, EditText editText) {
        if (mask == null)
            throw new RuntimeException("mask parameter from constructor can't be null");
        if (editText == null)
            throw new RuntimeException("editText parameter from constructor can't be null");
        mMask = mask;
        mEditText = editText;
        mIsUpdating = false;
        editText.addTextChangedListener(this);
        mCharRepresentation = '#';
    }

    public boolean acceptOnlyNumbers() {
        return mAcceptOnlyNumbers;
    }

    public void setAcceptOnlyNumbers(boolean acceptOnlyNumbers) {
        mAcceptOnlyNumbers = acceptOnlyNumbers;
    }

    public char getCharRepresentation() {
        return mCharRepresentation;
    }

    public void setCharRepresentation(char charRepresentation) {
        mCharRepresentation = charRepresentation;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        if (mIsUpdating) {
            mIsUpdating = false;
            return;
        }

        CharSequence insertedSequence = charSequence.subSequence(start, count + start);

        if (mAcceptOnlyNumbers && !isNumeric(insertedSequence)) {
            delete(start, start + count);
            return;
        }

        if (charSequence.length() > mMask.length()) {
            delete(start, start + count);
            return;
        }

        int length = mEditText.length();

        for (int i = 0; i < length; i++) {
            char m = mMask.charAt(i);
            char e = mEditText.getText().charAt(i);
            if (m != mCharRepresentation && m != e) {
                mIsUpdating = true;
                mEditText.getEditableText().insert(i, String.valueOf(m));
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    private void delete(int start, int end) {
        mIsUpdating = true;
        StringBuffer stringBuffer = new StringBuffer(mEditText.getEditableText().toString());
        stringBuffer = stringBuffer.delete(start, end);
        mEditText.setText(stringBuffer.toString());
        mEditText.setSelection(mEditText.length());
    }

    private boolean isNumeric(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence) || TextUtils.isDigitsOnly(charSequence);
    }
}