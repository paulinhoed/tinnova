var rootURL = "http://localhost:8080/WebServiceRest";

function listaTodos(callback) {
	$.ajax({
		type : 'GET',
		url : rootURL+ '/veiculos/',
		dataType : "json", 
		success : callback
	});
}

function listaUltimosReg(callback) {
	$.ajax({
		type : 'GET',
		url : rootURL+ '/veiculos/findByRegistradosUltimaSemana',
		dataType : "json", 
		success : callback
	});
}

function qtdNaoVendidos(callback) {
	$.ajax({
		type : 'GET',
		url : rootURL+ '/veiculos/findByQuantidadeNaoVendidos',
		dataType : "json", 
		success : callback
	});
}

function qtdPorMarca(marca, callback) {
	if (marca != null && marca != "" && marca != "none") {
	$.ajax({
		type : 'GET',
		url : rootURL+ '/veiculos/findByQuantidadeMarca/' + marca,
		dataType : "json", 
		success : callback
	});
	} else {
		listaTodos(callback);
	}
}

function qtdPorDecada(decada, callback) {
	if (decada != null && decada != "" && decada != "none") {
	$.ajax({
		type : 'GET',
		url : rootURL+ '/veiculos/findByQuantidadeDistribuidosNaDecada/' + decada,
		dataType : "json", 
		success : callback
	});
	} else {
		listaTodos(callback);
	}
}

function addVeiculo(veiculo, callback) {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL+ '/veiculos/',
		dataType : "json",
		data : veiculo,
		success : callback
	});
}

function updateVeiculo(veiculo, callback) {
	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		url : rootURL+ '/veiculos/',
		data : veiculo,
		success : callback
	});
}

function deleteVeiculo(id, callback) {
	$.ajax({
		type : 'DELETE',
		url : rootURL+ '/veiculos/' + id,
		success : callback
	});
}

function findById(id, callback) {
	$.ajax({
		type : 'GET',
		url : rootURL + '/veiculos/findById/' + id,
		dataType : "json",
		success : callback
	});
}


