/**
 * 
 */
 
 let index = {
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
	},
	
	sav: function(){
		let data = {
			username: $("#name").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		
		$.ajax({
			type: "POST",
			url: "/member/joinProcess",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			if(resp.status === 500){
				alert("회원가입에 실패하였습니다.");
			}else{
				alet("회원가입이 완료되었습니다.");
				location.href="/";
			}
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}