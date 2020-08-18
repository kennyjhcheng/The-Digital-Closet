## UBC CPSC 210 Project Notes

####Project Ideas
**Fashion clothing manager** -> creates sets, add pieces of clothing
* sorted by shirts, pants, accessories, shoes, hats, etc.
    * user able to create smaller categories
        * similar to file directory*
* view/insert pictures of clothing
* clothes that need to be washed -> highlighted in red or removed from options
* track what you wear each day
* login information
* sort clothes by location (drawer, closet, hanger, etc.) or style (t shit, pants, acessories, etc.), occasion (event, casual, formal, etc.) -> basically sort by tags

**Chinese Medicine Booking Application** -> books patients
* records patient information
* updatable local database for patient diagnostic and data
* patient searchable in application
* suggestions for common herbs or medicines to use for diagnoses
* Ingredient supply manager -> view how much has been sold etc.

#### User Stories
* As a user, I want to be able to add an item of clothing to my digital closet
* As a user, I want to be able to classify clothing  with tags
* As a user, I want to be able to view and sort clothing according to their tags
* As a user, I want to be able to create sets of clothing to design outfits
* As a user, I want to be able to track my daily outfits
* As a user, I want to be able to sort outfits by sets with all clean clothes, and sets with clothes that need washing
* As a user, I want to be able to login and logout to save and protect my information

#### Project Questions
* Ask arthur how to implement exception hierarchy with messages and printing specific message depending on which exception thrown
* Where to implement observer and observable (model or gui) for adding/remove/editing clothing as observable, and Jlists/Models as observer
* is it okay to use interfaces to ensure that certain methods are implemented (instead of using for override, etc.)

#### Resources
* https://howtodoinjava.com/library/json-simple-read-write-json-examples/
* https://mkyong.com/java/how-to-convert-java-object-to-from-json-jackson/

### project todo
additional userstory/features
* As a user, I want to be able to favorite outfits and view only my favorites
* As a user, I want to be able to sort outfits by sets with all clean clothes, and sets with clothes that need washing
* As a user, I want to be able to login and logout to save and protect my information (implemented in phase 2)
* As a user, I want to be able to track my daily outfits

make "comic sans ms" single point of control

addclothingbuttonlistener test what happens if type is null or other fields are null

remove trailing spaces in username password

adding/removing/editing viewPanel can be made into class where method is stored

iterating over clothing collection