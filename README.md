# Covid19 India Testathon - Team Jaguars

This project is built as part of the test-a-thon event.

It covers the automated regression testing of Covid-19 application. It tests all the mobile, API and UI tests

It has features as below:

- Pushes test results to Report portal
- Integrated with Jenkins for CI execution

## Scenarios to Automate:

1.	Open the URL https://www.covid19india.org/ 
2.	Select top 3 states based on the Deceased Count.
3.	Validate at least 2 of the details like Confirmed, Active, Recovered, Deceased with the data from API for the three states.
    a.  Make an API call https://api.covid19india.org/data.json and capture the details of the 3 states identified in step number 2.
    b.  Validate the captured details from above response in Web UI
    c.	Validate the captured details from above response in Mobile UI
4.	Validate the district totals
    a.	Make an API call https://api.covid19india.org/state_district_wise.json and capture the details of the top state identified in step number 2.
    b.	Validate the captured details from above response by clicking on the state name followed by clicking on “See More Details” on Web UI and Mobile UI.

