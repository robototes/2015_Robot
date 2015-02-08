package com.shsrobotics.recyclerush.auto.toteset;

import com.shsrobotics.library.TaskList;

public class ToteSet extends TaskList {
	
	public static void exec() {
		new ToteSet().start();
	}
	
	
	@Override
	public  void runTasks() {
		this.runParallel();
		//begin()
		
		
	}

}
