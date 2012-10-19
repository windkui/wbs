function actvieLog() {		
	logsAccordion.accordion( "activate" , '#log-'+this.title );	
}
function notifyTaskErrorOccur(errors) {
	var firstLog = logsShow.children('span').get(0);
	var len = errors.length;
	if(!firstLog) {
		for(var i=(len-1);i>=0;i--) {
			var newLog = $('<span>'+errors[i]+'</span><br/>');
			newLog.appendTo(logsShow);
		}
		logsShow.find('.optKeyword').click(actvieLog);
	} else {
		var newLog = $('<span>'+errors[len-1]+'</span><br/>');
		newLog.prependTo(logsShow);
		newLog.find('.optKeyword').click(actvieLog);
	}
}
function refreshTaskHistList() {
	listSearchForm.submit();
}
function initSyncTaskMonitorPage() {
	function loadErrorLogs() {
		var errorLogs = SyncTaskCenterService.getErrorMessages(notifyTaskErrorOccur);
	}
	dwr.engine.setActiveReverseAjax(true);
	logsShow = $('#errorLogs');	
	var pager = $(window).initDataPager({
		container: '#syncTaskMonitor',
		searchForm: "#syncTaskHistoryForm"
	});	
	listSearchForm = pager.getSearchForm();
	loadErrorLogs();	
}