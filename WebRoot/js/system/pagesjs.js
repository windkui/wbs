function setWindowCTX(ctx) {
	window.CTX = window.CTX||ctx;
}
function showMoreBroadcasts() {
	var loadUrl = CTX+'/message/broadcast.action';
	executeCommand('moreBroadcasts', '系统广播', loadUrl);
}
function showCitysDatDataEditor(id) {
	var loadUrl = CTX+'/manager/data/weatherElement.action?serDatId='+id;
	executeCommand('citysDatDataEditor', '城市报文编辑', loadUrl);
}
/** weather element manager page */
function initWeatherElementMgrPage() {
	$('.datePicker').datepicker();
	function renderWeatherData(data) {
		if(data==999.9) {
			return '<b style="color:#ff0000;">'+data+'</b>';
		}
		return data;
	}	
	function renderWindDirection(data) {
		return WindDirections[data]||renderWeatherData(data);
	}
	function renderWeatherPhenomena(data) {
		return WeatherPhenomenas[data]||renderWeatherData(data);
	}
	function renderWindPower(data, record) {
		return WindPowers[data]||renderWeatherData(data);
	}
	function renderTemperatureData(data) {
		if(data==999.9) {
			return '<b style="color:#ff0000;">'+data+'</b>';
		}
		return data+'℃';
	}
	function renderPercentData(data) {
		if(data==999.9) {
			return '<b style="color:#ff0000;">'+data+'</b>';
		}
		return data+'%';		
	}
	var grid = $('#updateDat-button').closest('#toolbarPanel').next('#dataPanel').find('#listDataTable');
	$('#updateDat-button').click(function() {		
		grid.omCrudGrid('batchSaveUpdate');
		return false;
	});
	var pager = $(window).initDataPagerUI({
		zoom: false,
		gridConfig: {
			limit: 10,
			colModel: [		           
				{header:'发布时间', name: 'infoTime', width: 100, sort:'clientSide', 
					renderer:function(value) {return uiRenderFormatDateCol(value, 'yy年mm月dd日');}},
				{header:'行号', name:'rowNo', width:50, sort:'clientSide'},
				{header:'时间间隔', name: 'timeCount', width: 100, sort:'clientSide'},
				{header:'温度', name: 'temperature', width: 100, sort:'clientSide', 
					renderer:renderTemperatureData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'temperature'
					}
				},
				{header:'相对湿度', name: 'relativeHumidity', width: 100, sort:'clientSide', 
						renderer:renderPercentData, editor:{
							type:'omNumberField', 
							rules:['required',true],
							name:'relativeHumidity'
						}
				},
				{header:'风向', name: 'windDirection', width: 100, sort:'clientSide', renderer:renderWindDirection, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'windDirection'
					}
				},
				{header:'风速', name: 'windSpeed', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'windSpeed'
					}
				},
				{header:'气压', name: 'airPressure', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'airPressure'
					}
				},
				{header:'降水量', name: 'precipitation', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'precipitation'
					}
				},
				{header:'总云量', name: 'totalCloudAmount', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'totalCloudAmount'
					}
				},
				{header:'低云量', name: 'lowCloudAmount', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'lowCloudAmount'
					}
				},
				{header:'天气现象', name: 'weatherPhenomena', width: 100, sort:'clientSide', renderer:renderWeatherPhenomena, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'weatherPhenomena'
					}
				},
				{header:'能见度', name: 'visibility', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'visibility'
					}
				},
				{header:'最高气温', name: 'maximumTemperature', width: 100, sort:'clientSide', renderer:renderTemperatureData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'maximumTemperature'
					}
				},
				{header:'最低气温', name: 'minimumTemperature', width: 100, sort:'clientSide', renderer:renderTemperatureData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'minimumTemperature'
					}
				},
				{header:'最大相对湿度', name: 'maximunRelativeHumidity', width: 100, sort:'clientSide', renderer:renderPercentData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'maximunRelativeHumidity'
					}
				},
				{header:'最小相对湿度', name: 'minimumRelativeHumidity', width: 100, sort:'clientSide', renderer:renderPercentData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'minimumRelativeHumidity'
					}
				},
				{header:'24小时累计降水量', name: 'precipitationAccumulatedTFHour', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'precipitationAccumulatedTFHour'
					}
				},
				{header:'12小时累计降水量', name: 'precipitationAccumulatedTWHour', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'precipitationAccumulatedTWHour'
					}
				},
				{header:'12小时总云量', name: 'totalCloudAmountTWHour', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'totalCloudAmountTWHour'
					}
				},
				{header:'12小时低云量', name: 'lowCludAmountTWHour', width: 100, sort:'clientSide', renderer:renderWeatherData, editor:{
						type:'omNumberField', 
						rules:['required',true],
						name:'lowCludAmountTWHour'
					}
				},
				{header:'12小时天气现象', name: 'weatherPhenomenaTWHour', width: 100, sort:'clientSide', renderer:renderWeatherPhenomena, editor:{
						type:'omNumberField', 
						name:'weatherPhenomenaTWHour'
					}
				},
				{header:'12小时风向', name: 'windDirectionTWHour', width: 100, sort:'clientSide', renderer:renderWindDirection, editor:{
						type:'omNumberField', 
						name:'windDirectionTWHour'
					}
				},
				{header:'12小时风力', name: 'windPowerTWHourt', width: 100, sort:'clientSide', renderer:renderWindPower, editor:{
						type:'omNumberField', 
						name:'windPowerTWHourt'
					}
				},
				{header:'基站信息', name: 'stationName', width: 100, sort:'clientSide'},
				{header:'经度', name: 'longitude', width: 100, sort:'clientSide'},
				{header:'纬度', name: 'latitude', width: 100, sort:'clientSide'},
				{header:'海拔', name: 'elevation', width: 100, sort:'clientSide'}
			],
			batchSaveUrl: CTX+'/manager/data/weatherElement!batchUpdate.action'
		}
	});		
}
/**
 * initialize sync data task input page
 */
