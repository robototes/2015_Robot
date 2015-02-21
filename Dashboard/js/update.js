/**
 * Get and set numbers from NetworkTable
 */
function get() {
    /*
     * AUTONOMOUS MODE
     */
    var autoMode = Autonomous.ROBOT_SET;
    if ($("#ROBOT_SET").checked)
	autoMode = Autonomous.ROBOT_SET;
    if ($("#TOTE_SET").checked)
	autoMode = Autonomous.TOTE_SET;
    if ($("#CENTER_RC_SET").checked)
	autoMode = Autonomous.CENTER_RC_SET;
    N.set(Dashboard.AUTO_MODE, autoMode);

    /*
     * SYSTEM STATUS
     */
    N.set(Dashboard.DISABLE_ELEVATOR, Systems.elevator.disabled);
    N.set(Dashboard.DISABLE_GRIPPER, Systems.gripper.disabled);
    N.set(Dashboard.DISABLE_ROLLERS, Systems.rollerIntake.disabled);

    Systems.driveBase.voltage = N.get(Dashboard.PDP_VOLT, 0.0);
    Systems.driveBase.current = N.get(Dashboard.PDP_CURR, 0.0);
    Systems.driveBase.power = N.get(Dashboard.PDP_POWER, 0.0);
    Systems.driveBase.temp = N.get(Dashboard.PDP_TEMP, 0.0);
    Systems.elevator.current = N.get(Dashboard.ELEV_CURR, 0.0);
    Systems.gripper.current = N.get(Dashboard.GRIPPER_CURR, 0.0);
    Systems.elevator.position = N.get(Dashboard.ELEVATOR_POSITION, 0.0);

    /*
     * STACK UPDATE
     */
    var oldCount = Stack.totes;
    var oldRC = Stack.hasRC;
    Stack.totes = N.get(Dashboard.TOTE_COUNT, 0.0);
    Stack.hasRC = N.get(Dashboard.HAS_RC, false);
    if (oldRC !== Stack.hasRC) {
	if (Stack.hasRC) {
	    Stack.addRC = true;
	} else { // just released
	    Stack.clear = true;
	}
    }
    if (oldCount !== Stack.totes) {
	if (Stack.totes === 0) { // just released
	    Stack.clear = true;
	} else {
	    Stack.addTote = Stack.totes - oldCount;
	}
    }
}

/**
 * Update the display
 */
function update() {
    var MIN_VOLTAGE = 11.0;
    var MAX_VOLTAGE = 13;
    var MAX_CURRENT = 160;
    var MAX_POWER = 1400;
    var MAX_TEMP = 80;
    var MAX_E_CURRENT = 32;
    var MAX_G_CURRENT = 36;

    /*
     * SYSTEM STATUS
     */
    var elevPosition = Systems.elevator.position.toFixed(1);

    var ON = "#2d4", OFF = "#d42";
    $("#status_BASE").style.backgroundColor = Systems.driveBase.voltage < MIN_VOLTAGE ? OFF : ON;
    $("#status_CLAW").style.backgroundColor = Systems.claw.disabled ? OFF : ON;
    $("#status_ELEVATOR").style.backgroundColor = Systems.elevator.current > MAX_E_CURRENT || Systems.elevator.disabled ? OFF : ON;
    $("#status_GRIPPER").style.backgroundColor = Systems.gripper.current > MAX_G_CURRENT || Systems.gripper.disabled ? OFF : ON;
    $("#status_ROLLERS").style.backgroundColor = Systems.rollerIntake.disabled ? OFF : ON;
    $("#status_BASE").innerHTML = Systems.driveBase.disabled ? "&times;" : "";
    $("#status_CLAW").innerHTML = Systems.claw.disabled ? "&times;" : "";
    $("#status_ELEVATOR").innerHTML = Systems.elevator.disabled ? "&times;" : elevPosition;
    $("#status_GRIPPER").innerHTML = Systems.gripper.disabled ? "&times;" : "";
    $("#status_ROLLERS").innerHTML = Systems.rollerIntake.disabled ? "&times;" : "";

    $("#voltage").style.width = (Systems.driveBase.voltage / MAX_VOLTAGE) * 80 + 20 + "%";
    $("#voltage").innerHTML = "Voltage &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.voltage.toFixed(1) + " V";
    $("#current").style.width = (Systems.driveBase.voltage / MAX_CURRENT) * 80 + 20 + "%";
    $("#current").innerHTML = "Current &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.current.toFixed(0) + " A";
    $("#power").style.width = (Systems.driveBase.power / MAX_POWER) * 80 + 20 + "%";
    $("#power").innerHTML = "Power &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.power.toFixed(0) + " W";
    $("#temp").style.width = (Systems.driveBase.temp / MAX_TEMP) * 80 + 20 + "%";
    $("#temp").innerHTML = "Temperature &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.temp.toFixed(1) + " &deg;F";

    /*
     * STACK
     */
    if (Stack.clear) {
	$(".stack").innerHTML = null;
	Stack.clear = false;
    }
    if (Stack.addRC) {
	var el = document.createElement("img");
	el.src = "assets/rc.png";
	el.className = "obj";
	$(".stack").appendChild(el);
	Stack.addRC = false;
    }
    if (Stack.addTote > 0) {
	for (var i = 0; i < Stack.addTote; i++) {
	    var el = document.createElement("img");
	    el.src = "assets/tote.png";
	    el.className = "obj";
	    $(".stack").appendChild(el);
	}
	Stack.addTote = 0;
    }

    $("#status_GRIPPER").style.bottom = Systems.elevator.position * 75 + 24 + "px";
    $("#gripper").style.bottom = Systems.elevator.position * 75 + 56 + "px";
    $(".stack").style.bottom = Systems.elevator.position * 75 + 20 + "px";
    
    $("#build").innerHTML = "Running Y-OS version + " + N.get(Dashboard.SOFTWARE_VERSION, 1).toFixed(1) + "." + BUILD_NUMBER;
    
    // call again
    REQ_ANIM_FRAME_ID = requestAnimationFrame(update);
}