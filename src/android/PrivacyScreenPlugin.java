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
        this.enable(callbackContext);
        return true;

      case Actions.DISABLE:
        this.disable(callbackContext);
        return true;

      default:
        callbackContext.error("Method " + action + " not found");
        break;
    }
    return false;
  }

  private void enable(CallbackContext callbackContext) throws JSONException {

    Activity activity = this.cordova.getActivity();
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

    callbackContext.success("true");

  }

  private void disable(CallbackContext callbackContext) throws JSONException {
    Activity activity = this.cordova.getActivity();

    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);

    callbackContext.success("true");

  }
}
