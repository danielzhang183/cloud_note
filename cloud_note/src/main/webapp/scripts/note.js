//
function showNoteMenu(){
	$("#note_ul").on("click",".btn_slide_down",function(){
		//显示菜单
		var note_menu=$(this).parents("li").find("div");
		note_menu.slideDown(300);
		$("#note_ul a").removeClass("checked");
		$(this).parent().addClass("checked");
		//阻止冒泡事件
		return false;
	});
	$("body").click(function(){
		$("#note_ul div").hide();
	});
};
//更新笔记信息
function updateNote(){
	//获取参数
	var $li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	var noteTitle=$("#input_note_title").val().trim();
	var noteBody=um.getContent();
	alert(noteId+","+noteTitle+","+noteBody);
    $.ajax({
		"url":path+"/note/updateNote.do",
		"type":"post",
		"data":{"noteId":noteId,"title":noteTitle,"body":noteBody},
		"dataType":"json",
		"success":function(result){
			if(result.status==0){
				//更新笔记列表的title
				var str="";
				str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				str+=noteTitle
				str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				//将字符串更新到li的a元素里
				$li.find("a").html(str);
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		"error":function(){
			alert("保存笔记失败");
		}
	});  
	
}
//显示笔记
function loadNote(){
	//设置选中效果
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	
	//获取参数
	var noteId=$(this).data("noteId");
	//alert(noteId);
	$.ajax({
		"url":path+"/note/load.do",
		"type":"post",
		"data":{"noteId":noteId},
		"dataType":"json",
		"success":function(result){
			if(result.status==0){
			var title=result.data.cn_note_title;
			var body=result.data.cn_note_body;
			$("#input_note_title").val(title);
			um.setContent(body);
			}
		},
		"error":function(){
			alert("笔记显示失败");
		}
	});
}
//显示所有笔记
function loadBookNotes(){
	//alert("绑定成功！");
	//设置选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取参数
	var bookId=$(this).data("bookId");
	//alert(bookId);
	$.ajax({
		"url":path+"/note/loadnotes.do",
		"type":"post",
		"data":{"bookId":bookId},
		"dataType":"json",
		"success":function(result){
			//获取笔记信息
			var notes=result.data;
			//清楚原列表信息
			$("#note_ul").empty();
			//循环添加li
			for(var i=0;i<notes.length;i++){
				//获取笔记ID
				var noteId=notes[i].cn_note_id;
				//获取笔记标题
				var noteTitle=notes[i].cn_note_title;
				createNoteLi(noteId,noteTitle);
			}
		},
		"error":function(){
			alert("笔记加载失败！");
		}
		
	});
};

//创建一个笔记li元素
function createNoteLi(noteId,noteTitle){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+=noteTitle
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='</a>';
	sli+='<div class="note_menu" tabindex="-1">';
	sli+='<dl>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
	sli+='</li>';
	//将sli字符串转换成jQuery对象里元素
	var $li=$(sli);
	//将bookId值与jQuery对象绑定
	$li.data("noteId",noteId);
	//将li元素添加到笔记本ul列表区
	$("#note_ul").append($li);
};

//创建一个新笔记
function sureAddNote(){
	//获取请求参数
	var title=$("#input_note").val().trim();
	var userId=getCookie("userId");
	var $li=$("#book_ul a.checked").parent();
	var bookId=$li.data("bookId");
	//数据格式检查
	var ok=true;
	if(title==""){
		ok=false;
		$("#title_span").html("笔记名不能为空！");
	}
	if(userId==null){//检查是否失效
		ok=false;
		window.location.href="login.html";
	}
	if(ok){
		$.ajax({
			"url":path+"/note/add.do",
			"type":"post",
			"data":{
				"title":title,"userId":userId,"bookId":bookId
			},
			"dataType":"json",
			"success":function(result){
				if(result.status==0){
					var noteId=result.data.cn_note_id;
					var noteTitle=result.data.cn_note_title;
					createNoteLi(noteId,noteTitle);
					alert(result.msg);
				}
			},
			"error":function(){
				alert("笔记创建失败！");
			}
		});
	}
};
//删除一个笔记
function sureDeleteNote(){
	//获取参数
	$li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	alert(noteId);
	$.ajax({
		"url":path+"/note/delete.do",
		"type":"post",
		"data":{"noteId":noteId},
		"dataType":"json",
		"success":function(result){
			if(result.status==0){
				$li.remove();
				//清除笔记内容
				$("#input_note_title").val("");
				um.setContent("");
				$('.close,.cancle').trigger('click');
				alert(result.msg);
			}
		},
		"error":function(){
			alert("笔记删除失败！");
		}
	});
};