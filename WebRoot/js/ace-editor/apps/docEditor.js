/**
 * Weather docData editor with ACE
 */
define(function(require, exports, module) {

if(!window.jQuery) {
	require('../jquery.min.js');
}
function isImage(fileName) {
	return /.*\.(gif|jpg|jpeg|bpm|png)/i.test(fileName);
}
require("ace/lib/fixoldbrowsers");
require("ace/config").init();
var event = require("ace/lib/event");
var theme = require("ace/theme/textmate");
var EditSession = require("ace/edit_session").EditSession;
var UndoManager = require("ace/undomanager").UndoManager;

var vim = require("ace/keyboard/vim").handler;
var emacs = require("ace/keyboard/emacs").handler;
var HashHandler = require("ace/keyboard/hash_handler").HashHandler;

function Doc(id, name, docData, file) {
	this.id = id;
    this.name = name;
    this.isImage = isImage(name);
    this.docData = docData;
    if(this.isImage) {    	
    } else {
    	this.doc = new EditSession(file);
    	this.doc.setUndoManager(new UndoManager());
    }    
}
var Editor = function(docDataList, options) {
	"use strict";
	this.docDataList = docDataList;
	this.settings = $.extend({
	  	saveAction: undefined,
	  	loadUrl: undefined,
		container: 'editor',		
		docSel: 'doc',
		wrapModeSel: 'soft_wrap',
		themeSel: 'theme',
		foldingSel: 'folding',
		selectStyleSel: 'select_style',
		highlightActiveSel: 'highlight_active',
		showHiddenSel: 'show_hidden',
		showPrintMarginSel: 'show_print_margin',
		softTabSel: 'soft_tab',
		editBtnSel: 'editBtn',
		saveBtnSel: 'saveBtn',
		saveFormSel: 'saveForm',
		idSel: 'id',
		contentSel: 'content',
		editorControlsSel: 'editorControls',
		imageShowSel: 'imageShow',
		imageContentSel: 'imageContent'
	}, options);
}
Editor.prototype.init = function() {
	var op = this.settings;
	var docDataList = this.docDataList;
	var env = {};
	var keybindings = {
	    // Null = use "default" keymapping
	    ace: null,
	    vim: vim,
	    emacs: emacs,
	    // This is a way to define simple keyboard remappings
	    custom: new HashHandler({
	        "gotoright":      "Tab",
	        "indent":         "]",
	        "outdent":        "[",
	        "gotolinestart":  "^",
	        "gotolineend":    "$"
	     })
	};
	var container = document.getElementById(op.container);
	
	// Splitting.
	var Split = require("ace/split").Split;
	var split = new Split(container, theme, 1);
	var editor = split.getEditor(0);
	env.editor = editor;
	split.on("focus", function(editor) {
	    env.editor = editor;
	    updateUIEditorOptions();
	});
	env.split = split;
	var DEFAULT_MODE = 'ace/mode/less';
	editor.getSession().setMode(DEFAULT_MODE);
	editor.setHighlightSelectedWord(true);
	editor.setAnimatedScroll(true);
	editor.setReadOnly(true);
	var docEl = document.getElementById(op.docSel);
	var wrapModeEl = document.getElementById(op.wrapModeSel);
	var themeEl = document.getElementById(op.themeSel);
	var foldingEl = document.getElementById(op.foldingSel);
	var selectStyleEl = document.getElementById(op.selectStyleSel);
	var highlightActiveEl = document.getElementById(op.highlightActiveSel);
	var showHiddenEl = document.getElementById(op.showHiddenSel);
	var showPrintMarginEl = document.getElementById(op.showPrintMarginSel);
	var softTabEl = document.getElementById(op.softTabSel);
	var editBtn = document.getElementById(op.editBtnSel);
	var saveBtn = document.getElementById(op.saveBtnSel);
	var saveFormEl = document.getElementById(op.saveFormSel);
	var contentEl = document.getElementById(op.contentSel);
	var editorControlsEl = document.getElementById(op.editorControlsSel);
	var imageShowEl = document.getElementById(op.imageShowSel);
	var imageContentEl = document.getElementById(op.imageContentSel);
	
	editBtn.onclick = function(event) {
		editor.setReadOnly(false);
		editor.focus();
	};
	var saveAction = op.saveAction;
	saveBtn.onclick = function(event) {
		if(!jQuery.fn.ajaSubmit) {
			require('../jquery.form.js');
		}
	  	var session = editor.getSession();
	  	var content = session.getValue();
	  	contentEl.value = content;
	  	var formObj = $(saveFormEl);
	  	formObj.ajaxSubmit({
	  		url: saveAction,
	  		success: function(data) {
	  			alert(data);
	  		},
	  		error: function(request, status, error) {
	  			alert('Error: '+request.responseText);
	  		},
	  		complete: function() {
	  			formObj.fadeIn();
	  		}
	  	});	  	
	  	formObj.fadeOut();
	  	return false;
	};
	
	var docDataCount = docDataList.length;
	var loadDocUrl = op.loadUrl;
	var docs = [];
	var docsById = {};
	for(var i in docDataList) {
		var docData = docDataList[i];
		var docObj = new Doc(docData.id, docData.name, docData, "数据载入中...");
		docs.push(docObj);	
		docsById[docObj.id] = docObj;	
	}
	function loadDoc(id) {
		var docObj = docsById[id];
		if(!docObj) {
			//return;
		}	
	    if(docObj.isImage) {
	    	return loadImageDoc(docObj);
	    } else if (!docObj.initialized) {
	    	var loadUrl = loadDocUrl+id;
	        var doc = docObj.doc;    	
	    	docObj.initialized = true;
			var settings = {
				doc: docObj.doc,
				url: loadUrl,
				success: function(data) {			
					doc.setValue(data);
				},
				error: function(request, status, error) {
					alert('error:'+request);
				}
			};
			jQuery.ajax(settings);    	
	    	doc.setMode(DEFAULT_MODE);
	    }
	    $(imageShowEl).hide();
	    $(editorControlsEl).show();
	    var session = env.split.setSession(doc);
	    session.name = doc.name;
	    updateUIEditorOptions();
	    env.editor.focus();
	}
	
	function loadImageDoc(doc) {
		$(container).hide();
		$(editorControlsEl).hide();
		imageContentEl.src = loadDocUrl+doc.id;	
		$(imageShowEl).show();
	}
	for(var i=0;i<docs.length;i++) {
		var doc = docs[i];
	    var option = document.createElement("option");
	    option.setAttribute("value", doc.id);
	    option.innerHTML = doc.name;
	    docEl.appendChild(option);		
	}
	bindDropdown("doc", function(value) {
		if(!value) return;
		loadDoc(value);
	});
	
	function updateUIEditorOptions() {
	    var editor = env.editor;
	    var session = editor.session;
	
	    session.setFoldStyle(foldingEl.value);
	
	    saveOption(docEl, session.name);
	    saveOption(wrapModeEl, session.getUseWrapMode() ? session.getWrapLimitRange().min || "free" : "off");
	
	    saveOption(selectStyleEl, editor.getSelectionStyle() == "line");
	    saveOption(themeEl, editor.getTheme());
	    saveOption(showHiddenEl, editor.getShowInvisibles());
	    saveOption(showPrintMarginEl, editor.renderer.getShowPrintMargin());
	    saveOption(softTabEl, session.getUseSoftTabs());
	}
	
	function saveOption(el, val) {
	    if (!el.onchange || el.onclick)
	        return;
	        
	    if ("checked" in el) {
	        if (val !== undefined)
	            el.checked = val;
	            
	        localStorage && localStorage.setItem(el.id, el.checked ? 1 : 0);
	    } 
	    else {
	        if (val !== undefined)
	            el.value = val;
	            
	        localStorage && localStorage.setItem(el.id, el.value);
	    }
	}
	
	event.addListener(themeEl, "mouseover", function(e){
	    this.desiredValue = e.target.value;
	    if (!this.$timer)
	        this.$timer = setTimeout(this.updateTheme);
	})
	
	event.addListener(themeEl, "mouseout", function(e){
	    this.desiredValue = null;
	    if (!this.$timer)
	        this.$timer = setTimeout(this.updateTheme, 20);
	})
	
	themeEl.updateTheme = function(){
	    env.split.setTheme(themeEl.desiredValue || themeEl.selectedValue);
	    themeEl.$timer = null;
	}
	
	bindDropdown("theme", function(value) {
	    if (!value)
	        return;
		env.editor.setTheme(value);
		themeEl.selectedValue = value;
	});
	
	bindDropdown("fontsize", function(value) {
	    env.split.setFontSize(value);
	});
	
	bindDropdown("folding", function(value) {
	    env.editor.getSession().setFoldStyle(value);
	    env.editor.setShowFoldWidgets(value !== "manual");
	});
	
	bindDropdown("soft_wrap", function(value) {
	    var session = env.editor.getSession();
	    var renderer = env.editor.renderer;
	    switch (value) {
	        case "off":
	            session.setUseWrapMode(false);
	            renderer.setPrintMarginColumn(80);
	            break;
	        case "40":
	            session.setUseWrapMode(true);
	            session.setWrapLimitRange(40, 40);
	            renderer.setPrintMarginColumn(40);
	            break;
	        case "80":
	            session.setUseWrapMode(true);
	            session.setWrapLimitRange(80, 80);
	            renderer.setPrintMarginColumn(80);
	            break;
	        case "free":
	            session.setUseWrapMode(true);
	            session.setWrapLimitRange(null, null);
	            renderer.setPrintMarginColumn(80);
	            break;
	    }
	});
	bindCheckbox("select_style", function(checked) {
	    env.editor.setSelectionStyle(checked ? "line" : "text");
	});
	
	bindCheckbox("highlight_active", function(checked) {
	    env.editor.setHighlightActiveLine(checked);
	});
	
	bindCheckbox("show_hidden", function(checked) {
	    env.editor.setShowInvisibles(checked);
	});
	
	bindCheckbox("show_print_margin", function(checked) {
	    env.editor.renderer.setShowPrintMargin(checked);
	});
	
	bindCheckbox("soft_tab", function(checked) {
	    env.editor.getSession().setUseSoftTabs(checked);
	});
	
	function bindCheckbox(id, callback) {
	    var el = document.getElementById(id);
	    if (localStorage && localStorage.getItem(id))
	        el.checked = localStorage.getItem(id) == "1";
	
	    var onCheck = function() {
	        callback(!!el.checked);
	        saveOption(el);
	    };
	    el.onclick = onCheck;
	    onCheck();
	}
	
	function bindDropdown(id, callback) {
	    var el = document.getElementById(id);
	    if (localStorage && localStorage.getItem(id))
	        el.value = localStorage.getItem(id);
	
	    var onChange = function() {
	        callback(el.value);
	        saveOption(el);
	    };
	    el.onchange = onChange;
	    if(el.selectedIndex<0) {
	    	el.selectedIndex=0;
	    }
	    onChange();
	}
	
	function onResize() {
	    var left = env.split.$container.offsetLeft;
	    var width = document.documentElement.clientWidth - left;
	    container.style.width = width + "px";
	    container.style.height = document.documentElement.clientHeight + "px";
	    env.split.resize();
	}
	
	window.onresize = onResize;
	env.editor.renderer.onResize(true);
	
	event.addListener(container, "dragover", function(e) {
	    return event.preventDefault(e);
	});
	
	event.addListener(container, "drop", function(e) {
	    var file;
	    try {
	        file = e.dataTransfer.files[0];
	    } catch(err) {
	        return event.stopEvent();
	    }
	
	    if (window.FileReader) {
	        var reader = new FileReader();
	        reader.onload = function() {
	            env.editor.getSelection().selectAll();
	
	            var mode = modesByName.text;
	            for (var i = 0; i < modes.length; i++) {
	                if (modes[i].supportsFile(file.name)) {
	                    mode = modes[i];
	                    break;
	                }
	            }
	
	            env.editor.onTextInput(reader.result);
	
	            env.editor.getSession().setMode(mode.mode);
	            env.editor.getSession().modeName = mode.name;
	        };
	        reader.readAsText(file);
	    }
	
	    return event.preventDefault(e);
	});
	
	/**
	 * This demonstrates how you can define commands and bind shortcuts to them.
	 */
	
	// Fake-Save, works from the editor and the command line.
	var commands = env.editor.commands;
	
	commands.addCommand({
	    name: "save",
	    bindKey: {
	        win: "Ctrl-S",
	        mac: "Command-S",
	        sender: "editor"
	    },
	    exec: function() {
	        alert("Fake Save File");
	    }
	});
	
	// Fake-Print with custom lookup-sender-match function.
	commands.addCommand({
	    name: "print",
	    bindKey: {
	        win: "Ctrl-P",
	        mac: "Command-P",
	        sender: function(env, sender, hashId, keyString) {
	            if (sender == "editor") {
	                return true;
	            } else {
	                alert("Sorry, can only print from the editor");
	            }
	        }
	    },
	    exec: function() {
	        alert("Fake Print File");
	    }
	});
	
	// add multiple cursor support to editor
	require("ace/multi_select").MultiSelect(env.editor);		
}
exports.DocEditor = Editor;
return Editor;

});