My solution for Dining philosophers problem (Dining kids problem)

The program accepts one parameter: the name of the input file to be loaded.
Input file structure:
[number of kids]
[name1] [hunger-time]
[name2] [hunger-time2]
...

Preschoolers sit in a circle, the last one is next to first one.
Each Child object creates a thread that checks whether the child is too hungry. If it is too hungry - it cries on System.err. Lunch takes 10 seconds. If program runs correctly - no child should ever cry.
