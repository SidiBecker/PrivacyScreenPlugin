var exec = require('cordova/exec');

var privacyScreen = function () {
};

var execMethod = function (method, param, success, error) {
  exec(success, error, 'PrivacyScreenPlugin', method, [param]);
};

privacyScreen.enable = function (param, success, error) {
    execMethod('enable', param, success, error);
};

privacyScreen.disable = function (param, success, error) {
    execMethod('disable', param, success, error);
};

if (!window.plugins) {
    window.plugins = {};
}

window.plugins.privacyScreen = privacyScreen;

module.exports = privacyScreen;
