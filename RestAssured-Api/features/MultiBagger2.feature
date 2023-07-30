Feature: Get Stocs data from Yahoo Finance

  Background: 
    Given I Load Money Control page

  @Invest
  Scenario Outline: Get Stocs data from Money Control
  
    And I get the Company- five days Perfermance "<indices>"  and Loss Days "<LossDays>"
    
    And I get the Company - MarketCap "<Marketcpaital>" and Stock Price less then "<StockPrice>"
    
    And I write the Company- five days Perfermance    

    Examples: 
      | indices       | LossDays |Marketcpaital |StockPrice|
      | All Companies |		3		 	 |100			    |		 100  |
     
