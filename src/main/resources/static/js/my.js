function upload() {
	$(".progress").removeClass("hidden");
	var files = $("#file")[0].files;
	var formData = new FormData();
	formData.append("files[0]" , files[0]);
	formData.append("files[1]" , files[1]);
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "upload", true);
	xhr.upload.addEventListener("progress",progressHandle, false);
	xhr.send(formData);
}

function progressHandle(e){
	var pc = parseInt((e.loaded / e.total * 100));
	$(".progress-bar").width(pc + "%");
}