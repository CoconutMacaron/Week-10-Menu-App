package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Pokemon;

public class PokemonDao {

	private Connection connection;
	private PokemonDao pokemonDao;
	private final String GET_POKEMON_BY_ID_QUERY = "SELECT * FROM pokemon WHERE id = ?";
	private final String CREATE_NEW_POKEMON_QUERY = "INSERT INTO pokemon(name, type) VALUES(?, ?)";
	private final String UPDATE_POKEMON_QUERY = "UPDATE pokemon SET name = ?, type = ? WHERE id = ?";
	private final String DELETE_POKEMON_BY_ID_QUERY = "DELETE FROM pokemon WHERE id = ?";
	
	public PokemonDao() {
		connection = DBConnection.getConnection();		
	}
	
	public Pokemon getPokemonById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_POKEMON_BY_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return showPokemon(rs.getInt(1), rs.getString(2), rs.getString(3));
	}
	
	public void createNewPokemon(String name, String type) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_POKEMON_QUERY);
		ps.setString(1, name);
		ps.setString(2, type);
		ps.executeUpdate();		
	}
	
	public void updatePokemon(String name, String type, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_POKEMON_QUERY);		
		ps.setString(1, name);
		ps.setString(2, type);
		ps.setInt(3, id);
		ps.executeUpdate();
	}
	
	public void deletePokemonById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_POKEMON_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	private Pokemon showPokemon(int id, String name, String type) throws SQLException {
		return new Pokemon(id, name, type);
	}
}
