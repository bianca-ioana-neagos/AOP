package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import controller.TodoController;
import model.Todo;
import repo.Repository;
import repo.TodoRepo;
import ui.Console;


public class Main {

	public static void main(String[] args) throws SAXException, ParserConfigurationException, TransformerException, IOException {
		String url = "jdbc:mysql://localhost:3306/Todo";
		String username = "root";
		String password = "";
		
		Repository<Integer, Todo> repo = new TodoRepo(url,username, password);
               
        TodoController ctrl = new TodoController(repo);
        
        Console console = new Console(ctrl);
        console.run();

	}

}
