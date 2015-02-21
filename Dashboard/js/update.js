/**
 * Get and set numbers from NetworkTable
 */
function get() {
    if (!CONNECTED) return;
    
    /*
     * AUTONOMOUS MODE
     */
    if (UPDATE_AUTONOMOUS) {
	var autoMode = Autonomous.ROBOT_SET;
	if ($("#ROBOT_SET").checked)
	    autoMode = Autonomous.ROBOT_SET;
	if ($("#TOTE_SET").checked)
	    autoMode = Autonomous.TOTE_SET;
	if ($("#CENTER_RC_SET").checked)
	    autoMode = Autonomous.CENTER_RC_SET;
	N.set(Dashboard.AUTO_MODE, autoMode);
	UPDATE_AUTONOMOUS = false;
    }

    /*
     * SYSTEM STATUS
     */
    if (UPDATE_SYSTEM_DISABLE) {
	N.set(Dashboard.DISABLE_ELEVATOR, Systems.elevator.disabled);
	N.set(Dashboard.DISABLE_GRIPPER, Systems.gripper.disabled);
	N.set(Dashboard.DISABLE_ROLLERS, Systems.rollerIntake.disabled);
	N.set(Dashboard.DISABLE_CLAW, Systems.claw.disabled);
	UPDATE_SYSTEM_DISABLE = false;
    }

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

    $("#voltage").style.width = (Systems.driveBase.voltage / MAX_VOLTAGE) * 75 + 25 + "%";
    $("#voltage").innerHTML = "Voltage &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.voltage.toFixed(1) + " V";
    $("#voltage").style.backgroundColor = Systems.driveBase.voltage < MIN_VOLTAGE ? "#d74" : "#ccc";
    $("#current").style.width = (Systems.driveBase.current / MAX_CURRENT) * 65 + 25 + "%";
    $("#current").innerHTML = "Current &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.current.toFixed(0) + " A";
    $("#current").style.backgroundColor = Systems.driveBase.current > MAX_CURRENT ? "#d74" : "#ccc";
    $("#power").style.width = (Systems.driveBase.power / MAX_POWER) * 65 + 25 + "%";
    $("#power").innerHTML = "Power &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.power.toFixed(0) + " W";
    $("#power").style.backgroundColor = Systems.driveBase.power > MAX_POWER ? "#d74" : "#ccc";
    $("#temp").style.width = (Systems.driveBase.temp / MAX_TEMP) * 65 + 25 + "%";
    $("#temp").innerHTML = "Temperature &nbsp;&nbsp;&nbsp;&nbsp;" + Systems.driveBase.temp.toFixed(1) + " &deg;F";
    $("#temp").style.backgroundColor = Systems.driveBase.temp > MAX_TEMP ? "#d74" : "#ccc";

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
    
    /*
     * ROBOT WARNING SYSTEM
    */
    if (!CONNECTED) return;
    
    
    // call again
    REQ_ANIM_FRAME_ID = requestAnimationFrame(update);
}

/**
 * one-time operations
 */ 
function init() {
    $("#build").innerHTML = "Running Y-OS version " + N.get(Dashboard.SOFTWARE_VERSION, 1).toFixed(1) + "." + BUILD_NUMBER;
}

/**
 * Testing
 */
function test() {
    N.set(Dashboard.PDP_CURR, 80);
    N.set(Dashboard.PDP_POWER, 960);
    N.set(Dashboard.PDP_TEMP, 78.7);
    N.set(Dashboard.PDP_VOLT, 12.7);
    N.set(Dashboard.ELEVATOR_POSITION, 1.5);
    N.set(Dashboard.ELEV_CURR, 16);
    N.set(Dashboard.GRIPPER_CURR, 24);
    N.set(Dashboard.HAS_RC, false);
    N.set(Dashboard.TOTE_COUNT, 3);
    N.set(Dashboard.SOFTWARE_VERSION, 2.5);
    UPDATE_AUTONOMOUS = true;
    UPDATE_SYSTEM_DISABLE = true;
}