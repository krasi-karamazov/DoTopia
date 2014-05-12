package com.augeo.dotopia.util.validation;

/**
 * Created by krasimir.karamazov on 2/28/14.
 */

import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ValidationUnitImlp implements AbstractValidationUnit {

    private List<AbstractValidator> mValidators = new ArrayList<AbstractValidator>();
    private TextView mComponent;
    private String mErrorMessage;
    public ValidationUnitImlp(TextView txtView) {
        mComponent = txtView;
    }

    @Override
    public void addValidator(AbstractValidator validator) {
        mValidators.add(validator);
    }

    @Override
    public TextView getAttachedComponent() {
        return mComponent;
    }

    @Override
    public boolean isValid() {
        boolean isValid = true;

        for(AbstractValidator validator : mValidators) {
            if(!validator.isValid(mComponent.getText())) {
                isValid = false;
                mErrorMessage = validator.getMessage();
                break;
            }
        }
        return isValid;
    }

    @Override
    public void showError() {
        this.mComponent.setError(mErrorMessage);
    }

    @Override
    public void hideError() {
        this.mComponent.setError(null);
    }
}

