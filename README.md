# Description
Fully customizeable, interactive and draggable Desktop Pet sitting on your taskbar to keep you company :)

# Plug and Play
Create your very own Desktop Pet without the need to ever touch code. The Engine is designed in a very generic way that allows you to copy paste your animation frames and after short configuration you are all set.

# How to run
1. Clone the repository and build the jar with `gradlew jar` or download the prebuilt jar from the release tab
2. Place the built jar in the root of the repository
3. Run the jar file using `javaw -jar <jar file>` (if u want to have a console for debugging purposes use `java -jar` instead)

# Custom animations
The Animation engine works with png files which are structured into folders and ordered frames. Please have a look at `ressources/` to get to know the structure. To make your custom animations work you will have to edit config.json.
The description of the state also depicts the name of the folder containing your animation frames which reside in a folder named same as the state category. the transitions are used for the finite state machine to switch between the different states.

For renaming the frames i added a little python script `rename.py`. Just put it in your folder with your animation frames and run it using `python3 rename.py`.

The mouseclick category is a special category that must only consist of exactly one state that is switched to whenever you click your Desktop Pet.

Be sure to also change the window size in the config.json to fit your frames (otherwise the window will not show)

# Example:
![image](https://github.com/PhilippVn/DesktopPet/assets/54031045/e1523084-ff92-466a-a4c7-446e2632ce03)
