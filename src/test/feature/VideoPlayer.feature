@SBS
Feature: Frontend Video Player Automation

  Scenario: Video Player Automation
   	Given I open the page "news/insight/tvepisode/burnout"
	When I click to play video
	#No pre-roll showing up, hence can't skip the ads etc.
	And I pause the video
	Then I repeat the play and stop after 2 mins