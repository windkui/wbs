/**
 * Weather DataSourceFile editor with ACE
 */
define(function(require, exports, module) {
	var DocEditor = require('./docEditor.js');
	var dataFileEditor = new DocEditor(dataFileList, {		
		loadUrl: CTX+"/data/dataSourceFileLoad.action?id=",
		saveAction: CTX+'/manager/data/dataSourceFile!saveFile.action',
		saveFormSel: 'dataFileSaveForm',
		idSel: 'fileId',
		contentSel: 'fileData'
	});
	dataFileEditor.init();
});