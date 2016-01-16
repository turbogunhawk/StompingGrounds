package me.tedlandis.stompinggrounds.util;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * implements {@link TextWatcher} with no-op implementations. override the methods you need.
 */
public class TextChangedAdapter implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
