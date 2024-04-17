@BookLab
Feature: Apollo247 Services

  Background: 
    Given User is on the Apollo247 home page

  @AddToCart
  Scenario: Add COVID-19 RT-PCR Test to Cart
    When User navigates to the COVID-19 RT-PCR test page
    And User clicks on the "Add to Cart" option
    And User clicks on "Proceed to Cart"
    Then User should see the test added to the cart

  @Search
  Scenario Outline: Search for specific Vaccine
    When User navigates to the COVID-19 RT-PCR test page
    And User clicks on the search bar
    And User enters <search_query> into the search bar
    And User clicks on the search button
    Then User should be directed to the relevant search results page

    Examples: 
      | search_query                              |
      | Thyroid Profile (Total T3, Total T4, TSH) |

  @Location
  Scenario: Enter Location for COVID-19 RT-PCR Test
    When User navigates to the COVID-19 RT-PCR test page
    And User clicks on the location bar 
    And User enters the Location in the location field
      | Indore |
    And User selects the suggested location
    Then User should see the selected location displayed in the location field 

  @SortedBy
  Scenario: Sort Full Body Checkup Tests by Price from Low to High
    When User navigates to the lab-tests page
    And User clicks on the Full Body Checkup option
    And User clicks on the "Sort By" drop-down menu
    And User selects "Price Low to High"
    Then User should see the Full Body Checkup tests sorted by price from low to high

  @Doctor
  Scenario: View RECOMMENDED BY Doctor Information
    When User navigates to the COVID-19 RT-PCR test page
    And the User clicks on a doctor's name
    Then the User should be able to navigate to the particular doctor's information page
