Description: A graphical game studio for quickly creating a text-based adventure game with decisions at each scene. This is done through a GUI consisting of boxes and arrows between them to represent scenes and player decisions.

(PRIORITY 1) When a user starts the program, they are shown an empty dark canvas with a button that has one option: create slide. When the ‘create slide’ button is clicked, the user drops a box on the canvas somewhere.  They can drop multiple boxes and connect them with decisions.

(PRIORITY 1) A user can click a slide box to bring up a text box in which the dialog/prompt for the slide is entered.

(PRIORITY 1) The user can click a decision arrow to bring up a text box in which the decision text is entered.

There are OUTPUT anchor points on the right side of each scene box, 1 more than the current number of decisions leading FROM that scene.

There are INPUT anchor points for each box, 1 more than the current number of decisions leading INTO that scene.

The user can create decision arrows by clicking 'add decision' on the slide box. When the user drops the arrow, that arrow is coloured red when unconnected to a scene box. The arrow is colored yellow if there is no decision text yet. The arrow is coloured white when connected and has decision text.

(PRIORITY 1) The user is given an option to “export” to an RDF string via the 'Save' button.

(PRIORITY 3) The user is given options to “export” their game to a .jar file.
