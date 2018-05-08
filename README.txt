Kamil Bartnicki REST Api

GET
/films/all - get list of all films with details.
/films/{name} - get one film with given name

POST
/films/add

Header:
  - Content-Type ›application/json  
Body:
  - name String
  - description  String
  - productionYear  Integer
  
  {
	  "title":"Shrek",
	  "description": "Film about an Ogre",
	  "premiereDate":"2001"
  }