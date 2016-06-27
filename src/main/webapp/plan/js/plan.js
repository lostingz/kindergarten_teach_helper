$(function(){
	initPlanList();
});

function initPlanList(){
	var url="/plan/list";
	$.ajax({
		url:url,
		data:{
			"c":1,
			"pt":1
		},
		success:function(data){
			buildTr(data);
		},
		error:function(data){
			alert("error");
		}
	});
}

function buildTr(data){
	var tbody=$('#plan_list tbody');
	for(var i=0;i<data.length;i++){
		tbody.append('<tr><td>'+data[i].title+'</td><td>'+data[i].datetime+'</td><td><span class="label label-warning">'+data[i].url+'</span></td>'+
                '<td><a class="btn btn-success" href="table.html#"> <i class="fa fa-search-plus "></i>'+
                '</a> <a class="btn btn-info" href="table.html#"> <i class="fa fa-edit "></i>'+
                '</a> <a class="btn btn-danger" href="table.html#"> <i class="fa fa-trash-o "></i>'+
                '</a></td></tr>');
	}
}