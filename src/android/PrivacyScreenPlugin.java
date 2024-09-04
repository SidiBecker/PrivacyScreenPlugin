/**
 * PrivacyScreenPlugin.java Cordova Plugin Implementation
 * Created by Tommy-Carlos Williams on 18/07/14.
 * Copyright (c) 2014 Tommy-Carlos Williams. All rights reserved.
 * MIT Licensed
 */
package cordova.plugin.privacyscreen;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import android.app.Activity;
import android.os.Looper;
import android.view.Window;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;

/**
 * This class sets the FLAG_SECURE flag on the window to make the app
 * private when shown in the task switcher
 */
public class PrivacyScreenPlugin extends CordovaPlugin {
  private interface Actions {
    public static final String ENABLE = "enable";
    public static final String DISABLE = "disable";
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    switch (action) {

      case Actions.ENABLE:
        this.setScreenCaptureOnMainThread(true, callbackContext);
        return true;

      case Actions.DISABLE:
        this.setScreenCaptureOnMainThread(false, callbackContext);
        return true;

      default:
        callbackContext.error("Method " + action + " not found");
        break;
    }
    return false;
  }

  private boolean isMainThread() {
    return Looper.getMainLooper().getThread() == Thread.currentThread();
  }

  private void setScreenCaptureOnMainThread(Boolean enable, CallbackContext callbackContext) {
    if (isMainThread()) {
      setScreenCapture(enable, callbackContext);
    }
    else {
      Activity activity = this.cordova.getActivity();

      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          setScreenCapture(enable, callbackContext);
        }
      });
    }
  }

  private void setScreenCapture(Boolean enable, CallbackContext callbackContext) {
    Activity activity = this.cordova.getActivity();

    Window window = activity.getWindow();

    if (enable) {
      window.addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }
    else {
      window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    callbackContext.success();
  }


}
