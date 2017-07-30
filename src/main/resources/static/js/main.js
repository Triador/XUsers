window.onload = function () {



	var userId;
	$("#userCreate").click(function() {
		$(this).parent().parent().attr({"action": "/adminapi/user/"});
	});

	$("#userUpdate").click(function() {
		$(this).parent().parent().attr({"action": "/adminapi/update/"+userId});
	});
	
	$(".edit").click(function() {
		userId = $(this).attr("id");
		$("input[id=userUpdate]").removeAttr('disabled');
		$('input[name=username]').val($(this).parent().parent().find('#username').html());
		$('input[name=email]').val($(this).parent().parent().find('#email').html());
		$('input[name=firstname]').val($(this).parent().parent().find('#firstname').html());
		$('input[name=lastname]').val($(this).parent().parent().find('#lastname').html());
		$('input[name=party]').val($(this).parent().parent().find('#party').html());
        $('input[name=birthday]').val($(this).parent().parent().find('#birthday').html());
        $('input[name=zip]').val($(this).parent().parent().find('#zip').html());
        $('input[name=country]').val($(this).parent().parent().find('#country').html());
        $('input[name=district]').val($(this).parent().parent().find('#district').html());
        $('input[name=street]').val($(this).parent().parent().find('#street').html());
        $('input[name=city]').val($(this).parent().parent().find('#city').html());




	});
    

}