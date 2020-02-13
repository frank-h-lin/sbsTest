@SBS @WIP
Feature: API Automation

  Scenario: AJAX
    Given I open the url "guide/ajax_radio_program_catchup_data/language/hindi/location/NSW/sublocation/Sydney"
    Then I can verify the api repsonse
    
  Scenario: Check frontend  
   	Given I open the page "language/hindi/program"
#	When I see the "SBS Language | Latest Hindi programs" page is loaded successfully
#	Then I can verify file numbers and dates match the api response
	