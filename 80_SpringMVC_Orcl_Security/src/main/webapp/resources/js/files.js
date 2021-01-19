let regExp = new RegExp("\.(exe|sh|bat|js|msi|dll)$");  //정규표현식 만드는 객체, \.: 점뒤로~, $: 이걸로 끝나는
let maxSize = 1048576; //1MB
	
	function fileExtAndSizeValidation(fileName, fileSize){// 여기 함수에서 검사후 아래에 포문(if)에 (리턴으로) 던져줌
		if(regExp.test(fileName)){
			alert(fileName + " 파일은\n허용되지 않는 파일형식입니다.");
			return false;
		}else if(fileSize > maxSize){
			alert(fileName + " : " + fileSize + "는 1MB를 초과합니다");
			return false;
		}else{
			return true;
		}
	}
	
	function printSize(fileSize) {
		if(fileSize===0) return '0 Byte';
		if(fileSize < 1024){
			return fileSize + " Byte";
		} else if(fileSize < 1024*1024){
			return parseFloat((fileSize/1024).toFixed(2)) + ' KB'; 
		}else{
			return parseFloat((fileSize/1024/1024).toFixed(2)) + ' MB'; 
		}
	}