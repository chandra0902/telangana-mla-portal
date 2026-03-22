function showPage(page){

document.querySelectorAll(".page").forEach(p=>p.classList.add("hidden"));
document.getElementById(page).classList.remove("hidden");

document.querySelectorAll(".sidebar a").forEach(a=>a.classList.remove("active"));
document.getElementById("menu-"+page).classList.add("active");

if(page==="dashboard") loadDashboard();
if(page==="manage") loadMLA();
}

window.onload=function(){
showPage("dashboard");
};