function initSyncTaskInputPage(options) {
	$('.getTaskConfigHelpBtn').click(function() {
		$(this).next().toggle('slow');
	});
	$('#monthExpression').regexMask('monthExpression');
	$('#weekExpression').regexMask('weekExpression');
	$('#dayExpression').regexMask('dayExpression');
	$('#hourExpression').regexMask('hourExpression');
	$('#minuteExpression').regexMask('minuteExpression');
	var deleteAdded = function() {
		$(this).parent().remove();
	};
	var TaskConfigTestUrl = CTX+'/manager/data/taskInfo!testTaskConfig.action';
	function testTaskConfig() {
		var testVal = $(this).prev(':text').val();
		if(testVal) {
			$.ajax({
				url: TaskConfigTestUrl,
				data: {'testConfigVal': testVal},
				success: function(data) {
					alert(data);
				}
			});
		}
		return false;
	}
	var downNameContainer = $('#downNameContainer');
	var DOWNNAME_TEMP = '<input type="text" name="downNames" class="text required" class="text" />'+
		'<a class="taskConfigTestBtn testLabel" href="javascript:void(0)">测试</a>'+
		'<a class="taskDownNameDelBtn deleteLabel" href="javascript:void(0)">删除</a>';
	$('#addDownNameBtn').button({icons:{primary:'ui-icon-circle-plus'}}).click(function() {		
		var downNameDiv = $('<div class="ui-state-default" />').append(DOWNNAME_TEMP);
		downNameDiv.children('.taskDownNameDelBtn').click(deleteAdded);
		downNameDiv.children('.taskConfigTestBtn').click(testTaskConfig);
		downNameContainer.append(downNameDiv);
	});
	$('.taskDownNameDelBtn').click(deleteAdded);
	var oldName = options.oldName;
	$(window).initInputPage({
		saveAction: CTX+'/manager/data/taskInfo!save.action',
		'validate': {
			onfocusout: false,
			rules: {
				name: {remoteValid: {url:CTX+"/manager/data/taskInfo!checkName.action", data:{oldName:oldName}}},
				path:{required:true}
			},
			messages: {
				name: {remoteValid: "该数据采集名已被使用!"},
				path:{required:'必选字段'}
			}
		}
	});
	$('.taskConfigTestLabel').click(testTaskConfig);
	var needParse = options.needParse;
	var getherType = options.getherType;
	function execute(){	
		if(needParse=="false"){
			$("#checkNeedParse").hide();
		}else{
			$("#checkNeedParse").show();
			$("#needParse01").attr("checked",true);
		}
		if(getherType=="LOCAL"){
			$("#checkFtp").hide(); 
			$("#ftpConfigId").removeClass("required");
		}else{
			$("#checkFtp").show(); 
		}
		
	}
	function checkGetherType(){
		$("#getherType").change(function(){
			var opt=$(this).children('option:selected').val();
			if('LOCAL'==opt){
				$("#checkFtp").hide(); 
			    $("#ftpConfigId").removeClass("required");
			}else{
				$("#checkFtp").show(); 
				$("#ftpConfigId").addClass("required")
			}
	 	});
	}	   
   	function checkNeedParse(){
		$("#checkNeedParse").hide();
  	 	$('.need-parse').change(function(){
			var flag = $("input[name='needParse']:checked").val();  
  		 	if("false"==flag){
				$("#checkNeedParse").hide(); 
  			 	$("#type").removeClass("required");
  		 	}else{
  		    	$("#checkNeedParse").show(); 
  			 	$("#type").addClass("required");
  		 	}
  	 	});
   	}	
	checkGetherType();
	checkNeedParse();
	execute();		
}