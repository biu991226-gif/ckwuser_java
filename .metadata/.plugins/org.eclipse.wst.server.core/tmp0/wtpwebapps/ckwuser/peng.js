$(function(){
			// Ajax button click
			

				$.ajax({
					url:'/ckwuser/Peng',
					type:'POST',
					data:{
						
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
					$('#res').html(data);
				
				})
	});	
				
