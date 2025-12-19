window.onload = function(){
// ページ読み込み時に実行したい処理
	getMessages();
}
setInterval(function() {
  // ここに1秒ごとに実行したい処理を記述
  getMessages();
}, 1000); // 1000ミリ秒（1秒）ごとに実行

$(function(){
			// Ajax button click
			$('#send').on('click',function(){

let url = new URL(window.location.href);
let params = url.searchParams;
var to = params.get('email');
console.log(to);
				$.ajax({
					url:'./message.php',
					type:'POST',
					data:{
						'content':$('#content').val(),
						'to':to
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
					getMessages();
					$('#content').val("");
				
				})
	});	
				
		});


function getMessages(){
	let url = new URL(window.location.href);
let params = url.searchParams;
var to = params.get('email');

				$.ajax({
					url:'./getMessages.php',
					type:'POST',
					data:{
						'content':$('#content').val(),
						'to':to
					}
				})
				// Ajaxリクエストが成功した時発動
				.done( (data) => {
					$('#history').html(data);
				
				})
	
}
