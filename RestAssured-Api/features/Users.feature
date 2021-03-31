Feature: Get Users- comments and Post
 
@API
  Scenario Outline: verify successfully fetched the Users, comments and Post- asserted the details
  
   Given I prepare "<httpmethod>" user Payload for "<queryparam>"  
   Then I check the response in the api call is success code <responsestatuscode>
   And I check "<keyname>" in response body is "<value>" on record entry index- <indexvalue>
   
   Examples:
   
   |httpmethod|queryparam	|keyname 	|value 					| indexvalue	|	responsestatuscode|
   |	get	  |  /users	 	|username	|Bret					|0				|				   200|
   |    get   |	 /users	 	|name		|Leanne Graham  		|0				|				   200|
   |    get   |	 /comments	|Email		|Nikita@garfield.biz    |3				|				   200|
   |    get   |	 /posts 	|id		    |2					    |1				|				   200|   