package com.varanegar.framework.base.account;

import android.content.Context;

import com.google.gson.Gson;
import com.varanegar.framework.util.SecurityUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import timber.log.Timber;

/**
 * Created by atp on 6/6/2017.
 */

public class AccountManager implements IAccountManager {
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String baseUrl;

    public void getAuthToken(final Account account, String scope,
                             final OnTokenAcquired onTokenAcquired, final OnError onError) {

        getToken(account.username, account.password,"password",
                scope,
                account.deviceId,
                account.token,
                "1e67dfff-e23d-461f-83ea-ba0974a46c1d",
                "")
                .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (response.isSuccessful()) {
                            Timber.i("Login successful.");
                            onTokenAcquired.run(response.body());
                        } else {
                            try {
                                String str = response.errorBody().string();
                                ErrorBody errorBody = new Gson().fromJson(str, ErrorBody.class);
                                Timber.d("Login Failed. error = %s, description = %s",
                                        errorBody.error, errorBody.description);
                                onError.onAuthenticationFailure(errorBody.error, errorBody.description);
                            } catch (Exception e) {
                                Timber.e(e,"Login Failed. Unknown error.");
                                onError.onAuthenticationFailure("Login Failed.", "Unknown error.");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Timber.e(t, "Login Failed.");
                        onError.onNetworkFailure(t);
                    }
                });
    }

    @Override
    public Call<Token> getToken(@Field("username") String username,
                                @Field("password") String password,
                                @Field("grant_type") String grantType,
                                @Field("scope") String scope,
                                @Field("DeviceId") String deviceId,
                                @Field("Token") String token,
                                @Field("vpnUser") String vpnUser,
                                @Field("SystemTypeId") String SystemTypeId
                                ) {
        if (baseUrl == null || baseUrl.isEmpty())
            throw new RuntimeException("Base Url has not been set.");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IAccountManager iAccountManager = retrofit.create(IAccountManager.class);
        return iAccountManager.getToken(username, password, grantType, scope,deviceId,token,vpnUser,SystemTypeId);
    }

    public void writeToFile(Token token, Context context, String fileName) {
        String json = new Gson().toJson(token);
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(fileName, Context.MODE_PRIVATE));
            String text = SecurityUtils.encrypt(json);
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        } catch (Exception e) {
            Timber.e(e.getMessage());
        }
    }

    public Token readFromFile(Context context, String fileName) {
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            byte[] buffer = new byte[1024];
            int n = 0;
            StringBuilder content = new StringBuilder();
            while ((n = inputStream.read(buffer)) != -1) {
                content.append(new String(buffer, 0, n));
            }
            String json = SecurityUtils.decrypt(content.toString());
            return new Gson().fromJson(json, Token.class);
        } catch (FileNotFoundException e) {
            return null;
        } catch (Exception e) {
            Timber.e(e.getMessage());
        }
        return null;
    }
}
