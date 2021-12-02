# iMaChef
Repo for team: D MART

Members: Dylan Tran, Marcus Lopez, Roderick Simms, Thomas Herron


# Description
iMaChef is a virtual Cookbook manager and step-by-step recipe assistant, featuring auto-play 
step progression, built-in timers, and per-step media displays.


# Usage
Import the repository into your Java IDE, compile, and run. (See Requirements and Compatibility for issues at this 
step)

For seeing all the app features, we recommend the recipe "(iMaChef Demo Recipe) Herron's 3 Bean Chili".

To run the recipe helper, select the recipe from the list and click the "Let's Cook!" button. The Prep view will
give you a list of tools and ingredients you need. The checkboxes are there for your own use, and are optional
(they do not need to be checked to continue). You can use either the "Let's Cook!" or "Auto" buttons to continue.
Auto will enable auto-play timers, which will proceed through the recipe steps automatically. Auto may be enabled 
and disabled at any time by clicking the Auto button in the corner. When Auto is green, auto-play is enabled. You 
may move forward and back between steps manually without disabling auto-play.

To create a new recipe, click the "Add a NEW Recipe" button on the Main menu. The first screen will allow you to 
name your recipe and add the tools and ingredients needed. Use the choice box to select the units of measurement 
for each ingredient entry. When ready, continue to the next page to add your steps. Fill each field for your step 
and click the "Add step" button to add it to the recipe. You can also click the list on the left before adding a 
step to be given the option to insert before, after, or replace a step, or to add it to the end of the list as 
usual. The recipe won't be saved until you click the "Done" button, and returning to the Main menu will reset 
your entry. If you don't have an image or video for the step you're adding, we recommend using the image 
"default.png" provided. Simply choose "image" from the choice box, click "Browse" and you will be taken to the 
iMaChef media folder where you may choose from the images provided. You may do the same for videos by selecting 
"video" from the choice box first.

To edit a recipe, select it while on the Main menu and click the "Edit Recipe" button (clicking the Edit button 
without selecting a recipe will have the same effect as clicking the "Add a NEW Recipe" button). You may make any 
changes you wish but they will not be saved until you click the "Done" button on the Steps screen.

The current repository contains a sample cookbook.dat file in the "data" folder, and "media" in the media folder. 
If you wish to remove these recipes, simply clear the cookbook.dat file in a text editor (or replace with an empty 
file of the same name).

Media stored in the media folder will be addressed with relative file paths when selected in the recipe editor. 
Linking to media elsewhere on your computer will use absolute file paths, making your recipe non-sharable without 
editing.

If you wish to undo a change to your cookbook, close the app and replace the cookbook.dat file with the 
cookbook.dat.bak file to undo the last save.


# Known bugs
The TextFlow in the Cook view sometimes cuts off recipe step descriptions. At this time we're 
unsure how to fix this, as there do not appear to be any issues with the code for replacing 
the text. We believe this may be a JavaFX issue.

The video player in the Cook view does not always resize properly to fit the area provided. At 
this time we're unsure how to fix this, as there do not appear to be any issues with the code 
to resize it.

There is a known issue where the cook view will not always display a step's image. We believe 
this might be a format/compatibility issue with JavaFX, as this only seems to occur for a 
specific and limited number of images.

Possibly fixed: Console may occasionally display errors related to cancelled or defunct timers. 
This error does not appear to affect app function at this time, but if you see a timer not working, 
try switching steps and coming back.


# Requirements and Compatibility
This app was coded with Java 9 and Scenebuilder 2.0. We recommend compiling with Java 9. If there 
are compiler/IDE issues after import, confirm your clone has the correct build path and compiler 
settings, and libraries in the ClassPath. (We've had issues with these resetting periodically 
after pulls)


# Copyright and Legal disclaimer
This app is a project in an educational environment. It is not being shared, offered, sold 
for profit, or provided for any purpose other than grading and evaluation.

Do not copy or distribute this project for any purpose other than official grading.

The students responsible for this app attempted to source uncopyrighted or creative commons 
material wherever possible, however some media (including but not necessarily limited to video 
clips) comes from copyrighted sources. We do not attempt to claim any rights or ownership to 
outside photographs or instructional video clips included in this project, and they are only being 
provided as examples of possible material the app might host. Please do not redistribute the 
example media contained in this project.