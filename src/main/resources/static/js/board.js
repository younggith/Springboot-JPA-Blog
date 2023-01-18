let index = {
	init: function() {
		$('#btn-save').on('click', ()=>{
			this.save();
		});
		$('#btn-delete').on('click', ()=>{
			this.deleteById();
		});
		$('#btn-update').on('click', ()=>{
			this.update();
		});
		/*$('#btn-login').on('click', ()=>{
			this.login();
		});*/
	},
	save: function() {
		//alert("user의 save함수 호출됨!");
		let data = {
			title: $('#title').val(),
			content: $('#content').val()
		};
		
		$.ajax({
			type: 'POST',
			url: '/api/board',
			data: JSON.stringify(data),	// http body 데이터 mimeType 필요!
			contentType: 'application/json; charset=utf-8',	//요청할때 데이터타입 지정
			dataType: 'json'	// 요청을 서버를통해 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면 => javascript오브젝트로 변경)
			//회원가입 수행 요청
		}).done(function (resp) {
			alert('글쓰기가 완료되었습니다.');
			location.href = '/';
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	},
	
	deleteById: function() {
		let id = $('#id').text();
		
		$.ajax({
			type: 'DELETE',
			url: '/api/board/'+id,
			dataType: 'json'	// 요청을 서버를통해 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면 => javascript오브젝트로 변경)
		}).done(function (resp) {
			alert('삭제가 완료되었습니다.');
			location.href = '/';
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		let id = $('#id').val();
		
		let data = {
			title: $('#title').val(),
			content: $('#content').val()
		};
		
		$.ajax({
			type: 'PUT',
			url: '/api/board/'+id,
			data: JSON.stringify(data),	// http body 데이터 mimeType 필요!
			contentType: 'application/json; charset=utf-8',	//요청할때 데이터타입 지정
			dataType: 'json'	// 요청을 서버를통해 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면 => javascript오브젝트로 변경)
			//회원가입 수행 요청
		}).done(function (resp) {
			alert('글수정이 완료되었습니다.');
			location.href = '/';
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	}
	
	/*login: function() {
		//alert("user의 save함수 호출됨!");
		let data = {
			Username: $('#Username').val(),
			password: $('#password').val()
		};
		//console.log(data);
		
		// ajax 호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다
		$.ajax({
			type: 'POST',
			url: '/api/user/login',
			data: JSON.stringify(data),	// http body 데이터 mimeType 필요!
			contentType: 'application/json; charset=utf-8',	//요청할때 데이터타입 지정
			dataType: 'json'	// 요청을 서버를통해 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면 => javascript오브젝트로 변경)
			//회원가입 수행 요청
		}).done(function (resp) {
			alert('로그인이 완료되었습니다.');
			location.href = '/';
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
	}*/
}

index.init();