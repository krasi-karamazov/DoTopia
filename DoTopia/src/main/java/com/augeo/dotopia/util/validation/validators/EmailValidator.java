package com.augeo.dotopia.util.validation.validators;

/**
 * Created by krasimir.karamazov on 2/28/14.
 */

import com.augeo.dotopia.R;
import com.augeo.dotopia.util.validation.AbstractValidator;
import java.util.regex.Pattern;


public class EmailValidator extends AbstractValidator {
    private Pattern mPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    public EmailValidator(String message) {
        super(message);
    }

    @Override
    public boolean isValid(CharSequence value) {
        return mPattern.matcher(value).matches();
    }
    
    @Override
    public boolean isValid(Boolean value) {
        return value;
    }

    @Override
    protected int getConcreteMessageId() {
        return R.string.invalid_email_error_msg;
    }

}
