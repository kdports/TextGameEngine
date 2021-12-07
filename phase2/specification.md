Specification
Description
An all-in-one text-based game editor AND player. A user can create a text-based game with the editor and play that same game in the player, with lots of features.

When a user starts the program, they are shown the title screen which prompts them to launch the editor or the game player (or quit). Launching the editor will bring the player to a blank canvas consisting of 5 buttons, add slide, save, load, playtest, and change theme. The save and load are simple, save the current state of the editor and load it back in. A user can add a slide via the “add slide” button and can edit this slide to have as many decisions or as long a dialogue as needed. Decisions point to other slides that are added, and an entire game is able to be made.

Slides have a “set first” option, which will tell the game player which slide is the starting slide. Each decision has an edit menu which contains the dialogue, the conditionals, as well as an item box. The item box allows the user to choose which item to give when this decision is chosen. The conditionals let the user decide which items or chosen decisions are needed to choose this decision option. Each decision has input and output anchors so the user can drag these and assign the decision to other slides.

The “Save” button saves the editor state to a TTL file using RDF ontological terms. Each value from the editor (the decisions, decision attributes, slides, slide locations etc) is saved into this file so that it can be properly loaded back in at any time.

The “playtest” button calls the gameplayer, which runs the current state of the editor as a game! The gameplayer is also accessed by clicking “Launch Player” from the main start screen. Themes can be changed and the speed at which the dialogue shows up can be changed in the settings. In the player, the user can load a TTL file that was saved from the editor. At the top is the dialogue of the slide and the bottom contains the decision dialogues. When a user clicks one of the decision dialogues, the outgoing slide attached to that decision is now displayed. There is also an inventory at the bottom to display the items collected.

Our specification from phase 0 and 1 has kept the same spirit of the program, but has changed drastically. Things aren’t launched in the same order, slides and decisions work a bit differently, and the player now has its own title screen! We were sure to add our new classes and methods to follow clean architecture and SOLID principles as highlighted in the below document. We’re very proud of how this turned out.
