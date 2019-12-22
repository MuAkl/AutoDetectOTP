// Empty constructor
function AutoDetectOTP() {}

// The function that passes work along to native shells
// Message is a string, duration may be 'long' or 'short'
AutoDetectOTP.prototype.show = function(message, duration, successCallback, errorCallback) {
  var options = {};
  options.message = message;
  options.duration = duration;
  cordova.exec(successCallback, errorCallback, 'AutoDetectOTP', 'show', [options]);
}

// Installation constructor that binds AutoDetectOTP to window
AutoDetectOTP.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.autoDetectOTP = new AutoDetectOTP();
  return window.plugins.autoDetectOTP;
};
cordova.addConstructor(AutoDetectOTP.install);