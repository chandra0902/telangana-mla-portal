function loadDashboard(){

document.getElementById("dashboard").innerHTML=`

<div class="analytics">

<div class="stat red">
👥 Total MLAs
<h2 id="totalMla">0</h2>
</div>

<div class="stat blue">
🏛 Parties
<h2 id="totalParty">0</h2>
</div>

<div class="stat green">
📍 Constituencies
<h2>119</h2>
</div>

</div>

<!-- CHARTS -->

<div class="charts-grid">

<div class="card">
<h3>📊 Party Distribution</h3>
<div class="chart-container">
<canvas id="partyChart"></canvas>
</div>
</div>

<div class="card">
<h3>📍 District-wise MLAs</h3>
<div class="chart-container">
<canvas id="districtChart"></canvas>
</div>
</div>

</div>

`;

fetch("viewMla")
.then(res=>res.json())
.then(data=>{

/* TOTALS */

document.getElementById("totalMla").innerText = data.length;

/* UNIQUE */

let parties = {};
let districts = {};
let partySet = new Set();

/* PROCESS DATA */

data.forEach(m=>{

partySet.add(m.party);

/* PARTY COUNT */
parties[m.party] = (parties[m.party] || 0) + 1;

/* DISTRICT COUNT */
districts[m.district] = (districts[m.district] || 0) + 1;

});

document.getElementById("totalParty").innerText = partySet.size;

/* PARTY CHART */

new Chart(document.getElementById("partyChart"),{

type:"doughnut",

data:{
labels:Object.keys(parties),
datasets:[{
data:Object.values(parties)
}]
},

options:{
responsive:true,
maintainAspectRatio:false,
plugins:{
legend:{position:"bottom"}
}
}

});

/* DISTRICT CHART */

new Chart(document.getElementById("districtChart"),{

type:"bar",

data:{
labels:Object.keys(districts),
datasets:[{
label:"MLAs",
data:Object.values(districts)
}]
},

options:{
responsive:true,
maintainAspectRatio:false
}

});

});

}