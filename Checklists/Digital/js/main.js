COMPLETED = false;
NAME = "";

document.onreadystatechange = function() {
	if (document.readyState !== "complete") return;
	
	if (localStorage.log) {
		LOG = JSON.parse(localStorage.log);
	} else {
		LOG = { }
		localStorage.log = JSON.stringify(LOG);
	}
	
	var modifiedPathName = location.pathname.toLowerCase().replace("/c:/users/", "");
	var allowedUserNames = ["s-mccartanc"];
	if (localStorage.verify_key === "ROBOTOTES_2412" && allowedUserNames.indexOf(modifiedPathName.slice(0, modifiedPathName.indexOf("/"))) > -1 && document.body.className === "red") {
		$("#watermark").remove();
	}
	
	if (document.body.className === "red") return; // if launcher.html
	
	var list = $("CHECKLIST").innerHTML.split("\n");
	NAME = $("CHECKLIST").getAttribute("name");
	
	$("CHECKLIST").remove();
	
	var title = list[2];
	$("title").innerHTML = title;
	var h = document.createElement("h1");
	h.innerHTML = title.toUpperCase();
	h.ondblclick = checkIfUsed;
	
	var ul = document.createElement("div");
	ul.id = "container";
	
	for (var i = 4; i < list.length - 2; i++) {
		var c = document.createElement("input");
		var l = document.createElement("div");
		var value = list[i].slice(2).replace("*", "<span class='bold'>").replace("*", "</span>");
		c.type = "checkbox";
		c.value = value;
		c.id = i.toString();
		l.id = i.toString();
		l.appendChild(c);
		l.innerHTML += value;
		ul.appendChild(l);
	}
	

	document.body.appendChild(h);
	document.body.appendChild(ul);
	
	var list = $$("#container > div");
	for (var i = 0; i < list.length; i++) {
		list[i].querySelector("input").onchange = update;
		list[i].querySelector("input").onclick = function(e) {
			e.stopPropagation();
		};
		list[i].onclick = function(e) {
			var el = this.querySelector("input");
			el.checked = !el.checked;	
			update();
		}
	}
	
	$("a.lr").onclick = function() { history.back() };
}

function update() {
	$("html").style.backgroundColor = "#fcfcfc";
	
	var list = $$("input");
	for (var i = 0; i < list.length; i++) {
		if (!list[i].checked) return;
	}
	
	if (!COMPLETED) {
		COMPLETED = true;
		if (!LOG[NAME]) {
			LOG[NAME] = [];
		}
		LOG[NAME].push(new Date().toString());
		
		localStorage.log = JSON.stringify(LOG);
	}
	
	$("html").style.backgroundColor = "#4e5";
}

function checkIfUsed() {
	var record = "NEVER";
	if (LOG[NAME]) {
		var record = LOG[NAME].pop();
		LOG[NAME].push(record);
	}
	
	$("#container").innerHTML = "THIS CHECKLIST LAST COMPLETED " + record + ".";
}

function clearLog() {
	localStorage.log="{}";
}

function getLog() {
	return LOG;
}

window.$ = function(s) { return document.querySelector(s); };
window.$$ = function(s) { return document.querySelectorAll(s); };
