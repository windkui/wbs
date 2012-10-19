function bar(value,ticks){
	    var chart;
	    $(document).ready(function() {
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'bar',
	                type: 'column'
	            },
	            title: {
	                text: '东华软件-气象要素比较矩状图'
	            },
	            subtitle: {
	                text: '最高温度'
	            },
	            xAxis: {
	                categories: ticks,
	                title: {
	                    text: "时间间隔(小时)",
	                    align:'high'
	                }
	            },
	            yAxis: {
	                min: 0,
	                title: {
	                    text: '温度 (.C)',
	                    align: 'high'
	                }
	            },
	            tooltip: {
	                formatter: function() {
	                    return '站点'+
	                        this.series.name +': '+ this.y +' .C';
	                }
	            },
	            plotOptions: {
	                column: {
	                    dataLabels: {
	                        enabled: true
	                    }
	                }
	            },
	            legend: {
	                layout: 'vertical',
	                align: 'right',
	                verticalAlign: 'top',
	                x: -50,
	                y: 30,
	                floating: true,
	                borderWidth: 1,
	                backgroundColor: '#FFFFFF',
	                shadow: true
	            },
	            credits: {
	                enabled: false
	            },
	            series: value
	        });
	    });
}

function arrTojson(arr){
    var i,jsonstr;
    jsonstr="[{";
    for(i=0;i<arr.length;i++){
        jsonstr += "\"" + arr[i] + "\""+ ":" + "\"" + arr[i] + "\",";
    }
    jsonstr = jsonstr.substring(0,jsonstr.lastIndexOf(','));
    jsonstr += "}]";
    return jsonstr;
}
