
(function () {
    
	$("#basic_info").show();
	$("#other_info").hide();
	
	$("#step_1").on("click", function(e){
			
		let is_valid = true;
		e.preventDefault();
		
		if( $("#name").val() === '' ) {
			$("#name_input_helper").show();
			is_valid = false;
		}
		
		if( $("#mail").val() === '' ) {
			$("#mail_input_helper").show();
			is_valid = false;
		}
		
		if( $("#password").val() === '' ) {
			$("#pwd_input_helper").show();
			is_valid = false;
		}
		
		if(is_valid) {
			
			$("#basic_info").hide();
			$("#other_info").show();
				
		}
		
	});
	
	$("#go_to_sign_in").on("click", function() {
		
		e.preventDefault();
		
	});
	
})();
