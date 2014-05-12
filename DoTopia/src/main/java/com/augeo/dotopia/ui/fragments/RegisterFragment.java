package com.augeo.dotopia.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.augeo.dotopia.R;
import com.augeo.dotopia.ui.activities.MainActivity;
import com.augeo.dotopia.ui.views.DoTopiaShadowButton;
import com.augeo.dotopia.util.validation.AbstractValidationUnit;
import com.augeo.dotopia.util.validation.Form;
import com.augeo.dotopia.util.validation.ValidationUnitImlp;
import com.augeo.dotopia.util.validation.validators.EmailValidator;
import com.augeo.dotopia.util.validation.validators.EmptyValidator;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class RegisterFragment extends BaseFragment {

    private static final String TAG = RegisterFragment.class.getSimpleName();

    private EditText mUsernameET;
    private EditText mEmailEt;
    private EditText mZipCodeET;
    private EditText mPasswordET;
    private EditText mConfirmPasswordET;
    private Form mForm;

    private DoTopiaShadowButton mRegisterButton;

    public static RegisterFragment getInstance(Bundle args) {
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        mUsernameET = (EditText)rootView.findViewById(R.id.et_user_name);
        mEmailEt = (EditText)rootView.findViewById(R.id.et_email);
        mZipCodeET = (EditText)rootView.findViewById(R.id.et_zip_code);
        mPasswordET = (EditText)rootView.findViewById(R.id.et_password);
        mConfirmPasswordET = (EditText)rootView.findViewById(R.id.et_confirm_password);
        mRegisterButton = (DoTopiaShadowButton)rootView.findViewById(R.id.but_login);
        mRegisterButton.setOnClickListener(getOnClickListener());
        setupValidation();
    }

    private void setupValidation() {
        mForm = new Form();
        AbstractValidationUnit userName = new ValidationUnitImlp(mUsernameET);
        AbstractValidationUnit email = new ValidationUnitImlp(mEmailEt);
        AbstractValidationUnit zipCode = new ValidationUnitImlp(mZipCodeET);
        AbstractValidationUnit password = new ValidationUnitImlp(mPasswordET);
        AbstractValidationUnit confirmPassword = new ValidationUnitImlp(mConfirmPasswordET);

        userName.addValidator(new EmptyValidator(getString(R.string.username_hint) + " " + getString(R.string.empty_field_error_msg)));
        email.addValidator(new EmailValidator(getString(R.string.invalid_email_error_msg)));
        zipCode.addValidator(new EmptyValidator(getString(R.string.zip_code_hint) + " " + getString(R.string.empty_field_error_msg)));
        password.addValidator(new EmptyValidator(getString(R.string.password_hint) + " " + getString(R.string.empty_field_error_msg)));
        confirmPassword.addValidator(new EmptyValidator(getString(R.string.password_hint) + " " + getString(R.string.empty_field_error_msg)));

        mForm.addValidationUnit(userName);
        mForm.addValidationUnit(email);
        mForm.addValidationUnit(zipCode);
        mForm.addValidationUnit(password);
        mForm.addValidationUnit(confirmPassword);
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mForm.isValid()){
                    if(!(mPasswordET.getText().equals(mConfirmPasswordET.getText()))) {
                        Toast.makeText(getActivity(), "Password do not match", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getActivity(), "Register", Toast.LENGTH_LONG).show();
                        final Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.register);
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }
}
