define(function(require, exports, module) {
	require('../css/general.css');
	require('../css/main.css');
	require('../css/table.css');
	require('jquery/validate/jquery.validate.css');	
	require.async('jquery/jquery.min', function(){
		require.async(['jquery/validate/jquery.validate.min'],function() {
            require.async(['jquery/validate/messages_cn'], function() {
            	jQuery(function() {
            		if(window.initPage) {
            			window.initPage.call(window);
            		}
				});
            });
		});
	});	
});