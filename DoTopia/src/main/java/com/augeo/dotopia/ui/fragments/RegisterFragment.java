package com.augeo.dotopia.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.augeo.dotopia.R;
import com.augeo.dotopia.models.AuthenticationToken;
import com.augeo.dotopia.models.DeviceClientObject;
import com.augeo.dotopia.models.RegisterRequestBody;
import com.augeo.dotopia.navigation.events.NavigationEvent;
import com.augeo.dotopia.networking.RestServiceCreator;
import com.augeo.dotopia.ui.activities.MainActivity;
import com.augeo.dotopia.ui.fragments.dialogs.BaseDialog;
import com.augeo.dotopia.ui.fragments.dialogs.ProgressDialog;
import com.augeo.dotopia.ui.views.DoTopiaShadowButton;
import com.augeo.dotopia.util.Constants;
import com.augeo.dotopia.util.DataHolder;
import com.augeo.dotopia.util.DoTopiaLog;
import com.augeo.dotopia.util.validation.AbstractValidationUnit;
import com.augeo.dotopia.util.validation.Form;
import com.augeo.dotopia.util.validation.ValidationUnitImlp;
import com.augeo.dotopia.util.validation.validators.EmailValidator;
import com.augeo.dotopia.util.validation.validators.EmptyValidator;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
                    if(!(mPasswordET.getText().toString().equals(mConfirmPasswordET.getText().toString()))) {
                        Toast.makeText(getActivity(), "Password do not match", Toast.LENGTH_LONG).show();
                    }else{
                        showProgressDialog();
                        final SharedPreferences prefs = getActivity().getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
                        if(!(prefs.contains(DeviceClientObject.CLIENT_ID_PREFS_KEY))) {
                            String deviceID = Settings.Secure.getString(getActivity().getContentResolver(),Settings.Secure.ANDROID_ID);
                            String deviceName = Build.MANUFACTURER + " " + Build.MODEL;
                            RestServiceCreator.createLoginRegisterService().registerDevice(deviceID, deviceName, "android", new Callback<DeviceClientObject>() {
                                @Override
                                public void success(DeviceClientObject deviceClientObject, Response response) {
                                    DoTopiaLog.d("Device registered successfully");
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString(DeviceClientObject.CLIENT_ID_PREFS_KEY, deviceClientObject.getClientId());
                                    editor.putString(DeviceClientObject.CLIENT_SECRET_PREFS_KEY, deviceClientObject.getClientSecret());
                                    editor.commit();
                                    completeRegistration();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    DoTopiaLog.d(error.getMessage());
                                }
                            });
                        }else{
                            completeRegistration();
                        }
                    }
                }
            }
        };
    }

    private void completeRegistration() {
        final SharedPreferences prefs = getActivity().getSharedPreferences(Constants.SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        String clientId = prefs.getString(DeviceClientObject.CLIENT_ID_PREFS_KEY, "");
        String clientSecret = prefs.getString(DeviceClientObject.CLIENT_SECRET_PREFS_KEY, "");

        if(!(TextUtils.isEmpty(clientId)) && !(TextUtils.isEmpty(clientSecret))) {
            RestServiceCreator.createLoginRegisterService().getTokenAsClient("client_credentials", clientId, clientSecret, new Callback<AuthenticationToken>() {
                @Override
                public void success(AuthenticationToken authenticationToken, Response response) {
                    hideProgressDialog();
                    String regEmail = mEmailEt.getText().toString();
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(Constants.SHARED_PREFS_REGISTERED_MAIL_KEY, regEmail);
                    editor.commit();
                    DataHolder.getInstance().setAuthToken(authenticationToken);
                    RegisterRequestBody body = new RegisterRequestBody();
                    body.setEmail(mEmailEt.getText().toString());
                    body.setPassword(mPasswordET.getText().toString());
                    body.setZip(mZipCodeET.getText().toString());
                    RestServiceCreator.createLoginRegisterService().registerUser(Constants.CONTENT_TYPE, "Bearer " + authenticationToken.getAccessToken(), body, new Callback<DeviceClientObject>() {
                        @Override
                        public void success(DeviceClientObject deviceClientObject, Response response) {
                            final Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            DoTopiaLog.d("MSG");
                        }
                    });

                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(getActivity(), "Registration error", Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgressDialog() {
        Bundle args = new Bundle();
        args.putString(BaseDialog.MESSAGE_ARG_KEY, getString(R.string.loging_in_label));
        BaseDialog progressDialog = ProgressDialog.getInstance(args);
        progressDialog.show(getActivity().getSupportFragmentManager(), ProgressDialog.TAG);
    }

    private void hideProgressDialog() {
        BaseDialog dialog = (ProgressDialog)getActivity().getSupportFragmentManager().findFragmentByTag(ProgressDialog.TAG);
        if(dialog != null) {
            DoTopiaLog.d("Dialog dismissed");
            dialog.dismiss();
        }
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
