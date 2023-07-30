Feature: Get Stocs data from Yahoo Finance

  Background: 
    Given I Load Money Control page

  @Invest
  Scenario Outline: Get Stocs data from Money Control 
    
  
    And I get the Company- five days Perfermance "<indices>"  
    
    And I get the Company - MarketCap "<Marketcpaital>"  
    
    And I write the Company- five days Perfermance
    

    Examples: 
      | indices       | Marketcpaital |
      | All Companies |			5000			|
     
