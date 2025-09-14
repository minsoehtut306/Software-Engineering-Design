/**
 * Entry point of the program
 */

public class Main {

	/**
	 * Initializes renderer and user, then starts the terminal user interface flow
	 * @param args
	 */
	public static void main(String[] args){

		/* 	Specify which viewer to use 
			https://graphstream-project.org/doc/Tutorials/Graph-Visualisation/1.3/
			https://graphstream-project.org/doc/Tutorials/Graph-Visualisation/
		*/
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer"); // GS 1.3
		System.setProperty("org.graphstream.ui", "swing");


		//Begin program menu flow by prompting user for user type
		UserManager.promptUserType();
	}
}