/**
 * initialize system with operamasksUI view 
 */
define(function(require, exports, module) {
	require('../css/main.css');
	require('../css/table.css');
	require('operamasks-ui/ui/om-ui-exts.css');
	require('jquery/validate/jquery.validate.css');
	require('../css/general.css');
	require.async('jquery/jquery.min', function(){
		//old JSP view loads
		require('jquery/jquery-checktree/jquery.treeview.css');		
		require.async(['jquery/jquery-checktree/jquery.checktreeview.min',
			'jquery/jquery-plugins/jquery.lightmask.min',
			'jquery/jquery-plugins/jquery-myutils.min',
			'jquery/jquery-plugins/jquery.datapager.min']);
		//old JSP view loads end
		require.async(["../dwr/engine",
		               "../dwr/interface/ApplicationMessageService",
		               "../dwr/util",
		               "system/applicationMessages"]);
		require.async(['jquery/validate/jquery.validate.min',
		               'jquery/jquery-utils/jquery.json.min',		               
		               'jquery/jquery-utils/jquery.extend.nativejs.min',
		               'jquery/jquery-plugins/jquery.zoomooz.min',
		               'jquery/jquery-plugins/jquery.form',
		               'jquery/jquery-plugins/jquery.regexp.mask.min',
		               'jquery/jquery-plugins/jquery.themeswitcher.min',
		               'jquery/jquery-filament-menu/fg.menu.min',
		               'jquery-ui/jquery-ui.min',
		               'operamasks-ui/js/operamasks-ui.min'],function() {			
            require.async(['jquery/validate/additional-methods.min',
                           'jquery/jquery-plugins/jquery.regexpmask.ext.system.min',
                           'jquery/validate/jquery.validate.extend-methods.min',
                           'jquery/validate/messages_cn',
                           'jquery-ui/timepicker/jquery-ui-timepicker-addon',
                           'jquery-ui/i18n/jquery-ui-i18n',                                                      
                           '../ckfinder/ckfinder',
                           'operamasks-ui/ui/omui-exts.min',
                           'system/themeswitcher'], function() {
            	$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
            	require.async(['jquery-ui/timepicker/localization/jquery-ui-timepicker-zh-CN',
            	               'operamasks-ui/manager-omUI.min',
            	               'operamasks-ui/application-omUI.min',
            	               'system/pagesjs'], function() {
                	jQuery(function() {
                		if(window.initPageUI) {
                			initPageUI();
                		} else if(window.initPage) {
                			initPage();
                		} else {
                			var appMsgsObj = $(window).initAppMessages();
                			$(document).initAdmin();
    					}
    				});            		
            	});
            });
		});
	});	
});