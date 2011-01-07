package fmi.android.game.backgammon;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * This is the main entry point for the Backgammon application This activity is
 * responsible for :
 * <ul>
 * <li>Showing the main game menu</li>
 * <li>Creating all needed classes for the client (the board) and the server
 * (depending on the game type)</li>
 * </ul>
 * When clicking on the Start Game button, the user is prompted to choose a game
 * type
 * <ul>
 * <li>Single phone - two users share the same phone in order to play</li>
 * <li>Bluetooth - The two users play with separate phones via bluetooth</li>
 * <li>AdHoc WiFi - The two users play with separate phones via the WiFi AdHoc
 * functionality (android version >= 2.2)</li>
 * </ul>
 * Server classes are created after the user has chosen the game type
 * 
 */
public class MainActivity extends Activity {

	// ----------------------------------------
	// CLASS CONSTANTS
	// ----------------------------------------

	/**
	 * this is the unique identifier for the game type chooser dialog the dialog
	 * is prompted to the user when he hits the start button
	 */
	private static final int GAME_TYPE_DIALOG = -1;

	// constants for the different game types
	private static final int SINGLE_PHONE = 0;
	private static final int BLUETOOTH = 1;
	private static final int ADHOC_WIFI = 2;

	// for debugging purposes, used in Log.d(TAG, msg) calls
	public static final String TAG = MainActivity.class.getSimpleName();

	// ----------------------------------------
	// OVERRIDEN METHODS
	// ----------------------------------------
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// UI components are in res/layout/main.xml
		setContentView(R.layout.main);

		// for now, handle the click event like that
		// but later will use better ways ;)

		final Button startButton = (Button) findViewById(R.id.start_game_button);
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// navigateToGame();
				showDialog(GAME_TYPE_DIALOG);
			}
		});

		final Button optionsButton = (Button) findViewById(R.id.options_button);
		optionsButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				navigateToOptions();
			}
		});
	}

	/**
	 * {@inheritDoc}
	 * This method is called after showDialog(GAME_TYPE_DIALOG) is called in the
	 * click handler for the start button
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;

		// according to the android dev guide,
		// using switch for dialogs is best practice
		switch (id) {
		case GAME_TYPE_DIALOG:
			// fetch the list of game_types in string.xml as an array ;)
			final String[] game_types = getResources().getStringArray(
					R.array.game_types);

			// check http://developer.android.com/guide/topics/ui/dialogs.html
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(
					getResources().getString(R.string.game_types_dialog_title))
					.setItems(game_types,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// which is array index
									createGame(which);
								}
							});
			dialog = builder.create();
			break;
		}

		return dialog;
	}

	// ----------------------------------------
	// PRIVATE METHODS
	// ----------------------------------------

	/**
	 * @private This method is called after the user has chosen the game type
	 *          It's responsible for creating everything needed, depending on
	 *          the game type
	 * @param gameType
	 *            - this is integer constant, showing the game type 0 is for
	 *            Single phone 1 is for Bluetooth 2 is for AdhocWifi
	 */
	private void createGame(int gameType) {
		// todo : implement the real logic
		switch (gameType) {
		case SINGLE_PHONE:
			Toast.makeText(getApplicationContext(), "Single Phone",
					Toast.LENGTH_SHORT).show();
			break;
		case BLUETOOTH:
			Toast.makeText(getApplicationContext(), "Bluetooth",
					Toast.LENGTH_SHORT).show();
			break;
		case ADHOC_WIFI:
			Toast.makeText(getApplicationContext(), "WifiAdhoc",
					Toast.LENGTH_SHORT).show();
			break;
		default:
			// unknown gametype, error must occur here
			break;
		}
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
