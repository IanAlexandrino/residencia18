const url = "http://localhost:8080/cursos/escola/1";

function hideLoader() {
  document.getElementById("loading").style.display = "none";
}

function show(cursos) {
  let tab = `
        <thead>
            <th scope = "col">#</th>
            <th scope = "col">Nome</th>
            <th scope = "col">Conteudo</thead>
        </thead>
    `;

  for (let curso of cursos) {
    tab += `
            <tr>
                <td scope = "row">${curso.id}</td>
                <td>${curso.nome}</td>
                <td>${curso.conteudo}</td>
            </tr>
        `;
  }

  document.getElementById("cursos").innerHTML = tab;
}

async function getAPI(url) {
  const response = await fetch(url, { method: "GET" });

  var data = await response.json();

  if (response) {
    hideLoader();
    show(data);
  }
}

getAPI(url);
