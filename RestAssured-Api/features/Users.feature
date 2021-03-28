Feature: Get Users
 
@users
  Scenario Outline: verify successfully fetched the Users and aseerted the details
  
   Given I prepare user Payload  
   Then I check the response in the api call is success code <responsestatuscode>
   And I check "<keyname>" in response body is "<value>" on record entry index- <indexvalue>
   
   Examples:
   
   |keyname 	|value 			| indexvalue	|	responsestatuscode|
   |username	|Bret			|0				|				   200|
   |name		|Leanne Graham  |0				|				   200|