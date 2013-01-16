package com.duobot

import java.util.concurrent.Callable
import java.util.concurrent.CompletionService
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

import org.junit.Test

class ExecutorCompletionServiceTest {
	@Test void main(){
		ExecutorService exec = Executors.newFixedThreadPool(15)
		CompletionService<String> serv =
				new ExecutorCompletionService<String>(exec);
		println "Start 5 threads to do works."
		(0..5).each{
			def i = it
			def callback = {
				println i
				Thread.sleep((long)(10000* Math.random()))
				return "contact ${i}"
			} 
			serv.submit(callback as Callable)
			
		}

		println "Start to display contents."

		(0..5).each {
			Future<String> task = serv.take()
			println " result is "+task.get()
		}

		println "End"

		exec.shutdown()
	}
}
