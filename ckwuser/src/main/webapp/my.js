$(function(){
			// Ajax button click
			

				$.ajax({
					url:'/ckwuser/My',
					type:'POST',
					data:{
						
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
					var str = data.split("/");
					$("#email").val(str[0]);
				$("#password").val(str[1]);
				})
	});	
	
	function save() {
		$.ajax({
					url:'/ckwuser/UpdateMy',
					type:'POST',
					data:{
						'email':$('#email').val(),
						'password':$('#password').val()
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
						$('#res').html(data);
				})
	}
				
