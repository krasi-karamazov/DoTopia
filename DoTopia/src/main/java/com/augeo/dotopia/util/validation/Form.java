package com.augeo.dotopia.util.validation;

/**
 * Created by krasimir.karamazov on 2/28/14.
 */

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Form {
    private List<AbstractValidationUnit> mValidationUnits = new ArrayList<AbstractValidationUnit>();

    public void addValidationUnit(AbstractValidationUnit mItem) {
        mValidationUnits.add(mItem);
    }

    public boolean isValid() {
        boolean isValid = true;
        for(AbstractValidationUnit unit : mValidationUnits) {
            if(!unit.isValid()) {
                unit.showError();
                isValid = false;
            }
        }
        return isValid;
    }
    public void clearErrors() {
    	for(AbstractValidationUnit unit : mValidationUnits) {
    		unit.hideError();
    	}
    }

    public void setupValidationUnitsListeners() {
        for(AbstractValidationUnit unit : mValidationUnits) {
            unit.getAttachedComponent().setOnFocusChangeListener(getOnFocusChangeListener(unit));
        }
    }

    private View.OnFocusChangeListener getOnFocusChangeListener(final AbstractValidationUnit unit) {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!v.hasFocus()){
                    if(!unit.isValid()) {
                        unit.showError();
                    }else{
                        unit.hideError();
                    }
                }
            }
        };
    }
}

