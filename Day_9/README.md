## Exercise 1: The list of companies
Let’s create our project and add a list of companies.
- Create android application project
- To work with data, add the Retrofit2 library
- Connect HTTP client to the project. Use Dagger2 to implement DI in the project, starting with the HTTP client. See an example in the `codesamples`  directory.
- Add a logger and timeouts to the HTTP client
- Add entities to the model to work with a list of companies
- Add a screen with two tabs: "Companies" and "Vacancies". Add a list of companies to the "Companies" tab. The "Vacancies" tab is empty. List items must represent a name and a field of activity of a company
- Add the <YourAPI> realization to get the data needed for the company list screen. You should get acquainted with a term “Repository” in android to write your model code accurately.
  
**Check the results:**
- When entering the application, there is a screen with two tabs. The first tab "Companies" is opened by default
- The "Companies" tab should display a list of companies received from our server (written in the previous day)

## Exercise 2: Company details
Now we’ll add a company details screen
- Add entities to the model to work with company details
- Create a screen with company details. On the screen, in addition to information about the company, there should be a list of vacancies in this company (for now is empty)
- From the list of companies, navigate to the details of a specific company
- Add an API realization to get the data needed for the company details screen

**Check the results:** click on an item in the list of companies to open a screen with the details of this company (including a list of its vacancies)

## Exercise 3: The list of vacancies
Here we’ll add a list of vacancies screen
- Add entities to the model to display a list of all vacancies
- On the main screen, in the "Vacancies" tab, add a list of vacancies. List items must represent vacancy title, candidate level, salary level, and company name
- Add an API realization to get the data needed for the list screen of all vacancies

**Check the results:** on the main screen, when you tap on the "Vacancies" tab, a list of all vacancies should be displayed

## Exercise 4: Vacancy details
Now we’ll develop a vacancy details screen.
- Add entities to the model to work with vacancy details
- Create a screen with vacancy details.
- On the screen, in addition to information about the vacancy, there should be a link to the company, navigating to the company details.
- From the list of vacancies tab navigate to the details of this vacancy
- From the list of vacancies in company details, navigate to the details of this vacancy
- Add an API realization to get the data needed for the vacancy details screen

**Check the results:** 
- in vacancy details click on a link with the name of a company to open this company details
- click on an item in the list of vacancies tab to open a screen with the details of this vacancy
- click on an item in the list of vacancies in company details to open a screen with the details of this vacancy

