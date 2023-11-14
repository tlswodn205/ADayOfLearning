
let search = {
	version:1,
	init : function(){
		$(document).on("click", "#search-btn", ()=>{
			this.search();
		});
	},
 	search:function(){
	let type=$("#type").val();
	let keyword=$("#keyword").val();
	let status=$("#status").val();
	
	let location = "?page=1&type="+type+"&keyword="+keyword
	
	if(status){
		location += "&keyword="+status;
	}
		window.location.href= location;
	}
}
search.init();