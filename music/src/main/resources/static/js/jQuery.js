$(document).ready(function() {

	$(".btnPlay").click(function() {
		
		var id = $(this).attr('id');
		var audio = '<audio controls="controls" autoplay="autoplay" controlsList="nodownload">'
			      + '<source src="/music/song?id='+ id +'" type="audio/mp3"></audio>'
		
		$("#audio").html(audio);
	});
});