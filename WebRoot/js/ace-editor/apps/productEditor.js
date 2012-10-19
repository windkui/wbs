/**
 * Weather Product editor with ACE
 */
define(function(require, exports, module) {
	var DocEditor = require('./docEditor.js');
	var productEditor = new DocEditor(productList, {		
		loadUrl: CTX+"/operation/productLoad.action?id=",
		saveAction: CTX+'/operation/product!save.action',
		saveFormSel: 'productSaveForm',
		idSel: 'productId',
		contentSel: 'productContent'
	});
	productEditor.init();
});