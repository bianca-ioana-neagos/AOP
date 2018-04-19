package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import controller.TodoController;
import model.Todo;


public class Console {
	private TodoController ctrl;

	public Console(TodoController ctrl) {
		this.ctrl = ctrl;
	}
	
	public void printMenu(){
	        System.out.println("1.Add a task");
	        System.out.println("2.Mark as done");
	        System.out.println("3.View task");
	        System.out.println("4.View all tasks");
	        System.out.println("0. EXIT");
	 
	        System.out.println("********************** \n");
	}
	
	public void run() throws SAXException, ParserConfigurationException, TransformerException, IOException{
    	Scanner s= new Scanner(System.in);
        
        	String cmdM = "";
            do {
            	printMenu();
                cmdM = s.nextLine();
                switch (cmdM) {
                	case "1":
                		save();
                        break;
                    case "2":
                        update();
                        break;
                    case "3":
                        findOne();
                        break;    
                    case "4":
                        findAll();
                        break;
                }
            }while(!cmdM.equals("0"));
        exit();
        
    }

	private void exit() {
		System.out.println("System exiting..");
    	System.exit(0);		
	}

	private void findAll() {
	    Set<Todo> movies=ctrl.findAll();
	    movies.stream().forEach(System.out::println);
		
	}

	private void findOne() throws IOException {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String title = bufferRead.readLine();
		Optional<Todo> todo =ctrl.findOne(title);
		System.out.println(todo.toString());	
	}

	private void update() {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        try {
	            System.out.println("title= ");
	            String title = bufferRead.readLine();
	         
	            System.out.println("********************** \n");
	            Todo todo=new Todo(title,true);
	            try{
	                ctrl.update(todo);
	            }
	            catch(Exception e) {
	                System.out.println(e.toString());
	            }
	            bufferRead.readLine();
	        }
	        catch (IOException e){
	            System.out.println("Invalid input");
	        }	
	}

	private void save() {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	        try {
	            System.out.println("title= ");
	            String title = bufferRead.readLine();
	            System.out.println("********************** \n");
	            Todo todo=new Todo(title,false);
	  
	            try{
	                ctrl.save(todo);
	            }
	            catch(Exception e) {
	                System.out.println(e.toString());
	            }
	            bufferRead.readLine();
	        }
	        catch (IOException e){
	            System.out.println("Invalid input");
	        }
		
	}
}
