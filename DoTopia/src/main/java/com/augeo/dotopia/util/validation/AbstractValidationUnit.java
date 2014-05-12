package com.augeo.dotopia.util.validation;

/**
 * Created by krasimir.karamazov on 2/28/14.
 */
import android.widget.TextView;

public interface AbstractValidationUnit {
    public void addValidator(AbstractValidator validator);
    public TextView getAttachedComponent();
    public boolean isValid();
    public void showError();
    public void hideError();
}

