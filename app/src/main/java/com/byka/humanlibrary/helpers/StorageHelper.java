package com.byka.humanlibrary.helpers;

import android.content.SharedPreferences;
import android.util.Base64;

import com.byka.humanlibrary.constants.RestConstants;

import static com.byka.humanlibrary.constants.Constants.StorageConstants.IS_LOGGED;
import static com.byka.humanlibrary.constants.Constants.StorageConstants.NICKNAME;
import static com.byka.humanlibrary.constants.Constants.StorageConstants.TOKEN;

public class StorageHelper {
    public static void saveUserInfo(final String token, final String userNickname, final SharedPreferences.Editor editor) {
        editor.putString(NICKNAME, userNickname).apply();
        editor.putString(TOKEN, Base64.encodeToString(token.getBytes(), Base64.DEFAULT)).apply();
        editor.putBoolean(IS_LOGGED, true).apply();
    }

    public static void removeUserInfo(final SharedPreferences.Editor editor) {
        editor.remove(NICKNAME).apply();
        editor.remove(TOKEN).apply();
        editor.remove(IS_LOGGED).apply();
        RestConstants.AUTH_TOKEN = null;
    }
}
