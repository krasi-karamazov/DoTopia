package com.augeo.dotopia.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.augeo.dotopia.R;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.ui.activities.MainActivity;
import com.augeo.dotopia.ui.views.DoTopiaShadowButton;
import com.augeo.dotopia.util.BusProvider;
import com.augeo.dotopia.util.validation.AbstractValidationUnit;
import com.augeo.dotopia.util.validation.Form;
import com.augeo.dotopia.util.validation.ValidationUnitImlp;
import com.augeo.dotopia.util.validation.validators.EmptyValidator;

/**
 * Created by krasimir.karamazov on 4/30/2014.
 */
public class LoginFragment extends BaseFragment {

    public static final String USERNAME_ARGS_KEY = "username";
    public static final String PASSWORD_ARGS_KEY = "password";


    private static final String TAG = LoginFragment.class.getSimpleName();

    private EditText mUserNameET;
    private EditText mPasswordET;
    private DoTopiaShadowButton mLoginButton;
    private Form mForm;


    public static LoginFragment getInstance(Bundle args) {
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initUI(View rootView) {
        mUserNameET = (EditText)rootView.findViewById(R.id.et_user_name);
        mUserNameET.setOnEditorActionListener(getOnEditorActionListener());
        mPasswordET = (EditText)rootView.findViewById(R.id.et_password);
        mPasswordET.setOnEditorActionListener(getOnEditorActionListener());

        mLoginButton = (DoTopiaShadowButton)rootView.findViewById(R.id.but_login);
        mLoginButton.setOnClickListener(getOnClickListener());

        if(getArguments() != null) {
            if(!TextUtils.isEmpty(getArguments().getString(USERNAME_ARGS_KEY))){
                mUserNameET.setText(getArguments().getString(USERNAME_ARGS_KEY));
            }

            if(!TextUtils.isEmpty(getArguments().getString(PASSWORD_ARGS_KEY))) {
                mPasswordET.setText(getArguments().getString(PASSWORD_ARGS_KEY));
            }
        }
        rootView.findViewById(R.id.tv_register_today).setOnClickListener(getOnClickListener());

        setupValidation();
    }

    private TextView.OnEditorActionListener getOnEditorActionListener() {
        return new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(textView.getId() == mUserNameET.getId()){
                    mPasswordET.requestFocus();
                    return true;
                }else if(textView.getId() == mPasswordET.getId()) {
                    performLogin();
                    return true;
                }
                return false;
            }
        };
    }

    private void setupValidation() {
        mForm = new Form();
        AbstractValidationUnit userName = new ValidationUnitImlp(mUserNameET);
        AbstractValidationUnit password = new ValidationUnitImlp(mPasswordET);
        userName.addValidator(new EmptyValidator(getString(R.string.username_hint) + " " + getString(R.string.empty_field_error_msg)));
        password.addValidator(new EmptyValidator(getString(R.string.password_hint) + " " + getString(R.string.empty_field_error_msg)));
        mForm.addValidationUnit(userName);
        mForm.addValidationUnit(password);
    }

    private View.OnClickListener getOnClickListener() {
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.tv_register_today:
                        BusProvider.getInstance().post(new NavigationEvent(RegisterFragment.getInstance(null)));
                        break;
                    case R.id.but_login:
                        performLogin();
                        break;
                }
            }
        };
    }

    private void performLogin(){
        if(mForm.isValid()){
            //Toast.makeText(getActivity(), "Login", Toast.LENGTH_LONG).show();
            //final Intent intent = new Intent(getActivity(), MainActivity.class);
            //startActivity(intent);
            //getActivity().finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.login);
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }
}
