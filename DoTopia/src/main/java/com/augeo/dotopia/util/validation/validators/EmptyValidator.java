package com.augeo.dotopia.util.validation.validators;

/**
 * Created by krasimir.karamazov on 4/10/2014.
 */
import android.text.TextUtils;
import com.augeo.dotopia.R;
import com.augeo.dotopia.util.validation.AbstractValidator;

public class EmptyValidator extends AbstractValidator {

    public EmptyValidator(String message) {
        super(message);
    }

    @Override
    public boolean isValid(CharSequence value) {
        return !TextUtils.isEmpty(value);
    }
    
    @Override
    public boolean isValid(Boolean value) {
        return value;
    }

    @Override
    protected int getConcreteMessageId() {
        return R.string.empty_field_error_msg;
    }

}
