package fmi.android.game.backgammon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This is the main entry point for the Backgammon application
 * This activity is reponsible for :
 * <ul>
 * <li> Showing the main game menu </li>
 * <li> Creating all needed classes for the client (the board) and the server (depending on the game type) </li>
 * </ul>
 * When clicking on the Start Game button, the user is prompted to choose a game type
 * <ul>
 * <li> Single phone - two users share the same phone in order to play </li>
 * <li> Bluetooth - The two users play with separate phones via bluetooth </li>
 * <li> AdHoc WiFi - The two users play with separate phones via the WiFi AdHoc functionality (android version >= 2.2) </li> 
 * </ul>
 * Server classes are created after the user has chosen the game type
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// UI components are in res/layout/main.xml
		setContentView(R.layout.main);
		
		// for now, handle the click event like that
		// but later will use better ways ;)
		
		final Button start = (Button) findViewById(R.id.start_game_button);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToGame();	
			}
		});
		
		final Button options = (Button) findViewById(R.id.options_button);
		options.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToOptions();
			}
		});
	}
	
	private void navigateToGame() {
		Intent gameIntent = new Intent(MainActivity.this, Backgammon.class);
		
		startActivity(gameIntent);
	}
	
	private void navigateToOptions() {
		Intent optionsIntent = new Intent(MainActivity.this, Options.class);
		startActivity(optionsIntent);
	}
}
