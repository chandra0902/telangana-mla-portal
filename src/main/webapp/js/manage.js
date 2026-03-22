let editId = null;
let currentMlaId = null;

/* LOAD PAGE */

function loadMLA(){

document.getElementById("manage").innerHTML = `

<div class="card">

<h3 id="formTitle">Add MLA</h3>

<form id="addForm">

<input name="name" id="name" placeholder="Name" required><br><br> <input name="age" id="age" type="number" placeholder="Age" required><br><br> <input name="party" id="party" placeholder="Party" required><br><br> <input name="constituency" id="constituency" placeholder="Constituency" required><br><br>

<button id="submitBtn" class="btn primary">Add MLA</button>

</form>

</div>

<div class="card">

<h3>MLA List</h3>

<table class="table">

<thead>
<tr>
<th>ID</th>
<th>Name</th>
<th>Party</th>
<th>Constituency</th>
<th>Action</th>
</tr>
</thead>

<tbody id="mlaBody"></tbody>

</table>

</div>

<!-- POPUP -->

<div id="detailsPopup" class="popup hidden">

<div class="popup-content">

<h3>Add MLA Details</h3>

<input id="d_bio" placeholder="Bio"><br><br> <input id="d_contact" placeholder="Contact"><br><br> <input id="d_email" placeholder="Email"><br><br> <input id="d_twitter" placeholder="Twitter"><br><br> <input id="d_district" placeholder="District"><br><br>

<button onclick="saveDetails()">Save</button> <button onclick="closePopup()">Cancel</button>

</div>

</div>
`;

/* FORM SUBMIT */

document.getElementById("addForm").onsubmit = function(e){

e.preventDefault();

let formData = new URLSearchParams(new FormData(this));

if(editId){

formData.append("id",editId);

fetch("updateMla",{
method:"POST",
headers:{
"Content-Type":"application/x-www-form-urlencoded"
},
body:formData
})
.then(()=>{

alert("✅ MLA Updated");
resetForm();
loadTable();

});

}else{

fetch("addMla",{
method:"POST",
headers:{
"Content-Type":"application/x-www-form-urlencoded"
},
body:formData
})
.then(()=>{

alert("✅ MLA Added");
this.reset();
loadTable();

});

}

};

loadTable();
}

/* LOAD TABLE */

function loadTable(){

fetch("viewMla")
.then(res=>res.json())
.then(data=>{

let rows="";

data.forEach(m=>{

rows+=`

<tr>

<td>${m.id}</td>
<td>${m.name}</td>
<td>${m.party}</td>
<td>${m.constituency}</td>

<td>

<button class="btn edit"
onclick="editRow(${m.id},'${m.name}','${m.party}','${m.constituency}',${m.age})">
✏️ </button>

<button class="btn delete"
onclick="deleteRow(${m.id})">
🗑 </button>

<button class="btn info"
onclick="openDetails(${m.id})">
➕ </button>

</td>

</tr>
`;

});

document.getElementById("mlaBody").innerHTML = rows;

});

}

/* EDIT */

function editRow(id,name,party,constituency,age){

editId = id;

document.getElementById("name").value = name;
document.getElementById("party").value = party;
document.getElementById("constituency").value = constituency;
document.getElementById("age").value = age;

document.getElementById("formTitle").innerText = "Update MLA";
document.getElementById("submitBtn").innerText = "Update MLA";

}

/* DELETE */

function deleteRow(id){

if(confirm("Delete MLA?")){

fetch("deleteMla?id="+id)
.then(()=>loadTable());

}

}

/* RESET */

function resetForm(){

editId = null;

document.getElementById("addForm").reset();

document.getElementById("formTitle").innerText = "Add MLA";
document.getElementById("submitBtn").innerText = "Add MLA";

}

/* POPUP */

function openDetails(id){
currentMlaId = id;
document.getElementById("detailsPopup").classList.remove("hidden");
}

function closePopup(){
document.getElementById("detailsPopup").classList.add("hidden");
}

/* SAVE DETAILS */

function saveDetails(){

let bio = document.getElementById("d_bio").value;
let contact = document.getElementById("d_contact").value;
let email = document.getElementById("d_email").value;
let twitter = document.getElementById("d_twitter").value;
let district = document.getElementById("d_district").value;

fetch("updateMla",{

method:"POST",

headers:{
"Content-Type":"application/x-www-form-urlencoded"
},

body:`id=${currentMlaId}&bio=${bio}&contact=${contact}&email=${email}&twitter=${twitter}&district=${district}`

})
.then(()=>{

alert("✅ Details Updated");

closePopup();
loadTable();

});

}
