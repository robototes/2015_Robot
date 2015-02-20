document.onreadystatechange = function() {
	if (document.readyState !== "complete") return;
	
	if (document.body.className === "red") return; // return if we're in launcher.html
	
	var list = $("CHECKLIST").innerHTML.split("\n");
	
	$("CHECKLIST").remove();
	
	var title = list[2];
	$("title").innerHTML = title;
	var h = document.createElement("h1");
	h.innerHTML = title.toUpperCase();
	
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
	document.body.style.backgroundColor = "#fcfcfc";
	
	var list = $$("input");
	for (var i = 0; i < list.length; i++) {
		if (!list[i].checked) return;
	}
	
	document.body.style.backgroundColor = "#4e5";
}

window.$ = function(s) { return document.querySelector(s); };
window.$$ = function(s) { return document.querySelectorAll(s); };