Feature: Get Stocs data from Yahoo Finance
 

  Background:   
   Given I Load Yahoo finance page
   
   
 @Invest  
   Scenario Outline: Get Stocs data from Yahoo Finance   
   And I Search Following Stocks "<stock>"
   And I Search go to the chart "<DayOMonth>"
   And I get the data       
   
   Examples:   
|stock		 |DayOMonth|
|21STCENMGM| Day    |
|3IINFOTECH| Day    |