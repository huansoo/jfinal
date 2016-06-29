jQuery(document).ready(
		function() {
			$("#username").focus();
			$('.page-container form').submit(
					function() {
						var username = $(this).find('.username').val();
						var password = $(this).find('.password').val();
						if (username == '') {
							$(this).find('.error').css('top', '75px');
							$(this).find('.error').html(
									$(this).find('.error').attr("value")
											+ $(this).find('.username').attr(
													"placeholder"));
							$(this).find('.error').fadeIn('fast');
							$(this).find('.username').focus();
							return false;
						}
						if (password == '') {
							$(this).find('.error').css('top', '142px')
							$(this).find('.error').html(
									$(this).find('.error').attr("value")
											+ $(this).find('.password').attr(
													"placeholder"));
							$(this).find('.error').fadeIn('fast');
							$(this).find('.password').focus();
							return false;
						}
						password = md5(password);
						mobile = "";
						try {
							var urlhash = window.location.hash;
							if (!urlhash.match("fromapp")) {
								if ((navigator.userAgent
										.match(/(iPhone|iPod|Android|ios|iPad)/i))) {
									mobile = "mobile";
								}
							}
						} catch (err) {
						}
						$('#submit').attr("disabled", true);
						// 调用AJAX
						$.post("login", {
							username : username,
							password : password,
							mobile   : mobile
						}, function(data, status) {
							if (status != "success") {
								$("#error").css('top', '142px');
								$("#error").html($("#error").attr("value2"));
								$("#error").fadeIn('fast');
								$("#password").focus();
							} else {
								if (data.statusCode != "200") {
									$("#error").css('top', '142px');
									$("#error")
											.html($("#error").attr("value3"));
									$("#error").fadeIn('fast');
									$("#password").focus();
								} else {
									window.location.href = $('#submit').attr("success");
									return;
								}
							}
							$('#submit').attr("disabled", false);
						});
						return false;
					});

			$('.page-container form .username, .page-container form .password')
					.keyup(function(event) {
						if (event.which != 13)
							$(this).parent().find('.error').fadeOut('fast');
					});

		});
