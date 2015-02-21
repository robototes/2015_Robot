Dashboard = {
    TABLE_NAME: "ROBOT",
//    IP: "RoboRIO-2412.local",
    IP: "localhost",
    AUTO_MODE: "auto-mode",
    PDP_TEMP: "pdp-temp",
    PDP_VOLT: "pdp-volt",
    PDP_CURR: "pdp-curr",
    PDP_POWER: "pdp-power",
    ELEV_CURR: "elev-curr",
    GRIPPER_CURR: "gripper-curr",
    TOTE_COUNT: "toteCount",
    HAS_RC: "hasRC",
    X_POSITION: "x-position",
    Y_POSITION: "y-position",
    HEADING: "heading",
    DISABLE_GRIPPER: "disable-gripper",
    DISABLE_ROLLERS: "disable-rollers",
    DISABLE_ELEVATOR: "disable-elevator",
    ELEVATOR_POSITION: "elev-pos",
    SOFTWARE_VERSION: "version"
};

Autonomous = {
    ROBOT_SET: 0,
    TOTE_SET: 1,
    STACK_SET: 2,
    CENTER_RC_SET: 3
};

Systems = {
    driveBase: {
	current: 0,
	voltage: 0,
	power: 0,
	temp: 0,
	disabled: false
    },
    gripper: {
	current: 0,
	disabled: false
    },
    elevator: {
	current: 0,
	position: 0,
	disabled: false
    },
    rollerIntake: {
	disabled: false
    },
    claw: {
	disabled: false
    }
};
Stack = {
    totes: 0,
    hasRC: false,
    addRC: false,
    addTote: 0,
    clear: false
};

REQ_ANIM_FRAME_ID = -1;
GET_VALUES_ID = -1;
BUILD_NUMBER = ~~(Math.random() * 1000);

document.addEventListener("readystatechange", function() {
    if (document.readyState !== "complete")
	return;

    N = new NetworkTable(Dashboard.TABLE_NAME, Dashboard.IP);

    // try to connect until connected
    var ct = 0;
    var id = setInterval(function() {
	if (N.isConnected() || ++ct > 8) {
	    clearInterval(id);
	    GET_VALUES_ID = setInterval(get, 100);
	} else {
	    N.connect();
	}
    }, 250);
    REQ_ANIM_FRAME_ID = requestAnimationFrame(update);

    var list = $$("form#autonomousSelection > div");
    for (var i = 0; i < list.length; i++) {
	list[i].querySelector("input").onclick = function (e) {
	    e.stopPropagation();
	};
	list[i].onclick = function () {
	    this.querySelector("input").checked = true;
	};
    }
    
    list = $$(".statusLight");
    for (var i = 0; i < list.length; i++) {
	list[i].onclick = function () {
	    switch (this.id) {
		case "status_ROLLERS":
		    Systems.rollerIntake.disabled = !Systems.rollerIntake.disabled;
		    break;
		case "status_GRIPPER":
		    Systems.gripper.disabled = !Systems.gripper.disabled;
		    break;
		case "status_ELEVATOR":
		    Systems.elevator.disabled = !Systems.elevator.disabled;
		    break;
	    }
	}
    }
});

window.$ = function (s) {
    return document.querySelector(s);
};
window.$$ = function (s) {
    return document.querySelectorAll(s);
};