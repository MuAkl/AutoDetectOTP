package com.muakl.cordova.plugin;
// The native Toast API
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class AutoDetectOTP extends CordovaPlugin {
  private static final String DURATION_LONG = "long";
  @Override
  public PluginResult execute(String action, JSONArray args,
    final CallbackContext callbackContext) {
      // Verify that the user sent a 'show' action
      if (!action.equals("show")) {
        callbackContext.error("\"" + action + "\" is not a recognized action.");
        return new PluginResult(PluginResult.Status.INVALID_ACTION);//false;
      }
      String message;
      String duration;
      try {
        JSONObject options = args.getJSONObject(0);
        message = options.getString("message");
        duration = options.getString("duration");
      } catch (JSONException e) {
        callbackContext.error("Error encountered: " + e.getMessage());
        return new PluginResult(PluginResult.Status.ERROR);//false;
      }
      Pattern p = Pattern.compile("\\d{3,8}");
            Matcher m = p.matcher(message);
            String OTP ;
	    	if(m.find()) {
	    		OTP =m.group();
	    	}
	    	else {
	    		OTP="";
            }
      // Create the toast
      Toast toast = Toast.makeText(cordova.getActivity(), OTP,
        DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
      // Display toast
      toast.show();
      // Send a positive result to the callbackContext
      PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
      callbackContext.sendPluginResult(pluginResult);
      return new PluginResult(PluginResult.Status.OK, OTP);//true;
  }
}
