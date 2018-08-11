function sureSearchShare(event){
	var code=event.keyCode;
	if(code==13){
		$("#search_ul li").remove();
		//获取请求参数
		var keyword=$("#search_note").val().trim();
		page=1;
		loadPageShare(keyword,page);
	}
};
function moreSearchShare(){
	//获取参数
	var keyword=$("#search_note").val().trim();
	page=page+1;
	//发送ajax请求加载列表
	loadPageShare(keyword,page);
};

function loadPageShare(keyword,page){
	$.ajax({
		"url":path+"/share/search.do",
		"type":"post",
		"data":{"keyword":keyword,"page":page},
		"dataType":"json",
		"success":function(result){
			//获取数据
			var shares=result.data;
			for(var i=0;i<shares.length;i++){
				var shareId=shares[i].cn_share_id;
				var shareTitle=shares[i].cn_share_title;
				var sli="";
				sli+='<li class="online">';
				sli+='<a href="#">';
				sli+='	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				sli+=shareTitle;
				sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button><div class="time"></div>';
				sli+='</a>';
				sli+='</li>';
				var $li=$(sli);
				//绑定shareId
				$li.data("shareId",shareId);
				//将li对象添加到ul当中
				$("#search_ul").append($li);
			}
			//切换显示区
			//全部笔记区域隐藏
			$("#pc_part_2").hide();
			//搜索笔记区域显示
			$("#pc_part_6").show();
		},
		"error":function(){
			alert("搜索失败！")
		}
	});
};
//分享笔记
function sureShareNote(){
	//获取参数
	$li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	//alert(noteId);
	$.ajax({
		"url":path+"/share/add.do",
		"type":"post",
		"data":{"noteId":noteId},
		"dataType":"json",
		"success":function(result){
			if(result.status==0){
				var noteTitle=$li.text();
				var str="";
				str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				str+=noteTitle;
				str+='  <i class="fa fa-sitemap"></i>';
				str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				$li.find("a").html(str);
				alert(result.msg);
			}
		},
		"error":function(){
			alert("笔记分享失败！");
		}
	});
};