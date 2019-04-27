package com.byka.humanlibrary.helpers;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.ref.WeakReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseHelper {
    private static final Logger logger = Logger.getLogger("ResponseHelper");

    @Nullable
    private WeakReference<TextView> textViewWeakReference;

    @Nullable
    private Context context;

    public ResponseHelper(Context context) {
        this.context = context;
    }

    public ResponseHelper(TextView textView) {
        this.textViewWeakReference = new WeakReference<>(textView);
    }

    public <T> T parseResponse(ResponseEntity<T> resp) {
        switch (resp.getStatusCode()) {
            case OK:
//                hideEmptyListView();
                return resp.getBody();
            case UNAUTHORIZED:
                showUnauthorizedMessage();
                break;
            case FORBIDDEN:
                showPermissionsMessage();
                break;
            case INTERNAL_SERVER_ERROR:
                showServerError();
                break;
            default:
                showUnknownException(resp.getStatusCode());
                break;
        }

        return null;
    }

    private void hideEmptyListView() {
        if (textViewWeakReference != null) {
            TextView textView = textViewWeakReference.get();
            if (textView != null) {
                textView.setVisibility(View.INVISIBLE);
            } else {
                logger.log(Level.INFO, "Cannot find empty list text view to hide");
            }
        }
    }

    private void showUnauthorizedMessage() {
        showMessage("Your need to auth first");
    }

    private void showServerError() {
        showMessage("Exception, try again");
    }

    private void showPermissionsMessage() {
        showMessage("You doesn't have permissions to do this");
    }

    private void showMessage(String text) {
        if (textViewWeakReference != null) {
            TextView textView = textViewWeakReference.get();
            if (textView != null) {
                textView.setText(text);
                textView.setVisibility(View.VISIBLE);
            } else {
                logger.log(Level.INFO, "Cannot find empty list text view to hide");
            }
        } else if (context != null) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }

    private void showUnknownException(HttpStatus status) {
        showMessage(status.getReasonPhrase());
    }
}
