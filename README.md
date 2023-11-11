# Description
Fully customizeable Desktop Pet sitting on your taskbar to keep you company :)

# Custom animations
The Animation engine works with png files which are structured into folders and ordered frames. Please have a look at `ressources/` to get to know the structure. To make your custom animations work you will have to edit config.json.
The description of the state also depicts the name of the folder containing your animation frames which reside in a folder named same as the state category. the transitions are used for the finite state machine to switch between the different states.
You can also change the window size to fit your Desktop Pet.

The mouseclick category is a special category that must only consist of exactly one state that is switched to whenever you click your Desktop Pet.

Be sure to also change the window size in the config.json to fit your frames (otherwise the window will not show)