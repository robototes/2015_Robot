chrome.app.runtime.onLaunched.addListener(function () {
    chrome.system.display.getInfo(function (screens) {
	var screen;
	for (var i = 0; i < screens.length; i++) {
	    if (screens[i].isPrimary) {
		screen = screens[i];
		break;
	    }
	}

	chrome.app.window.create('index.html', {
	    "outerBounds": {
		"width": screen.workArea.width,
		"height": screen.workArea.height - 200,
		"top": 0,
		"left": 0
	    },
	    "frame": {
		"color": "#2f2f2f"
	    }
	});
    });

});