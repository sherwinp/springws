<script type="text/javascript">
function onwindowload(){
	console.log("winodw loaded.");
	$('#jstree').jstree();
}
function onajaxupload(){
	var form = $('#form1')[0];
	var formData = new FormData(form);
	formData.append('files', $('input[type=file]')[0].files[0]);
	$.ajax({
		type: 'POST',
		url: 'upload',
	    data: formData,
	    // THIS MUST BE DONE FOR FILE UPLOADING
	    contentType: false,
	    processData: false,
	    beforeSend: function() {
	    	$('#main').waitMe({
	    		effect : 'bounce',
	    		text : '',		
	    		bg : 'rgba(0,9,5, 0.07)',		
	    		});
	    },
	    success: function(msg) {
	    	$('#main').waitMe("hide");
	    },
	    error: function() {
	    	$('#main').waitMe("hide");
	    }
	})
	return false;
}
window.onload = onwindowload;

</script>