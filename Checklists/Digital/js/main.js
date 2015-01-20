document.onreadystatechange = function() {
	if (document.readyState !== "complete") return;
	
	var list = $("CHECKLIST").innerHTML.split("\n");
	
	$("CHECKLIST").remove();
	
	var title = list[2];
	$("title").innerHTML = title;
	var h = document.createElement("h1");
	h.innerHTML = title.toUpperCase();
	
	var ul = document.createElement("div");
	
	for (var i = 4; i < list.length - 2; i++) {
		var c = document.createElement("input");
		var l = document.createElement("div");
		var value = list[i].slice(2);
		c.type = "checkbox";
		c.value = value;
		c.id = i.toString();
		l.appendChild(c);
		l.innerHTML += value;
		ul.appendChild(l);
	}
	

	document.body.appendChild(h);
	document.body.appendChild(ul);
}

window.$ = function(s) { return document.querySelector(s); };