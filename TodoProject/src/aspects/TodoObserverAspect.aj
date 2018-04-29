package aspects;

import java.util.List;
import java.util.ArrayList;

import controller.TodoController;
import ui.Console;

public aspect TodoObserverAspect {
	declare parents : TodoController implements Subject;
	declare parents : Console implements Observer;
	
	private List<Observer> Subject.observers=new ArrayList<Observer>();;
	
	public void Subject.addObserver(Observer obs){
		System.out.println("Adding observer");
		observers.add(obs);
	}
	
	public void Subject.removeObserver(Observer obs){
		System.out.println("Removing observer");
		observers.remove(obs);
		
	}
	
	public void Subject.notifyObservers(Object o){
		for(Observer ob:observers)
			ob.update(o);
	}
	
	pointcut observed(TodoController ctrl):execution(@annotations.Notify * *(..)) && this(ctrl);
	
	//pointcut observed(TodoController ctrl):execution(@Notify * controller.TodoController.U*(..)) && this(ctrl);
	
	TodoController ctrl;
	//adding an observer
	after(TodoController c, Console handler): initialization(ui.Console.new(*))
	&&this(handler)&&args(c){
		c.addObserver(handler);
		ctrl = c;
	}
	
	//observers notification
	after(TodoController c) returning: observed(c){
		System.out.println("Inside ObserverAspect: notifyObservers");
		c.notifyObservers(null);	
	}
	
	
	//observer action
	public void Console.update(Object o){
		System.out.println("Inside ObserverAspect: Console.update ");
		System.out.println("State was updated ");	
	}
	
	//removing an observer	
	after(Console handler):execution(* Console.exit(..))&& this(handler){
		ctrl.removeObserver(handler);
	}
}
