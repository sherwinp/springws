function waitonme() {
	// Issue an AJAX HEAD request for each one
	$.ajax({
		type: 'HEAD',
		url: 'download/internal',
		complete: function(xhr) {
			var size  = xhr.getResponseHeader('download_progress');
			console.log('size is: ' + size);
			setTimeout( waitonme_end, 3000, size );
			console.log('size so far: ' + size);
		}
	});
	$('#main').waitMe({
		effect : 'bounce',
		text : '',		
		bg : 'rgba(0,9,5, 0.07)',		
		});
	return true;
}
function waitonme_end(lastBlockCount){
	$.ajax({
		type: 'HEAD',
		url: 'download/internal',
		complete: function(xhr) {
			var size  = xhr.getResponseHeader('download_progress');
			console.log('size is: ' + size);
			console.log('lastBlockCount: ' + lastBlockCount);
			if( ( size == -1 || size == 0 || lastBlockCount == size) ){
				$('#main').waitMe("hide")
			}else{
				setTimeout( waitonme_end, 4000, size );
				console.log('size so far: ' + size);
			}
		}
	});
}
function humanize(size) {
	var units = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
	var ord = Math.floor(Math.log(size) / Math.log(1024));
	ord = Math.min(Math.max(0, ord), units.length - 1);
	var s = Math.round((size / Math.pow(1024, ord)) * 100) / 100;
	return s + ' ' + units[ord];
}