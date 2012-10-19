/**
 * system theme switcher
 */
define(function(require, exports, module) {

var OM_CSS = require.resolve('operamasks-ui/css/{_THEME_}/om-default.css');
var UI_CSS = require.resolve('jquery-ui/theme/omui-{_THEME_}/jquery-ui.css');
var omCssPaths = {
		'omTheme': OM_CSS,
		'uiTheme': UI_CSS			
};
var themeKey = 'omtheme';
window.changeOmTheme = function(themeName) {
	$.changeTheme(themeKey, themeName, omCssPaths);
};
function getOmTheme() {
	return $.getTheme(themeKey)||'default';
}
var omTheme = getOmTheme();
if(omTheme!='default') {
	changeOmTheme(omTheme);
}	
	
});