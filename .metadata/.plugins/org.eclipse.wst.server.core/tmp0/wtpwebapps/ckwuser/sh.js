$(function(){
			// Ajax button click
			$('#sh').on('click',function(){

				$.ajax({
					url:'/ckwuser/Sh',
					type:'POST',
					data:{
						'email':$('#email').val()
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
					$('#res').html(data);
				
				})
	});	
			
				
		});

function addFriend(){
				$.ajax({
					url:'/ckwuser/AddFriend',
					type:'POST',
					data:{
						'to':$('#email').val()
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
					$('#res').html(data);
				})
	
}