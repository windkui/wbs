(function() {

$.extend(window, {
	showAppMessage: function(msg) {
		var type = msg.type||'error';
		if(type=='error') {
			msg.content = '<span class="errorMessage">'+msg.content+'</span>';				
		}
		$.omMessageTip.show({content:msg.content, title:msg.title, type:type});
	},
	showAppBroadcast: function(broadcast, options) {
		var topDoc = window.document.body;				
		var key = 'appBroadcastDlg';
		var dlg = $.data(topDoc, key);
		if(!dlg) {
			var container = $(topDoc).children('#appBroadcastDlg');
			if(!container.length) {
				container = $('<div id="appBroadcastDlg" />').appendTo(topDoc);
			}
			container = container[0];			
			dlg = new ApplicationBroadcasts(container, options);
			$.data(topDoc, key, dlg);
		}
		dlg.showBroadcasts();
		return dlg;
	}
});
/**
 * Application Messages
 */
$.extend($.fn, {
	initAppMessages: function(options) {
		if(!this.length) {
			log('initAppMessages: no element selected');
			return this;
		}
		var container = this[0];
		var key = 'appMsgs';
		var appMsgs = $.data(container, key);
		if(appMsgs) {
			return appMsgs;
		}
		appMsgs = new ApplicationMessages(container, options);
		$.data(container, key, appMsgs);
		return appMsgs;
	}
});

var ApplicationMessages = function(container, options) {
	this.settings = $.extend(true, ApplicationMessages.defaults, options);
	this._container = container;
	this.init();
}

$.extend(ApplicationMessages, {
	defaults: {},
	prototype: {
		init: function() {
			dwr.engine.setActiveReverseAjax(true);
			ApplicationMessageService.addOnlineUser();
		}
	}
});

var ApplicationBroadcasts = function(container, options) {
	this.settings = $.extend(true, ApplicationBroadcasts.defaults, options);
	this._container = container;
	this.init();
}
$.extend(ApplicationBroadcasts, {
	defaults: {
		sliderUrl: window.CTX+'/message/broadcast!slider.action'
	},	
	prototype: {
		init: function() {
			dwr.engine.setActiveReverseAjax(true);
			var me = this,
				element = $(me._container);
			me._loadForm = me._loadForm||$('<form method="post" />');
			me._dlg = element.omDialog({
				resizable:false,
				title: '系统广播'
			});
		},
		showBroadcasts: function() {
			var me = this,
				slider = me._container,
				loadForm = me._loadForm,
				sliderUrl = me.settings.sliderUrl,
				dlg = me._dlg;
			loadForm.ajaxSubmit({
				url: sliderUrl,
				target: slider,
				success: function(data) {				
					dlg.omDialog('open');
				}
			});
		}
	}
});

//helper fn for console logging
function log() {
	var msg = '[applicationMessages] ' + Array.prototype.join.call(arguments,'');
	if (window.console && window.console.log) {
		window.console.log(msg);
	}
	else if (window.opera && window.opera.postError) {
		window.opera.postError(msg);
	}
};

	
})(window.jQuery);