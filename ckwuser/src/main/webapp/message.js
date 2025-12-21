window.onload = function(){
	getMessages();
}
	
	function send() {
		let url = new URL(window.location.href);
		let param = url.searchParams;
		var to = param.get('email');
		$.ajax({
					url:'/ckwuser/Message',
					type:'POST',
					data:{
						'to':to,
						'content':$('#content').val()
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
						$('#res').html(data);
						$('#content').val('');
						getMessages();
				})
	}
	
		function getMessages() {
		let url = new URL(window.location.href);
		let param = url.searchParams;
		var to = param.get('email');
		$.ajax({
					url:'/ckwuser/GetMessages',
					type:'POST',
					data:{
						'to':to
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
						$('#history').html(data);
				})
	}
				
