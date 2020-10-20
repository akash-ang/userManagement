$(document).ready(function() {
	$("#userEmail").blur(function() {

		$.ajax({
			type : "GET",
			url : "uniqueMailCheck",
			data : {
				email : this.value
			},
			success : function(data) {
				if (data == "duplicate") {
					$("#emailErrorMsg").html("<font color='red'> Email Id Already Registered </font>");
					$("#submitButton").prop("disabled", true);
				} else {
					$("#emailErrorMsg").text("");
					$("#submitButton").prop("disabled", false);
				}
			}
		});
	});
	$("#countryId").change(function() {
		$('#stateId option:not(:first)').remove();
		$('#cityId option:not(:first)').remove();
				$.ajax({
			type : "GET",
			url : "countryChange",
			data : {
				countryId : this.value
			},
			success : function(stateMap) {
				$.each(stateMap , function(stateId , stateName){
					$('#stateId')
			          .append($('<option>', { value : stateId })
			          .text(stateName));
				});
			}
		});
	});
	$("#stateId").change(function() {
		$('#cityId option:not(:first)').remove();
		$.ajax({
			type : "GET",
			url : "stateChange",
			data : {
				stateId : this.value
			},
			success : function(cityMap) {
				$.each(cityMap , function(cityId , cityName){
					$('#cityId')
			          .append($('<option>', { value : cityId })
			          .text(cityName));
				});
			}
		});
	});
});

$(document).ready(function() {
	$("#datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		yearRange : "1900:+0"
	});
});
