window.addEventListener('DOMContentLoaded', function () {
  let tconst = getQueryVariable("tconst")
  if (tconst)
    getMovieInfo()
  let form = document.getElementsByTagName("form")[0]
  form.addEventListener("submit", event => {
      event.preventDefault()
      event.stopPropagation()
    if (tconst)
      update()
    else
      insert()
  })
})
function addValidated(){
  let form = document.getElementsByTagName("form")[0]
  form.classList.add('was-validated')
}
function insertAkaTitle() {
  document.getElementById("aka-group").appendChild(createAkaElement("", ""))
}

function insertPrincipal() {
  document.getElementById("principals-group").appendChild(createPrinElement("", ""))
}

function removeValidated() {
  console.log("reset")
  let form = document.getElementsByTagName("form")[0]
  form.classList.remove("was-validated")
}

function update() {
  sendForm("/title/update")
}

function insert() {
  sendForm("/title/insert")
}

function sendForm(url) {
  let form = document.getElementsByTagName("form")[0]
  const jsonSend = axios.create({
    headers: {
      "Content-Type": "application/json"
    }
  })
  let json = {}
  let single_input = document.getElementsByClassName("single-input")
  let crew_input = document.getElementsByClassName("crew-input")
  let rating_input = document.getElementsByClassName("rating-input")
  let primaryTitle
  Array.prototype.forEach.call(single_input, elem => {
    json[elem.name] = elem.value
    if (elem.name === "primaryTitle")
      primaryTitle = elem.value
  })
  json.crew = {}
  Array.prototype.forEach.call(crew_input, elem => {
    json["crew"][elem.name] = elem.value
  })
  json.rating = {}
  Array.prototype.forEach.call(rating_input, elem => {
    json["rating"][elem.name] = elem.value
  })
  let title_input = document.getElementsByClassName("akas-input")
  let region_input = document.getElementsByClassName("region-input")
  let nconst_input = document.getElementsByClassName("nconst-input")
  let category_input = document.getElementsByClassName("category-input")
  json.akas = []
  if(url === "/title/insert"){
    let aka = {}
    aka.title = primaryTitle
    aka.region = ""
    json.akas.push(aka)
  }
  json.principals = []
  for (let i = 0; i < title_input.length; i++) {
    let aka = {}
    aka.title = title_input[i].value
    aka.region = region_input[i].value
    if (aka.title !== "" || aka.region !== "")
      json.akas.push(aka)
  }
  for (let i = 0; i < nconst_input.length; i++) {
    let principal = {}
    principal.nconst = nconst_input[i].value
    principal.category = category_input[i].value
    if (principal.nconst !== "" || principal.category !== "")
      json.principals.push(principal)
  }
  console.log(JSON.stringify(json))
  console.log(url)
  jsonSend.post(url, JSON.stringify(json))
      .then(res=>{
        if(res.data["isOk"] === true)
          alert("操作成功！")
        else
          alert("操作失败！")
      })

}

function getMovieInfo() {
  let tconst = getQueryVariable("tconst")
  axios.get(`/title/getTitleByTconst?tconst=${tconst}&fetchAll=1`)
      .then(res => {
        let data = res.data.data[0]
        console.log(data)
        let single_input = document.getElementsByClassName("single-input")
        Array.prototype.forEach.call(single_input, elem => {
          elem.value = data[elem.name]
        })
        console.log(document.getElementById("tconst").value)
        let crew_input = document.getElementsByClassName("crew-input")
        let rating_input = document.getElementsByClassName("rating-input")
        if (data["crew"] !== null)
          Array.prototype.forEach.call(crew_input, elem => {
            elem.value = data["crew"][elem.name]
          })
        if (data["rating"] !== null)
          Array.prototype.forEach.call(rating_input, elem => {
            elem.value = data["rating"][elem.name]
          })
        let aka_group = document.getElementById("aka-group")
        aka_group.innerHTML = ""
        if (data["akas"] !== null)
          data["akas"].forEach(elem => {
            aka_group.appendChild(createAkaElement(elem["title"], elem["region"]))
          })
        let prin_group = document.getElementById("principals-group")
        prin_group.innerHTML = ""
        if (data["principals"] !== null)
          data["principals"].forEach(elem => {
            prin_group.appendChild(createPrinElement(elem["nconst"], elem["category"]))
          })
      })
}

function createAkaElement(aka_title, region) {
  let div = document.createElement("div")
  div.className = "form-row form-group"
  div.style.maxWidth = "99%"
  let aka_label = document.createElement("label")
  let region_label = document.createElement("label")
  let aka_input = document.createElement("input")
  let region_input = document.createElement("input")
  aka_label.className = "col-form-label col-2 col-lg-1"
  aka_label.innerText = "Aka Title:"
  aka_input.className = "form-control akas-input col-10 col-lg-4"
  aka_input.placeholder = "please input Aka Title"
  aka_input.value = aka_title
  region_label.className = "col-form-label  col-2 col-lg-1 offset-lg-1"
  region_label.innerText = "Region:"
  region_input.className = "form-control region-input col-10 col-lg-5"
  region_input.placeholder = "please input Region"
  region_input.value = region
  div.appendChild(aka_label)
  div.appendChild(aka_input)
  div.appendChild(region_label)
  div.appendChild(region_input)
  return div
}

function createPrinElement(nconst, category) {
  let div = document.createElement("div")
  div.className = "form-row form-group"
  div.style.maxWidth = "99%"
  let nconst_label = document.createElement("label")
  let category_label = document.createElement("label")
  let nconst_input = document.createElement("input")
  let category_input = document.createElement("input")
  nconst_label.className = "col-form-label col-2 col-lg-1"
  nconst_label.innerText = "NameID:"
  nconst_input.className = "form-control nconst-input col-10 col-lg-4"
  nconst_input.placeholder = "please input NameID"
  nconst_input.value = nconst
  category_label.className = "col-form-label  col-2 col-lg-1 offset-lg-1"
  category_label.innerText = "Category:"
  category_input.className = "form-control category-input col-10 col-lg-5"
  category_input.placeholder = "please input Category"
  category_input.value = category
  div.appendChild(nconst_label)
  div.appendChild(nconst_input)
  div.appendChild(category_label)
  div.appendChild(category_input)
  return div
}

function getQueryVariable(variable) {
  const query = window.location.search.substring(1);
  const vars = query.split("&");
  for (let i = 0; i < vars.length; i++) {
    const pair = vars[i].split("=");
    if (pair[0] === variable) {
      return pair[1];
    }
  }
  return false;
}
