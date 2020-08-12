## *The Digital Closet* Personal Project
### UBC CPSC 210 Term Project
###### Felix Grund (professor) and Arthur Marques (TA)

My term project is called *The Digital Closet*.   

Have you ever wanted to visually create outfits using your own clothes in your closet? Do you 
wake up in the morning scrambling to find the perfect outfit amongst the cesspool you call a closet?<br>

> What will the application do?<br>

Look no further because *The Digital Closet* is the perfect app for you! Recreate your closet simply by
entering some information about each piece of clothing! Give each piece of clothing tags so that you
can easily find them within the app. Create and save your favorite oufits to remove the morning stressors
which will keep you looking beautiful and fashionable. Finally, track what you wore each day and favorite
your best sets.

> Who will use it?<br>

You might be wondering, is this the right application for you? ***OF COURSE IT IS***!!! Teenagers and young 
adults can use this app to constantly look stylish without the daily hassle of **thinking**. All the adults
going through **mid-life crises** can look stylish while contemplating your self-identity and confidence. Even
you seniors can make use of this app. It's never too late to look good. From babies to the casket, this app is
for ***ANYONE***.

> Why is this project of interest to you?<br>

This project was inspired by my laziness and how much I pointlessly stare at things on my phone. Whenever, I procrastinate
on responsibilities such as washing or folding my clothes, I end up procrastinating by playing with a trending app
or watching a YouTube video. I thought, what if I could organize my clothes with my phone! It would be
much more interesting, fun, and it might motivate me to organize my actual clothes after playing around
with the app especially if the app requires interaction and observations of the clothing items. Many others own 
multiple times the amount of clothes and accessories that I own. I can only imagine how hard it is for them to 
build the self-motivation. This app is for all the people who procrastinate on folding clothes but can also
be used by anyone who simply wants to look good without the stress!

---

### User Stories
* As a user, I want to be able to add an item of clothing to my digital closet
* As a user, I want to be able to classify clothing by Type 
* As a user, I want to be able to view and sort clothing according to their tags
* As a user, I want to be able to create sets of clothing to design outfits
* As a user, I want to be able to edit my outfits and clothes
* As a user, I want to be able to login and register an account in which my closet is saved
* as a user, I want to be able to load the closet associated with my account when I Login

---

### Setup
>Import everything in the External Libraries folder to your classpath

This project uses:
* jackson-annotations-2.11.1.jar
* jackson-core-2.11.1.jar
* jackson-databind-2.11.1.jar
* jackson-datatype-jsr310-2.11.1.jar

---

## Phase 3
### Instructions for Grader
* To run the GUI, please run the ***Main*** class in the gui package
* You can generate the ***first*** required event by inputting a username and password in the respective text fields
and press the Register button
    * You may register any number of accounts, which will be saved in the UserInfo.json file in the data directory
    * You must login by inputting a **registered** account and clicking the login button to use the application 
* You can generate the ***second*** required event by navigating to the Closet tab and into the Add Clothing Form
    * Fill out the form according to the instructions and click the Add Clothing button
    * You can add as many items of Clothing as you wish, which can be viewed in the View Clothing section
* You can trigger one of the 3 audio components by:
    * starting the application will play soothing startup music
    * closing a frame by clicking the top-right "X" or clicking the Save and Quit button will play a shut-down sound
    * clicking any button will play a button click (in the panels containing implementations)
* You can save the state of the application by navigating to the ***Save and Quit*** tab and clicking the Save and Quit Button
    * you may quit from the Main Menu or login again/to a different account
    * data is stored in the 2 user-specific files labelled *username*Closet.json and *username*StyleBoard.json
    * for the purpose of this phase of the project, there will only be data in the *username*Closet.json file
* You can reload the state of the application by logging in with the user-specific credentials
    * loaded data is user-specific
    
---

## Phase 4
### Phase 4: Task 2
> Test and design a class that is robust.  You must have at least one method that throws a checked exception. 
> You must have one test for the case where the exception is expected and another where the exception is not expected.
* An exception that I designed for my project is the DuplicateClothingException
* A method that throws this exception is the addClothing(Clothing clothing) method in the Closet class of my model 
* An example of when this exception is caught is in gui.tabbedframe.closetpane.AddClothingButtonListener
    * when adding a clothing, if the clothing already exists in the closet, exception is thrown, and a message is
     displayed to the user informing them that this clothing already exists
* This is tested in the test.model.ClosetTests Class in which:
    * testAddClothingMultipleNoException is a test case where no exception is expected
    * testAddClothingExpectDuplicateClothingException is a test case where the exception is expected





