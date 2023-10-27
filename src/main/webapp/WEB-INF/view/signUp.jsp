<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file ="/WEB-INF/view/layout/header.jsp" %>
<!-- header.jp -->

<div class="col-sm-8">
	<h2>회원 가입</h2>
	<h5>어서오세요 환영합니다.</h5>
	<div class="bg-light p-md-5 h-75">
	
	    <form action="javascript:alert(grecaptcha.getResponse(widgetId1));" onclick="grecaptchaCheck()">
      <div id="example1"></div>
      <br>
      <input type="submit" value="getResponse">
    </form>
    <br>
    <!-- Resets reCAPTCHA widgetId2 upon submit. -->
    <form action="javascript:grecaptcha.reset(widgetId2);">
      <div id="example2"></div>
      <br>
      <input type="submit" value="reset">
    </form>
    <br>
    <!-- POSTs back to the page's URL upon submit with a g-recaptcha-response POST parameter. -->
    <form id="ex3" action="http://www.google.com/recaptcha/api/siteverify?secret=6LdlBDMoAAAAAP7BHcrmNBMclxHp2y-1_KBAczAB" method="POST">
      <div id="example3"></div>
      <br>
      <input type="button" value="Submit" onclick="grecaptchaCheck(this.form)">
    </form>
    <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
        async defer>
    </script>
		  <form action="/user/signUp-proc" method="post">
		  
            <div class="form-group">
              <label for="email">Email address : </label>
              <input type="email" class="form-control" placeholder="Enter email" id="email" name = "email">
            </div>
            
            <div class="form-group">
              <label for="pwd">Password : </label>
              <input type="password" class="form-control" placeholder="Enter password" id="pwd" id="password">
            </div>
            
            <div class="form-group">
              <label for="fullname">Fullname : </label>
              <input type="text" class="form-control" placeholder="Enter fullname" id="fullname" id="fullname">
            </div>
            
             <script src="https://www.google.com/recaptcha/api.js" async defer></script>
			 <div class="g-recaptcha" data-sitekey="6LdlBDMoAAAAAJ5e259lVDfM02OcrCd5TftL9awD"></div>
            
            
            
            <button type="submit" class="btn btn-primary success" onclick="return check_recaptcha();">Submit</button>
          </form>
          
	</div>
</div>

<script type="text/javascript">
      var verifyCallback = function(response) {
        alert(response);
        widgetId1= response;
      };
      var widgetId1;
      var widgetId2;
      var onloadCallback = function() {
        // Renders the HTML element with id 'example1' as a reCAPTCHA widget.
        // The id of the reCAPTCHA widget is assigned to 'widgetId1'.
        widgetId1 = grecaptcha.render('example1', {
          'sitekey' : '6LdlBDMoAAAAAJ5e259lVDfM02OcrCd5TftL9awD',
          'theme' : 'light'
        });
        widgetId2 = grecaptcha.render(document.getElementById('example2'), {
          'sitekey' : '6LdlBDMoAAAAAJ5e259lVDfM02OcrCd5TftL9awD'
        });
        grecaptcha.render('example3', {
          'sitekey' : '6LdlBDMoAAAAAJ5e259lVDfM02OcrCd5TftL9awD',
          'callback' : verifyCallback,
          'theme' : 'dark'
        });
      };
      
      function formChange(form){
    	  form.action = "http://www.google.com/recaptcha/api/siteverify?secret=6LdlBDMoAAAAAP7BHcrmNBMclxHp2y-1_KBAczAB&response="+widgetId1;
    	  form.submit();
      }
      
      

      
      function grecaptchaCheck(form){
    	let ppc = $("#ex3")[0];
        var form_data = new FormData(form);
        var form_data2 = new FormData(ppc);

          $.ajax("http://www.google.com/recaptcha/api/siteverify?secret=6LdlBDMoAAAAAP7BHcrmNBMclxHp2y-1_KBAczAB&response="+widgetId1, {
              type: "Post",
             // dataType: "form",
              data: form_data2,
              contentType: false,
              processData: false,
              headers: {
            	    'Access-Control-Allow-Credentials' : true,
            	    'Access-Control-Allow-Origin':'*',
            	    'Access-Control-Allow-Methods':'POST',
            	    'Access-Control-Allow-Headers':'application/json',
              }
          }).done((res) => {
          	console.log(res);
          });
      }
    </script>

<!-- footer.jp -->
<%@ include file ="/WEB-INF/view/layout/footer.jsp" %>