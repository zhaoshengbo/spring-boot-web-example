function upload(fileInput) {
	var file = fileInput.files[0];
	var formData = new FormData();
	formData.append("file" , file);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "upload", true);
	xhr.upload.addEventListener("progress",progressHandle, false);
	xhr.send(formData);
}

function progressHandle(e){
	var pc = parseInt((e.loaded / e.total * 100));
	$(".progress-bar").width(pc + "%");
}