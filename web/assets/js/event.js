window.onload = function() {
	
	/* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
	if(document.getElementById("writeFree")) {
		const $writeFree = document.getElementById("writeFree");
		$writeFree.onclick = function() {
			location.href = "/WWW/board/free/select";
							/board/free/select
		}
	}
	
	if(document.getElementById("cancleFree")) {
		const $cancleFree = document.getElementById("cancleFree");
		$cancleFree.onclick = function() {
			location.href = "/chap99/notice/list";
							/board/free/insert
			
		}
	}
}
