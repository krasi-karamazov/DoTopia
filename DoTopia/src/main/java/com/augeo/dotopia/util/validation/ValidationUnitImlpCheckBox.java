package com.augeo.dotopia.util.validation;

import java.util.ArrayList;
import java.util.List;

import android.widget.CheckBox;

public class ValidationUnitImlpCheckBox implements AbstractValidationUnit {

    private List<AbstractValidator> mValidators = new ArrayList<AbstractValidator>();
    private CheckBox mCheckBox;
    private String mErrorMessage;
    public ValidationUnitImlpCheckBox(CheckBox chkBox){
    	mCheckBox = chkBox;
    }

    @Override
    public void addValidator(AbstractValidator validator) {
        mValidators.add(validator);
    }

    @Override
    public CheckBox getAttachedComponent() {
        return mCheckBox;
    }

    @Override
    public boolean isValid() {
        boolean isValid = true;

        for(AbstractValidator validator : mValidators) {
            if(!validator.isValid(mCheckBox.isChecked())) {
                isValid = false;
                mErrorMessage = validator.getMessage();
                break;
            }
        }
        return isValid;
    }

    @Override
    public void showError() {
        this.mCheckBox.setError(mErrorMessage);
    }

    @Override
    public void hideError() {
        this.mCheckBox.setError(null);
    }
}
