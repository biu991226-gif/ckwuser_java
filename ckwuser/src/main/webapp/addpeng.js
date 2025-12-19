$(function(){
			// Ajax button click
			$('#addpeng').on('click',function(){

				$.ajax({
					url:'./addpeng.php',
					type:'POST',
					data:{
						'content':$('#content').val()
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
					$('#res').html(data);
				
				})
	});	
				
		});