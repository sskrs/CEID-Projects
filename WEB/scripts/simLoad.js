	var $loading = $('#loading').hide();
		$(document)
		.ajaxStart(function () {
		$loading.show();
		$('#mapid').hide();
		})
		.ajaxStop(function () {
		$loading.hide();
		$('#mapid').show();
		});