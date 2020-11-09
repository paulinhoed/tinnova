$(function () {

	actions();

	listarTodos();

});

function actions() {
	$("#btnListAll").click(function () {
		listarTodos();
	});
	$("#btnListLastReg").click(function () {
		listarUltimosRegistradosSemana();
	});
	$("#btnQtdNaoVendidos").click(function () {
		exibirQuantidadeNaoVendidos();
	});
	$("#selecionaFabricante").change(function () {
		exibirQuantidadePorMarca(this);
	});
	$("#selecionaDecada").change(function () {
		exibirQuantidadePorDecada(this);
	});
}

function formToJSON() {
	var result = {
		"id": $('#veiculoId').val(),
		"veiculo": $('#veiculoNome').val(),
		"marca": $('#fabricante').val(),
		"ano": $('#ano').val(),
		"descricao": $('#descricao').val(),
		"vendido": $('#vendido').val()
	};
	return (JSON.stringify(result));
}

function loadVeiculo(id) {
	findById(id, function (data) {
		fillForm(data);
		$("#form").css({
			"visibility": "visible",
			"display": "block"
		});
		$("#fieldset").html(" Editando Veículo ");
	});
}

function clearForm() {
	$("#fieldset").html("");
	fillForm({
		"veiculo": "",
		"marca": "",
		"ano": "",
		"descricao": "",
		"vendido": false
	});
}

function fillForm(veiculo) {
	$("#veiculoId").val(veiculo.id);

	$("#veiculoNome").val(veiculo.veiculo);

	if (veiculo.marca != "")
		$("#fabricante").val(veiculo.marca);

	$("#ano").val(veiculo.ano);

	$("#descricao").val(veiculo.descricao);

	$("#vendido").prop('checked', veiculo.vendido);
}


function saveOrUpdate() {
	let check = validateInput();
	if(check == "ok") {
		if ($("#veiculoId").val() != null && $("#veiculoId").val() != "") {
			updateVeiculo(formToJSON(), callbackUpdate());
		} else {
			addVeiculo(formToJSON(), callbackSave());
		}
	}
	else {
		alert(check);
	}
}

function validateInput() {
	var msgHeader = "Os seguintes campos devem ser preenchidos corretamente:\n";
	var msg = "";
	if ($("#veiculoNome").val() == null || $("#veiculoNome").val() == "") 
		msg+="*Nome;\n"
	
	if ($("#fabricante").val() == null || $("#fabricante").val() == "" || $("#fabricante").val() == "none") 
		msg+="*Fabricante;\n"
	
	if ($("#ano").val() == null || $("#ano").val() == "") 
		msg+="*Ano;\n"
	
	if ($("#descricao").val() == null || $("#descricao").val() == "") 
		msg+="*Descrição;\n"
	if(msg === "")
		return "ok";
	else{
		return msgHeader+msg;
	}		
	
}

function newVeiculo() {
	clearForm();
	$("#form").css({
		"visibility": "visible",
		"display": "block"
	});
	$("#fieldset").html(" Novo Veículo ");
}

function cancel() {
	$("#form").css({
		"visibility": "hidden",
		"display": "none"
	});
	clearForm();
}

function renderList(data) {
	var html = "";

	html += "<ul>";

	if (data.length > 0) {
		for (var i = 0; i < data.length; i++) {
			var veiculo = data[i];

			html += "<li><strong>#" + veiculo.id + " | " + veiculo.veiculo + " | " + veiculo.marca + " | " + veiculo.ano + " | " + veiculo.descricao +
				"</strong>    ";
				
				 html += "<input type='button' value='Remover' title='Apagar este veículo' onclick='removerVeiculo("
                + veiculo.id + ")'>";

			html += "<input type='button' value='Alterar' title='Alterar este veículo' onclick='alterarVeiculo(" +
				veiculo.id + ")'>";

			html += "</li>";

		}
	} else {
		if (data.hasOwnProperty('naoVendidos')) {
			let qtd = JSON.stringify(data['naoVendidos']).replaceAll('"', '');
			html += "<li><strong>Existem " + qtd + " veículos que estão como não vendidos na base</li>";
		} else if (data.hasOwnProperty('qtdMarca')) {
			let marca = JSON.stringify(data['marca']);
			let qtd = JSON.stringify(data['qtdMarca']).replaceAll('"', '');
			html += "<li><strong>Foram distribuídos " + qtd + " veículos do fabricante " + marca + "</li>";
		} else if (data.hasOwnProperty('qtdDecada')) {
			let decada = JSON.stringify(data['decada']);
			let qtd = JSON.stringify(data['qtdDecada']).replaceAll('"', '');
			html += "<li><strong>Foram distribuídos " + qtd + " veículos na decada de " + decada + "</li>";
		} else
			html += "<li><strong>Nenhum resultado encontrado</li>";
	}

	html += "</ul>";

	$("#result").html(html);
}

function clearList() {
	$("#result").html();
}

function listarTodos() {
	clearList();
	listaTodos(renderList);
}

function listarUltimosRegistradosSemana() {
	clearList();
	listaUltimosReg(renderList);
}

function exibirQuantidadeNaoVendidos() {
	clearList();
	qtdNaoVendidos(renderList);
}

function exibirQuantidadePorMarca(obj) {
	clearList();
	let marca = $(obj).val();
	qtdPorMarca(marca, renderList);
}

function exibirQuantidadePorDecada(obj) {
	clearList();
	let decada = $(obj).val();
	qtdPorDecada(decada, renderList);
}

function removerVeiculo(id) {
    deleteVeiculo(id, callbackDelete());
}

function alterarVeiculo(id) {
	newVeiculo();
	loadVeiculo(id);
}

function callbackDelete() {
	alert('Veículo removido com sucesso!');
	clearForm();
	listarTodos(renderList);
}

function callbackSave() {
	alert('Veículo criado com sucesso!');
	clearForm();
	$("#form").css({
		"visibility": "hidden",
		"display": "none"
	});
	listarTodos(renderList);
}

function callbackUpdate() {
	alert('Veículo atualizado com sucesso!');
	clearForm();
	$("#form").css({
		"visibility": "hidden",
		"display": "none"
	});
	listarTodos(renderList);
}

