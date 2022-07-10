package week10assignment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PokemonDao;
import entity.Pokemon;

public class Menu {

	private PokemonDao pokemonDao = new PokemonDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Pokemon",
			"Create Pokemon",
			"Update Pokemon",
			"Delete Pokemon"
			);

	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try { 
			
			if (selection.equals("1")) {
				displayPokemon();
			} else if (selection.equals("2")) {
				createPokemon();
			} else if (selection.equals("3")) {
				updatePokemon();
			} else if (selection.equals("4")) {
				deletePokemon();
			} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press enter to continue...");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an Option:\n---------------------------------------");
		for (int i=0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayPokemon() throws SQLException {
		System.out.println("Enter Pokemon Id: ");
		int id = Integer.parseInt(scanner.nextLine());
		Pokemon pokemon = pokemonDao.getPokemonById(id);
		System.out.println("\tPokemon ID: " + pokemon.getId() + " Name: " + pokemon.getName() + " Type: " + pokemon.getType());
	}
	
	
	private void createPokemon() throws SQLException {
		System.out.println("Enter name of new Pokemon: ");
		String name = scanner.nextLine();
		System.out.println("Enter type of new Pokemon: ");
		String type = scanner.nextLine();		
		pokemonDao.createNewPokemon(name, type);
	}
	
	private void updatePokemon() throws SQLException {	
		System.out.println("Enter correct name of Pokemon: ");
		String name = scanner.nextLine();
		System.out.println("Enter correct type of Pokemon: ");
		String type = scanner.nextLine();
		System.out.println("Enter ID of Pokemon: ");
		int id = scanner.nextInt();
		pokemonDao.updatePokemon(name, type, id);
	}
	
	
	private void deletePokemon() throws SQLException {
		System.out.println("Enter Pokemon ID to delete");
		int id = Integer.parseInt(scanner.nextLine());
		pokemonDao.deletePokemonById(id);	
	}
		
}
