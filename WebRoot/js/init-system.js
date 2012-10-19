/**
 * initialize system with operamasksUI view 
 */
define(function(require, exports, module) {
	require('../css/main.css');
	require('../css/table.css');	
	require('jquery/validate/jquery.validate.css');
	require('jquery/jquery-checktree/jquery.treeview.css');
	require('../css/general.css');
	require.async('jquery/jquery.min', function(){
		require.async(['jquery/validate/jquery.validate.min',		               
		               'jquery/jquery-plugins/jquery.form',
		               'jquery/jquery-filament-menu/fg.menu.min',
		               'jquery-ui/jquery-ui.min',
		               'jquery/jquery-checktree/jquery.checktreeview.min',
		               'jquery/jquery-plugins/jquery.lightmask.min'],function() {
            require.async(['jquery/validate/additional-methods.min',
                           'jquery-ui/i18n/jquery-ui-i18n',
                           'jquery/validate/jquery.validate.extend-methods.min',
                           'jquery/validate/messages_cn',
                           'jquery-ui/i18n/jquery-ui-i18n',
                           'jquery/jquery-plugins/jquery-myutils.min',
                           '../ckfinder/ckfinder.js',
                           'jquery/jquery-plugins/jquery.datapager.min'], function() {
            	$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
                jQuery(function() {
                	if(window.initPage) {
                		initPage();
                	} else {                			
                		$(document).initAdmin();
    				}
    			});            		
            });
		});
	});	
});