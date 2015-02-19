document.addEventListener("onreadystatechange", function() {
	if (document.readyState !== "complete") return;
	
	
});

window.$ = function(s) { return document.querySelector(s); };
window.$$ = function(s) { return document.querySelectorAll(s); };